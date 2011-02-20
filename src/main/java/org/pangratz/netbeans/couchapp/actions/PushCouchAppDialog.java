/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class PushCouchAppDialog extends JDialog {

    public static void main(String... args) {
        List<CouchDbServer> servers = new ArrayList<CouchDbServer>();
        servers.add(new CouchDbServer("default", "http://localhost:5984/my_db"));

        PushCouchAppDialog pushCouchAppDialog = new PushCouchAppDialog(servers);
        pushCouchAppDialog.setVisible(true);
        System.out.println(pushCouchAppDialog.getChosenCouchDbUrl());
    }
    private final PushCouchAppPanel pushCouchAppPanel;

    public PushCouchAppDialog(List<CouchDbServer> servers) {
        super();

        this.pushCouchAppPanel = new PushCouchAppPanel(this, servers);
        getContentPane().add(pushCouchAppPanel);
        pack();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
    }

    public String getChosenCouchDbUrl() {
        return this.pushCouchAppPanel.getChosenCouchDbUrl();
    }
}
