/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import javax.swing.Action;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class GenerateShowAction extends AbstractGenerateAction {

    public GenerateShowAction(Lookup lookup) {
        super(lookup);
    }

    public GenerateShowAction() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Enter name of show";
    }

    @Override
    protected boolean generate(Object showName) {
        try {
            couchappUtil.generateShow(getCouchAppDirectory(), (String) showName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new GenerateShowAction(actionContext);
    }

    @Override
    protected String getName() {
        return "Generate Show...";
    }
}
