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

    /**
     * Constructor of the EditeurDeTexteGraphique's class
     */
    public EditeurDeTexteGraphique() {
        super();
    }

    /**
     * Method called by the main function to print the UI
     */
    public void run() {
        JFrame fenetre = new JFrame();
        fenetre.setSize(800, 600);
        fenetre.setPreferredSize(fenetre.getSize());
        fenetre.setLayout(new BorderLayout());
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 8));
        
        // Configuration of action buttons 
        JButton buttonCopy = new JButton("Copier");
        JButton buttonCut = new JButton("Couper");
        JButton buttonPaste = new JButton("Coller");
        JButton buttonUndo = new JButton("Annuler");
        JButton buttonRedo = new JButton("Refaire");
        JButton buttonSuppr = new JButton("Supprimer");
        JButton buttonRet = new JButton("Retour Arr");
        JButton buttonRecord = new JButton("Record");
        JButton buttonReplay = new JButton("Replay");
        
        grid.add(buttonCopy);
        grid.add(buttonCut);
        grid.add(buttonPaste);
        grid.add(buttonSuppr);
        grid.add(buttonRet);
        grid.add(buttonUndo);
        grid.add(buttonRedo);
        grid.add(buttonRecord);
        grid.add(buttonReplay);
              
        
        JPanel gridbottom = new JPanel();
        gridbottom.setLayout(new GridLayout(1, 2));
        final JTextPane tmpTextarea = new JTextPane();     
        tmpTextarea.setBackground(Color.lightGray);
        JButton buttonEcrire        = new JButton("Ecrire");        
        gridbottom.add(tmpTextarea);
        gridbottom.add(buttonEcrire);
        

        // Give access to the editor object in the listeners
        final EditeurDeTexte edt = this;
        
        class MyJTextArea extends JTextArea {

            EditeurDeTexte e;

            public MyJTextArea(EditeurDeTexte e) {
                super();
                this.e = e;
            }

            public void actualiserContenu(){
            	this.setText(e.getZoneDeTravail().getBuffer().toString());                
            }
            
            public void actualiserSelection(){
            	e.setSelection(this.getSelectionStart(), this.getSelectionEnd()-this.getSelectionStart());
            }
        }

        final MyJTextArea zdt = new MyJTextArea(this);
        zdt.setBackground(Color.WHITE);
        
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
        
        buttonRecord.addActionListener(new ActionListener() {
            private Boolean recordState=false;
            public void actionPerformed(ActionEvent e) {
            	if(!recordState){
            		edt.startRecord();
                	recordState=true;
                	((JButton) e.getSource()).setText("Stop");
            	}else{
            		edt.stopRecord();
                	recordState=false;                	
                	((JButton) e.getSource()).setText("Record");
            	}
            }
        }); 
        
        buttonReplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	edt.replay();
            	zdt.actualiserContenu();
            }
        }); 
        
        buttonRet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	zdt.actualiserSelection();
            	RetourArriere ret = new RetourArriere(edt);edt.addAction(ret);
            	ret.executer();                
                zdt.actualiserContenu();
            }
        });
        
        /**
         * Constructor of the internal class MyKeyListener to handle writing events
         */
        class MyKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e){}
            public void keyReleased(KeyEvent e){}
            public void keyTyped(KeyEvent e){e.consume();}
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
