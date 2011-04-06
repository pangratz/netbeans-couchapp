package org.pangratz.netbeans.couchapp.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

public abstract class AbstractGenerateAction extends AbstractCouchAppAction implements TextFieldChangeListener {

    public AbstractGenerateAction(Lookup context) {
        super(context);

        putValue(NAME, getName());
    }

    public AbstractGenerateAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String msg = getMessage();

        Object value;
        if (hasOptions()) {
            value = showOptionDialog(msg, getOptions());
        } else {
            value = showOptionDialog(msg);
        }

        // user cancelled dialog
        if (value == null) {
            return;
        }

        boolean fsModified = generate(value);

        // refresh the directory
        if (fsModified) {
            FileUtil.refreshFor(getCouchAppDirectory());
        }
    }

    @Override
    protected String showOptionDialog(String msg) {
        InputPanel panel = new InputPanel();
        panel.setMessage(msg);
        panel.setTextFieldChangeListener(this);
        int chosenOption = JOptionPane.showConfirmDialog(null, panel, null, JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == chosenOption) {
            return panel.getValue();
        }
        return null;
    }

    public String isValid(String value) {
        return null;
    }

    protected boolean hasOptions() {
        return false;
    }

    protected Object[] getOptions() {
        return null;
    }

    protected abstract String getName();

    protected abstract String getMessage();

    protected abstract boolean generate(Object chosenOption);
}
