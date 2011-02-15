/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.nodes;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 100)
public class ImportantFilesNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project prjct) {
        try {
            Node nodes = DataObject.find(prjct.getProjectDirectory().getFileObject("/")).getNodeDelegate();
            ImportantFilesNode nd = new ImportantFilesNode(nodes, Lookups.singleton(prjct));
            return NodeFactorySupport.fixedNodeList(nd);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }

    private static class ImportantFilesNode extends FilterNode {

        private final Image icon;

        private ImportantFilesNode(Node original, Lookup lkp) throws DataObjectNotFoundException {
            super(original, new ProxyChildren(original), lkp);

            Image configBadgeIcon = ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/config-badge.gif");
            Image couchdDbIcon = ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/couchdb-icon-16px.png");
            icon = ImageUtilities.mergeImages(couchdDbIcon, configBadgeIcon, 7, 7);
        }

        @Override
        public String getDisplayName() {
            return "Important Files";
        }

        @Override
        public Image getIcon(int type) {
            return icon;
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }
    }

    private static class ProxyChildren extends FilterNode.Children {

        public ProxyChildren(Node original) {
            super(original);
        }

        @Override
        protected Node[] createNodes(Node key) {
            List<Node> result = new ArrayList<Node>();

            for (Node node : super.createNodes(key)) {
                DataObject dataObject = (DataObject) node.getLookup().lookup(DataObject.class);

                if (dataObject != null) {
                    FileObject fileObject = dataObject.getPrimaryFile();
                    File file = FileUtil.toFile(fileObject);

                    if (accept(file)) {
                        result.add(node);
                    }
                }
            }

            return result.toArray(new Node[result.size()]);
        }

        private boolean accept(File file) {
            String name = file.getName();
            if (".couchapprc".equals(name)) {
                return true;
            }
            if ("couchapp.json".equals(name)) {
                return true;
            }
            if ("_id".equals(name)) {
                return true;
            }

            return false;
        }
    }
}
