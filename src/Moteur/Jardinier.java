package Moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du jardinier
 */
public class Jardinier extends Personnage {

    public Jardinier(Plateau p)
    {
        super(p);
    }

    public boolean PousserOuMangerBambou(Point3D p)
    {
        System.out.println("Jardinier en " + getCoord().getX() + ", " + getCoord().getY() + ", " + getCoord().getZ()+ " (Parcelle "+getPlateau().getParcelle((p)).getType()+")");
        return this.getPlateau().getParcelle(p).pousserBambou();
    }

}
