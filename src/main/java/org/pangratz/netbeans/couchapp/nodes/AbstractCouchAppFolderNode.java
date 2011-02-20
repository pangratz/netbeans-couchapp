package org.pangratz.netbeans.couchapp.nodes;

import java.awt.Image;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

public abstract class AbstractCouchAppFolderNode extends FilterNode implements NodeFactory {

    public AbstractCouchAppFolderNode(Node original, Lookup lkp) throws DataObjectNotFoundException {
        super(original, new ProxyChildren(original), lkp);

    }

    protected abstract FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException;

    protected abstract String getFolderPath();

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public NodeList<?> createNodes(Project proj) {
        try {
            String path = getFolderPath();
            FileObject fileObject = proj.getProjectDirectory().getFileObject(path);
            Node original = DataObject.find(fileObject).getNodeDelegate();
            Node newNode = getNode(original, Lookups.singleton(proj));
            return NodeFactorySupport.fixedNodeList(newNode);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }
}
