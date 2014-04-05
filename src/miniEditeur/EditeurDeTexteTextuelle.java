<<<<<<< HEAD
package miniEditeur;

import java.util.Scanner;

public class EditeurDeTexteTextuelle extends EditeurDeTexte{

	public EditeurDeTexteTextuelle(){
		super();		
	}

	public void run() {
		while(true){
			System.out.println("*******************************************************************************************************\n");
			System.out.println(super.toString()+"\n");			
			System.out.println("*******************************************************************************************************");
			Scanner sc = new Scanner(System.in);
			System.out.println("Que voulez vous faire ?");
			System.out.println("e : écrire du texte");
			System.out.println("ret : supprime la selection ou le caractere precedent");
			System.out.println("suppr : effacer la selection");
			System.out.println("s : selectionner du texte");
			System.out.println("cp : copier la selection (précisez la selection avant de copier)");
			System.out.println("p : coller la selection (précisez la selection avant de coller)");
			System.out.println("cu : couper la selection (précisez la selection avant de couper)");
			System.out.println("u : defaire");
			System.out.println("r : refaire");
			
			String str = sc.nextLine();
			
			
			if(str.equals("e")){
				Action a=new Ecrire(this);
				System.out.println("Ecrivez votre texte");
				String text = sc.nextLine();
				setTampon(text);
				a.executer();
			}else if(str.equals("ret")){	
				Action a=new RetourArriere(this);
				a.executer();
			}else if(str.equals("suppr")){	
				Action a=new Suppr(this);
				a.executer();
			}else if(str.equals("s")){	
				System.out.println("Ecrivez l'index de début de la sélection");
				Integer start = sc.nextInt();
				System.out.println("Ecrivez l'index de fin de la sélection");
				Integer end = sc.nextInt();
				setSelection(start, end);
			}else if(str.equals("cp")){
				Action a=new Copier(this);
				a.executer();
			}else if(str.equals("p")){
				Action a=new Coller(this);
				a.executer();
			}else if(str.equals("cu")){
				Action a=new Couper(this);
				a.executer();
			}else if(str.equals("u")){
				Action a=new Undo(this);
				a.executer();
			}else if(str.equals("r")){
				Action a=new Redo(this);
				a.executer();
			}
		}
	}

}

=======
package miniEditeur;

import java.util.Scanner;

public class EditeurDeTexteTextuelle extends EditeurDeTexte{

	public EditeurDeTexteTextuelle(){
		super();		
	}

	public void run() {
		while(true){
			System.out.println("*******************************************************************************************************\n");
			System.out.println(super.toString()+"\n");			
			System.out.println("*******************************************************************************************************");
			Scanner sc = new Scanner(System.in);
			System.out.println("Que voulez vous faire ?");
			System.out.println("e : écrire du texte");
			System.out.println("s : selectionner du texte");
			System.out.println("cp : copier la selection");
			System.out.println("p : coller la selection");
			System.out.println("cu : couper la selection");
			System.out.println("u : defaire");
			System.out.println("r : refaire");
			
			String str = sc.nextLine();
			
			
			if(str.equals("e")){
				Action a=new Ecrire(this);
				System.out.println("Ecrivez votre texte");
				String text = sc.nextLine();
				setTampon(text);
				a.executer();
			}else if(str.equals("s")){	
				System.out.println("Ecrivez l'index de début de la sélection");
				Integer start = sc.nextInt();
				System.out.println("Ecrivez l'index de fin de la sélection");
				Integer end = sc.nextInt();
				setSelection(start, end);
			}else if(str.equals("cp")){
				
			}else if(str.equals("p")){
				
			}else if(str.equals("cu")){
				
			}else if(str.equals("u")){
				
			}else if(str.equals("r")){
				
			}
		}
	}

}

>>>>>>> branch 'master' of https://github.com/richardlt/simpletexteditor.git
