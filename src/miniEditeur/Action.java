package miniEditeur;

public abstract class Action {
	
	protected Boolean state;
	
	public Action(){
		this.state=false;
	}
	
	public Boolean getState(){
		return state;
	}
	
	public abstract void executer();
	
	public abstract void annuler();
		
}

