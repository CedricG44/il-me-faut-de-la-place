package IHM.FenetrePrincipale;

import Core.Api;
import Core.DirectoryNode;
import IHM.FenetreFiltres.FiltreWindow;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Fenêtre principale
 *
 * @author Cédric GARCIA
 */
public class MainWindow extends JFrame {

    private Api api;
    private String path;

    public MainWindow(String path) {

        super("Il me faut de la place !");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        this.path = path;
        api = new Api(path);

        DefaultTreeModel treeModel = api.getModelTree();
        JTree tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new TreeCellRenderer());
        JScrollPane scrollPanelTree = new JScrollPane(tree);
        scrollPanelTree.setViewportView(tree);

        JTextField txtFieldPath = new JTextField(path);
        txtFieldPath.setFont(new Font("Tahoma", 0, 12));

        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itmQuitter = new JMenuItem("Quitter");
        itmQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        menuFichier.add(itmQuitter);
        JMenu menuOptions = new JMenu("Options");
        JMenuItem itmCleanCache = new JMenuItem("Nettoyer le cache");
        itmCleanCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                api.cleanCache();
            }
        });
        menuOptions.add(itmCleanCache);

        JPopupMenu treePopupMenu = new JPopupMenu();
        JMenuItem itemAnalyse = new JMenuItem("Analyse à partir de ce répertoire");
        itemAnalyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if ((node != null) && (node.getUserObject().getClass() == DirectoryNode.class)) {
                    DirectoryNode directoryNode = (DirectoryNode) node.getUserObject();
                    txtFieldPath.setText(directoryNode.getFile().getAbsolutePath());
                    api.setCustomTree(directoryNode.getFile().getAbsolutePath());
                    tree.setModel(api.getModelTree());
                }
            }
        });
        treePopupMenu.add(itemAnalyse);
        tree.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    treePopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFichier);
        menuBar.add(menuOptions);
        this.setJMenuBar(menuBar);

        JButton btnAnalyse = new JButton("Analyse");
        btnAnalyse.addActionListener(new ButtonAnalyseActionListener(api, tree, txtFieldPath));

        JButton btnAnalyseFiltre = new JButton("Analyse filtrée");
        FiltreWindow filtreWindow = new FiltreWindow(api, tree);
        btnAnalyseFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtreWindow.setVisible(true);
            }
        });

        txtFieldPath.addActionListener(new ButtonAnalyseActionListener(api, tree, txtFieldPath));

        String[] entetes = {"Nom", "Modifié le", "Type", "Taille"};

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Nom");
        tableModel.addColumn("Modifié le");
        tableModel.addColumn("Type");
        tableModel.addColumn("Taille");
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPanelTable = new JScrollPane(table);
        scrollPanelTable.setViewportView(table);
        tree.addTreeSelectionListener(new TreeListener(tree, table));

        JButton btnDoublons = new JButton("Rechercher les doublons");
        btnDoublons.addActionListener(new ButtonDoublonsActionListener(api));

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAnalyse, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAnalyseFiltre, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPanelTree, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFieldPath)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDoublons, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPanelTable, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAnalyse, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addComponent(btnAnalyseFiltre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFieldPath)
                                .addComponent(btnDoublons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPanelTable, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                                .addComponent(scrollPanelTree))
                        .addContainerGap())
        );

        this.pack();
    }
}
