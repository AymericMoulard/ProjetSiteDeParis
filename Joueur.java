package siteParis;

import java.util.Collection;
import java.util.LinkedList;


public class Joueur {

	/**
	 * @uml.property  name="nom"
	 */
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
      
   
	/**
	 * Getter of the property <tt>nom</tt>
	 * @return  Returns the nom.
	 * @uml.property  name="nom"
	 */
	public String getNom() {
		return nom;
	}
   

	/**
	 * Setter of the property <tt>nom</tt>
	 * @param nom  The nom to set.
	 * @uml.property  name="nom"
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @uml.property  name="prenom"
	 */


	/**
	 * Getter of the property <tt>prenom</tt>
	 * @return  Returns the prenom.
	 * @uml.property  name="prenom"
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter of the property <tt>prenom</tt>
	 * @param prenom  The prenom to set.
	 * @uml.property  name="prenom"
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @uml.property  name="pseudo"
	 */


   /**
	 * Getter of the property <tt>nom</tt>
	 * @return  Returns the nom.
	 * @uml.property  name="nom"
	 */
	public String getPseudo() {
		return pseudo;
	}
   

	/**
	 * Setter of the property <tt>nom</tt>
	 * @param nom  The nom to set.
	 * @uml.property  name="nom"
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


}
