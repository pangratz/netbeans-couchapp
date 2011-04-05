/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.util.logging.Logger;
import javax.swing.JComponent;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.pangratz.netbeans.couchapp.editor//CouchAppEditor//EN", autostore = false)
public final class CouchAppEditorTopComponent extends TopComponent implements ProjectCustomizer.CompositeCategoryProvider {

    private static CouchAppEditorTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/pangratz/netbeans/couchapp/couchdb-icon-16px.png";
    private static final String PREFERRED_ID = "CouchAppEditorTopComponent";

    public CouchAppEditorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(CouchAppEditorTopComponent.class, "CTL_CouchAppEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(CouchAppEditorTopComponent.class, "HINT_CouchAppEditorTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        propertiesPanel = new javax.swing.JPanel();
        couchAppNameLabel = new javax.swing.JLabel();
        couchAppTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        descriptionTextField = new javax.swing.JTextField();
        designDocIdTextField = new javax.swing.JTextField();
        designDocIdLabel = new javax.swing.JLabel();
        couchDbInstancesPanel = new javax.swing.JPanel();
        couchDbInstancesScrollPane = new javax.swing.JScrollPane();
        couchDbInstancesTable = new javax.swing.JTable();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.jLabel1.text")); // NOI18N

        propertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.propertiesPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(couchAppNameLabel, org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.couchAppNameLabel.text")); // NOI18N

        couchAppTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.couchAppTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(descriptionLabel, org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.descriptionLabel.text")); // NOI18N

        descriptionTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.descriptionTextField.text")); // NOI18N

        designDocIdTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.designDocIdTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(designDocIdLabel, org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.designDocIdLabel.text")); // NOI18N

        javax.swing.GroupLayout propertiesPanelLayout = new javax.swing.GroupLayout(propertiesPanel);
        propertiesPanel.setLayout(propertiesPanelLayout);
        propertiesPanelLayout.setHorizontalGroup(
            propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(couchAppNameLabel)
                    .addComponent(descriptionLabel)
                    .addComponent(designDocIdLabel))
                .addGap(18, 18, 18)
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionTextField)
                            .addComponent(couchAppTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addContainerGap(217, Short.MAX_VALUE))
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addComponent(designDocIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addGap(217, 217, 217))))
        );
        propertiesPanelLayout.setVerticalGroup(
            propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(couchAppNameLabel)
                    .addComponent(couchAppTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(designDocIdLabel)
                    .addComponent(designDocIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        couchDbInstancesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CouchAppEditorTopComponent.class, "CouchAppEditorTopComponent.couchDbInstancesPanel.border.title"))); // NOI18N

        couchDbInstancesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Name", "Location"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        couchDbInstancesScrollPane.setViewportView(couchDbInstancesTable);

        javax.swing.GroupLayout couchDbInstancesPanelLayout = new javax.swing.GroupLayout(couchDbInstancesPanel);
        couchDbInstancesPanel.setLayout(couchDbInstancesPanelLayout);
        couchDbInstancesPanelLayout.setHorizontalGroup(
            couchDbInstancesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(couchDbInstancesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(couchDbInstancesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );
        couchDbInstancesPanelLayout.setVerticalGroup(
            couchDbInstancesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(couchDbInstancesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(couchDbInstancesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(propertiesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(couchDbInstancesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(couchDbInstancesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel couchAppNameLabel;
    private javax.swing.JTextField couchAppTextField;
    private javax.swing.JPanel couchDbInstancesPanel;
    private javax.swing.JScrollPane couchDbInstancesScrollPane;
    private javax.swing.JTable couchDbInstancesTable;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JLabel designDocIdLabel;
    private javax.swing.JTextField designDocIdTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel propertiesPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized CouchAppEditorTopComponent getDefault() {
        if (instance == null) {
            instance = new CouchAppEditorTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the CouchAppEditorTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized CouchAppEditorTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(CouchAppEditorTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof CouchAppEditorTopComponent) {
            return (CouchAppEditorTopComponent) win;
        }
        Logger.getLogger(CouchAppEditorTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 100)
    public static CouchAppEditorTopComponent create() {
        return getDefault();
    }

    @Override
    public Category createCategory(Lookup lkp) {
        return ProjectCustomizer.Category.create(
                "CouchDB",
                "CouchDB Config",
                null);
    }

    @Override
    public JComponent createComponent(Category ctgr, Lookup lkp) {
        return this;
    }
}
