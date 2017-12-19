package IHM.FenetrePrincipale;

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
        
        JButton btnAnalyse = new JButton("Analyse");
        btnAnalyse.addActionListener(new ButtonAnalyseActionListener(api, tree, txtFieldPath));
        
        JButton btnAnalyseFiltre = new JButton("Analyse filtrée");
        btnAnalyseFiltre.addActionListener(new ButtonAnalyseFiltreActionListener(api, tree));
        
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
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1Tree, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnalyse, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnalyseFiltre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldPath)
                    .addComponent(scrollPane1Table, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalyse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalyseFiltre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane1Tree)
                    .addComponent(scrollPane1Table, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)))
        );

        pack();
    }
    
    public static void main(String[] args) {
        
        MainWindow mainWindow = new MainWindow();
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
