package miniEditeur;
import java.util.ArrayList;

public abstract class EditeurDeTexte extends Thread{
	
	private ArrayList<Action> actionList;
	private ZoneDeTravail zoneDeTravail;
	
	private String tampon;

	public EditeurDeTexte(){
		super();
		actionList=new ArrayList<Action>();
		zoneDeTravail=new ZoneDeTravail(this);
	}
	
	public abstract void run();
	
	public ZoneDeTravail getZoneDeTravail(){
		return zoneDeTravail;
	}
	
	public String getTampon(){
		String result=tampon.toString();
		tampon="";
		return result;
	}
	
	public void setTampon(String text){
		tampon=text;
	}
	
	public void setSelection(Integer start, Integer end){
		Selection s=zoneDeTravail.getSelection();
		s.setIndexDebut(start);
		s.setIndexFin(end);
	}
		
	public String toString(){
		return zoneDeTravail.toString();
	}
}

