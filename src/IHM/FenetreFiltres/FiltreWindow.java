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
 * Fenêtre des paramétrage des filtres
 *
 * @author Cédric GARCIA
 */
public class FiltreWindow extends JFrame {

    private Api api;
    private JTree tree;
    private ArrayList<FileFilter> filtres;

    public FiltreWindow(Api api, JTree tree) {

        super("Analyse avec filtres");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

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

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setEnabled(false);

        JButton btnAnalyse = new JButton("Analyse");
        btnAnalyse.setEnabled(false);
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

                if (txtFieldExtension.getText().isEmpty()) {
                    btnAjouter.setEnabled(false);
                } else {
                    btnAjouter.setEnabled(true);
                }
            }
        });

        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnToutSupprimer = new JButton("Tout supprimer");
        btnSupprimer.setEnabled(false);
        btnToutSupprimer.setEnabled(false);

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                filtres.remove(listFiltres.getSelectedIndex());
                listModel.removeElementAt(listFiltres.getSelectedIndex());
                System.out.println(filtres);
                if (listModel.isEmpty()) {
                    btnSupprimer.setEnabled(false);
                    btnToutSupprimer.setEnabled(false);
                    btnAnalyse.setEnabled(false);
                }
            }
        });

        btnToutSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                filtres.clear();
                listModel.removeAllElements();
                btnSupprimer.setEnabled(false);
                btnToutSupprimer.setEnabled(false);
                btnAnalyse.setEnabled(false);
            }
        });

        btnAjouter.addActionListener(new ButtonAjouterActionListener(txtFieldExtension, listModel, filtres, btnSupprimer, btnToutSupprimer, btnAnalyse));

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblExtension)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldExtension, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(scrollPanelFiltres)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(btnSupprimer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnToutSupprimer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAnalyse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAnnuler, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAjouter)
                                .addComponent(lblExtension)
                                .addComponent(txtFieldExtension, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSupprimer)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnToutSupprimer)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAnalyse)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAnnuler))
                                .addComponent(scrollPanelFiltres, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                        .addContainerGap())
        );

        this.pack();
    }
}
