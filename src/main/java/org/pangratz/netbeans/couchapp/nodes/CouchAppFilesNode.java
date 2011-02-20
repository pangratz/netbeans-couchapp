package org.pangratz.netbeans.couchapp.nodes;

import java.awt.Image;
import java.io.File;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

@NodeFactory.Registration(projectType = "org-pangratz-netbeans-couchapp-CouchAppProject", position = 25)
public class CouchAppFilesNode extends AbstractCouchAppFolderNode {

    private Image icon;

    public CouchAppFilesNode() {
        super();
    }

    private CouchAppFilesNode(Node original, Children children, Lookup lookup) throws DataObjectNotFoundException {
        super(original, children, lookup);
    }

    @Override
    protected FilterNode getNode(Node original, Lookup lookup) throws DataObjectNotFoundException {
        ImportantFilesProxyChildren children = new ImportantFilesProxyChildren(original);
        return new CouchAppFilesNode(original, children, lookup);
    }

    @Override
    protected String getFolderPath() {
        return "/";
    }

    @Override
    public String getDisplayName() {
        return "CouchApp Files";
    }

    @Override
    public Image getIcon(int type) {
        if (icon == null) {
            Image originalIcon = getOriginal().getIcon(type);
            Image configBadgeIcon = ImageUtilities.loadImage("org/pangratz/netbeans/couchapp/config-badge.gif");
            icon = ImageUtilities.mergeImages(originalIcon, configBadgeIcon, 7, 7);
        }
        return icon;
    }

    private static class ImportantFilesProxyChildren extends ProxyChildren {

        public ImportantFilesProxyChildren(Node original) {
            super(original);
        }

        protected boolean accept(File file) {
            String name = file.getName();
            if (ICouchAppUtil.COUCHAPPRC.equals(name)) {
                return true;
            }
            if (ICouchAppUtil.COUCHAPP_JSON.equals(name)) {
                return true;
            }
            if (ICouchAppUtil._ID.equals(name)) {
                return true;
            }

            return false;


        }
    }
}
