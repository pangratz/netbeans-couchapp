/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.wizard;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.WizardDescriptor;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;
import org.pangratz.netbeans.couchapp.RuntimeCouchAppUtil;

public class CreateCouchAppWizardIterator implements WizardDescriptor.InstantiatingIterator {

    private int index;
    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wiz;

    public CreateCouchAppWizardIterator() {
    }

    public static CreateCouchAppWizardIterator createIterator() {
        return new CreateCouchAppWizardIterator();
    }

    private WizardDescriptor.Panel[] createPanels() {
        return new WizardDescriptor.Panel[]{
                    new CreateCouchAppWizardPanel(),};
    }

    private String[] createSteps() {
        return new String[]{
                    NbBundle.getMessage(CreateCouchAppWizardIterator.class, "LBL_CreateProjectStep")
                };
    }

    public Set/*<FileObject>*/ instantiate() throws IOException {
        Set<FileObject> resultSet = new LinkedHashSet<FileObject>();
        File dirF = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));

        ICouchAppUtil couchAppUtil = Lookup.getDefault().lookup(ICouchAppUtil.class);

        String createType = (String) wiz.getProperty("createType");
        if (CreateCouchAppWizardPanel.CLONE_COUCHAPP.equalsIgnoreCase(createType)) {
            String cloneCouchAppUrl = (String) wiz.getProperty("cloneCouchAppUrl");
            couchAppUtil.cloneCouchApp(dirF, new URL(cloneCouchAppUrl));
        } else {
            couchAppUtil.generateCouchApp(dirF);
        }

        ProjectManager projectManager = ProjectManager.getDefault();
        FileObject dir = FileUtil.toFileObject(dirF);
        Project proj = projectManager.findProject(dir);
        OpenProjects.getDefault().open(new Project[]{proj}, true);

        return resultSet;
    }

    public void initialize(WizardDescriptor wiz) {
        this.wiz = wiz;
        index = 0;
        panels = createPanels();
        // Make sure list of steps is accurate.
        String[] steps = createSteps();
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                // Step #.
                // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps);
            }
        }
    }

    public void uninitialize(WizardDescriptor wiz) {
        this.wiz.putProperty("projdir", null);
        this.wiz.putProperty("name", null);
        this.wiz = null;
        panels = null;
    }

    public String name() {
        return MessageFormat.format("{0} of {1}",
                new Object[]{new Integer(index + 1), new Integer(panels.length)});
    }

    public boolean hasNext() {
        return index < panels.length - 1;
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    public WizardDescriptor.Panel current() {
        return panels[index];
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }
}
