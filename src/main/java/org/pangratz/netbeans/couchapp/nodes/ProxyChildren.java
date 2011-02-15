/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.nodes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;

public class ProxyChildren extends FilterNode.Children {

    public ProxyChildren(Node original) {
        super(original);
    }

    @Override
    protected Node[] createNodes(Node key) {
        List<Node> result = new ArrayList<Node>();

        for (Node node : super.createNodes(key)) {
            DataObject dataObject = (DataObject) node.getLookup().lookup(DataObject.class);

            if (dataObject != null) {
                FileObject fileObject = dataObject.getPrimaryFile();
                File file = FileUtil.toFile(fileObject);

                if (accept(file)) {
                    result.add(node);
                }
            }
        }

        return result.toArray(new Node[result.size()]);
    }

    protected boolean accept(File file) {
        return true;
    }
}
