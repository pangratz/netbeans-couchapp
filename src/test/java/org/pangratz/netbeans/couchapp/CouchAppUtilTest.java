/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class CouchAppUtilTest extends TestCase {

    private RuntimeCouchAppUtil couchAppUtil;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.couchAppUtil = new RuntimeCouchAppUtil();
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

    private File generateCouchApp() throws IOException {
        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);
        couchAppUtil.generateCouchApp(tmpDir);

        return tmpDir;
    }
}
