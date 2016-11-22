package siteParis;

import java.util.Collection;
import java.util.LinkedList;

public class Competiteur {


   private String nomCompetiteur;
	private LinkedList<Joueur> joueurs;
	private LinkedList<Competition> listeCompetitions;
   
   public Competiteur(String nomCompetiteur) throws CompetitionException{
      
      if ( nomCompetiteur == null ) throw new CompetitionException();
      if ( !nomCompetiteur.matches("[0-9A-Za-z-_]{4,}")) throw new CompetitionException();        
      
      
      this.nomCompetiteur = nomCompetiteur;
      this.listeCompetitions = new LinkedList<Competition>();
      this.joueurs = new LinkedList<Joueur>();
      }
   
   public String getNomCompetiteur(){
      return this.nomCompetiteur;
      }
   


}
