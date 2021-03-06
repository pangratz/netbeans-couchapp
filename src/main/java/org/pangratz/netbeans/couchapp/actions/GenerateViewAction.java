package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import javax.swing.Action;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public class GenerateViewAction extends AbstractGenerateAction {

    public GenerateViewAction(Lookup lookup) {
        super(lookup);
    }

    public GenerateViewAction() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Enter name of view";
    }

    @Override
    protected boolean generate(Object viewName) {
        try {
            couchappUtil.generateView(getCouchAppDirectory(), (String) viewName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new GenerateViewAction(actionContext);
    }

    @Override
    protected String getName() {
        return "Generate View...";
    }

    @Override
    protected String getErrorMessage() {
        return "View with given name already exists!";
    }

    @Override
    protected String getFolderName() {
        return ICouchAppUtil.FOLDER_VIEWS;
    }
}
