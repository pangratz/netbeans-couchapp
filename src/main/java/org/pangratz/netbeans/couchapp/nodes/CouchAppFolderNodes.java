package org.pangratz.netbeans.couchapp.nodes;

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
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject")
public class CouchAppFolderNodes implements NodeFactory {

    private String getPath(String folderName) {
        return "/" + folderName;
    }

    private static final class FolderNode extends FilterNode {

        public FolderNode(Node original, Lookup lkp, String displayName) {
            super(original, new ProxyChildren(original), lkp);

            setDisplayName(displayName);
        }
    }

    @Override
    public NodeList<?> createNodes(Project project) {
        try {
            FileObject projectDir = project.getProjectDirectory();
            Lookup lkp = Lookups.singleton(project);

            Node[] nodes = new Node[7];
            nodes[0] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_ATTACHMENTS), "Attachments");
            nodes[1] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_EVENTLY), "Evently");
            nodes[2] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_FILTERS), "Filters");
            nodes[3] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_LISTS), "Lists");
            nodes[4] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_SHOWS), "Shows");
            nodes[5] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_UPDATES), "Updates");
            nodes[6] = getNode(lkp, projectDir, getPath(ICouchAppUtil.FOLDER_VIEWS), "Views");

            return NodeFactorySupport.fixedNodeList(nodes);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }

    private Node getNode(Lookup lkp, FileObject projectDir, String path, String displayName) throws DataObjectNotFoundException {
        DataObject dataObj = DataObject.find(projectDir.getFileObject(path));
        Node node = dataObj.getNodeDelegate();
        return new FolderNode(node, lkp, displayName);
    }
}
