package champollion;
import java.util.*;
public class Intervention {
	private Date debut;
	private int duree;
	private int heureDebut;
	private TypeIntervention type;
    private UE matiere;
    private Enseignant intervenant;
    private Salle salle;

	public Intervention(Date debut, int duree, int heureDebut, TypeIntervention type, UE ue, Salle salle, Enseignant intervention) {
		super();
		this.debut = debut;
		this.duree = duree;
		this.heureDebut = heureDebut;
		this.type = type;
		this.matiere = ue;
		this.salle =salle;
		this.intervenant = intervenant;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}
	public TypeIntervention getType() {
		return type;
	}
	public void setType(TypeIntervention type) {
		this.type = type;
	}
	public UE getMatiere() {
		return matiere;
	}
	
	
}
