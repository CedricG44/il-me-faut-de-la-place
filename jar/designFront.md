# Cédric GARCIA - Projet ACDC - designFront.md - 09/01/2018


## 1 - Utilisation du .jar


### 1.1 - Execution du .jar

- Récupérer le fichier il-me-faut-de-la-place.jar
- Le placer dans le répertoire souhaité.
- Executer le .jar

### 1.2 - Options disponibles

Cet exécutable reprend toutes les fonctionnalités du projet de mon collègue Vivien LOURADOUR, auxquelles j'ai ajouté une interface graphique. Son projet est disponible [ici](https://github.com/vivienlouradour/Il-me-faut-de-la-place).


## 2 - Conception et architecture de l'IHM

L'IHM repose sur 4 différents packages qui composent les fonctionnalités applicatives. Toutes les classes qui composent l'interface héritent de la classe JFrame native en Java. Les Listener correspondant aux actions spécifiques des composants graphiques sont soit externalisés dans des classes propres, lorsqu'il y a des traitements spécifiques à effectuer, ou bien dans des classes anonymes lorsques les actions restent basiques.

### 2.1 - Le package IHM.FenetreDemarrage

Ce package comprend la classe StartWindow. Elle correspond à la fenêtre qui s'ouvre lors de l'ouverture du logiciel et demande à l'utilisateur de renseigner le chemin à partir duquel il souhaite parcourir l'arborescence de son disque dur. L'utilisateur valide son choix et la fenêtre principale du logiciel apparaît.

Image : [StartWindow](StartWindow.PNG).

### 2.2 - Le package IHM.FenetrePrincipale

Ce package comprend la classe MainWindow. Elle correspond à la fenêtre principale du logiciel. On y retrouve une barre de menus JMenuBar, trois boutons JButton, un champ de texte JTextField, un arbre JTree et un tableau JTable.

Les boutons permettent d'analyser l'arborescence, de l'analyser en appliquant une liste de filtres pour l'extension des fichiers, et enfin d'afficher les doublons. Ces trois fonctionnalités reposent sur le chemin renseigné par l'utilisateur dans la fenêtre de démarrage. Il apparaît ensuite dans le champ de texte et est modifiable. On peut reparcourir l'arborescence en cliquant sur l'un des deux boutons d'analyse, ou en appuyant sur la touche entrée du clavier (analyse simple, sans filtres).
Le parcours de l'arborescence permet de compléter le JTree, à gauche de la fenêtre. Y apparaissent les fichiers et dossiers, que l'on peut parcourir. Un clic droit permet à l'utilisateur de parcourir l'arborescence directement à partir d'un dossier voulu.
Lorsque l'on clique gauche sur un dossier de cet arbre, son contenu apparaît dans le tableau de droite, ainsi que toutes les informations concernant les fichiers et dossiers qu'il contient. ces informations sont triables par nom, date de dernière modification ou taille, par un clic sur l'entête de la colonne correspondante du tableau.

Enfin, le bouton de recherche de doublons ouvre une nouvelle fenêtre et active le mécanisme de hash des fichiers. Les fichiers sont comparés à partir de ce hash et sont cachés dans un fichier. La création de ce fichier rend plus rapide l'affichage des doublons lors d'une utilisation ultérieure. Une fois cette recherche éffectuée, les doublons apparaissent. Ce traitement peut être assez long si l'on part de haut dans l'arborescence, et ce même avec le système de cache.

Image : [MainWindow](MainWindow.PNG).

### 2.3 - Le package IHM.FenetreFiltres

Ce package comprend la classe FiltreWindow. Elle correspond à la fenêtre qui permet à l'utilisateur de saisir manuellement les extensions de fichiers qu'il souhaite faire apparaître dans l'arbre. Il peut saisir les extensions une à une, elles sont mémorisées sous forme de liste. Elles peuvent être supprimées au besoin, ou toutes supprimées. Un bouton permet de valider l'analyse avec ces filtres.

Image : [FiltreWindow](FiltreWindow.PNG).

### 2.4 - Le package IHM.fenetreDoublons

Ce package comprend la classe DoublonsWindow. Elle correspond à la fenêtre d'affichage des doublons trouvés à partir du chemin donné par l'utilisateur, ainsi qu'à tous les niveaux inférieurs. Les doublons apparaissent dans tableau , ainsi que toutes les informations concernant ces fichiers. Comme au point 2.2, ces informations sont triables par nom, date de dernière modification ou taille, par un clic sur l'entête de la colonne correspondante du tableau.
Un clic droit permet de supprimer le doublon sélectionné de la liste de doublons ainsi que du disque dur de la machine.

Image : [DoublonsWindow](DoublonsWindow.PNG).


## 3 - Difficultées rencontrées

Je n'ai pas rencontré de difficultés particulières sur cette partie. J'avais déjà eu l'occasion d'utiliser Swing pour créer des IHM. Egalement, le cours d'IHM que nous avons eu en début d'année m'a permis de mieux apprendre à utiliser Swing, notamment dans la manière d'organiser les classes et les Listener pour les actions sur les composants graphiques.
