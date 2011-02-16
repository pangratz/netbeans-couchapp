/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import java.util.List;
import org.openide.util.Exceptions;
import org.pangratz.netbeans.couchapp.CouchAppProject;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class PushCouchAppAction extends AbstractGenerateAction {

    public PushCouchAppAction(CouchAppProject cap) {
        super(cap);

        putValue(NAME, "Push CouchApp ...");
    }

    @Override
    protected String getMessage() {
        return "Choose destination where the CouchApp shall be pushed to";
    }

    @Override
    protected boolean hasOptions() {
        return true;
    }

    @Override
    protected Object[] getOptions() {
        try {
            List<CouchDbServer> couchDbServers = couchappUtil.getCouchDbServers(couchAppDirectory);
            String[] names = new String[couchDbServers.size()];
            int count = 0;
            for (CouchDbServer server : couchDbServers) {
                names[count++] = server.getName();
            }
            return names;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return new String[]{"default"};
    }

    @Override
    protected boolean generate(Object chosenDbName) {
        try {
            couchappUtil.pushCouchApp(couchAppDirectory, (String) chosenDbName);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }
}
