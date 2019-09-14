package points;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Points extends JPanel {

    Point points[] = {new Point(100,100),
                      new Point(100,250),
                      new Point(400,250),
                      new Point(400,400)};
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        Dimension size = getSize();
        Insets insets = getInsets();
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        g2.setStroke(new BasicStroke(3));
        
        for (int i = 1; i < points.length-1; i++) {
            g2.drawLine(points[i].x, h-points[i].y, points[i].x, h-points[i].y);    // initial point
        }
        g2.setStroke(new BasicStroke(1));
        
        double aux_x=0, aux_y=0;
        int x = points[0].x;
        int y = points[0].y;
        int n = points.length-1;  // Quantity of oints minus 1.
        for (float u = 0; u <= 1; u += 0.01) {
            for (int k = 0; k <= n; k++) {
                aux_x += points[k].x*bez(n,k,u);
                aux_y += points[k].y*bez(n,k,u);
            }
            // x,y son los valores de las coordenadas para mi respectivo 'u'.
            g2.drawLine(x, h-y, (int)aux_x, h-(int)aux_y);
            x = (int)aux_x;
            y = (int)aux_y;
            aux_x = 0; 
            aux_y= 0;
        }
    }

    private static double bez(int n, int k, double u){
        return ((fact(n)/(fact(k)*fact(n-k)))*Math.pow(u, k)*Math.pow(1-u, n-k));
    }
    
    private static long fact(int number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++)
            result *= factor;
        return result;
    }
    
    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Points");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Points());
        // Asignarle tamaño
        frame.setSize(500, 500);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }
}
