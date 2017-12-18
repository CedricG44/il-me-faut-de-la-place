package IHM;

import Core.DirectoryNode;
import Core.Node;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Cédric GARCIA
 */
public class TreeListener implements TreeSelectionListener {

    private JTree tree;
    private JTable table;
    
    public TreeListener(JTable table) {
        
        this.table = table;
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
        tree = (JTree)e.getSource();
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
            }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node.getUserObject().getClass() == DirectoryNode.class) {
            DirectoryNode directoryNode = (DirectoryNode)node.getUserObject();
            ArrayList<Node> listFiles = directoryNode.getChilds();
            for(int i = 0; i < listFiles.size(); i++) {
                File file = listFiles.get(i).getFile();
                
                Date lastModified = new Date(file.lastModified());
                DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                
                String type = "";
                if(file.isDirectory()) {
                    type = "Dossier de fichiers";
                } else {
                    type = "Fichier";
                }
                
                tableModel.insertRow(i, new Object[] { file.getName(), shortDateFormat.format(lastModified), type, file.length() });
            }
        }
    }
}
