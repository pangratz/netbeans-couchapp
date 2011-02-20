package org.pangratz.netbeans.couchapp.nodes;

import org.netbeans.spi.project.ui.support.NodeFactory;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 300)
public class ViewsFilesNode extends AbstractCouchAppFolderNode {

    public ViewsFilesNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        super(original, lookup);
    }

    public ViewsFilesNode() {
        super();
    }

    @Override
    protected FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        return new ViewsFilesNode(original, lookup);
    }

    @Override
    protected String getFolderPath() {
        return "/views";
    }

    @Override
    public String getDisplayName() {
        return "Views";
    }
}
