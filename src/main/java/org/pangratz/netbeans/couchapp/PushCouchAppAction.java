/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.project.Project;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.NotifyDescriptor.Message;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;

public final class PushCouchAppAction extends AbstractAction implements ContextAwareAction {

    private static class ContextAwarePushCouchAppAction extends AbstractAction {

        private final Lookup actionContext;

        public ContextAwarePushCouchAppAction(Lookup actionContext) {
            this.actionContext = actionContext;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Message message = new NotifyDescriptor.Message("Perform pushing ContextAwarePushCouchAppAction");
            DialogDisplayer.getDefault().notify(message);
        }

        @Override
        public boolean isEnabled() {
            Project project = actionContext.lookup(Project.class);
            String msg = "ContextAwarePushCouchAppAction#isEnabled";
            if (project != null) {
                msg += " with project: " + project.getClass();
            }
            Message message = new NotifyDescriptor.Message(msg);
            DialogDisplayer.getDefault().notify(message);
            return super.isEnabled();
        }
    }

    public PushCouchAppAction() {
        super();

        putValue(NAME, "Push CouchApp Action");
    }

    public void actionPerformed(ActionEvent e) {
        Message message = new NotifyDescriptor.Message("Perform pushing");
        DialogDisplayer.getDefault().notify(message);
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        System.out.println("#createContextAwareInstance");
        return new ContextAwarePushCouchAppAction(actionContext);
    }
}
