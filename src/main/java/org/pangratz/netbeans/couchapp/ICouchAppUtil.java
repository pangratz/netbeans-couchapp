/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ICouchAppUtil {

    public static final String COUCHAPPRC = ".couchapprc";

    public void generateCouchApp(File folder) throws IOException;

    public void generateView(File folder, String viewName) throws IOException;

    public void generateShow(File folder, String showName) throws IOException;

    public void generateList(File folder, String listName) throws IOException;

    public void generateUpdate(File folder, String updateFunctionName) throws IOException;

    public void generateFilter(File folder, String filterName) throws IOException;

    /**
     * Push given CouchApp in folder to destination.
     * @return URL to pushed CouchApp
     */
    public String pushCouchApp(File folder, String destination) throws IOException;

    public List<CouchDbServer> getCouchDbServers(File folder) throws IOException;

    public static final class CouchDbServer {

        private final String name, server;

        public CouchDbServer(String name, String server) {
            this.name = name;
            this.server = server;
        }

        public String getName() {
            return name;
        }

        public String getServer() {
            return server;
        }
    }
}
