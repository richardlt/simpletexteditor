package miniEditeur;
import java.util.ArrayList;

public abstract class EditeurDeTexte extends Thread{
	
	private ArrayList<Action> actionList;
	private ZoneDeTravail zoneDeTravail;

	public EditeurDeTexte(){
		super();
		actionList=new ArrayList<Action>();
		zoneDeTravail=new ZoneDeTravail(this);
	}
	
	public abstract void run();
	
	public ZoneDeTravail getZoneDeTravail(){
		return zoneDeTravail;
	}
}

