package takhte.nard;
 
import ij.ImagePlus;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel; 

class Mohre {
    int color;
    JLabel object;
    int width = 25;
    int heigth = 16;
    Stick stick;
    int number;
    Mohre(int color){
        this.color = color;
        ImagePlus ip = new ImagePlus();
        ip.setProcessor(new ImagePlus("a", new ImageIcon((color == 1 ? "./Image/blueCircle.png" : "./Image/redCircle.png")).getImage()).getProcessor().resize(width, heigth));
        object = new JLabel(new ImageIcon(ip.getImage()));        
    }
    void show(){
        Board.Takhte.add(object);
        object.setBounds(stick.stickObject.getX(), stick.stickObject.getY() + (stick.number < 13 ? 0 : 113) + (stick.number < 13 ? (25 - heigth / 2) : -1 * (25 - heigth / 2)) * (stick.getSize() - 1), width, heigth);
        object.setVisible(true);
    }
}
class Stick{
    ArrayList<Mohre> mohreha;
    JLabel stickObject;
    int number;
    int width = 25;
    int heigth = 130;
    Stick(int number){
        mohreha = new ArrayList<>();
        this.number = number;
        ImagePlus ip = new ImagePlus();
        ip.setProcessor(new ImagePlus("a", new ImageIcon("./Image/triangle1.png").getImage()).getProcessor().resize(width, heigth));
        if (number < 13) ip.getProcessor().rotate(180);
        stickObject = new JLabel(new ImageIcon(ip.getImage()));
        
        if (number < 13) stickObject.setBounds(10 + 50 * (number - 1) + (number > 6 ? 20 : 0), 10, 25, 130);
        else stickObject.setBounds(591 - (10 + 50 * (number - 13) + (number > 12 + 6 ? 20 : 0)), 10 + 130 + 30, 25, 130);
        
        stickObject.setSize(25, 130);
        stickObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Board.selected == null) {
                    Board.selected = Stick.this;
                }
                else if (Board.selected == Stick.this) {
                    Board.selected = null;
                } 
                else if (Board.selected.getSize() == 0) {
                    Board.selected = null;
                }
                else {
                    Board.move(Board.selected.number, Stick.this.number);
                    Board.selected = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    int getSize(){
        return mohreha.size();
    }
    int getColor(){
        return mohreha.get(0).color;
    }
    void add(Mohre mohre){
        mohreha.add(mohre);
        mohre.stick = this;
        mohre.number = getSize() + 1;
        this.hide();
        mohre.show();
        this.show();
    }

    void remove(int number){
        mohreha.remove(number);
    }
    
    void show(){
        Board.Takhte.add(stickObject);
        stickObject.setVisible(true);
    }
    void hide(){
        Board.Takhte.remove(stickObject);
        stickObject.setVisible(false);
    }
    
}

class Board {
    static ArrayList<Stick> sticks;
    static JFrame Takhte;
    static Stick selected;
    
    Board(JFrame Takhte){
        sticks = new ArrayList<>();
        Board.Takhte = Takhte; 
                
        for(int i = 0; i < 25; i++){
            sticks.add(new Stick(i));
        }
        firstSituation();
    }
    void firstSituation(){
        
        for (int i = 0; i < 5; i++) sticks.get(1).add(new Mohre(1));
        for (int i = 0; i < 3; i++) sticks.get(5).add(new Mohre(2));
        for (int i = 0; i < 5; i++) sticks.get(7).add(new Mohre(2));
        for (int i = 0; i < 2; i++) sticks.get(12).add(new Mohre(1));
        for (int i = 0; i < 2; i++) sticks.get(13).add(new Mohre(2));
        for (int i = 0; i < 5; i++) sticks.get(18).add(new Mohre(1));
        for (int i = 0; i < 3; i++) sticks.get(20).add(new Mohre(1));
        for (int i = 0; i < 5; i++) sticks.get(24).add(new Mohre(2));
        
        
        for(int i = 0; i < 25; i++){
            sticks.get(i).show();
        }
        
    }
    static void move(int stick1, int stick2){
        sticks.get(stick2).add(sticks.get(stick1).mohreha.get(sticks.get(stick1).getSize() - 1));
        sticks.get(stick1).remove(sticks.get(stick1).getSize() - 1);
    }

    void check(){
        
    }
    
}

public class Takhte extends javax.swing.JFrame {

    Board board = new Board(this);
    
    public Takhte() {
        this.setTitle("TakhteNard STEP #1 & STEP #2");
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Takhte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Takhte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Takhte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Takhte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Takhte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
