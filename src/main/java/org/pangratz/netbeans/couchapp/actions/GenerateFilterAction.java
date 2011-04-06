package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import javax.swing.Action;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.pangratz.netbeans.couchapp.ICouchAppUtil;

public class GenerateFilterAction extends AbstractGenerateAction {

    public GenerateFilterAction(Lookup lookup) {
        super(lookup);
    }

    public GenerateFilterAction() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Enter name of filter";
    }

    @Override
    protected boolean generate(Object filterName) {
        try {
            couchappUtil.generateFilter(getCouchAppDirectory(), (String) filterName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new GenerateFilterAction(actionContext);
    }

    @Override
    protected String getName() {
        return "Generate Filter...";
    }

    @Override
    protected String getErrorMessage() {
        return "Filter already exists!";
    }

    @Override
    protected String getFolderName() {
        return ICouchAppUtil.FOLDER_FILTERS;
    }
}
