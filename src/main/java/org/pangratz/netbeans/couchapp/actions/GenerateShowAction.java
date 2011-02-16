/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateShowAction extends AbstractGenerateAction {

    public GenerateShowAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate Show ...");
    }

    @Override
    protected String getMessage() {
        return "Enter name of show";
    }

    @Override
    protected boolean generate(Object showName) {
        try {
            couchappUtil.generateShow(couchAppDirectory, (String) showName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }
}
