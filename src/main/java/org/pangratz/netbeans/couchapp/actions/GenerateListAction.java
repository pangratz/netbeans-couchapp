package org.pangratz.netbeans.couchapp.actions;

import java.io.IOException;
import javax.swing.Action;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class GenerateListAction extends AbstractGenerateAction {

    private GenerateListAction(Lookup actionContext) {
        super(actionContext);
    }

    public GenerateListAction() {
        super();
    }

    @Override
    protected String getMessage() {
        return "Enter name of list";
    }

    @Override
    protected boolean generate(Object listName) {
        try {
            couchappUtil.generateList(getCouchAppDirectory(), (String) listName);
            return true;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new GenerateListAction(actionContext);
    }

    @Override
    protected String getName() {
        return "Generate List...";
    }
}
