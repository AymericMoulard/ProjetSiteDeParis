package siteParis;


import java.util.LinkedList;
import java.util.Collection;


/**
 * 
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris. 
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>       
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :       
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un compétiteur  :       
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier {

   //* Attributs */
   private LinkedList<Joueur> joueurs;
   private LinkedList<Competition> competitions;
   private String passwordGestionnaire;
   //comptesDesJoueurs n'est pas utilis�, d�pend de la s�curit� qu'on veut
   //private LinkedList<long> comptesDesJoueurs;
   

	/**
	 * constructeur de <code>SiteDeParisMetier</code>. 
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire.   
	 * 
	 * @throws MetierException  levée 
	 * si le <code>passwordGestionnaire</code>  est invalide 
	 */
   public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {
      
      validitePasswordGestionnaire(passwordGestionnaire);
      
      this.joueurs = new LinkedList<Joueur>();
      this.competitions = new LinkedList<Competition>();
      this.passwordGestionnaire = passwordGestionnaire;
   
   }





	// Les méthodes du gestionnaire (avec mot de passe gestionnaire)



	/**
	 * inscrire un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
   public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {
   	
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
      for (Joueur j:joueurs){
         if ( j.getNom().equals(nom) && j.getPrenom().equals(prenom) || j.getPseudo().equals(pseudo) )
            throw new JoueurExistantException();
      }
      Joueur joueur = new Joueur(nom,prenom,pseudo);
      this.joueurs.add(joueur);  
      
      return "unPasswordUnique";
   }

	/**
	 * supprimer un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée 
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 * 
	 */
   public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {
   	
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
      boolean ilya=false;
      int position=0;
      
      for (Joueur j:joueurs){
         if ( j.getPseudo().equals(pseudo)) {
            ilya = true;
            position = joueurs.indexOf(j);}
            
      }
      if (!ilya) throw new JoueurInexistantException();
      
      this.joueurs.remove(position);
      
      
      return 0;
   }



	/**
	 * ajouter une compétition.  
	 * 
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser  
	 * @param competiteurs   les noms des différents compétiteurs de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom. 
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture 
	 * n'est pas instanciée ou est dépassée.
	 */
   public void ajouterCompetition(String nomCompetition, DateFrancaise dateCloture, String[] stringCompetiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
      
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      // La verification que le nom de la comp�tition est recevable s'effectue dans la classe Competition
      
      // Verification que la comp�tition n'est pas d�ja enregistr�e
      if (this.competitions.size() > 0){
         for (Competition c:competitions){
            if ( c.getNomCompetition().equals(nomCompetition) )
               throw new CompetitionExistanteException();
         }
      }
      // Verification que la liste des comp�titeurs fournie contient au moins deux comp�titeurs 
      if ( stringCompetiteurs == null ) throw new MetierException();
      if ( stringCompetiteurs.length <= 1  ) throw new CompetitionException();
      
      // V�rification qu'un comp�titeur n'est pas pr�sent deux fois dans la liste fournie (ne se bat pas contre lui-m�me)
      for (int i=0;i<stringCompetiteurs.length;i++){
         for (int j=0;j<stringCompetiteurs.length;j++){
            if ( (stringCompetiteurs[i]== stringCompetiteurs[j]) &&  (i!=j) )
               throw new CompetitionException();
         }
      }
      
      // V�rification que les dates sont coh�rentes   
      if ( dateCloture == null   ) throw new CompetitionException();  
      if ( dateCloture.estDansLePasse()   ) throw new CompetitionException();  
      
      
      LinkedList<Competiteur> competiteurs = new LinkedList<Competiteur>();
      for (String s: stringCompetiteurs)
         competiteurs.add( new Competiteur(s) );
         
          
      Competition competition = new Competition(nomCompetition,dateCloture);
      competition.setListeCompetiteurs(competiteurs);      
      //Ajout de la comp�tition cr��e dans la liste de comp�titions du site
      this.competitions.add(competition);
      
   }


	/**Competition avec vainqueur).  
	 * 
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 * 
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 * 
	 * @param competition   le nom de la compétition  
	 * @param vainqueur   le nom du vainqueur de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée 
	 * si le nom de la compétition ou du vainqueur est invalide, 
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition,
	 * si la date de clôture de la compétition est dans le futur.
	 * 
	 */	
   public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException  {
   
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
     
   }



	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à créditer  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
   public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
      
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
      validiteJoueur(nom, prenom, pseudo);
      existanceJoueur(nom, prenom, pseudo);
      
      // Verification que la somme � cr�diter est correcte
      if (sommeEnJetons < 0) {throw new MetierException();}
      //On cr�dite enfin
      getJoueur(nom, prenom, pseudo).crediterJoueur(sommeEnJetons);
      
   }


	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à débiter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides 
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 * 
	 */

   public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {
   
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
      validiteJoueur(nom, prenom, pseudo);
      existanceJoueur(nom, prenom, pseudo);
      
      // Verification que la somme � d�biter est correcte
      if (sommeEnJetons < 0) {throw new MetierException();}
      // Verification que le solde est suffisant
      if (getJoueur(nom, prenom, pseudo).getSommeEnJetons()<sommeEnJetons){throw new JoueurException();}
      //On cr�dite enfin      
      getJoueur(nom, prenom, pseudo).debiterJoueur(sommeEnJetons);      
   }



	/** 
	 * consulter les  joueurs.
	 * 
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris 

	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
   public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {
   
      validitePasswordGestionnaire(passwordGestionnaire);
      veracitePasswordGestionnaire(passwordGestionnaire);
      
      LinkedList<LinkedList <String>> listeAretourner = new LinkedList<LinkedList <String>>();
         for (Joueur j:joueurs) {
            LinkedList<String> attributsDuJoueur = new LinkedList<String>();
            attributsDuJoueur.add(j.getNom());
            attributsDuJoueur.add(j.getPrenom());
            attributsDuJoueur.add(j.getPseudo());
            // Conversion de la valeur du compte en jetons de long � String
            String compteDuJoueur = Long.toString(j.getSommeEnJetons());
            attributsDuJoueur.add(compteDuJoueur);
            // A continuer,je ne sais pas encore comment on g�re la mise sur un vainqueur
            
            // Une fois les attributs empaquet�s, on les ajoute � la liste g�n�rale
            listeAretourner.add(attributsDuJoueur); 
         }
      return listeAretourner;
   }








	// Les méthodes avec mot de passe utilisateur



	/**
	 * miserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 * 
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordJoueur  le password du joueur  
	 * @param miseEnJetons   la somme en jetons à miser  
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition  
	 * 
	 * @throws MetierException levée si la somme en jetons est négative.
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * @throws CompetitionException levée 
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition,
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé).
	 * @throws JoueurException   levée 
	 * si <code>pseudo</code> ou <code>password</code> sont invalides, 
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif).
	 */
   public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {
   
   }


    

	// Les méthodes sans mot de passe


	/**
	 * connaître les compétitions en cours.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom de la compétition,  </li>
	 *  <li>       la date de clôture de la compétition. </li>
	 *  </ul>
	 */
   public LinkedList <LinkedList <String>> consulterCompetitions(){
      return new LinkedList <LinkedList <String>>();
   } 

	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.  
	 * 
	 * @param competition   le nom de la compétition  
	 * 
	 * @throws CompetitionException   levée  
	 * si le nom de la compétition est invalide.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * 
	 * @return la liste des compétiteurs de la  compétition.
	 */
   public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
      validiteCompetition(competition);
      
      // L'existance de la comp�tition est v�rifi�e � l'interieur de "getCompetition" 
      LinkedList<Competiteur> listeCompetiteurs = getCompetition(competition).getListeCompetiteurs();
      
      // Construction puis remplissage de la liste � renvoyer
      LinkedList<String> listeCompetiteursString = new LinkedList<String>();
      for (Competiteur c:listeCompetiteurs) {
         listeCompetiteursString.add(c.getNomCompetiteur());
         }
         
      // on retourne la liste compos�e du nom des competiteurs   
      return listeCompetiteursString;
   }

	/**
	 * vérifier la validité du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code> est invalide.  
	 */
   protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
      if (passwordGestionnaire==null) throw new MetierException();
      if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
   }

   protected void veracitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
      if ( !this.passwordGestionnaire.equals(passwordGestionnaire) ) throw new MetierException();
   }
   
   protected void validiteJoueur(String nom, String prenom, String pseudo) throws JoueurException {
      if (nom == null || prenom == null || pseudo == null){ throw new JoueurException(); }
      if (!nom.matches("[A-Za-z-]{1,}") || !prenom.matches("[A-Za-z-]{1,}") || !pseudo.matches("[0-9A-Za-z]{4,}")){ throw new JoueurException();}
   }
   
   protected void existanceJoueur(String nom, String prenom, String pseudo) throws JoueurInexistantException {
      boolean joueurInexistant = true;
      for(Joueur j:joueurs){
         if (j.getNom().equals(nom) && j.getPrenom().equals(prenom) && j.getPseudo().equals(pseudo)){
            joueurInexistant = false;
         }
      }
      if (joueurInexistant) {throw new JoueurInexistantException(); }
   }
   
   // Validit� ou existance de la comp�tition
   protected void validiteCompetition(String nomCompetition) throws CompetitionException {
      if (nomCompetition == null) {throw new CompetitionException();}
      if (!nomCompetition.matches("[a-zA-Z0-9-._]{4,}")) {throw new CompetitionException();}
   }
   
/* protected void existanceCompetition(String nomCompetition) throws CompetitionInexistanteException {
      boolean competitionInexistante = true;
      for(Competition c:competitions){
         if (c.getNomCompetition().equals(nomCompetition)){
            competitionInexistante = false;
         }
      }
      if (competitionInexistante) {throw new CompetitionInexistanteException(); }
   }*/
   
   //getJoueur permet de r�cup�rer "joueur" � partir de son nom, pr�nom, pseudo
   protected Joueur getJoueur(String nom, String prenom, String pseudo) throws JoueurInexistantException {
      boolean joueurInexistant = true;
      for(Joueur j:joueurs){
         if (j.getNom().equals(nom) && j.getPrenom().equals(prenom) && j.getPseudo().equals(pseudo)){
            joueurInexistant = false;
            return j;
         }
      }
      if (joueurInexistant) {throw new JoueurInexistantException(); }
      return null;
   }
   
   // getCompetition permet de r�cup�rer "competition" �partir de son nom
   protected Competition getCompetition(String nomCompetition) throws CompetitionInexistanteException {
      boolean competitionInexistante = true;
      for(Competition c:competitions){
         if (c.getNomCompetition().equals(nomCompetition)){
            competitionInexistante = false;
            return c;
         }
      }
      if (competitionInexistante) {throw new CompetitionInexistanteException(); }
      return null;
   }
   

}



