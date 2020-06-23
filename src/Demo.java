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
import Dessin.*;
import java.awt.Color;

public class Demo {

    public static void main(Fenetre f, String [] args) {
        System.out.print("Arguments :");
        for (int i=0; i<args.length; i++)
            System.out.print(" " + args[i]);
        System.out.println("");

        f.tracer(new Point(10, 10));
        f.tracer(new Point(490, 10, Color.red));
        f.tracer(new Point(490, 150));
        f.tracer(new Point(10, 150, Color.red));
        
        f.tracer(new Segment(10,10,490,150));
        f.tracer(new Segment(10,150,490,10, Color.red));

        
        
        f.effacer(new Point(10, 10));
        f.effacer(new Point(490, 10));
        f.effacer(new Point(490, 150));
        f.effacer(new Point(10, 150));
        f.effacer(new Segment(10,10,490,150));
    }
}
