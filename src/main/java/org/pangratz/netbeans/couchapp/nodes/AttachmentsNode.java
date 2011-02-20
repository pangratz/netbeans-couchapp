package org.pangratz.netbeans.couchapp.nodes;

import org.netbeans.spi.project.ui.support.NodeFactory;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 50)
public class AttachmentsNode extends AbstractCouchAppFolderNode {

    public AttachmentsNode() {
        super();
    }

    private AttachmentsNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        super(original, lookup);
    }

    @Override
    protected FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        return new AttachmentsNode(original, lookup);
    }

    @Override
    protected String getFolderPath() {
        return ICouchAppUtil.FOLDER_ATTACHMENTS;
    }

    @Override
    public String getDisplayName() {
        return "Attachments";
    }
}
