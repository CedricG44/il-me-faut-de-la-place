package IHM.FenetreDoublons;

import Core.Api;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cédric GARCIA
 */
public class DoublonsWindow extends JFrame {
    
    private Api api;
    private HashMap<String, ArrayList<File>> doublons;
    
    public DoublonsWindow(Api api) {
        
        super("Doublons");
        this.setVisible(true);
    
        this.api = api;
        
        ListDoublons listDoublons = new ListDoublons(api);
        Thread doublonsThread = new Thread(listDoublons);
        doublonsThread.start();
        
        JDialog dialog = new JDialog();
        dialog.setTitle("Veuillez patienter");
        JPanel pane = new JPanel();
        pane.add(new JLabel("Veuillez patienter pendant la recherche des doublons"));
        dialog.getContentPane().add(pane);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.pack();
        
        dialog.setVisible(true);
        while(doublonsThread.isAlive()) {}
        dialog.setVisible(false);
        doublons = listDoublons.getDoublons();
                
        String[] entetes = {"Nom", "Modifié le", "Type", "Taille"};
                
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nom");
        tableModel.addColumn("Modifié le");
        tableModel.addColumn("Type");
        tableModel.addColumn("Taille");
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane1Table = new JScrollPane(table);
        scrollPane1Table.setViewportView(table);
        
        for(HashMap.Entry<String, ArrayList<File>> i : doublons.entrySet()) {
            ArrayList<File> listFile = i.getValue();
            for(int j = 0; j < listFile.size(); j++) {
                File file = new File(listFile.get(j).getAbsolutePath());
                
                Date lastModified = new Date(file.lastModified());
                DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                
                String type = "";
                if(file.isDirectory()) {
                    type = "Dossier de fichiers";
                } else {
                    type = "Fichier";
                }
                
                tableModel.insertRow(j, new Object[] { file.getName(), shortDateFormat.format(lastModified), type, file.length() });
            }
        }
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane1Table, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane1Table, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }
}
