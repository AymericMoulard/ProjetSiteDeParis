package siteParis;


import java.util.LinkedList;


public class Competition {

	
	/*private LinkedList<Competiteur> listeCompetiteurs;
	
	private DateFrancaise dateCloture;
	*/

	/**
	 * @uml.property  name="nomCompetition"
	 */
	private String nomCompetition;
	private DateFrancaise dateCloture;
   
   private LinkedList<Competiteur> competiteurs;
   private LinkedList<Pari> paris;
	
	
	public Competition(String nomCompetition, DateFrancaise dateCloture) throws CompetitionException{
   
      if ( nomCompetition == null ) throw new CompetitionException();
      if ( !nomCompetition.matches("[0-9A-Za-z-._]{4,}")) throw new CompetitionException();        
       
   
		this.nomCompetition = nomCompetition;
		this.dateCloture = dateCloture;
		this.competiteurs = new LinkedList<Competiteur>();
      this.paris = new LinkedList<Pari>();
	}
   
	public String getNomCompetition() {
		return nomCompetition;
	}
   public DateFrancaise getDateCloture(){
      return dateCloture;
   }





	/**
	 * Setter of the property <tt>listeCompetiteurs</tt>
	 * @param listeCompetiteurs  The competiteurs to set.
	 * @uml.property  name="listeCompetiteurs"
	 */
	public void setListeCompetiteurs(LinkedList<Competiteur> listeCompetiteurs) {
		competiteurs = listeCompetiteurs;
	}
   
   public void setListeParis(LinkedList<Pari> listeParis) {
		paris = listeParis;
	}

   public void ajouterPari(Pari pari){
      this.paris.add(pari);
   }

	/**
	 * Getter of the property <tt>listeCompetiteurs</tt>
	 * @return   Returns the competiteurs.
	 * @uml.property   name="listeCompetiteurs"
	 */
	public LinkedList<Competiteur> getListeCompetiteurs() {
		return competiteurs;
	}

   public LinkedList<Pari> getListeParis() {
		return paris;
	}
	
   
   
   
      protected void validiteCompetiteur(String nomCompetiteur) throws CompetitionException {
         if (nomCompetiteur == null) {throw new CompetitionException();}
         if (!nomCompetiteur.matches("[a-zA-Z0-9-._]{4,}")) {throw new CompetitionException();}
      }
      
     protected void existanceCompetiteur(String nomCompetiteur) throws CompetitionException {
         boolean competiteurInexistante = true;
         for(Competiteur c:competiteurs){
            if (c.getNomCompetiteur().equals(nomCompetiteur)){
               competiteurInexistante = false;
            }
         }
         if (competiteurInexistante) {throw new CompetitionException(); }
      }



   private long getSommePourCompetiteur(String nomCompetiteur){
      long somme = 0;
      for (Pari p:paris){
         if (p.getVainqueur().equals(nomCompetiteur))
           somme += p.getSommeMise(); 
      }
      return somme;
   
   }
   
    private long getSommeTotal(){
      long somme = 0;
      for (Pari p:paris){
         somme += p.getSommeMise(); 
      }
      return somme;
   
   }




}
