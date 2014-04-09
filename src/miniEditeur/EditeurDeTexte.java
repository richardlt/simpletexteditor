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
		String result=tampon;
		tampon="";
		return result;
	}
	
	public void setTampon(String text){
		tampon=text;
	}
	
	public void setSelection(Integer position, Integer longueur){
		Selection s=zoneDeTravail.getSelection();
		if(position+longueur>this.zoneDeTravail.getBuffer().size()){
			if(position>this.zoneDeTravail.getBuffer().size()){
				s.setSelection(this.zoneDeTravail.getBuffer().size(), 0);
			}else{
				s.setSelection(position, this.zoneDeTravail.getBuffer().size()-position);
			}
		}else{
			s.setSelection(position, longueur);
		}
	}
		
	public String toString(){
		return zoneDeTravail.toString();
	}
	
	public void addAction(Action a){
		this.actionList.add(a);
	}

	public ArrayList<Action> getAction() {
		return this.actionList;
	}
	
}

