/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateUpdateAction extends AbstractGenerateAction {

    public GenerateUpdateAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate Update function ...");
    }

    @Override
    protected String getMessage() {
        return "Enter name of update function";
    }

    @Override
    protected boolean generate(Object updateFunctionName) {
        try {
            couchappUtil.generateUpdate(couchAppDirectory, (String) updateFunctionName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }
}
