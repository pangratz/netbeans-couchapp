/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import org.netbeans.api.project.Project;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.CouchAppProject;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public abstract class AbstractCouchAppAction extends AbstractAction {

    protected ICouchAppUtil couchappUtil;
    protected File couchAppDirectory;

    public AbstractCouchAppAction(CouchAppProject couchAppProject) {
        super();

        couchappUtil = Lookup.getDefault().lookup(ICouchAppUtil.class);
        couchAppDirectory = couchAppProject.getCouchAppDirectory();
        if (couchAppDirectory == null) {
            throw new IllegalStateException("couchAppDirectory is null!");
        }
    }

    protected Object showOptionDialog(String msg, Object[] options) {
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", msg,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
        return selectedValue;
    }

    protected String showOptionDialog(String msg) {
        return JOptionPane.showInputDialog(null, msg);
    }
}
