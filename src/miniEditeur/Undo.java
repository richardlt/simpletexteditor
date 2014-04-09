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
    	if(this.action==null){
    		int index=super.editeurDeTexte.getAction().size()-1;
    		do{
        		if(index<0){this.action=null;break;}
    			this.action=super.editeurDeTexte.getAction().get(index);
    			index--;
    		}while(!(this.action.getClass()!=this.getClass() && this.action.getState()==true));
    	}
    	if(this.action!=null){
    		this.action.annuler();
        	this.state=true;
    	}
    }

	@Override
	public void annuler(){
		this.action.executer();
		this.state=false;
	}

}
