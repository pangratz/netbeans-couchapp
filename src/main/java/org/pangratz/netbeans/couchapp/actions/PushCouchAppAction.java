/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.awt.HtmlBrowser.URLDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class PushCouchAppAction extends AbstractGenerateAction {

    public PushCouchAppAction(Lookup actionContext) {
        super(actionContext);
    }

    public PushCouchAppAction() {
        super();

        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/couchdb-icon-16px.png")));
    }

    @Override
    protected String getMessage() {
        return "Choose destination where the CouchApp shall be pushed to";
    }

    @Override
    protected String showOptionDialog(String msg) {
        try {
            List<CouchDbServer> couchDbServers = couchappUtil.getCouchDbServers(getCouchAppDirectory());
            PushCouchAppDialog dialog = new PushCouchAppDialog(couchDbServers);
            dialog.setVisible(true);
            return dialog.getChosenCouchDbUrl();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    @Override
    protected boolean generate(Object chosenDbName) {
        try {
            String url = couchappUtil.pushCouchApp(getCouchAppDirectory(), (String) chosenDbName);

            // show the pushed couchapp
            URLDisplayer.getDefault().showURLExternal(new URL(url));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }

    @Override
    protected String getName() {
        return "Push CouchApp...";
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new PushCouchAppAction(actionContext);
    }
}
