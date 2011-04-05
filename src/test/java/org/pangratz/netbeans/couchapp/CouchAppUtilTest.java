/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class CouchAppUtilTest extends TestCase {

    private RuntimeCouchAppUtil couchAppUtil;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.couchAppUtil = new RuntimeCouchAppUtil();
    }

    public void testCloneCouchApp() throws IOException {
        File couchAppDir = generateCouchApp();
        String couchAppUrl = "http://localhost:5984/clone";
        couchAppUrl = couchAppUtil.pushCouchApp(couchAppDir, couchAppUrl);
        couchAppUrl = couchAppUrl.replace("/index.html", "");

        File tmpDir = getTmpFolder();
        URL url = new URL(couchAppUrl);
        couchAppUtil.cloneCouchApp(tmpDir, url);

        assertTrue("missing .couchapprc file", new File(tmpDir, ".couchapprc").exists());
        assertTrue("missing couchapp.json file", new File(tmpDir, "couchapp.json").exists());
        assertTrue("missing _attachments directory", new File(tmpDir, "_attachments").exists());
    }

    public void testGenerateFilter() throws IOException {
        File tmpDir = generateCouchApp();

        couchAppUtil.generateFilter(tmpDir, "myFilter");

        assertTrue("filters/myFilter.js does not exist", new File(tmpDir, "filters/myFilter.js").exists());
    }

    public void testGenerateList() throws IOException {
        File tmpDir = generateCouchApp();

        couchAppUtil.generateList(tmpDir, "myListName");

        assertTrue("lists/myListName.js does not exist", new File(tmpDir, "lists/myListName.js").exists());
    }

    public void testGenerateShow() throws IOException {
        File tmpDir = generateCouchApp();

        couchAppUtil.generateShow(tmpDir, "my-cool-show-name");

        assertTrue("shows/my-cool-show-name.js does not exist", new File(tmpDir, "shows/my-cool-show-name.js").exists());
    }

    public void testCreateCouchApp() throws IOException {
        File tmpDir = generateCouchApp();

        assertTrue("missing couchapp.json file", new File(tmpDir, "couchapp.json").exists());
        assertTrue("missing _attachments directory", new File(tmpDir, "_attachments").exists());
    }

    public void testCreateView() throws IOException {
        File tmpDir = generateCouchApp();

        couchAppUtil.generateView(tmpDir, "myView");

        assertTrue("myView does not exist", new File(tmpDir, "views/myView").exists());
        assertTrue("myView/map.js does not exist", new File(tmpDir, "views/myView/map.js").exists());
    }

    public void testPushApp() throws IOException {
        File tmpDir = generateCouchApp();
        String url = couchAppUtil.pushCouchApp(tmpDir, "http://localhost:5984/testdb");

        String expected = "http://localhost:5984/testdb/_design/" + tmpDir.getName() + "/index.html";

        assertEquals(expected, url);
    }

    public void testeGenerateUpdate() throws IOException {
        File tmpDir = generateCouchApp();

        couchAppUtil.generateUpdate(tmpDir, "myUpdate");

        assertTrue("updates/myUpdate.js does not exist", new File(tmpDir, "updates/myUpdate.js").exists());
    }

    public void testGetCouchDbServers() throws IOException {
        File tmpDir = generateCouchApp();

        // create sample .couchapprc file in the created couchapp folder
        File couchapprc = new File(tmpDir, ICouchAppUtil.COUCHAPPRC);
        InputStream inAnd = CouchAppUtilTest.class.getResourceAsStream("sample_couchapprc.json");
        FileOutputStream OutBurger = new FileOutputStream(couchapprc);
        IOUtils.copy(inAnd, OutBurger);

        List<CouchDbServer> couchDbServers = couchAppUtil.getCouchDbServers(tmpDir);
        assertNotNull("no list of CouchDbServers", couchDbServers);
        assertEquals("expected 2 entries", 2, couchDbServers.size());

        CouchDbServer first = couchDbServers.get(0);
        assertEquals("expected default as name of first server", "default", first.getName());
        assertEquals("expected different URL of first server", "http://localhost:5984/mydb", first.getServer());

        CouchDbServer second = couchDbServers.get(1);
        assertEquals("expected prod as name of second server", "prod", second.getName());
        assertEquals("expected different URL of second server", "http://localhost:5984/mydb_prod", second.getServer());
    }

    public void testWriteProperties() throws IOException {
        Map<String, Object> properties = new HashMap<String, Object>(4);
        properties.put(ICouchAppUtil.PROP_COUCHAPP_NAME, "My CouchApp Name");
        properties.put(ICouchAppUtil.PROP_COUCHAPP_DESCRIPTION, "My CouchApp Description");
        properties.put(ICouchAppUtil.PROP_DESIGN_DOC_ID, "_design/newdesignname");
        List<CouchDbServer> servers = new LinkedList<CouchDbServer>();
        servers.add(new CouchDbServer("debug_db", "http://127.0.0.1:5984/debug_db"));
        servers.add(new CouchDbServer("prod_db", "http://127.0.0.1:5984/prod_db"));
        properties.put(ICouchAppUtil.PROP_COUCHDB_SERVERS, servers);

        File folder = generateCouchApp();
        couchAppUtil.writeProperties(folder, properties);

        // check the design doc id
        File idFile = new File(folder, ICouchAppUtil._ID);
        String designDocName = IOUtils.toString(new FileReader(idFile));
        assertEquals("_design/newdesignname", designDocName);

        // check the couchapp name and description
        File couchAppJsonFile = new File(folder, ICouchAppUtil.COUCHAPP_JSON);
        String tanga = IOUtils.toString(new FileReader(couchAppJsonFile));
        Object obj = JSONValue.parse(tanga);
        JSONObject json = (JSONObject) obj;
        String name = json.get("name").toString();
        assertEquals("My CouchApp Name", name);
        String description = json.get("description").toString();
        assertEquals("My CouchApp Description", description);

        System.out.println(folder.toString());

        // check the couchdb servers
        List<CouchDbServer> couchDbServers = couchAppUtil.getCouchDbServers(folder);
        assertEquals(servers.size(), couchDbServers.size());
        CouchDbServer first = couchDbServers.get(0);
        assertEquals("prod_db", first.getName());
        assertEquals("http://127.0.0.1:5984/prod_db", first.getServer());

        CouchDbServer second = couchDbServers.get(1);
        assertEquals("debug_db", second.getName());
        assertEquals("http://127.0.0.1:5984/debug_db", second.getServer());
        
    }

    public void testReadProperties() throws IOException {
        // create tmp couchapp with default couchdb entries
        File tmpDir = generateCouchApp();
        File couchapprc = new File(tmpDir, ICouchAppUtil.COUCHAPPRC);
        InputStream inAnd = CouchAppUtilTest.class.getResourceAsStream("sample_couchapprc.json");
        FileOutputStream OutBurger = new FileOutputStream(couchapprc);
        IOUtils.copy(inAnd, OutBurger);

        Map<String, Object> properties = couchAppUtil.readProperties(tmpDir);
        assertNotNull(properties);
        assertEquals(4, properties.size());

        String couchAppName = (String) properties.get(ICouchAppUtil.PROP_COUCHAPP_NAME);
        assertNotNull(couchAppName);
        assertEquals("Name of your CouchApp", couchAppName);

        String couchAppDescription = (String) properties.get(ICouchAppUtil.PROP_COUCHAPP_DESCRIPTION);
        assertNotNull(couchAppDescription);
        assertEquals("CouchApp", couchAppDescription);

        String designDocId = (String) properties.get(ICouchAppUtil.PROP_DESIGN_DOC_ID);
        assertNotNull(designDocId);
        assertEquals("_design/" + tmpDir.getName(), designDocId);

        List<CouchDbServer> couchDbServers = (List<CouchDbServer>) properties.get(ICouchAppUtil.PROP_COUCHDB_SERVERS);
        assertNotNull("no list of CouchDbServers", couchDbServers);
        assertEquals("expected 2 entries", 2, couchDbServers.size());

        CouchDbServer first = couchDbServers.get(0);
        assertEquals("expected default as name of first server", "default", first.getName());
        assertEquals("expected different URL of first server", "http://localhost:5984/mydb", first.getServer());

        CouchDbServer second = couchDbServers.get(1);
        assertEquals("expected prod as name of second server", "prod", second.getName());
        assertEquals("expected different URL of second server", "http://localhost:5984/mydb_prod", second.getServer());
    }

    private File getTmpFolder() {
        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);
        return tmpDir;
    }

    private File generateCouchApp() throws IOException {
        File tmpDir = getTmpFolder();
        couchAppUtil.generateCouchApp(tmpDir);
        return tmpDir;
    }
}
