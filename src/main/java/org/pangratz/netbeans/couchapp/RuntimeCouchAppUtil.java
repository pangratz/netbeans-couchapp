/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.openide.util.Exceptions;

public class RuntimeCouchAppUtil implements ICouchAppUtil {

    @Override
    public void generateCouchApp(File folder) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s generate %s", couchappPyFile, folder.getPath());
        executeCommand(cmd);
    }

    private String getCouchappPyFile() throws IOException {
        ClassLoader syscl = Thread.currentThread().getContextClassLoader();
        URL couchappZipURL = syscl.getResource("modules/couchapp.zip");
        // System.out.println(couchappZipURL != null ? couchappZipURL.getPath() : "NO COUCHAPP.ZIP");

        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);

        unzipTo(couchappZipURL, tmpDir);
        return tmpDir.getPath() + "/Couchapp.py";
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
    public void pushCouchApp(File folder, String destination) throws IOException {
        String couchappPyFile = getCouchappPyFile();
        String cmd = String.format("python %s push %s %s", couchappPyFile, folder.getPath(), destination);
        String output = executeCommand(cmd);
        System.out.println(output);
    }
}