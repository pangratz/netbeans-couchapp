/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateViewAction extends AbstractGenerateAction {

    public GenerateViewAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate View ...");
    }

    @Override
    protected String getMessage() {
        return "Enter name of view";
    }

    @Override
    protected boolean generate(Object viewName) {
        try {
            couchappUtil.generateView(couchAppDirectory, (String) viewName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }
}
