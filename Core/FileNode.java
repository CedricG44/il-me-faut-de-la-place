package Core;

import java.io.File;
import java.util.ArrayList;

/**
 * Représente un noeud Fichier
 */
public class FileNode extends Node {

    protected FileNode(File file){
        super(file);
    }

    @Override
    protected ArrayList<Node> getChilds() {
        return null;
    }

}
