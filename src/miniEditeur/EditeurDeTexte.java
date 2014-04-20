package miniEditeur;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class EditeurDeTexte extends Thread{
	
	protected ArrayList<Action> actionList;
	protected ArrayList<Action> record;
	private Boolean flagRecord;
	private ZoneDeTravail zoneDeTravail;	
	private String tampon;

	public EditeurDeTexte(){
		super();
		actionList=new ArrayList<Action>();
		zoneDeTravail=new ZoneDeTravail(this);
		record=new ArrayList<Action>();
		flagRecord=false;
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
		if(flagRecord && !a.getClass().getSimpleName().equals("Undo") && !a.getClass().getSimpleName().equals("Redo")){
			this.record.add(a);
		}
	}

	public ArrayList<Action> getAction() {
		return this.actionList;
	}

	public void startRecord() {
		this.record=new ArrayList<Action>();	
		this.flagRecord=true;
	}

	public void stopRecord() {
		this.flagRecord=false;		
	}

	public void replay() {
		for(Action a : record){
			Action copy=a.clone();
			this.addAction(copy);
			copy.executer();
		}
	}
	
}

