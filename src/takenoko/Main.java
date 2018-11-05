package takenoko;

import takenoko.joueur.BotJardinier;
import takenoko.joueur.BotPanda;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import takenoko.joueur.BotParcelle;
import takenoko.joueur.Joueur;

import java.util.ArrayList;

/**
 * La classe principal deroulement d'une partie entre tout les joueurs
 */

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        /*
        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        BotPanda botPanda = new BotPanda(CouleurBot.BLEU);
        BotParcelle botParcelle = new BotParcelle(CouleurBot.ROUGE);

        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);
        listeDesJoueurs.add(botParcelle);

        Affichage.setVerbose(true);

        Partie partie = new Partie();
        partie.jouer(listeDesJoueurs);

        Affichage.affichagePlateau();
        Affichage.affichageResultatsPartie(listeDesJoueurs);
        */
        SpringApplication.run(Main.class, args);


    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Partie partie, @Autowired BotJardinier botJardinier, @Autowired BotPanda botPanda, @Autowired BotParcelle botParcelle) {
        return args -> {
                deroulePartie(partie, botJardinier,botPanda,botParcelle);

        };
    }

    private void deroulePartie( Partie partie, BotJardinier botJardinier,BotPanda botPanda, BotParcelle botParcelle) {

        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        botJardinier.setCouleur(CouleurBot.VERT);
        botPanda.setCouleur(CouleurBot.BLEU);
        botParcelle.setCouleur(CouleurBot.ROUGE);

        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);
        listeDesJoueurs.add(botParcelle);

        Affichage.setVerbose(true);

        partie.jouer(listeDesJoueurs);
        Affichage.affichagePlateau();
        Affichage.affichageResultatsPartie(listeDesJoueurs);
    }
}
