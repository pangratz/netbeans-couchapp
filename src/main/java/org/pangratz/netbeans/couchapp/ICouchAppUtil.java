package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONAware;

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

    public static final String PROP_COUCHAPP_NAME = "couchapp_name";
    public static final String PROP_COUCHAPP_DESCRIPTION = "couchapp_description";
    public static final String PROP_DESIGN_DOC_ID = "design_doc_id";
    public static final String PROP_COUCHDB_SERVERS = "couchdb_servers";

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

    public Map<String, Object> readProperties(File folder) throws IOException ;
    public void writeProperties(File folder, Map<String, Object> properties) throws IOException;

    public static final class CouchDbServer implements JSONAware {

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

        @Override
        public String toJSONString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\")").append(name).append("\"");
            sb.append(" : { \"db\" : \"").append(server).append("\" }");
            return sb.toString();
        }
    }
}
