package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import javax.swing.Action;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class GenerateUpdateAction extends AbstractGenerateAction {

    public GenerateUpdateAction(Lookup lookup) {
        super(lookup);
    }

    public GenerateUpdateAction() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Enter name of update function";
    }

    @Override
    protected boolean generate(Object updateFunctionName) {
        try {
            couchappUtil.generateUpdate(getCouchAppDirectory(), (String) updateFunctionName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new GenerateUpdateAction(actionContext);
    }

    @Override
    protected String getName() {
        return "Generate Update...";
    }
}
