package siteParis;

import java.util.Collection;
import java.util.LinkedList;


public class Joueur {

	private String nom;
	private String prenom;
	private String pseudo;
   private long sommeEnJetons;

   
   public Joueur(String nom, String prenom, String pseudo) throws JoueurException{
      if ( nom == null || prenom == null || pseudo == null ) throw new JoueurException();
      if ( !nom.matches("[A-Za-z-]{1,}") || !prenom.matches("[A-Za-z-]{1,}") || !pseudo.matches("[0-9A-Za-z]{4,}") )
         throw new JoueurException();        
         
      this.nom = nom; 
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.sommeEnJetons = 0;
   }
      
   
	public String getNom() {
		return nom;
	}
   

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getPseudo() {
		return pseudo;
	}
   

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
   
   protected void crediterJoueur(long creditEnJetons) {
      this.sommeEnJetons += creditEnJetons;
   }
   
   protected void debiterJoueur(long debitEnJetons) {
      this.sommeEnJetons -= debitEnJetons;
   }
   
   public long getSommeEnJetons() {
      return this.sommeEnJetons;
   }
   
}
