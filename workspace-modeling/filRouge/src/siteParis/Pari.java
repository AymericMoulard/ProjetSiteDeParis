package siteParis;

import java.util.Collection;
import java.util.LinkedList;


public class Pari {


   private String pseudo;
   private String vainqueur;
   private long sommeMise;
   
   
   public Pari(pseudo,vainqueur,somme){
      
      this.pseudo = pseudo;
      this.vainqueur = vainqueur;
      this.sommeMise = somme;
   }
   
   public String getPsuedo() {return pseudo;}
   public String getVanqueur() {return vanqueur;}
   public long getSommeMise() {return sommeMise;}

   
}
