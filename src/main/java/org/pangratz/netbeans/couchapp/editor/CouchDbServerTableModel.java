/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.netbeans.couchapp.editor;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.pangratz.netbeans.couchapp.ICouchAppUtil.CouchDbServer;

public class CouchDbServerTableModel extends DefaultTableModel {

    public static final String[] columns = new String[]{"Name", "Url"};
    private final List<CouchDbServer> servers = new LinkedList<CouchDbServer>();

    public CouchDbServerTableModel() {
        super();
    }

    public List<CouchDbServer> getServers() {
        return servers;
    }

    public void setServers(List<CouchDbServer> servers) {
        this.servers.clear();
        this.servers.addAll(servers);
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        if (servers != null) {
            return servers.size();
        }

        return 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        CouchDbServer server = servers.get(row);

        String name = server.getName();
        if (col == 0) {
            name = (String) value;
        }

        String url = server.getServer();
        if (col == 1) {
            url = (String) value;
        }

        CouchDbServer newServer = new CouchDbServer(name, url);
        servers.set(row, newServer);
        fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        CouchDbServer server = servers.get(row);
        if (col == 0) {
            return server.getName();
        } else if (col == 1) {
            return server.getServer();
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    public void addEmptyRow() {
        servers.add(new CouchDbServer("name", "http://localhost:5984/"));
        fireTableRowsInserted(servers.size(), servers.size());
    }

    @Override
    public void removeRow(int row) {
        servers.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
