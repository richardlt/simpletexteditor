package miniEditeur;

public class Undo extends ActionReversion {

    /**
     * Constructor of the Undo class
     *
     * @param e the editor to link with
     */
    public Undo(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to undo the previous reversible action
     */
    public void executer() {
    	if(this.action==null && this.state==-1){
    		int index=super.editeurDeTexte.getAction().size()-1;
    		do{
    			index--;
    			if(index<0){this.action=null;break;}
    			this.action=super.editeurDeTexte.getAction().get(index);   			
    		}while(!(this.action.getClass()!=this.getClass() && this.action.getState()==1));
    	}
    	if(this.action!=null){
    		this.action.annuler();
        	this.state=1;
    	}
    }

	@Override
	public void annuler(){
		this.action.executer();
		this.state=0;
	}
    
    public String toString(){
		if(this.action!=null){return "Undo("+this.action.toString()+")";}
		else{return "Undo";}
    }

}
