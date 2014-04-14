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

    protected static int startIndex, endIndex;
    /**
     * Constructor of the EditeurDeTexteGraphique's class
     */
    public EditeurDeTexteGraphique() {
        super();
        this.startIndex = 0;
        this.endIndex   = 0;
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
        
        class MyJTextArea extends JTextArea {

            EditeurDeTexte e;

            public MyJTextArea(EditeurDeTexte e) {
                super();
                this.e = e;
            }

            public void actualiserContenu() {
                this.setText(e.getZoneDeTravail().print());
            }
        }

        final MyJTextArea zdt = new MyJTextArea(this);

        // Add of action listener on every button created up there
        buttonCopy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!zdt.getSelectedText().equals("")) {
                    byte[] tmp = zdt.getSelectedText().getBytes();
                    edt.getZoneDeTravail().getPressePapier().setValue(new Buffer(tmp));
                    edt.setSelection(startIndex, endIndex);                    
                    Copier copier = new Copier(edt);
                    copier.executer();
                    edt.addAction(copier);
                    System.out.println("Contenu de la text area : "+edt.getZoneDeTravail().print());
                }
            }
        });

        buttonCut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                if (zdt.getSelectedText() != null) {
                    System.out.println(startIndex+" / "+endIndex);
                    byte[] tmp = zdt.getSelectedText().getBytes();
                    edt.getZoneDeTravail().getPressePapier().setValue(new Buffer(tmp));
                    edt.setSelection(startIndex, endIndex);                    
                    Couper couper = new Couper(edt);
                    couper.executer();
                    edt.addAction(couper);
                    zdt.actualiserContenu();
                    System.out.println("Contenu de la text area : "+edt.getZoneDeTravail().print());
                }
                
            }
        });

        buttonPaste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               if (edt.getZoneDeTravail().getPressePapier() != null) {
                    Coller coller = new Coller(edt);
                    coller.executer();
                    zdt.replaceSelection(zdt.getSelectedText());
                    edt.addAction(coller);
                    zdt.actualiserContenu();
                    System.out.println("Contenu de la text area : "+edt.getZoneDeTravail().print());
                }
            }
        });

        buttonUndo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Undo undo = new Undo(edt);
                undo.executer();
                edt.addAction(undo);
                zdt.actualiserContenu();
            }
        });

        buttonRedo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Redo redo = new Redo(edt);
                redo.executer();
                edt.addAction(redo);
                zdt.actualiserContenu();
            }
        });

        grid.add(buttonCopy);
        grid.add(buttonCut);
        grid.add(buttonPaste);
        grid.add(buttonUndo);
        grid.add(buttonRedo);

        zdt.setBackground(Color.WHITE);

        /**
         * Constructor of the internal class MyKeyListener to handle writing events
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
                    edt.setTampon("" + e.getKeyChar());
                    Ecrire ecrire = new Ecrire(edt);
                    ecrire.executer();
                    edt.addAction(ecrire);
                    zdt.actualiserContenu();
                }
            }
        }

        /**
         * Constructor of the internal class MyMouseListener to handle selection events
         */
        class MyMouseListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                startIndex  = zdt.getCaretPosition();
                endIndex    = zdt.getCaretPosition();
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
                    endIndex = zdt.getCaretPosition();
                    autoSwitchIndex();
                    edt.setSelection(startIndex, endIndex);
                }
            }

            public void autoSwitchIndex() {
                if (EditeurDeTexteGraphique.startIndex > EditeurDeTexteGraphique.endIndex) {
                    int tmp = EditeurDeTexteGraphique.startIndex;
                    EditeurDeTexteGraphique.startIndex = EditeurDeTexteGraphique.endIndex;
                    EditeurDeTexteGraphique.endIndex = tmp;
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
