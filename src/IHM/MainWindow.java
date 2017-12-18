package IHM;

import Core.Api;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 * Fenêtre principale
 * 
 * @author Cédric GARCIA
 */
public class MainWindow extends JFrame {
    
    private Api api;
    private String path;
    
    public MainWindow() {
        
        super("Il me faut de la place !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        path = "D:\\Users\\Cédric\\Downloads";
        api = new Api(path);
        
        JMenu menuFichier = new JMenu("Fichier");
        DefaultTreeModel treeModel = api.getModelTree();
        JTree tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new TreeCellRenderer());
        JScrollPane scrollPane1Tree = new JScrollPane(tree);
        scrollPane1Tree.setViewportView(tree);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFichier);
        this.setJMenuBar(menuBar);
        
        JTextField txtFieldPath = new JTextField(path);
        
        JButton btnLister = new JButton("Lister fichiers/répertoires");
        btnLister.addActionListener(new ButtonListerActionListener(api, tree, txtFieldPath));
        
        String[] entetes = {"Nom", "Modifié le", "Type", "Taille"};
                
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nom");
        tableModel.addColumn("Modifié le");
        tableModel.addColumn("Type");
        tableModel.addColumn("Taille");
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane1Table = new JScrollPane(table);
        tree.addTreeSelectionListener(new TreeListener(table));
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1Tree)
                    .addComponent(btnLister, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldPath)
                    .addComponent(scrollPane1Table, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLister, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1Tree)
                    .addComponent(scrollPane1Table, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)))
        );

        pack();
    }
    
    public static void main(String[] args) {
        
        MainWindow mainWindow = new MainWindow();
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
