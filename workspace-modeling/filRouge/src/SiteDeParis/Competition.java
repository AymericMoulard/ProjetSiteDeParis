package siteParis;


import java.util.LinkedList;


public class Competition {


	private String nomCompetition;
	private DateFrancaise dateCloture;
   
   private LinkedList<Competiteur> competiteurs;
   protected LinkedList<Pari> paris;
	
	
	public Competition(String nomCompetition, DateFrancaise dateCloture, String[] stringCompetiteurs) throws CompetitionException{
   
      if ( nomCompetition == null ) throw new CompetitionException();
      if ( !nomCompetition.matches("[0-9A-Za-z-._]{4,}")) throw new CompetitionException();        
       
      // Verification que la liste des compétiteurs fournie contient au moins deux compétiteurs 
      if ( stringCompetiteurs.length <= 1  ) throw new CompetitionException();
      
      // Vérification que les dates sont cohérentes   
      if ( dateCloture == null   ) throw new CompetitionException();  
      if ( dateCloture.estDansLePasse()   ) throw new CompetitionException();
      
      // Vérification qu'un compétiteur n'est pas présent deux fois dans la liste fournie (ne se bat pas contre lui-même)
      for (int i=0;i<stringCompetiteurs.length;i++){
         for (int j=0;j<stringCompetiteurs.length;j++){
            if ( (stringCompetiteurs[i]== stringCompetiteurs[j]) &&  (i!=j) )
               throw new CompetitionException();
         }
      }

		this.competiteurs = new LinkedList<Competiteur>();      
      for (String s: stringCompetiteurs) {
         this.competiteurs.add( new Competiteur(s) );
         }
         
		this.nomCompetition = nomCompetition;
		this.dateCloture = dateCloture;
      this.paris = new LinkedList<Pari>();
	}
   
	public String getNomCompetition() {
		return nomCompetition;
	}
   public DateFrancaise getDateCloture(){
      return dateCloture;
   }


	public void setListeCompetiteurs(LinkedList<Competiteur> listeCompetiteurs) {
		competiteurs = listeCompetiteurs;
	}
   
   public void setListeParis(LinkedList<Pari> listeParis) {
		paris = listeParis;
	}

   public void ajouterPari(Pari pari){
      this.paris.add(pari);
   }

	public LinkedList<Competiteur> getListeCompetiteurs() {
		return competiteurs;
	}

   protected LinkedList<Pari> getListeParis() {
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



   protected long getSommePourCompetiteur(String nomCompetiteur){
      long somme = 0;
      for (Pari p:paris){
         if (p.getVainqueur().equals(nomCompetiteur))
           somme += p.getSommeMise(); 
      }
      return somme;
   
   }
   
    protected long getSommeTotal(){
      long somme = 0;
      for (Pari p:paris){
         somme += p.getSommeMise(); 
      }
      return somme;
   
   }




}
