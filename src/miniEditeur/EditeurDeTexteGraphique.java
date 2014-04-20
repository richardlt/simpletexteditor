package miniEditeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class EditeurDeTexteGraphique extends EditeurDeTexte {

    //protected static int startIndex, endIndex;
    /**
     * Constructor of the EditeurDeTexteGraphique's class
     */
    public EditeurDeTexteGraphique() {
        super();
        //this.startIndex = 0;
        //this.endIndex   = 0;
    }

    /**
     * Method called by the main function to print the UI
     */
    @Override
    public void run() {
        JFrame fenetre = new JFrame();
        fenetre.setSize(800, 600);
        fenetre.setPreferredSize(fenetre.getSize());
        fenetre.setLayout(new BorderLayout());

        // Configuration of action buttons 
        JButton buttonCopy = new JButton("Copier");
        buttonCopy.setSize(30, 20);
        buttonCopy.setBackground(Color.BLACK);
        buttonCopy.setForeground(Color.WHITE);
        JButton buttonCut = new JButton("Couper");
        buttonCut.setSize(30, 20);
        buttonCut.setBackground(Color.BLUE);
        buttonCut.setForeground(Color.WHITE);
        JButton buttonPaste = new JButton("Coller");
        buttonPaste.setSize(30, 20);
        buttonPaste.setBackground(Color.RED);
        JButton buttonUndo = new JButton("Annuler");
        buttonUndo.setSize(30, 20);
        buttonUndo.setBackground(Color.YELLOW);
        JButton buttonRedo = new JButton("Refaire");
        buttonRedo.setSize(30, 20);
        buttonRedo.setBackground(Color.ORANGE);
        JButton buttonSuppr = new JButton("Supprimer");
        buttonSuppr.setSize(30, 20);
        buttonSuppr.setBackground(Color.GRAY);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 6));
        JPanel gridbottom = new JPanel();
        final JTextPane tmpTextarea = new JTextPane();        
        JButton buttonEcrire        = new JButton("Écrire");
        buttonEcrire.setBackground(Color.GREEN);
        gridbottom.add(tmpTextarea);
        gridbottom.add(buttonEcrire);
        gridbottom.setLayout(new GridLayout(1, 2));
        

        // Give access to the editor object in the listeners
        final EditeurDeTexte edt = this;
        
        class MyJTextArea extends JTextArea {

            EditeurDeTexte e;

            public MyJTextArea(EditeurDeTexte e) {
                super();
                this.e = e;
            }

            public void actualiserContenu(){
            	this.setText("");
                this.setText(e.getZoneDeTravail().getBuffer().toString());                
                System.out.println("actualiserContenu() - "+e.getZoneDeTravail().getBuffer().toString());
            }
            
            public void actualiserSelection(){
            	e.setSelection(this.getSelectionStart(), this.getSelectionEnd()-this.getSelectionStart());
            }
        }

        final MyJTextArea zdt = new MyJTextArea(this);

        // Add of action listener on every button created up there
        buttonCopy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zdt.actualiserSelection();
        		Copier copier = new Copier(edt);edt.addAction(copier);
                copier.executer();
                zdt.actualiserContenu();
            }
        });

        buttonCut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
            	zdt.actualiserSelection();
            	Couper couper = new Couper(edt);edt.addAction(couper);
                couper.executer();                
                zdt.actualiserContenu();                
            }
        });

        buttonPaste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	Coller coller = new Coller(edt);edt.addAction(coller);
            	coller.executer();
                zdt.actualiserContenu();
            }
        });

        buttonSuppr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	Suppr suppr = new Suppr(edt);edt.addAction(suppr);
                suppr.executer();
                zdt.actualiserContenu();
            }
        });

        buttonUndo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	Undo undo = new Undo(edt);edt.addAction(undo);
                undo.executer();                
                zdt.actualiserContenu();
            }
        });
        
        buttonRedo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	Redo redo = new Redo(edt);edt.addAction(redo);
                redo.executer();
                zdt.actualiserContenu();
            }
        });

        grid.add(buttonCopy);
        grid.add(buttonCut);
        grid.add(buttonPaste);
        grid.add(buttonSuppr);
        grid.add(buttonUndo);
        grid.add(buttonRedo);

        zdt.setBackground(Color.WHITE);

        buttonEcrire.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	edt.setTampon(tmpTextarea.getText());
                Ecrire ecrire = new Ecrire(edt);edt.addAction(ecrire);
                ecrire.executer();                
                zdt.actualiserContenu();
            }
        });        
        
        /**
         * Constructor of the internal class MyKeyListener to handle writing events
         */
        class MyKeyListener implements KeyListener {

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
            	e.consume();
            }
        }

        MyKeyListener kl = new MyKeyListener();

        zdt.addKeyListener(kl);

        fenetre.add(grid, BorderLayout.NORTH);
        fenetre.add(zdt, BorderLayout.CENTER);
        fenetre.add(gridbottom, BorderLayout.SOUTH);
        fenetre.setVisible(true);
        fenetre.pack();
        fenetre.repaint();
    }

}
