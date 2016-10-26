package siteParis;

import java.util.Collection;
import java.util.LinkedList;

// Commentaire pour git
public class Competiteur {


   private String nomCompetiteur;
	private LinkedList<Joueur> joueurs;
	/** 
	 * @uml.property name="competition1"
	 * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite" inverse="competiteur1:siteParis.Competition"
	 * @uml.association name="participe à"
	 */
	/** 
	 * @uml.property name="listeCompetitions"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="competiteur:siteParis.Competition"
	 * @uml.association name="participe"
	 */
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
