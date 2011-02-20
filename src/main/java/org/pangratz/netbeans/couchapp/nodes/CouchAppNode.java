/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.nodes;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Action;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class CouchAppNode extends AbstractNode {

    final CouchAppProject project;

    public CouchAppNode(CouchAppProject project) throws DataObjectNotFoundException {
        super(NodeFactorySupport.createCompositeChildren(project, "Projects/org-pangratz-netbeans-couchapp-CouchAppProject/Nodes"),
                Lookups.fixed(new Object[]{project}));

        this.project = project;
    }

    @Override
    public Action[] getActions(boolean arg0) {
        List<? extends Action> actionsForPath = Utilities.actionsForPath("Actions/CouchApp/");

        List<Action> nodeActions = new LinkedList<Action>();
        nodeActions.add(CommonProjectActions.newFileAction());
        nodeActions.add(null);
        nodeActions.add(CommonProjectActions.copyProjectAction());
        nodeActions.add(CommonProjectActions.deleteProjectAction());
        nodeActions.add(null);
        nodeActions.addAll(actionsForPath);
        nodeActions.add(null);
        nodeActions.add(CommonProjectActions.setAsMainProjectAction());
        nodeActions.add(CommonProjectActions.closeProjectAction());
        return nodeActions.toArray(new Action[]{});
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
