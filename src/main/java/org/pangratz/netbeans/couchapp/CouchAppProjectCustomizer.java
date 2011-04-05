/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.pangratz.netbeans.couchapp.editor.AdditionalCouchAppCompositePanelProvider;
import org.pangratz.netbeans.couchapp.editor.CouchAppPropertiesPanel;

class CouchAppProjectCustomizer implements CustomizerProvider {

    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    // Names of categories
    private static final String COUCHAPP_CATEGORY = "CouchAppCategory";

    private void init() {
        ProjectCustomizer.Category couchAppCategory = ProjectCustomizer.Category.create(
                COUCHAPP_CATEGORY,
                "CouchApp",
                null,
                null);

        categories = new ProjectCustomizer.Category[]{couchAppCategory};

        Map panels = new HashMap();
        panels.put(couchAppCategory, new CouchAppPropertiesPanel());

        panelProvider = new AdditionalCouchAppCompositePanelProvider(panels);
    }

    @Override
    public void showCustomizer() {
        init();

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("action invoked!!");
            }
        };

        Dialog dialog = ProjectCustomizer.createCustomizerDialog(categories, panelProvider, null, listener, null);
        dialog.setVisible(true);
    }
}
