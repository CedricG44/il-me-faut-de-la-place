package IHM.FenetreDoublons;

import Core.Api;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Fenêtre d'affichage des doublons
 *
 * @author Cédric GARCIA
 */
public class DoublonsWindow extends JFrame {

    private Api api;
    private HashMap<String, ArrayList<File>> doublons;

    public DoublonsWindow(Api api) {

        super("Doublons");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.api = api;

        ListDoublons listDoublons = new ListDoublons(api);
        Thread doublonsThread = new Thread(listDoublons);
        doublonsThread.start();

        JDialog dialog = new JDialog();
        dialog.setTitle("Veuillez patienter");
        dialog.setLocationRelativeTo(null);
        JPanel pane = new JPanel();
        pane.add(new JLabel("Veuillez patienter pendant la recherche des doublons"));
        dialog.getContentPane().add(pane);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.pack();

        dialog.setVisible(true);
        while (doublonsThread.isAlive()) {
        }
        dialog.setVisible(false);
        doublons = listDoublons.getDoublons();

        String[] entetes = {"Nom", "Modifié le", "Type", "Taille"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Nom");
        tableModel.addColumn("Chemin d'accès");
        tableModel.addColumn("Modifié le");
        tableModel.addColumn("Type");
        tableModel.addColumn("Taille");
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPanelTable = new JScrollPane(table);
        scrollPanelTable.setViewportView(table);

        for (HashMap.Entry<String, ArrayList<File>> i : doublons.entrySet()) {
            ArrayList<File> listFile = i.getValue();
            for (int j = 0; j < listFile.size(); j++) {
                File file = new File(listFile.get(j).getAbsolutePath());

                Date lastModified = new Date(file.lastModified());
                DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

                String type = "";
                if (file.isDirectory()) {
                    type = "Dossier de fichiers";
                } else {
                    type = "Fichier";
                }

                tableModel.insertRow(j, new Object[]{file.getName(), file.getAbsolutePath(), shortDateFormat.format(lastModified), type, file.length()});
            }
        }

        JPopupMenu deletePopupMenu = new JPopupMenu();
        JMenuItem itemDelete = new JMenuItem("Supprimer le doublon");
        itemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = new File((String) tableModel.getValueAt(table.getSelectedRow(), 1));
                file.delete();
                tableModel.removeRow(table.getSelectedRow());
            }
        });
        deletePopupMenu.add(itemDelete);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {

                    deletePopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelTable, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelTable, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        this.pack();
    }
}
