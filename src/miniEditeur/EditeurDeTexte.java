package miniEditeur;
import java.util.Set;
import java.util.HashSet;

public abstract class EditeurDeTexte extends Thread
{
	
	private Set<Action> action;
	private ZoneDeTravail zoneDeTravail;

	public EditeurDeTexte(){
		super();
	}
	
}

