package org.pangratz.netbeans.couchapp.nodes;

import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public class ListsNode extends AbstractCouchAppFolderNode {

    public ListsNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        super(original, lookup);
    }

    @Override
    protected FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        return new ListsNode(original, lookup);
    }

    @Override
    protected String getFolderPath() {
        return "/" + ICouchAppUtil.FOLDER_VIEWS;
    }

    @Override
    public String getDisplayName() {
        return "Views";
    }
}
