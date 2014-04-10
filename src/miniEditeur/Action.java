package miniEditeur;

public abstract class Action {
	
	protected Integer state;
	
	public Action(){
		this.state=-1;
	}
	
	public Integer getState(){
		return state;
	}
	
	public abstract void executer();
	
	public abstract void annuler();
	
	public abstract String toString(); 
		
}

