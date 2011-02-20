package org.pangratz.netbeans.couchapp;

import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.nodes.CouchAppNode;

class CouchAppProjectLogicalView implements LogicalViewProvider {

    private final CouchAppProject project;

    public CouchAppProjectLogicalView(CouchAppProject project) {
        this.project = project;
    }

    @Override
    public org.openide.nodes.Node createLogicalView() {
        try {
            return new CouchAppNode(project);
        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
            //Fallback—the directory couldn't be created -
            //read-only filesystem or something evil happened
            return new AbstractNode(Children.LEAF);
        }
    }

    @Override
    public Node findPath(Node root, Object target) {
        //leave unimplemented for now
        return null;
    }
}
