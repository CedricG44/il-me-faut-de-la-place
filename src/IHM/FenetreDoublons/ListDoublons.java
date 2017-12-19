package IHM.FenetreDoublons;

import Core.Api;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cédric GARCIA
 */
public class ListDoublons implements Runnable {

    private Api api;
    private HashMap<String, ArrayList<File>> doublons;

    public ListDoublons(Api api) {
        
        this.api = api;
    }
    
    @Override
    public void run() {
        
        doublons = api.getDoublons();
    }

    public HashMap<String, ArrayList<File>> getDoublons() {
        
        return doublons;
    }
}
