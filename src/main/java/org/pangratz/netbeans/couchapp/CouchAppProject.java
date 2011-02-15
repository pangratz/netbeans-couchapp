package org.pangratz.netbeans.couchapp;

import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

class CouchAppProject implements Project {

    private FileObject projectDir;
    LogicalViewProvider logicalView = new CouchAppProjectLogicalView(this);
    private ProjectState state = null;
    private Lookup lkp;
    private Image icon;

    public CouchAppProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    FileObject getCouchappJson(boolean create) {
        FileObject result = projectDir.getFileObject(CouchAppProjectFactory.COUCHAPP_JSON_FILE);
        if (result == null && create) {
            try {
                result = projectDir.createData("couchapp", "json");
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }
        return result;
    }

    @Override
    public Lookup getLookup() {
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{
                        this, //project spec requires a project be in its own lookup
                        state, //allow outside code to mark the project as needing saving
                        new ActionProviderImpl(), //Provides standard actions like Build and Clean
                        new DemoDeleteOperation(),
                        new DemoCopyOperation(this),
                        new Info(), //Project information implementation
                        logicalView, //Logical view of project implementation
                        new CouchAppLookupItem(),
                        // new CouchAppProjectOpenedHook(),
                        // new PushCouchAppAction()
                    });
        }
        return lkp;
    }

    private final class CouchAppProjectOpenedHook extends ProjectOpenedHook {

        @Override
        protected void projectOpened() {
            NotifyDescriptor nd = new NotifyDescriptor.Message("Project opened");
            // DialogDisplayer.getDefault().notify(nd);
        }

        @Override
        protected void projectClosed() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private final class ActionProviderImpl implements ActionProvider {

        public static final String PUSH_COUCHAPP_ACTION = "PUSH_COUCHAPP_ACTION";
        private String[] supported = new String[]{
            ActionProvider.COMMAND_DELETE,
            ActionProvider.COMMAND_COPY,
            ActionProviderImpl.PUSH_COUCHAPP_ACTION
        };

        @Override
        public String[] getSupportedActions() {
            return supported;
        }

        @Override
        public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_DELETE)) {
                DefaultProjectOperations.performDefaultDeleteOperation(CouchAppProject.this);
            }
            if (string.equalsIgnoreCase(ActionProvider.COMMAND_COPY)) {
                DefaultProjectOperations.performDefaultCopyOperation(CouchAppProject.this);
            }
            if (PUSH_COUCHAPP_ACTION.equalsIgnoreCase(string)) {
                NotifyDescriptor nd = new NotifyDescriptor.Message("Push Couchapp");
                DialogDisplayer.getDefault().notify(nd);
            }
        }

        @Override
        public boolean isActionEnabled(String command, Lookup lookup) throws IllegalArgumentException {
            if ((command.equals(ActionProvider.COMMAND_DELETE))) {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_COPY))) {
                return true;
            } else if (command.equals(PUSH_COUCHAPP_ACTION)) {
                return true;
            } else {
                throw new IllegalArgumentException(command);
            }
        }
    }

    private final class DemoDeleteOperation implements DeleteOperationImplementation {

        public void notifyDeleting() throws IOException {
        }

        public void notifyDeleted() throws IOException {
        }

        public List<FileObject> getMetadataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }

        public List<FileObject> getDataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }
    }

    private final class DemoCopyOperation implements CopyOperationImplementation {

        private final CouchAppProject project;
        private final FileObject projectDir;

        public DemoCopyOperation(CouchAppProject project) {
            this.project = project;
            this.projectDir = project.getProjectDirectory();
        }

        public List<FileObject> getMetadataFiles() {
            return Collections.EMPTY_LIST;
        }

        public List<FileObject> getDataFiles() {
            return Collections.EMPTY_LIST;
        }

        public void notifyCopying() throws IOException {
        }

        public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
        }
    }

    /** Implementation of project system's ProjectInformation class */
    private final class Info implements ProjectInformation {

        String image;

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        //Let the filesystem determine the icon to be displayed:
        @Override
        public Icon getIcon() {
            return ImageUtilities.image2Icon(ImageUtilities.loadImage("/org/pangratz/netbeans/couchapp/couchdb-icon-16px.png"));
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
        }

        @Override
        public Project getProject() {
            return CouchAppProject.this;
        }
    }
}
