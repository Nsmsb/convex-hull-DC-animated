/*
Dessin - package de visualisation pas à pas d'algorithmes de dessin
Copyright (C) 2009 Guillaume Huard
Ce programme est libre, vous pouvez le redistribuer et/ou le modifier selon les
termes de la Licence Publique Générale GNU publiée par la Free Software
Foundation (version 2 ou bien toute autre version ultérieure choisie par vous).

Ce programme est distribué car potentiellement utile, mais SANS AUCUNE
GARANTIE, ni explicite ni implicite, y compris les garanties de
commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
Licence Publique Générale GNU pour plus de détails.

Vous devez avoir reçu une copie de la Licence Publique Générale GNU en même
temps que ce programme ; si ce n'est pas le cas, écrivez à la Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
États-Unis.

Contact: Guillaume.Huard@imag.fr
         ENSIMAG - Laboratoire LIG
         51 avenue Jean Kuntzmann
         38330 Montbonnot Saint-Martin
*/
package Dessin;

public interface Fenetre {
    /* Methodes permettant de tracer ou effacer un objet graphique dans la
     * fenetre associee. Lors de l'appel de ces methodes, une pause est
     * effectuee apres avoir trace ou efface l'objet donne.
     * Remarque : on ne peut effacer qu'un objet graphique identique a un objet
     * graphique deja trace (excepte pour la couleur qui peut etre quelconque
     */
    public void tracer(ObjetGraphique o);
    public void effacer(ObjetGraphique o);

    /* Methodes permettant de tracer ou effacer un objet graphique dans la
     * fenetre associee sans marquer de pause apres l'operation. Ces methodes
     * s'utilisent de la meme maniere que leurs homologue marquant une pause et
     * sont compatibles avec elles (on peut effacerSansDelai un objet graphique
     * trace avec delai et inversement)
     */
    public void tracerSansDelai(ObjetGraphique o);
    public void effacerSansDelai(ObjetGraphique o);

    /* Methodes permettant de connaitre les dimensions de la fenetre de dessin
     */
    public int largeur();
    public int hauteur();
}
