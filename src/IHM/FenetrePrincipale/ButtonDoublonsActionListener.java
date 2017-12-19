package IHM.FenetrePrincipale;

import Core.Api;
import IHM.FenetreDoublons.DoublonsWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cédric GARCIA
 */
public class ButtonDoublonsActionListener implements ActionListener {

    private Api api;

    public ButtonDoublonsActionListener(Api api) {
        
        this.api = api;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        DoublonsWindow doublonsWindow = new DoublonsWindow(api);
    }
    
}
