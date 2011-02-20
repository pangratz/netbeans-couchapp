package org.pangratz.netbeans.couchapp.nodes;

import org.netbeans.spi.project.ui.support.NodeFactory;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject")
public class FiltersNode extends AbstractCouchAppFolderNode {

    private FiltersNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        super(original, lookup);
    }

    public FiltersNode() {
        super();
    }

    @Override
    protected FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        return new FiltersNode(original, lookup);
    }

    @Override
    protected String getFolderPath() {
        return "/" + ICouchAppUtil.FOLDER_FILTERS;
    }

    @Override
    public String getDisplayName() {
        return "Filters";
    }
}
