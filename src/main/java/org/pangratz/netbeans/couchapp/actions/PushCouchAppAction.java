/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class PushCouchAppAction extends AbstractCouchAppAction {

    public PushCouchAppAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Push CouchApp ...");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            List<CouchDbServer> couchDbServers = couchappUtil.getCouchDbServers(couchAppDirectory);
            String[] names = new String[couchDbServers.size()];
            int count = 0;
            for (CouchDbServer server : couchDbServers) {
                names[count++] = server.getName();
            }

            Object chosenDbName = showOptionDialog("Choose destination where the CouchApp shall be pushed to", names);
            if (chosenDbName != null) {
                couchappUtil.pushCouchApp(couchAppDirectory, (String) chosenDbName);
                FileUtil.refreshFor(couchAppDirectory);
            }

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
