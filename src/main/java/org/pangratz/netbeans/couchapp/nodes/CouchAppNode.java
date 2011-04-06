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
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;
import org.pangratz.netbeans.couchapp.CouchAppProject;

public class CouchAppNode extends AbstractNode {

    private final CouchAppProject project;
    private final Image icon;

    public CouchAppNode(CouchAppProject project) throws DataObjectNotFoundException {
        super(getChildren(project), Lookups.fixed(new Object[]{project}));

        this.project = project;
        this.icon = ImageUtilities.icon2Image(ProjectUtils.getInformation(project).getIcon());
    }

    private static Children getChildren(CouchAppProject project) {
        String pathToNodes = "Projects/org-pangratz-netbeans-couchapp-CouchAppProject/Nodes";
        return NodeFactorySupport.createCompositeChildren(project, pathToNodes);
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
        nodeActions.add(null);
        nodeActions.add(CommonProjectActions.customizeProjectAction());
        return nodeActions.toArray(new Action[]{});
    }

    @Override
    public Image getIcon(int type) {
        return this.icon;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getDisplayName() {
        return project.getProjectDirectory().getName();
    }
}
