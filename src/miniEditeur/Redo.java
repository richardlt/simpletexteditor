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
	    		if(index<0){this.action=null;break;}
	    		this.action=super.editeurDeTexte.getAction().get(index);
				index--;
			}while(!(this.action.getClass()!=this.getClass() && this.action.getState()==false));
    	}
    	if(this.action!=null){
    		this.action.executer();
        	this.state=true;
    	}
    }

	@Override
	public void annuler() {
		this.action.annuler();
		this.state=false;
	}

}
