package IHM.FenetreFiltres;

import Core.Api;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Cédric GARCIA
 */
public class ButtonAnalyseActionListener implements ActionListener {
    
    private Api api;
    private JTree tree;
    private ArrayList<FileFilter> filtres;

    public ButtonAnalyseActionListener(Api api, JTree tree, ArrayList<FileFilter> filtres) {
        
        this.api = api;
        this.tree = tree;
        this.filtres = filtres;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        tree.setModel(api.getModelTree(filtres));
    }
}
