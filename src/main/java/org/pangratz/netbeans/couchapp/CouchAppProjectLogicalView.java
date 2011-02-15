package org.pangratz.netbeans.couchapp;

import java.awt.Image;
import javax.swing.Action;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;

class CouchAppProjectLogicalView implements LogicalViewProvider {

    private final CouchAppProject project;

    public CouchAppProjectLogicalView(CouchAppProject project) {
        this.project = project;
    }

    @Override
    public org.openide.nodes.Node createLogicalView() {
        try {
            return new CouchAppNode(project);
        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
            //Fallback—the directory couldn't be created -
            //read-only filesystem or something evil happened
            return new AbstractNode(Children.LEAF);
        }
    }

    /** This is the node you actually see in the project tab for the project */
    private static final class CouchAppNode extends AbstractNode {

        final CouchAppProject project;

        public CouchAppNode(CouchAppProject project) throws DataObjectNotFoundException {
//            super(node, new FilterNode.Children(node),
//                    //The projects system wants the project in the Node's lookup.
//                    //NewAction and friends want the original Node's lookup.
//                    //Make a merge of both
//                    new ProxyLookup(new Lookup[]{Lookups.singleton(project),
//                        node.getLookup()
//                    }));
            super(NodeFactorySupport.createCompositeChildren(project, "Projects/org-pangratz-netbeans-couchapp-CouchAppProject/Nodes"),
                    Lookups.fixed(new Object[]{project}));
            this.project = project;
        }

        @Override
        public Action[] getActions(boolean arg0) {
            Action[] nodeActions = new Action[7];
            nodeActions[0] = CommonProjectActions.newFileAction();
            nodeActions[1] = CommonProjectActions.copyProjectAction();
            nodeActions[2] = CommonProjectActions.deleteProjectAction();
            nodeActions[5] = CommonProjectActions.setAsMainProjectAction();
            nodeActions[6] = CommonProjectActions.closeProjectAction();
            return nodeActions;
        }

        //Set the icon based on the ProjectInformation impl:
        @Override
        public Image getIcon(int type) {
            return ImageUtilities.icon2Image(ProjectUtils.getInformation(project).getIcon());
        }

        //Set the icon based on the ProjectInformation impl:
        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }
    }

    @Override
    public Node findPath(Node root, Object target) {
        //leave unimplemented for now
        return null;
    }
}
