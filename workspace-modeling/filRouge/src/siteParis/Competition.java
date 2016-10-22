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
	
	
	public Competition(String nomCompetition, DateFrancaise dateCloture) throws CompetitionException{
   
      if ( nomCompetition == null ) throw new CompetitionException();
      if ( !nomCompetition.matches("[0-9A-Za-z-._]{4,}")) throw new CompetitionException();        
       
   
		this.nomCompetition = nomCompetition;
		this.dateCloture = dateCloture;
		this.competiteurs = new LinkedList<Competiteur>();
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

	/**
	 * Getter of the property <tt>listeCompetiteurs</tt>
	 * @return   Returns the competiteurs.
	 * @uml.property   name="listeCompetiteurs"
	 */
	public LinkedList<Competiteur> getListeCompetiteurs() {
		return competiteurs;
	}


	







}
