/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;

public class AdditionalCouchAppCompositePanelProvider implements ProjectCustomizer.CategoryComponentProvider {

    private Map panels;
    private JPanel EMPTY_PANEL = new JPanel();

    public AdditionalCouchAppCompositePanelProvider(Map panels) {
        this.panels = panels;
    }

    public JComponent create(ProjectCustomizer.Category category) {
        JComponent panel = (JComponent) panels.get(category);
        return panel == null ? EMPTY_PANEL : panel;
    }
}
