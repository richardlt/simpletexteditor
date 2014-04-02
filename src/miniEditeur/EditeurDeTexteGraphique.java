package miniEditeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.dnd.DragGestureListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EditeurDeTexteGraphique extends EditeurDeTexte{

	public EditeurDeTexteGraphique(){
		super();
	}

	public void run() {
		JFrame fenetre = new JFrame();
		fenetre.setSize(800, 600);
		fenetre.setPreferredSize(fenetre.getSize());
		fenetre.setLayout(new BorderLayout());
		
		JButton buttonCopy 	= new JButton("Copier"); 	buttonCopy.setSize(30, 20); 	buttonCopy.setBackground(Color.BLACK); 	buttonCopy.setForeground(Color.WHITE);
		JButton buttonCut 	= new JButton("Couper"); 	buttonCut.setSize(30, 20);		buttonCut.setBackground(Color.BLUE);	buttonCut.setForeground(Color.WHITE);
		JButton buttonPaste = new JButton("Coller"); 	buttonPaste.setSize(30, 20);	buttonPaste.setBackground(Color.RED);
		JButton buttonUndo 	= new JButton("Annuler"); 	buttonUndo.setSize(30, 20);		buttonUndo.setBackground(Color.YELLOW);
		JButton buttonRedo 	= new JButton("Refaire"); 	buttonRedo.setSize(30, 20);		buttonRedo.setBackground(Color.ORANGE);
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(1, 5));
		
		// Rendre l'accès à l'éditeur de texte graphique dans les listeners
		final EditeurDeTexte edt = this;
		
		buttonCopy.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Copier copier = new Copier(edt);
				copier.executer();				
			}
		});
		
		buttonCut.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Couper couper = new Couper(edt);
				couper.executer();
			}
		});
		
		buttonPaste.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Coller coller = new Coller(edt);
				coller.executer();
			}
		});
		
		buttonUndo.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Undo undo = new Undo(edt);
				undo.executer();
			}
		});
		
		buttonRedo.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Redo redo = new Redo(edt);
				redo.executer();
			}
		});
		
		
		grid.add(buttonCopy);
		grid.add(buttonCut);
		grid.add(buttonPaste);
		grid.add(buttonUndo);
		grid.add(buttonRedo);
		
		final JTextArea zdt = new JTextArea();	zdt.setBackground(Color.WHITE);
		
		class MyMouseListener implements MouseListener {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}

			// Get selected text in the text area
			public void mouseReleased(MouseEvent e) {
				if(zdt.getSelectedText() != null)
					System.out.println(zdt.getSelectedText());
			}
		};
		
		MyMouseListener mml = new MyMouseListener();
		
		zdt.addMouseListener(mml);
				
		fenetre.add(grid, BorderLayout.NORTH);
		fenetre.add(zdt, BorderLayout.CENTER);
		fenetre.setVisible(true);
		fenetre.pack();
		fenetre.repaint();	
	}

}

