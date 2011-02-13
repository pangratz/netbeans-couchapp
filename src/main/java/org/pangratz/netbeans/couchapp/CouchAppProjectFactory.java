package org.pangratz.netbeans.couchapp;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=ProjectFactory.class)
public class CouchAppProjectFactory implements ProjectFactory {

    // public static final String COUCHAPP_RC_FILE = "couchapp.rc";
    public static final String COUCHAPP_JSON_FILE = "couchapp.json";
    // public static final String PROJECT_DIR = "app";

    //Specifies when a project is a project, i.e.,
    //if the project directory "Orders" is present:
    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(COUCHAPP_JSON_FILE) != null;
    }

    //Specifies when the project will be opened, i.e.,
    //if the project exists:
    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        return isProject(dir) ? new CouchAppProject(dir, state) : null;
    }

    @Override
    public void saveProject(final Project project) throws IOException, ClassCastException {
        FileObject projectRoot = project.getProjectDirectory();
        /*
        if (projectRoot.getFileObject(PROJECT_DIR) == null) {
            throw new IOException("Project dir " + projectRoot.getPath() +
                    " deleted," +
                    " cannot save project");
        }*/
        //Force creation of the texts dir if it was deleted:
        ((CouchAppProject) project).getCouchappJson(true);
    }
    
}
