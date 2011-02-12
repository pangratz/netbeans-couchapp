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
import org.openide.util.Exceptions;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 300)
public class ViewNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project proj) {
        ViewFilesNode nd = null;
        try {
            nd = new ViewFilesNode(proj);
            return NodeFactorySupport.fixedNodeList(nd);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }

    private class ViewFilesNode extends FilterNode {

        private ViewFilesNode(Project proj) throws DataObjectNotFoundException {
            super(DataObject.find(proj.getProjectDirectory().getFileObject("app/views")).getNodeDelegate());
        }

        @Override
        public String getDisplayName() {
            return "Views";
        }
    }
}
