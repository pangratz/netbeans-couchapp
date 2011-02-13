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
import java.net.URL;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.openide.util.Exceptions;

public class RuntimeCouchAppUtil implements ICouchAppUtil {

    @Override
    public void generateCouchApp(File folder) throws IOException {

        ClassLoader syscl = Thread.currentThread().getContextClassLoader();
        URL couchappZipURL = syscl.getResource("modules/couchapp.zip");
        System.out.println(couchappZipURL != null ? couchappZipURL.getPath() : "NO COUCHAPP.ZIP");

        String uuid = UUID.randomUUID().toString();
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), uuid);
        unzipTo(couchappZipURL, tmpDir);


        String couchappPyFile = tmpDir.getPath() + "/Couchapp.py";
        String cmd = String.format("python %s generate %s", couchappPyFile, folder.getPath());
        System.out.println(cmd);

        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        try {
            exec.waitFor();
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void unzipTo(URL couchappZipURL, File tmpDir) throws IOException {

        System.out.println("extracting to " + tmpDir);

        try {
            int BUFFER = 1024;

            BufferedOutputStream dest = null;
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(couchappZipURL.openStream()));
            ZipEntry entry;
            // loop through zipped file entries
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Extracting: " + entry.getName() + " --> " + entry.isDirectory());

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
            e.printStackTrace();
        }
    }
}
