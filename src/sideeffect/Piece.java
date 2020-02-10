/*
 * SIDE EFFECT Classe Pièce
 * Sulwen de la CROIX / Matthieu SERFATY
 * EPF Ecole d'ingénieur-e-s 27/01/2017
 */
package sideeffect;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author mserfaty
 */
public abstract class Piece {

    // Déclaration des attributs
    private int forme[][] = new int[3][3];
    private int nbBloc;
    private int nbPoint;
    private int position = 0;

    //Constructeur
    public Piece(int nbBloc, int nbPoint) {
        this.nbBloc = nbBloc;
        this.nbPoint = nbPoint;
    }

    @Override
    public String toString() {
        return "Piece{" + "nbBloc=" + nbBloc + ", nbPoint=" + nbPoint + ", position=" + position + '}';
    }

    
    public int[][] getForme() {
        return forme;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    //Déclaration des méhodes
    public void pivoter() {
        int i,j;
        int[][] tab = new int[3][3];
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                tab[i][j] = forme[i][j];
            }
        }
        forme[2][0] = tab[0][0];
        forme[2][1] = tab[1][0];
        forme[2][2] = tab[2][0];
        forme[0][2] = tab[2][2];
        forme[1][2] = tab[2][1];
        forme[0][1] = tab[1][2];
        forme[0][0] = tab[0][2];
        forme[1][0] = tab[0][1];
    }
    
    public void afficher(JLabel[][] tab){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                switch (forme[i][j]) {
                    case 2:
                        tab[i][j].setBackground(Color.BLUE);
                        break;
                    case 1:
                        tab[i][j].setBackground(Color.RED);
                        break;
                    default:
                        tab[i][j].setBackground(Color.DARK_GRAY);
                        break;
                }
            }
        }
    }

}
