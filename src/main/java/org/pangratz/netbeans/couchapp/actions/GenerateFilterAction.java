/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class GenerateFilterAction extends AbstractGenerateAction {

    public GenerateFilterAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Generate Filter ...");
    }

    @Override
    protected String getMessage() {
        return "Enter name of filter";
    }

    @Override
    protected boolean generate(Object filterName) {
        try {
            couchappUtil.generateFilter(couchAppDirectory, (String) filterName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }
}
