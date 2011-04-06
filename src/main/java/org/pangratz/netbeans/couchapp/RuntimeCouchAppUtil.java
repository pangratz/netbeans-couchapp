/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = ICouchAppUtil.class)
public class RuntimeCouchAppUtil implements ICouchAppUtil {

    private static String COUCHAPP_FILE = null;

    @Override
    public void generateCouchApp(File folder) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate %s", couchappPyFile, folder.getPath());
        executeCommand(cmd);
    }

    private String getCouchappPyFile() throws IOException {
        if (COUCHAPP_FILE == null) {
            ClassLoader syscl = Thread.currentThread().getContextClassLoader();
            URL couchappZipURL = syscl.getResource("modules/couchapp.zip");
            // System.out.println(couchappZipURL != null ? couchappZipURL.getPath() : "NO COUCHAPP.ZIP");

            String uuid = UUID.randomUUID().toString();
            File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);

            unzipTo(couchappZipURL, tmpDir);

            COUCHAPP_FILE = tmpDir.getPath() + "/Couchapp.py";
        }
        return COUCHAPP_FILE;
    }

    private void unzipTo(URL couchappZipURL, File tmpDir) throws IOException {

        // System.out.println("extracting to " + tmpDir);

        try {
            int BUFFER = 1024;

            BufferedOutputStream dest = null;
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(couchappZipURL.openStream()));
            ZipEntry entry;
            // loop through zipped file entries
            while ((entry = zis.getNextEntry()) != null) {
                // System.out.println("Extracting: " + entry.getName() + " --> " + entry.isDirectory());

                File newFile = new File(tmpDir, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {

                    int count;
                    // buffer to read bytes of the file
                    byte data[] = new byte[BUFFER];

                    // write the files to the disk
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile), BUFFER);

                    // read of bytes and write to file in output folder
                    while ((count = zis.read(data, 0, BUFFER)) != -1) {
                        out.write(data, 0, count);
                    }

                    out.flush();
                    out.close();
                }

                zis.closeEntry();
            }
            zis.close();
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }
    }

    @Override
    public void generateView(File folder, String viewName) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate view %s %s", couchappPyFile, folder.getPath(), viewName);
        executeCommand(cmd);
    }

    private String executeCommand(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        try {
            exec.waitFor();
            InputStream in = exec.getInputStream();
            StringWriter sw = new StringWriter();
            IOUtils.copy(exec.getErrorStream(), sw);
            return sw.toString();
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
        return "ERROR";
    }

    @Override
    public String pushCouchApp(File folder, String destination) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s push %s %s", couchappPyFile, folder.getPath(), destination);
        String output = executeCommand(cmd);

        // output should match string "XXX http://PATH.TO/COUCHAPP"
        int httpIndex = output.indexOf("http://");
        String url = output.substring(httpIndex);
        return url.trim();
    }

    @Override
    public void generateShow(File folder, String showName) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate show %s %s", couchappPyFile, folder.getPath(), showName);
        executeCommand(cmd);
    }

    @Override
    public void generateList(File folder, String listName) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate list %s %s", couchappPyFile, folder.getPath(), listName);
        executeCommand(cmd);
    }

    @Override
    public void generateFilter(File folder, String filterName) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate filter %s %s", couchappPyFile, folder.getPath(), filterName);
        executeCommand(cmd);
    }

    @Override
    public List<CouchDbServer> getCouchDbServers(File folder) throws IOException {
        // get the JSON file
        File couchappRc = new File(folder, COUCHAPPRC);

        if (!couchappRc.exists()) {
            throw new IllegalStateException(COUCHAPPRC + " file does not exist in folder " + folder);
        }

        List<CouchDbServer> couchDbServers = new LinkedList<CouchDbServer>();
        String tanga = IOUtils.toString(new FileReader(couchappRc));

        // TODO implement error handling
        Object obj;
        try {
            obj = JSONValue.parseWithException(tanga);
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
            return couchDbServers;
        }

        try {
            JSONObject json = (JSONObject) obj;
            JSONObject env = (JSONObject) json.get("env");

            if (env == null) {
                return couchDbServers;
            }

            for (Object object : env.keySet()) {
                String name = object.toString();

                JSONObject severObj = (JSONObject) env.get(name);
                String server = severObj.get("db").toString();

                couchDbServers.add(new CouchDbServer(name, server));
            }
            return couchDbServers;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        return new LinkedList<CouchDbServer>();
    }

    @Override
    public void generateUpdate(File folder, String updateFunctionName) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate update %s %s", couchappPyFile, folder.getPath(), updateFunctionName);
        executeCommand(cmd);
    }

    @Override
    public void cloneCouchApp(File folder, URL couchAppUrl) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s clone %s %s", couchappPyFile, couchAppUrl.toString(), folder.getPath());
        executeCommand(cmd);
    }

    @Override
    public Map<String, Object> readProperties(File folder) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>(4);

        // get the design doc id
        File idFile = new File(folder, _ID);
        String designDocName = IOUtils.toString(new FileReader(idFile));
        map.put(PROP_DESIGN_DOC_ID, designDocName);

        // get the couchapp name and description
        File couchAppJsonFile = new File(folder, COUCHAPP_JSON);
        String tanga = IOUtils.toString(new FileReader(couchAppJsonFile));
        try {
            Object obj = JSONValue.parseWithException(tanga);
            JSONObject json = (JSONObject) obj;

            String name = json.get("name").toString();
            String description = json.get("description").toString();

            map.put(PROP_COUCHAPP_NAME, name);
            map.put(PROP_COUCHAPP_DESCRIPTION, description);
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
            throw new IllegalStateException("exception while reading " + COUCHAPP_JSON + " file");
        }

        List<CouchDbServer> couchDbServers = getCouchDbServers(folder);
        map.put(PROP_COUCHDB_SERVERS, couchDbServers);

        return map;
    }

    @Override
    public void writeProperties(File folder, Map<String, Object> properties) throws IOException {
        File idFile = new File(folder, _ID);
        String designDocId = (String) properties.get(PROP_DESIGN_DOC_ID);
        FileWriter fw = new FileWriter(idFile);
        IOUtils.write(designDocId, fw);
        IOUtils.closeQuietly(fw);

        // write the name and description
        File couchappJsonFile = new File(folder, COUCHAPP_JSON);
        JSONObject couchappJson = new JSONObject();
        couchappJson.put("name", properties.get(PROP_COUCHAPP_NAME));
        couchappJson.put("description", properties.get(PROP_COUCHAPP_DESCRIPTION));
        fw = new FileWriter(couchappJsonFile);
        couchappJson.writeJSONString(fw);
        IOUtils.closeQuietly(fw);

        // write the couchdb servers
        File couchappRcFile = new File(folder, COUCHAPPRC);
        JSONObject couchappRcJson = new JSONObject();
        JSONObject env = new JSONObject();
        List<CouchDbServer> servers = (List<CouchDbServer>) properties.get(PROP_COUCHDB_SERVERS);
        for (CouchDbServer couchDbServer : servers) {
            JSONObject serverObj = new JSONObject();
            serverObj.put("db", couchDbServer.getServer());
            env.put(couchDbServer.getName(), serverObj);
        }
        couchappRcJson.put("env", env);
        fw = new FileWriter(couchappRcFile);
        couchappRcJson.writeJSONString(fw);
        IOUtils.closeQuietly(fw);
    }
}
