/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.pangratz.netbeans.couchapp.CouchAppProject;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public class CouchAppProjectCustomizer implements CustomizerProvider {

    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    // Names of categories
    private static final String COUCHAPP_CATEGORY = "CouchAppCategory";
    private Category couchAppCategory;
    private Map<Category, JComponent> panels;
    private final CouchAppProject couchAppProject;

    public CouchAppProjectCustomizer(CouchAppProject couchAppProject) {
        super();
        this.couchAppProject = couchAppProject;
    }

    private void init() {
        couchAppCategory = ProjectCustomizer.Category.create(COUCHAPP_CATEGORY, "CouchApp", null);

        categories = new ProjectCustomizer.Category[]{couchAppCategory};

        panels = new HashMap<ProjectCustomizer.Category, JComponent>();
        panels.put(couchAppCategory, getCouchAppPanel());
        panelProvider = new AdditionalCouchAppCompositePanelProvider(panels);
    }

    @Override
    public void showCustomizer() {
        init();

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                CouchAppPropertiesPanel couchAppPanel = (CouchAppPropertiesPanel) panels.get(couchAppCategory);
            }
        };

        Dialog dialog = ProjectCustomizer.createCustomizerDialog(categories, panelProvider, null, listener, null);
        dialog.setVisible(true);
    }

    private JComponent getCouchAppPanel() {
        CouchAppPropertiesPanel couchAppPropertiesPanel = new CouchAppPropertiesPanel();
        Lookup context = Utilities.actionsGlobalContext();
        ICouchAppUtil couchAppUtil = context.lookup(ICouchAppUtil.class);

        try {

            File projectDir = couchAppProject.getCouchAppDirectory();
            Map<String, Object> properties = couchAppUtil.readProperties(projectDir);
            couchAppPropertiesPanel.setProperties(properties);

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return couchAppPropertiesPanel;
    }
}
