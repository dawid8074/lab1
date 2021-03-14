import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.*;


class Surface extends JPanel implements ActionListener {

    private final int DELAY = 1500;
    private Timer timer;
    int punkty_chmury[][] = {{ 60, 50 }, { 170, 50 }, { 170, 100 }, { 60, 100 }, { 60, 50 }};
    int przesuniecie1;
    int przesuniecie2;
    Random rng= new Random();

    public Surface() {

        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {

        return timer;
    }
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setPaint(new Color(135, 206, 230));                    //trawa
        g2d.fillRect(0, 0, 100000, 100000);
        g2d.setRenderingHints(rh);
        g2d.setPaint(new Color(00, 80, 00));                    //trawa
        g2d.fillRect(0, 570, 100000, 100000);
        g2d.setPaint(new Color(138, 69, 19));                    //pień drzewa 1
        g2d.fillRect(560, 340, 50, 280);
        g2d.setPaint(new Color(34, 139, 34));                    //korona drzewa 1
        g2d.fill(new Ellipse2D.Double(485, 160, 200, 240));
        g2d.setPaint(new Color(138, 69, 19));                   //pień drzewa 2
        g2d.fillRect(190, 340, 50, 280);
        g2d.setPaint(new Color(34, 139, 34));                   //korona drzewa 2
        g2d.fill(new Ellipse2D.Double(115, 160, 200, 240));
        g2d.setPaint(new Color(255, 255, 10));                   //słońce
        g2d.fillOval(1150,-140, 250, 250);
        przesuniecie1=rng.nextInt(10);
        for (int i=0; i<15; i++)
        {
            if (i<8)
                g2d.drawLine(1125+(10*i)-(3*i)+przesuniecie1,1+(10*i)+(3*i)+przesuniecie1,1050+(i*15)-(3*i)+przesuniecie1,1+(15*i)+(3*i)+przesuniecie1);
            else
                g2d.drawLine(1125+(10*i)-(45-(3*i))+przesuniecie1,1+(10*i)+(45-(3*i))+przesuniecie1,1050+(i*15)-(45-(3*i))+przesuniecie1,1+(15*i)+(45-(3*i))+przesuniecie1);
        }


        g2d.setPaint(new Color(0, 206, 235));                 //chmura
        przesuniecie1=rng.nextInt(1000);
        GeneralPath chmura= new GeneralPath();
        chmura.moveTo(punkty_chmury[0][0]+przesuniecie1,punkty_chmury[0][1]);
        chmura.quadTo(105+rng.nextInt(40)-20+przesuniecie1,rng.nextInt(40)-20,punkty_chmury[1][0]+przesuniecie1,punkty_chmury[1][1]);
        chmura.quadTo(220+rng.nextInt(40)-20+przesuniecie1,75+rng.nextInt(40)-20,punkty_chmury[2][0]+przesuniecie1,punkty_chmury[2][1]);
        chmura.quadTo(105+rng.nextInt(40)-20+przesuniecie1,140+rng.nextInt(40)-20,punkty_chmury[3][0]+przesuniecie1,punkty_chmury[3][1]);
        chmura.quadTo(10+rng.nextInt(40)-20+przesuniecie1,75+rng.nextInt(40)-20,punkty_chmury[4][0]+przesuniecie1,punkty_chmury[4][1]);
        chmura.closePath();
        g2d.fill(chmura);
        przesuniecie2=rng.nextInt(1000);
        while(Math.abs(przesuniecie1-przesuniecie2)<400)
        {
            przesuniecie2=rng.nextInt(1000);
        }
        GeneralPath chmura2= new GeneralPath();
        chmura2.moveTo(punkty_chmury[0][0]+przesuniecie2,punkty_chmury[0][1]);
        chmura2.quadTo(105+rng.nextInt(40)-20+przesuniecie2,rng.nextInt(40)-20,punkty_chmury[1][0]+przesuniecie2,punkty_chmury[1][1]);
        chmura2.quadTo(220+rng.nextInt(40)-20+przesuniecie2,75+rng.nextInt(40)-20,punkty_chmury[2][0]+przesuniecie2,punkty_chmury[2][1]);
        chmura2.quadTo(105+rng.nextInt(40)-20+przesuniecie2,140+rng.nextInt(40)-20,punkty_chmury[3][0]+przesuniecie2,punkty_chmury[3][1]);
        chmura2.quadTo(10+rng.nextInt(40)-20+przesuniecie2,75+rng.nextInt(40)-20,punkty_chmury[4][0]+przesuniecie2,punkty_chmury[4][1]);
        chmura2.closePath();
        g2d.fill(chmura2);

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

public class Obrazek extends JFrame {

    public Obrazek() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Basic shapes");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Obrazek ex = new Obrazek();
                ex.setVisible(true);
            }
        });
    }
}