package pedro.pg.interfacegrafica.utilitario;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author administrador
 */
public class IconeQuadrado implements Icon
{
    private final int comprimento = 16;
    private final int altura = 16;
    
    //private BasicStroke stroke = new BasicStroke( 4 );

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) 
    {
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect( x + 1, y + 1, comprimento - 2, altura - 2 );
        
        g2d.setColor( Color.BLACK );
        g2d.drawRect(x + 1, y + 1, comprimento - 2, altura - 2 );
        
        /*g2d.setColor(Color.RED);
        
        g2d.setStroke(stroke);
        g2d.drawLine(x +10, y + 10, x + comprimento -10, y + altura -10);
        g2d.drawLine(x +10, y + altura -10, x + comprimento -10, y + 10);*/
        
        g2d.dispose();
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIconWidth() 
    {
        return comprimento;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIconHeight() 
    {
        return altura;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
