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

            public void actualiserContenu() {
                this.setText(e.getZoneDeTravail().print());
                System.out.println(e.getZoneDeTravail().print());
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
                }
            }
        });

        buttonCut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                if (zdt.getSelectedText() != null) {
                    byte[] tmp = zdt.getSelectedText().getBytes();
                    edt.getZoneDeTravail().getPressePapier().setValue(new Buffer(tmp));
                    edt.setSelection(startIndex, endIndex);                    
                    Couper couper = new Couper(edt);
                    couper.executer();
                    edt.addAction(couper);
                    zdt.actualiserContenu();
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
                }
            }
        });

        buttonSuppr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Suppr suppr = new Suppr(edt);
                suppr.executer();
                edt.addAction(suppr);
                zdt.actualiserContenu();
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
        grid.add(buttonSuppr);
        grid.add(buttonUndo);
        grid.add(buttonRedo);

        zdt.setBackground(Color.WHITE);

        buttonEcrire.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                edt.setTampon(tmpTextarea.getText());
                edt.setSelection(startIndex, endIndex);
                Ecrire ecrire = new Ecrire(edt);
                ecrire.executer();
                edt.addAction(ecrire);
                zdt.actualiserContenu();
            }
        });        
        
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
                zdt.setText("");
            }
        }

        /**
         * Constructor of the internal class MyMouseListener to handle selection events
         */
        class MyMouseListener implements MouseListener {

            @Override
            // Get cursor position (insert action)
            public void mouseClicked(MouseEvent arg0) {
                startIndex  = zdt.getCaretPosition();
                endIndex    = startIndex;
                edt.setSelection(startIndex, endIndex); 
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
        fenetre.add(gridbottom, BorderLayout.SOUTH);
        fenetre.setVisible(true);
        fenetre.pack();
        fenetre.repaint();
    }

}
