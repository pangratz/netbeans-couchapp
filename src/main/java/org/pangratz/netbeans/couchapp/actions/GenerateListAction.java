/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateListAction extends AbstractGenerateAction {

    public GenerateListAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate List ...");
    }

    @Override
    protected String getMessage() {
        return "Enter name of list";
    }

    @Override
    protected boolean generate(Object listName) {
        try {
            couchappUtil.generateList(couchAppDirectory, (String) listName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }
}
