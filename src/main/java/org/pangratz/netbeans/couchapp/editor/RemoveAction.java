/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RemoveAction extends AbstractAction implements ListSelectionListener {

    private final JTable couchDbInstancesTable;
    private final CouchDbServerTableModel couchDbServerTableModel;

    public RemoveAction(JTable couchDbInstancesTable, CouchDbServerTableModel couchDbServerTableModel) {
        super();

        setEnabled(false);
        putValue(NAME, "-");
        this.couchDbInstancesTable = couchDbInstancesTable;
        this.couchDbServerTableModel = couchDbServerTableModel;

        ListSelectionModel selectionModel = this.couchDbInstancesTable.getSelectionModel();
        selectionModel.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int row = couchDbInstancesTable.getSelectedRow();
        couchDbServerTableModel.removeRow(row);
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int selectedRowCount = couchDbInstancesTable.getSelectedRowCount();
        setEnabled(selectedRowCount > 0);
    }
}
