package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface ICouchAppUtil {

    public static final String COUCHAPPRC = ".couchapprc";
    public static final String COUCHAPP_JSON = "couchapp.json";
    public static final String _ID = "_id";
    public static final String README_MD = "README.md";
    
    public static final String FOLDER_ATTACHMENTS = "_attachments";
    public static final String FOLDER_VIEWS = "views";
    public static final String FOLDER_SHOWS = "shows";
    public static final String FOLDER_LISTS = "lists";
    public static final String FOLDER_FILTERS = "filters";
    public static final String FOLDER_EVENTLY = "evently";
    public static final String FOLDER_UPDATES = "updates";

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

    public void cloneCouchApp(File folder, URL couchAppUrl) throws IOException;

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
