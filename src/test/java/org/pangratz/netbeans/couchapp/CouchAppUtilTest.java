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

    public void testCreateCouchApp() throws IOException {
        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);
        couchAppUtil.generateCouchApp(tmpDir);
    }
}
