/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.util.ContextAwareAction;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

public final class PushCouchAppAction extends AbstractAction implements LookupListener, ContextAwareAction {

    private Lookup context = null;
    private Lookup.Result<CouchAppProject> result = null;

    public PushCouchAppAction() {
        this(Utilities.actionsGlobalContext());
    }

    public PushCouchAppAction(Lookup context) {
        super();
        init(context);

        putValue(NAME, "PushMyCouchApp");
        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/couchdb-icon-16px.png")));
    }

    private void init(Lookup context) {
        this.context = context;
        result = context.lookupResult(CouchAppProject.class);
        result.addLookupListener(this);
        resultChanged(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Iterator<? extends CouchAppProject> it = result.allInstances().iterator();
        while (it.hasNext()) {
            Object object = it.next();
            System.out.println("Push Couch App Action --> " + object);
        }
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        setEnabled(result.allItems().size() != 0);
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new PushCouchAppAction(actionContext);
    }
}
