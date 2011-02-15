/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import junit.framework.TestCase;

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

        couchAppUtil.pushCouchApp(tmpDir, "http://localhost:5984/testdb");
    }

    private File generateCouchApp() throws IOException {
        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);
        couchAppUtil.generateCouchApp(tmpDir);

        return tmpDir;
    }
}
