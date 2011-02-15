/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.nodes;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 200)
public class ShowsNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project proj) {
        ShowsFilesNode nd = null;
        try {
            Node original = DataObject.find(proj.getProjectDirectory().getFileObject("/shows")).getNodeDelegate();
            nd = new ShowsFilesNode(original, Lookups.singleton(proj));
            return NodeFactorySupport.fixedNodeList(nd);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }

    private class ShowsFilesNode extends FilterNode {

        private ShowsFilesNode(Node original, Lookup lkp) throws DataObjectNotFoundException {
            super(original, new ProxyChildren(original), lkp);
        }

        @Override
        public String getDisplayName() {
            return "Shows";
        }
    }
}
