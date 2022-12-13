package champollion;
import java.util.*;
public class Enseignant extends Personne {
    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
	private ArrayList<Intervention> lesInterventions = new ArrayList<>(); 
	//private ArrayList<UE> lesEnseignements;
	private ArrayList <ServicePrevu> lesServicesPrevu = new ArrayList<>();
    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
    	int heuresPrevues = 0;
    	
        for (ServicePrevu s : lesServicesPrevu) {
        	heuresPrevues += s.getVolumeCM()*1.5;
        	heuresPrevues += s.getVolumeTD();
        	heuresPrevues += s.getVolumeTP()*0.75;

        	
        }
        return Math.round( heuresPrevues);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
    	int heuresPrevuesUE =0;
    	
    	for (ServicePrevu s : lesServicesPrevu) {
    		if(s.getUe().equals(ue)) {
    			heuresPrevuesUE += s.getVolumeCM()*1.5;
            	heuresPrevuesUE += s.getVolumeTD();
            	heuresPrevuesUE += s.getVolumeTP()*0.75;
    		}
    	}
    	return Math.round(heuresPrevuesUE);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
    	 boolean isPresent = false;
         for(ServicePrevu s : lesServicesPrevu){
             if(s.getUe().equals(ue)){
            	 isPresent = true;
                 s.setVolumeCM(s.getVolumeCM()+volumeCM);
                 s.setVolumeTD(s.getVolumeTD()+volumeTD);
                 s.setVolumeTP(s.getVolumeTP()+volumeTP);
             }
         }
         if(!isPresent){
             lesServicesPrevu.add(new ServicePrevu( volumeCM, volumeTD, volumeTP, ue));
         }
         
    }
    
    public void ajouterIntervention(Intervention inter)  {
    	lesInterventions.add(inter);
    }
    
    public int resteAPlanifier(UE ue, TypeIntervention type)  {
        boolean isOk = false;
        for(ServicePrevu s1 : lesServicesPrevu){
            if(s1.getUe().equals(ue)){
                isOk = true;
            }
        }
        
        if(!isOk){
            throw new IllegalArgumentException("Ce professeur n'enseigne pas dans cet ue");
        }

        int nbHeureUe = 0;
        int nbHeurePlan = 0;
        for(ServicePrevu s2 : lesServicesPrevu){
            if(s2.getUe().equals(ue)){
                switch (type) {
                    case CM:
                    	nbHeureUe = s2.getVolumeCM();
                    case TD:
                    	nbHeureUe = s2.getVolumeTD();
                    case TP:
                    	nbHeureUe = s2.getVolumeTP();
                    default:
                        break;
                }
            }
        }
        for(Intervention i : lesInterventions){
            if(i.getMatiere().equals(ue) && i.getType()==type){
            	nbHeurePlan = nbHeurePlan + i.getDuree();
            }
        }
        return nbHeureUe-nbHeurePlan ;
    }
    
    
    public boolean enSousService(){
        return this.heuresPrevues()<192;
    }

    
    
    

}
