package joueur;

import moteur.Enums;
import moteur.Parcelle;
import moteur.Plateau;
import javafx.geometry.Point3D;
import moteur.Enums.CouleurBot;
import moteur.objectifs.Objectif;
import moteur.objectifs.ObjectifParcelle;

import java.util.ArrayList;

/**
 * La classe du bot Jardinier
 */
public class BotParcelle extends Bot{

    int choixchange;
    /**
     * Le constructeur
     * @param couleur
     */
    public BotParcelle(CouleurBot couleur){
        super(couleur);
        choixchange=0;
    }

    private void switchchoix(){
        choixchange=(choixchange+1)%4;
    }


    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Les differents choix du Botparcelle.
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites){
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
            return Enums.Action.PIOCHEROBJECTIFPARCELLE;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)){
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)&& choixchange==0){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)&& choixchange>0){
            switchchoix();
            return Enums.Action.DEPLACERPANDA;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)){
            switchchoix();
            return Enums.Action.DEPLACERPANDA;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            return Enums.Action.DEPLACERJARDINIER;
        }
        return null;
    }

    /**
     * Renvoie un choix de coordonne pour la pose des parcelles parmis une liste de possibilités
     * @param possibilites
     * @param parcelle
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites,Parcelle parcelle) {
        Plateau plateau=Plateau.getInstance();
        for(int sizeMax=6;sizeMax>0;sizeMax--){
            for (Point3D coordonne : possibilites){
                if(plateau.getParcelleVoisineMemeCouleur(coordonne,parcelle).size()==sizeMax){
                    return coordonne;
                }
            }
        }
        return super.choixCoordonnePoseParcelle(possibilites,parcelle);
    }

    /**
     * Renvoie un choix de coordonne pour le deplacement du panda parmis une liste de possibilités.
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        for (int maxBambou=4;maxBambou>0;maxBambou--){
            for (Point3D coordonne : possibilites){
                if(Plateau.getInstance().getParcelle(coordonne).getNbBambou()>=maxBambou){
                    return coordonne;
                }
            }
        }
        return super.choixDeplacementPanda(possibilites);
    }

    /**
     * Renvoie un choix de parcelle parmis une liste de possibilités.
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites){
        if(this.getListObjectifs().isEmpty()) return super.choixParcellePioche(possibilites);
        Objectif objParcelle = choixObjectifPrioritaire();
        for(Parcelle parcelle:possibilites){
            if(objParcelle.getCouleur()==parcelle.getType()){
                return parcelle;
            }
        }
        return super.choixParcellePioche(possibilites);
    }

    /**
     * Renvoie un objectif ciblé en priorité par le joueur.
     * @return
     */
    @Override
    public Objectif choixObjectifPrioritaire() {
        for(Objectif objectif:getListObjectifs()){
            if(objectif instanceof ObjectifParcelle){
                return objectif;
            }
        }
        return super.choixObjectifPrioritaire();
    }

}
