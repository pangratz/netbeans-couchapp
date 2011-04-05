/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;

public class CouchAppProjectCustomizer implements CustomizerProvider {

    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    // Names of categories
    private static final String COUCHAPP_CATEGORY = "CouchAppCategory";
    private Category couchAppCategory;
    private Map<Category, JComponent> panels;

    private void init() {
        couchAppCategory = ProjectCustomizer.Category.create(COUCHAPP_CATEGORY, "CouchApp", null);

        categories = new ProjectCustomizer.Category[]{couchAppCategory};

        panels = new HashMap<ProjectCustomizer.Category, JComponent>();
        panels.put(couchAppCategory, new CouchAppPropertiesPanel());
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
}
