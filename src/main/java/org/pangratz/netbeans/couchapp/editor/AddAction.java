/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class AddAction extends AbstractAction {

    private final CouchDbServerTableModel couchDbServerTableModel;

    public AddAction(CouchDbServerTableModel couchDbServerTableModel) {
        super();

        setEnabled(false);
        this.couchDbServerTableModel = couchDbServerTableModel;

        putValue(NAME, "+");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        couchDbServerTableModel.addEmptyRow();
    }
}
