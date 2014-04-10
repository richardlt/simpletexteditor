package miniEditeur;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class EditeurDeTexte extends Thread{
	
	protected ArrayList<Action> actionList;
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
		String temp="Action : ";
		Iterator<Action> it=this.actionList.iterator();
		while(it.hasNext()){
			temp+=it.next().toString()+" - ";
		}
		return temp+"\n"+zoneDeTravail.toString();
	}
	
	public void addAction(Action a){
		this.actionList.add(a);
	}

	public ArrayList<Action> getAction() {
		return this.actionList;
	}
	
}

