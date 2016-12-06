package siteParis;

import java.util.Collection;
import java.util.LinkedList;

public class Competiteur {


   private String nomCompetiteur;
   
   public Competiteur(String nomCompetiteur) throws CompetitionException{
      
      if ( nomCompetiteur == null ) throw new CompetitionException();
      if ( !nomCompetiteur.matches("[0-9A-Za-z-_]{4,}")) throw new CompetitionException();
      
      this.nomCompetiteur = nomCompetiteur;
      }
   
   public String getNomCompetiteur(){
      return this.nomCompetiteur;
      }
}
