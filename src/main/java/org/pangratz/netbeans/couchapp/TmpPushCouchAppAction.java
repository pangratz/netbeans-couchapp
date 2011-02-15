/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;


public class TmpPushCouchAppAction extends AbstractAction implements ContextAwareAction {

    private final Project p;

    public TmpPushCouchAppAction(Lookup context) {
        p = context.lookup(Project.class);
        String name = ProjectUtils.getInformation(p).getDisplayName();
        // TODO state for which projects action should be enabled
        // setEnabled(name.equals("sample_couchapp"));
        setEnabled(true);
        // putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
        // TODO menu item label with optional mnemonics
        putValue(NAME, "&Info on " + name);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new TmpPushCouchAppAction(actionContext);
    }
}
