/*
 * SIDE EFFECT Classe Joueur
 * Sulwen de la CROIX / Matthieu SERFATY
 * EPF Ecole d'ingénieur-e-s 27/01/2017
 */
package sideeffect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mserfaty
 */
public class Joueur {

    // Déclaration des attributs
    private int score;
    private String Pseudo;
    private String nomFichier = "sauvegarde.txt";
    private String meilleurScores = "scores.txt";

    //Constructeur
    public Joueur(String Pseudo) {
        this.score = 0;
        this.Pseudo = Pseudo;
    }

    // Déclaration des méthodes
    public void majScore(int Score) {
        score = Score;

    }

    public int getScore() {
        return score;
    }

    public void chargementGrille(Grille grilleJeu) { //permet de propager l'exception à la méthode appelante
        try {
            int i, j; //variables d'itération
            FileReader fich = new FileReader(nomFichier); //Ouvre le fichier contenant la grille
            BufferedReader br = new BufferedReader(fich); //Crée un curseur permettant de parcourir le fichier
            String ligne = br.readLine(); //Lit la ligne où se situe le curseur
            //String a = String.valueOf(ligne);
            //score = a.charAt(0);
            score = Integer.valueOf(ligne);
            ligne = br.readLine();
            for (i = 0; i < grilleJeu.getTaille(); i++) { //parcoure le fichier avec le nombre de cases exactes de la grille
                for (j = 0; j < grilleJeu.getTaille(); j++) {
                    int x = Integer.valueOf(ligne); //La variable x prend la valeur de l'entier sur la ligne
                    grilleJeu.getGrille()[i][j] = x; //La case de la grille prend la valeur de l'entier lu
                    ligne = br.readLine(); //Le curseur descend à la ligne en dessous
                }

            }
            fich.close(); //Ferme le fichier
        } catch (IOException e) {
            System.out.println("Partie sauvegardée introuvable");
        }

    }

    public void sauvegardeScore() {
        try {
            FileWriter fich = new FileWriter(meilleurScores, true);
            fich.write(Pseudo + System.lineSeparator() + score + System.lineSeparator());
            fich.close();
        } catch (IOException e) {
            System.out.println("fichier de sauvegarde de score introuvable");
        }

    }

    public void sauvegardeGrille(Grille grilleJeu) { //Sauvegarde la grille utilisateur

        try { //On exécute la méthode et on affiche un message 
            int i; //Déclaration des variables d'itération
            int j;
            FileWriter fich = new FileWriter(nomFichier); //Ouvre le fichier en mode écriture
            fich.write(score + System.lineSeparator());
            for (i = 0; i < grilleJeu.getTaille(); i++) { //Parcoure la grille
                for (j = 0; j < grilleJeu.getTaille(); j++) {
                    fich.write(grilleJeu.getGrille()[i][j] + System.lineSeparator()); //Ecrit l'entier correspondant à la case et reviens à la ligne
                }

            }

            fich.close(); //ferme le fichier
        } catch (IOException ex) {
            System.out.println("La grille n'a pas pu être sauvegardée");//Affiche un message d'erreur si le fichier n'a pas pu être ouvert
        }
    }

}
