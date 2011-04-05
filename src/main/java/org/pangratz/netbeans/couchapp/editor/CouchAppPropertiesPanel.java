/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CouchAppPropertiesPanel.java
 *
 * Created on 05.04.2011, 14:15:09
 */
package org.pangratz.netbeans.couchapp.editor;

import java.util.List;
import java.util.Map;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

/**
 *
 * @author clemens
 */
public class CouchAppPropertiesPanel extends javax.swing.JPanel {

    private final CouchDbServerTableModel couchDbServerTableModel;

    /** Creates new form CouchAppPropertiesPanel */
    public CouchAppPropertiesPanel() {
        couchDbServerTableModel = new CouchDbServerTableModel();
        initComponents();
    }

    public CouchDbServerTableModel getCouchDbServerTableModel() {
        return couchDbServerTableModel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        propertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.propertiesPanel.border.title"))); // NOI18N

        couchAppNameLabel.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.couchAppNameLabel.text")); // NOI18N

        couchAppTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.couchAppTextField.text")); // NOI18N

        descriptionLabel.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.descriptionLabel.text")); // NOI18N

        descriptionTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.descriptionTextField.text")); // NOI18N

        designDocIdTextField.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.designDocIdTextField.text")); // NOI18N

        designDocIdLabel.setText(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.designDocIdLabel.text")); // NOI18N

        org.jdesktop.layout.GroupLayout propertiesPanelLayout = new org.jdesktop.layout.GroupLayout(propertiesPanel);
        propertiesPanel.setLayout(propertiesPanelLayout);
        propertiesPanelLayout.setHorizontalGroup(
            propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(couchAppNameLabel)
                    .add(descriptionLabel)
                    .add(designDocIdLabel))
                .add(18, 18, 18)
                .add(propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(designDocIdTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .add(descriptionTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .add(couchAppTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addContainerGap())
        );
        propertiesPanelLayout.setVerticalGroup(
            propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(couchAppNameLabel)
                    .add(couchAppTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(descriptionLabel)
                    .add(descriptionTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(propertiesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(designDocIdLabel)
                    .add(designDocIdTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        couchDbInstancesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CouchAppPropertiesPanel.class, "CouchAppPropertiesPanel.couchDbInstancesPanel.border.title"))); // NOI18N

        couchDbInstancesTable.setModel(couchDbServerTableModel);
        couchDbInstancesScrollPane.setViewportView(couchDbInstancesTable);

        org.jdesktop.layout.GroupLayout couchDbInstancesPanelLayout = new org.jdesktop.layout.GroupLayout(couchDbInstancesPanel);
        couchDbInstancesPanel.setLayout(couchDbInstancesPanelLayout);
        couchDbInstancesPanelLayout.setHorizontalGroup(
            couchDbInstancesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(couchDbInstancesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(couchDbInstancesScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );
        couchDbInstancesPanelLayout.setVerticalGroup(
            couchDbInstancesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(couchDbInstancesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(couchDbInstancesScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, couchDbInstancesPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, propertiesPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(propertiesPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(couchDbInstancesPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JPanel propertiesPanel;
    // End of variables declaration//GEN-END:variables

    public void setProperties(Map<String, Object> props) {
        String couchappName = (String) props.get(ICouchAppUtil.PROP_COUCHAPP_NAME);
        couchAppTextField.setText(couchappName);

        String couchappDescription = (String) props.get(ICouchAppUtil.PROP_COUCHAPP_DESCRIPTION);
        descriptionTextField.setText(couchappDescription);

        String designDocName = (String) props.get(ICouchAppUtil.PROP_DESIGN_DOC_ID);
        designDocIdTextField.setText(designDocName);

        List<CouchDbServer> servers = (List<CouchDbServer>) props.get(ICouchAppUtil.PROP_COUCHDB_SERVERS);
        couchDbServerTableModel.setServers(servers);
    }

    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
