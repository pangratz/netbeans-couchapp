/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.actions;

import java.io.File;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.pangratz.netbeans.couchapp.CouchAppProject;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public abstract class AbstractCouchAppAction extends AbstractAction implements LookupListener, ContextAwareAction {

    private Lookup context = null;
    private Lookup.Result<CouchAppProject> result = null;
    protected final ICouchAppUtil couchappUtil;
    private File couchAppDirectory;

    public AbstractCouchAppAction() {
        this(Utilities.actionsGlobalContext());
    }

    public AbstractCouchAppAction(Lookup context) {
        super();
        init(context);

        couchappUtil = Lookup.getDefault().lookup(ICouchAppUtil.class);
    }

    public File getCouchAppDirectory() {
        Collection<? extends CouchAppProject> allInstances = result.allInstances();
        if (allInstances != null && allInstances.size() > 0) {
            FileObject projectDir = allInstances.iterator().next().getProjectDirectory();
            return FileUtil.toFile(projectDir);
        }
        
        return null;
    }

    protected Object showOptionDialog(String msg, Object[] options) {
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", msg,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
        return selectedValue;
    }

    protected String showOptionDialog(String msg) {
        return JOptionPane.showInputDialog(null, msg);
    }

    private void init(Lookup context) {
        this.context = context;
        result = context.lookupResult(CouchAppProject.class);
        result.addLookupListener(this);
        resultChanged(null);
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        setEnabled(result.allItems().size() != 0);
    }
}
