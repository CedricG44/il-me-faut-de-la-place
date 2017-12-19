package IHM.FenetrePrincipale;

import Core.Api;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Cédric GARCIA
 */
public class ButtonAnalyseActionListener implements ActionListener {
    
    private Api api;
    private JTree tree;
    private JTextField txtFieldPath;

    public ButtonAnalyseActionListener(Api api, JTree tree, JTextField txtFieldPath) {
        
        this.api = api;
        this.tree = tree;
        this.txtFieldPath = txtFieldPath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        File file = new File(txtFieldPath.getText());
        if(file.exists() && file.isDirectory()) {
            api.setCustomTree(txtFieldPath.getText());
            tree.setModel(api.getModelTree());
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un nom de répertoire valide.", "Répertoire invalide", JOptionPane.WARNING_MESSAGE);
        }
    }
}
