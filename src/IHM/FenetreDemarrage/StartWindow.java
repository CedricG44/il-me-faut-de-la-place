package IHM.FenetreDemarrage;

import IHM.FenetrePrincipale.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * Fenêtre de démarrage du logiciel
 *
 * @author Cédric GARCIA
 */
public class StartWindow extends JFrame {

    public StartWindow() {

        super("Démarrage");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}

        JLabel lblInfo = new JLabel("Veuillez sélectionner le répertoire à partir duquel générer l'arborescence :");
        JLabel lblPath = new JLabel("Répertoire :");
        JTextField txtFieldPath = new JTextField();
        JButton btnAnalyser = new JButton("Analyser");
        btnAnalyser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = new File(txtFieldPath.getText());
                if (file.exists() && file.isDirectory()) {
                    MainWindow mainWindow = new MainWindow(txtFieldPath.getText());
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nom de répertoire valide.", "Répertoire invalide", JOptionPane.WARNING_MESSAGE);
                    txtFieldPath.setText("");
                }
            }
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblInfo)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPath)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldPath)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalyser)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInfo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPath)
                                .addComponent(txtFieldPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnalyser)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.pack();
    }

    public static void main(String[] args) {

        StartWindow startWindow = new StartWindow();
    }
}
