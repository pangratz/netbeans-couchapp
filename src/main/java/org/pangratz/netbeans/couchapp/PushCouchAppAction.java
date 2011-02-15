/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.awt.Component;
import java.awt.Image;
import java.util.Arrays;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.NodeAction;

public final class PushCouchAppAction extends NodeAction {

    private final Lookup actionContext;

    public PushCouchAppAction() {
        this(Utilities.actionsGlobalContext());
    }

    private PushCouchAppAction(Lookup actionContext) {
        super();

        // set name and icon
        putValue(NAME, "Push CouchApp");
        Image icon = ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/couchdb-icon-16px.png");
        setIcon(ImageUtilities.image2Icon(icon));

        this.actionContext = actionContext;
    }

    @Override
    protected void performAction(Node[] nodes) {

        if (nodes == null || nodes.length != 1) {
            return;
        }

        // get the project
        Node node = nodes[0];
        CouchAppProject project = node.getLookup().lookup(CouchAppProject.class);
        if (project != null) {
            return;
        }
        // get the ICouchAppUtil
        ICouchAppUtil couchappUtil = actionContext.lookup(ICouchAppUtil.class);

        // TODO create dialog and ask for location where the couch app shall be pushed ...
    }

    @Override
    protected boolean enable(Node[] nodes) {
        // check if a single CouchAppProject is selected
        if (nodes == null || nodes.length != 1) {
            return false;
        }

        Node node = nodes[0];
        CouchAppProject project = node.getLookup().lookup(CouchAppProject.class);
        if (project != null) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public String getName() {
        return (String) getValue(NAME);
    }
}
