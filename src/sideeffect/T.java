/*
 * SIDE EFFECT Sous - Classe de Pièce 
 * Forme T
 * Sulwen de la CROIX / Matthieu SERFATY
 * EPF Ecole d'ingénieur-e-s 27/01/2017
 */
package sideeffect;

import java.util.Random;

/**
 *
 * @author mserfaty
 */
public class T extends Piece{
    
    // Déclaration des attributs
    
    // Déclaration des méthodes
    
    // Constructeur
    public T(int nbBloc, int nbPoint) {//On représente un T dans un tableau 3*3
        super(nbBloc, nbPoint);
        Random r = new Random();
         int[][] tab = super.getForme();
         tab[0][0] = 1 +r.nextInt(2);;
         tab[1][0] = 1 +r.nextInt(2);;
         tab[2][0] = 1 +r.nextInt(2);;
         tab[0][1] = 0;
         tab[1][1] = 1 +r.nextInt(2);;
         tab[2][1] = 0;
         tab[0][2] = 0;
         tab[1][2] = 1 +r.nextInt(2);;
         tab[2][2] = 0;
    }
}

