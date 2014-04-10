package miniEditeur;

public class Redo extends ActionReversion {
    
    /**
     * Constructor of the Redo class
     *
     * @param e the editor to link with
     */
    public Redo(EditeurDeTexte e) {
        super(e);
    }

    /**
     * Method that send an order to redo the next action
     */
    public void executer() {
    	if(this.action==null){
    		int index=super.editeurDeTexte.getAction().size()-1;
	    	do{
	    		index--;
	    		System.out.println(index);
	    		if(index<0){this.action=null;break;}
	    		this.action=super.editeurDeTexte.getAction().get(index);
			}while(!(this.action.getClass().getSimpleName().equals("Undo") && this.action.getState()==1));
    	}
    	if(this.action!=null){
    		this.action.annuler();
        	this.state=1;
    	}
    }

	@Override
	public void annuler() {
		this.action.executer();
		this.state=0;
	}
    
    public String toString(){
    	if(this.action!=null){return "Redo("+this.action.toString()+")";}
		else{return "Redo";}
    }

}
