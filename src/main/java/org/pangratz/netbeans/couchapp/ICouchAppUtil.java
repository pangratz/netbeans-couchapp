/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pangratz.netbeans.couchapp;

import java.io.File;
import java.io.IOException;

public interface ICouchAppUtil {

    public void generateCouchApp(File folder) throws IOException;

    public void generateView(File folder, String viewName) throws IOException;

    public void generateShow(File folder, String showName) throws IOException;

    public void pushCouchApp(File folder, String destination) throws IOException;

}
