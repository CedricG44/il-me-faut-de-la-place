package IHM.FenetreFiltres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Cédric
 */
public class ButtonAjouterActionListener implements ActionListener {

    private JTextField txtFieldExtension;
    private DefaultListModel listModel;
    private ArrayList<FileFilter> filtres;
    private JButton btnSupprimer;
    private JButton btnToutSupprimer;

    public ButtonAjouterActionListener(JTextField txtFieldExtension, DefaultListModel listModel, ArrayList<FileFilter> filtres,
            JButton btnSupprimer, JButton btnToutSupprimer) {
        
        this.txtFieldExtension = txtFieldExtension;
        this.listModel = listModel;
        this.filtres = filtres;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String ext = txtFieldExtension.getText();
        if((ext.startsWith(".")) && !listModel.contains(ext)) {
            listModel.addElement(ext);
            filtres.add(new FileFilter() {
                @Override
                public boolean accept(File file) {
                     
                    return file.getName().contains(ext);
                }
            });
            txtFieldExtension.setText("");
            btnSupprimer.setEnabled(true);
            btnToutSupprimer.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une autre extension.", "Extension invalide", JOptionPane.WARNING_MESSAGE);
        }
    }
}
