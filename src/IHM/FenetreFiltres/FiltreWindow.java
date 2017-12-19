package IHM.FenetreFiltres;

import Core.Api;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Cédric
 */
public class FiltreWindow extends JFrame {
    
    private Api api;
    private JTree tree;
    private ArrayList<FileFilter> filtres;
    
    public FiltreWindow(Api api, JTree tree) {
        
        super("Analyse avec filtres");
        this.setVisible(true);
        
        this.api = api;
        this.tree = tree;
        this.filtres = new ArrayList<>();
        
        JLabel lblExtension = new JLabel("Extension des fichiers à filtrer :");
        JTextField txtFieldExtension = new JTextField();
        
        JList<String> listFiltres = new JList<>();
        DefaultListModel listModel = new DefaultListModel();
        listFiltres.setModel(listModel);
        JScrollPane scrollPanelFiltres = new JScrollPane();
        scrollPanelFiltres.setViewportView(listFiltres);
        
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnToutSupprimer = new JButton("Tout supprimer");
        btnSupprimer.setEnabled(false);
        btnToutSupprimer.setEnabled(false);
        
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeElementAt(listFiltres.getSelectedIndex());
                filtres.remove(listFiltres.getSelectedIndex());
                if(listModel.isEmpty()) {
                    btnSupprimer.setEnabled(false);
                    btnToutSupprimer.setEnabled(false);
                }
            }
        });
        
        btnToutSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                filtres.clear();
                listModel.removeAllElements();
            }
        });
        
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setEnabled(false);
        btnAjouter.addActionListener(new ButtonAjouterActionListener(txtFieldExtension, listModel, filtres, btnSupprimer, btnToutSupprimer));
        
        JButton btnAnalyse = new JButton("Analyse");
        btnAnalyse.addActionListener(new ButtonAnalyseActionListener(api, tree, filtres));
        btnAnalyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        txtFieldExtension.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                
                if(txtFieldExtension.getText().isEmpty()) {
                    btnAjouter.setEnabled(false);
                } else {
                    btnAjouter.setEnabled(true);
                }
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblExtension)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldExtension, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPanelFiltres)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnToutSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnalyse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouter)
                    .addComponent(lblExtension)
                    .addComponent(txtFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSupprimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnToutSupprimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalyse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler))
                    .addComponent(scrollPanelFiltres, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }
}
