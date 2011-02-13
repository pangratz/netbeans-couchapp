/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.nodes;

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

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 100)
public class ImportantFilesNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project prjct) {
        try {
            Node nodes = DataObject.find(prjct.getProjectDirectory().getFileObject("/")).getNodeDelegate();
            ImportantFilesNode nd = new ImportantFilesNode(nodes);
            return NodeFactorySupport.fixedNodeList(nd);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }

    private static class ImportantFilesNode extends FilterNode {

        private ImportantFilesNode(Node original) throws DataObjectNotFoundException {
            super(original, new ProxyChildren(original));
        }

        @Override
        public String getDisplayName() {
            return "Important Files";
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
                DataObject dataObject = (DataObject)node.getLookup().lookup(DataObject.class);

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
            if (".couchapprc".equals(name))
                return true;
            if ("couchapp.json".equals(name))
                return true;

            return false;
        }
    }
}
