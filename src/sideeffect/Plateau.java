
/*
 * SIDE EFFECT Classe Plateau
 * Sulwen de la CROIX / Matthieu SERFATY
 * EPF Ecole d'ingénieur-e-s 27/01/2017
 */
package sideeffect;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 *
 * @author mserfaty
 */
public class Plateau {

    // Déclaration des attributs 
    private Grille grille = new Grille(20);
    private Piece plateau[] = new Piece[4];

    //Constructeur
    public Plateau() {
    }

    // Déclaration des méthodes
    public Grille getGrille() {
        return grille;
    }

    // Méthodes d'affichage
    public void affichageMenu() {

    }

    public void affichagePlateau() {

    }
    // Autres méthodes

    public boolean VerifPlacement(int x, int y, int indice) throws IndexOutOfBoundsException {
        int i = 0, j = 0;   //Déclaration des variables d'itération
        for (i = 0; i < 3; i++) {  //On parcours le tableau de la pièce
            for (j = 0; j < 3; j++) {
                try{
                if ((plateau[indice].getForme()[i][j] >= 1)) { //On teste si la case de la grille est remplie alors qu'on veut y placer une case
                    if (grille.getGrille()[x + i][y + j] >= 1) {
                        return false;
                    } //Retourne faux si on ne peut pas placer la pièce
                }
                }catch(IndexOutOfBoundsException e){
                    System.out.println("OUT OF BOUNDS");
                }
            }
        }
        return true; //Retourne vrai si on peut placer la pièce
    }

    public boolean deplacementPiece(int x, int y, int indice) throws IndexOutOfBoundsException {//pas mis
        int i = 0, j = 0;   //Déclaration des variables d'itération
        if (VerifPlacement(x, y, indice) == true) {   //On vérifie que la piece puisse être placée
            for (i = 0; i < 3; i++) {  //On parcours le tableau de la pièce
                for (j = 0; j < 3; j++) {
                    if(plateau[indice].getForme()[i][j] != 0){
                    grille.getGrille()[x + i][y + j] = plateau[indice].getForme()[i][j];    //On remplit les cases de la grille correcpondant à la pièce
                }
                }
            }
            return true;

        } else {
            System.out.println("La pièce ne pas être placée ici\n");    //Si la pièce ne peut pas être placée, on affiche un message d'erreur
        }
        return false;

    }

    public int choixDePiece() {  //Cette méthode permet de choisir la pièce à déplacer//pas mis
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        return choix;   //Retourne l'indice de la pièce à déplacer
    }

    public int couleurAleatoire(int valeurMax) {//Renvoie le nombre de couleurs de la pièce//Pas mis
        Random r = new Random();
        return 1 + r.nextInt(2); //Génère un entier aléatoire entre 1 et 2 compris

    }

    public void créerPiece(int coord) {  //Permet de créer une pièce et de l'ajouter sur le plateau//pas mis
        
        Random r = new Random();
        int valeur = r.nextInt(4); //Génère un nombre aléatoire entre 0 et 3 compris
        switch (valeur) {
            case 0: //Cas de la pièce I
                I p1 = new I(3, 5); //Crée une pièce de type I
                plateau[coord] = p1; //Place la nouvelle pièce dans la case du plateau correspondante
                break;
            case 1: //Cas de la piece T
                T p2 = new T(5, 10);
                plateau[coord] = p2;
                break;
            case 2: //Cas de la piece L
                L p3 = new L(4, 8);
                plateau[coord] = p3;
                break;
            case 3: //case de la piece Z
                Z p4 = new Z(5, 12);
                plateau[coord] = p4;
                break;
        }

    }

    //Getters
    public Piece[] getPlateau() {
        return plateau;
    }

}
