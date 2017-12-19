package IHM.FenetrePrincipale;

import Core.Api;
import IHM.FenetreFiltres.FiltreWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTree;

/**
 *
 * @author Cédric GARCIA
 */
public class ButtonAnalyseFiltreActionListener implements ActionListener {
    
    private FiltreWindow filtreWindow;
    private Api api;
    private JTree tree;
    
    public ButtonAnalyseFiltreActionListener(Api api, JTree tree) {
        
        this.api = api;
        this.tree = tree;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        filtreWindow = new FiltreWindow(api, tree);
    }
    
}
