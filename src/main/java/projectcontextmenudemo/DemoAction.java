package projectcontextmenudemo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.NotifyDescriptor.Message;
import org.openide.awt.DynamicMenuContent;
import org.openide.filesystems.FileUtil;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.actions.Presenter;

public class DemoAction extends AbstractAction implements ContextAwareAction, Presenter.Toolbar {

    public
    @Override
    void actionPerformed(ActionEvent e) {
        Message message = new NotifyDescriptor.Message("Perform Demo Action");
        DialogDisplayer.getDefault().notify(message);
    }

    public
    @Override
    Action createContextAwareInstance(Lookup context) {
        return new ContextAction(context);
    }

    @Override
    public Component getToolbarPresenter() {
        return new JLabel("I am the DemoAction Toolbar");
    }

    private static final class ContextAction extends AbstractAction {

        private final Project p;

        public ContextAction(Lookup context) {
            p = context.lookup(Project.class);
            String name = ProjectUtils.getInformation(p).getDisplayName();
            // TODO state for which projects action should be enabled
            char c = name.charAt(0);
            setEnabled('s' == c);
            putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
            // TODO menu item label with optional mnemonics
            putValue(NAME, "&Info on " + name);
        }

        public
        @Override
        void actionPerformed(ActionEvent e) {
            // TODO what to do when run
            String msg = "Project location: "
                    + FileUtil.getFileDisplayName(p.getProjectDirectory());
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(msg));
        }
    }
}
