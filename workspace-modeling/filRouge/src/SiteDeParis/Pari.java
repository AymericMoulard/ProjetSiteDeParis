package siteParis;

import java.util.Collection;
import java.util.LinkedList;


public class Pari {


   private String pseudo;
   private String vainqueur;
   private long sommeMise;
   
   
   public Pari(String pseudo,String vainqueur,long somme){
      
      this.pseudo = pseudo;
      this.vainqueur = vainqueur;
      this.sommeMise = somme;
   }
   
   public String getPseudo() {return pseudo;}
   public String getVainqueur() {return vainqueur;}
   public long getSommeMise() {return sommeMise;}

   
}
