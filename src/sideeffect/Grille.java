/*
 * SIDE EFFECT Classe Gille
 * Sulwen de la CROIX / Matthieu SERFATY
 * EPF Ecole d'ingénieur-e-s 27/01/2017
 */
package sideeffect;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author mserfaty
 */
public class Grille {

    // Déclaration des attributs
    private int taille;
    private int grille[][] = new int[20][20];

    //Constructeur
    public Grille(int taille) {
        this.taille = taille;
    }

    public void afficher() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (j == 0) {
                    System.out.print('\n');
                }
                System.out.print(grille[i][j]);
            }
        }
    }

    public void afficher(JLabel tab[][]) {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {

                switch (grille[i][j]) {
                    case 11:
                        tab[i][j].setBackground(Color.RED);
                        break;
                    case 22:
                        tab[i][j].setBackground(Color.BLUE);
                        break;
                    case 33:
                        tab[i][j].setBackground(Color.DARK_GRAY);
                        break;
                    case 2:
                        tab[i][j].setBackground(Color.BLUE);
                        break;
                    case 1:
                        tab[i][j].setBackground(Color.RED);
                        break;
                    case 0:
                        tab[i][j].setBackground(Color.LIGHT_GRAY);
                    default:
                        break;
                }

            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public int[][] getGrille() {
        return grille;
    }

    // Déclaration des méthodes
    public void creerGrille(int taille) {
        int i, j;

        //Initialisation des coins 
        grille[0][0] = 0;
        grille[0][taille - 1] = 0;
        grille[taille - 1][0] = 0;
        grille[taille - 1][taille - 1] = 0;

        //Initialisation des bords de la grille
        i = 0;
        for (j = 1; j < (taille - 1); j++) {
            grille[i][j] = 22;      //Le chiffre 22 correspond à la couleur rouge   
        }
        i = taille - 1;
        for (j = 1; j < (taille - 1); j++) {
            grille[i][j] = 22;
        }
        j = 0;
        for (i = 1; i < (taille - 1); i++) {
            grille[i][j] = 11;      //Le chiffre 11 correspond à la couleur bleu  
        }
        j = taille - 1;
        for (i = 1; i < (taille - 1); i++) {
            grille[i][j] = 11;
        }

        //Initialisation du reste de la grille
        for (i = 1; i < taille - 1; i++) {
            for (j = 1; j < taille - 1; j++) {
                if(j%2 == 0){
                    grille[i][j] = 1;
                }
                if(i%2 == 0){
                    grille[i][j] = 2;
                }
                grille[i][j] = 0;
            }
        }

        //Initialisation du bloc central de la grille
        i = taille / 2;
        grille[i - 1][i - 1] = 33;
        grille[i - 1][i] = 33;
        grille[i][i - 1] = 33;
        grille[i][i] = 33;

    }

    public boolean verifFinDuJeu(Plateau p) {
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j <= taille - 2; j++) {
                for (k = 1; k < taille - 2; k++) {
                    if (p.VerifPlacement(j, k, i) == true) {
                        return false; //Lorsque qu'il est encore possible de poser une pièce sur le plateau la partie continue.
                    }
                }
            }
        }
        return true;
    }

    private boolean testCase(int i, ArrayList<int[]> ListeFait, ArrayList<int[]> ListeChemin, int x, int y) {
        int[] tab = new int[2]; //tableau temporaire permettant de stocker les coordonnées
        switch (i) {  //Permet de savoir quelle case on étudie
            case 0: //Cas de la case de gauche
                if (grille[x][y] != grille[x - 1][y]) { //Si la cases de gauche n'est pas de la même couleur on retourne false
                    return false;
                }
                for (i = 0; i < ListeFait.size(); i++) {    //On teste si la case de gauche appartient à la liste des cases faites
                    tab = ListeFait.get(i);
                    if (tab[0] == x - 1 && tab[1] == y) {
                        return false;
                    }
                }
                for (i = 0; i < ListeChemin.size(); i++) {  //On teste si la case de gauche appartient à la liste des cases à étudier
                    tab = ListeChemin.get(i);
                    if ((tab[0] == x - 1) && (tab[1] == y)) {
                        return false;
                    }
                }
                break;

            case 1: //Cas de la case de droite
                if (grille[x][y] != grille[x + 1][y]) {
                    return false;
                }
                for (i = 0; i < ListeFait.size(); i++) {
                    tab = ListeFait.get(i);
                    if (tab[0] == x + 1 && tab[1] == y) {
                        return false;
                    }
                }
                for (i = 0; i < ListeChemin.size(); i++) {
                    tab = ListeChemin.get(i);
                    if (tab[0] == x + 1 && tab[1] == y) {
                        return false;
                    }
                }
                break;
            case 2: //Cas de la case du haut
                if (grille[x][y] != grille[x][y - 1]) {
                    return false;
                }
                for (i = 0; i < ListeFait.size(); i++) {
                    tab = ListeFait.get(i);
                    if (tab[0] == x && tab[1] == y - 1) {
                        return false;
                    }
                }
                for (i = 0; i < ListeChemin.size(); i++) {
                    tab = ListeChemin.get(i);
                    if (tab[0] == x && tab[1] == y - 1) {
                        return false;
                    }
                    return true;
                }
                break;
            case 3: //Cas de la case du bas
                if (grille[x][y] != grille[x][y + 1]) {
                    return false;
                }
                for (i = 0; i < ListeFait.size(); i++) {
                    tab = ListeFait.get(i);
                    if (tab[0] == x && tab[1] == y + 1) {
                        return false;
                    }
                }
                for (i = 0; i < ListeChemin.size(); i++) {
                    tab = ListeChemin.get(i);
                    if (tab[0] == x && tab[1] == y + 1) {
                        return false;
                    }
                    return true;
                }
                break;
        }
        return true;

    }

    public int Verifchemins(int score) { //Renvoie le score équivalent aux chemins
        ArrayList<int[]> ListeFait = new ArrayList<>();    //Liste des cases à partir desquelles aucun chemin n'est possible
        ArrayList<int[]> ListeChemin = new ArrayList<>();  //Liste du chemin emprunté actuellement
        int x = 0, y = 0, i, j, k;
        /*Declaration des variables : x et y sont les coordonnées du curseur elles sont initialisées sur le carré central
         i,j et k les variables d'itération.
         */
        for (j = 0; j < 8; j++) {
            switch (j) {
                case (0):
                    x = taille / 2 - 1; //On se place sur la case au dessus du bloc central à gauche
                    y = x - 1;

                    break;
                case (1):
                    x = x + 1; //On se place sur la case au dessus du bloc central à droite
                    break;
                case (2):
                    x = x + 1;  //On se place sur la case à droite du bloc central en bas
                    y = y + 1;
                    break;
                case (3):
                    y = y + 1; //On se place sur la case à droite du bloc central en bas
                    break;
                case (4):
                    x = x - 1; //On se place sur la case au dessoud du bloc central à droite
                    y = y + 1;
                    break;

                case (5):
                    x = x - 1; //On se place sur la case au dessous du bloc central à gauche
                    break;
                case (6):
                    x = x - 1; //On se place sur la case à gauche du bloc central en bas
                    y = y - 1;
                    break;
                case (7):
                    y = y - 1; //On se place sur la case à gauche du bloc central en haut
                    break;
            }

            int[] tab = new int[2];    //Tableau provisoire permettant de stocker des coordonnées
            tab[0] = x;
            tab[1] = y;
            ListeChemin.add(tab);        //Ajoute x et y à la liste des prochaines cases à regarder

            boolean bool = true; //Permet de faire une boucle while
            boolean cheminTermine = false;

            while (bool == true) {
                if ((x == 1) && (y == 1)) {    //Si on est dans un coin, on a forcement gagné
                    cheminTermine = true;
                } else if (y == 1) { //Si on se trouve tout en haut de la grille et que la couleur de la case est rouge, on a un chemin
                    if (grille[x][y] == 1) {
                        cheminTermine = true;
                    }
                } else if (x == 1) { //Si on se trouve tout à gauche de la grille et que la case est bleue, on a un chemin
                    if (grille[x][y] == 2) {
                        cheminTermine = true;
                    }
                } else if ((x == taille - 2) && (y == taille - 2)) { //C'est un coin
                    cheminTermine = true;
                } else if (x == taille - 2) {    //C'est l'extrémité à droite
                    if (grille[x][y] == 2) {
                        cheminTermine = true;
                    }
                } else if (y == taille - 2) {    //C'est l'extrémité du bas
                    if (grille[x][y] == 1) {
                        cheminTermine = true;
                    }
                } else if (grille[x][y] == 0) {
                    bool = false;
                }
                if (bool == true) {
                    for (i = 0; i < 4; i++) {  //Nous avons 4 cases adjacentes à parcourir donc on itère de 0 à 3
                        switch (i) {
                            case 0: //On regarde la case de gauche
                                if (testCase(i, ListeFait, ListeChemin, x, y) == true) {   //Si elle correspond à un chemin empruntable on continue sur cette case
                                    tab = new int[2];
                                    tab[0] = x - 1;
                                    tab[1] = y;
                                    ListeChemin.add(tab);  //On ajoute la case sur laquelle est notre curseur à la liste des chemins
                                }
                                break;
                            case 1:    //Cas de la case de droite
                                if (testCase(i, ListeFait, ListeChemin, x, y) == true) {
                                    tab = new int[2];
                                    tab[0] = x + 1;
                                    tab[1] = y;
                                    ListeChemin.add(tab);
                                }
                                break;
                            case 2:    //Cas de la case du haut
                                if (testCase(i, ListeFait, ListeChemin, x, y) == true) {
                                    tab = new int[2];
                                    tab[0] = x;
                                    tab[1] = y - 1;
                                    ListeChemin.add(tab);
                                }
                                break;
                            case 3:    //Cas de la case du bas
                                if (testCase(i, ListeFait, ListeChemin, x, y) == true) {
                                    tab = new int[2];
                                    tab[0] = x;
                                    tab[1] = y + 1;
                                    ListeChemin.add(tab);
                                }
                                break;
                        }
                    }

                    ListeFait.add(ListeChemin.get(0));  //On ajoute la case inexploitable à la liste des cases faites
                    ListeChemin.remove(0);  //On enlève la case inexploitable de la liste des cases à étudier
                    if (ListeChemin.size() == 0) {   //Si la liste des cases à étudier est vide, on retourne 0
                        if (cheminTermine == true) {
                            score = score + ListeFait.size();
                            for (k = 0; k < ListeFait.size(); k++) {
                                grille[ListeFait.get(k)[0]][ListeFait.get(k)[1]] = 0;
                            }

                        }
                        ListeFait.clear();
                        bool = false;
                    } else {
                        x = ListeChemin.get(0)[0];  //On prend les coordonnées de la case à étudier
                        y = ListeChemin.get(0)[1];
                    }
                } else {
                    ListeChemin.clear();
                    ListeFait.clear();
                }

            }

        }
        return score;
    }

    public void win() {
        for (int i = 1; i < 9; i++) {
            grille[i][10] = 2;
        }
    }

}
