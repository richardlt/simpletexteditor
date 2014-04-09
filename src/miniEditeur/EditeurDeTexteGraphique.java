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

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 5));

        // Give access to the editor object in the listeners
        final EditeurDeTexte edt = this;

        // Add of action listener on every button created up there
        buttonCopy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Copier copier = new Copier(edt);
                copier.executer();
            }
        });

        buttonCut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Couper couper = new Couper(edt);
                couper.executer();
            }
        });

        buttonPaste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Coller coller = new Coller(edt);
                coller.executer();
            }
        });

        buttonUndo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Undo undo = new Undo(edt);
                undo.executer();
            }
        });

        buttonRedo.addActionListener(new ActionListener() {

            @Override
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

        
        class MyJTextArea extends JTextArea {
        	
        	EditeurDeTexte e;
        	
        	public MyJTextArea(EditeurDeTexte e){
        		super();
        		this.e=e;
        	}
        	
        	public void actualiserContenu(){
        		this.setText(e.getZoneDeTravail().getBuffer().toString());
        	}
        }
        
        final MyJTextArea zdt = new MyJTextArea(this);
        
        
        zdt.setBackground(Color.WHITE);

        /**
         * Constructor of the internal class MyKeyListener to handle writing
         * events
         */
        class MyKeyListener implements KeyListener {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_DELETE) {
                	setTampon(""+e.getKeyChar());
                	Ecrire ecrire = new Ecrire(edt);
                    ecrire.executer();
                    zdt.setText("");
                    zdt.actualiserContenu();
                }
            }

        }

        /**
         * Constructor of the internal class MyMouseListener to handle selection
         * events
         */
        class MyMouseListener implements MouseListener {

            private int startIndex, endIndex;

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startIndex = zdt.getCaretPosition();
            }

            // Get selected text in the text area
            @Override
            public void mouseReleased(MouseEvent e) {
                if (zdt.getSelectedText() != null) {
                    Selection selection = new Selection();
                    endIndex = zdt.getCaretPosition();
                    autoSwitchIndex(this.startIndex, this.endIndex);
                    selection.setSelection(startIndex, endIndex);
                    //System.out.println("indexDebut : "+this.startIndex+" / indexFin : "+this.endIndex);
                }
            }

            public void autoSwitchIndex(int startIndex, int endIndex) {
                if (startIndex > endIndex) {
                    int tmp = this.startIndex;
                    this.startIndex = this.endIndex;
                    this.endIndex = tmp;
                }
            }

        }

        MyMouseListener mml = new MyMouseListener();
        MyKeyListener kl = new MyKeyListener();

        zdt.addMouseListener(mml);
        zdt.addKeyListener(kl);

        fenetre.add(grid, BorderLayout.NORTH);
        fenetre.add(zdt, BorderLayout.CENTER);
        fenetre.setVisible(true);
        fenetre.pack();
        fenetre.repaint();
    }

}
