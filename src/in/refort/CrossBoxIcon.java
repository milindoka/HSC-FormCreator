package in.refort;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class CrossBoxIcon implements Icon, UIResource, Serializable {
    
    private static final int SIZE = 13;
     
    public int getIconHeight() {
        return SIZE;
    }
     
    public int getIconWidth() {
        return SIZE;
    }
     
    public void paintIcon( Component c, Graphics g, int x, int y ) {
        if ( c instanceof JCheckBox ) {
            JCheckBox check = (JCheckBox)c;
            ButtonModel bm = check.getModel();
            if ( bm.isEnabled() ) {
                if ( bm.isPressed() && bm.isArmed() ) {
                    g.setColor( MetalLookAndFeel.getControlShadow() );
                    g.fillRect( x, y, SIZE - 1, SIZE - 1 );
                    drawPressed3DBorder( g, x, y, SIZE, SIZE );
                } else {
                    drawFlush3DBorder( g, x, y, SIZE, SIZE );
                }
                g.setColor( MetalLookAndFeel.getControlInfo() );
            } else {
                g.setColor( MetalLookAndFeel.getControlShadow() );
                g.drawRect( x, y, SIZE-2, SIZE-2);
            }
             
            if ( bm.isSelected() ) {
                drawCheck( g, x, y );
            }
        }
    }
     
    protected void drawCheck( Graphics g, int x, int y ) 
     {    Graphics2D g2 = (Graphics2D) g;
         g2.setStroke(new BasicStroke(2));
    	g2.setColor(Color.RED);
         g2.drawLine(x+1,y+1, x+SIZE-1, y+SIZE-2);
    	//g2.setStroke(new BasicStroke(3));
    	g2.drawLine(x+1,y+SIZE-2, x+SIZE-2, y+2);
        //g.fillRect( x + 2, y + ( SIZE / 2 ) - 1, SIZE - 5, 2 );
        //g.fillRect( x + ( SIZE / 2 ) - 1, y + 2, 2, SIZE - 5 );
    }
     
    protected void drawPressed3DBorder(Graphics g, int x, int y, int w, int h) 
    {
        g.translate( x, y);
        drawFlush3DBorder(g, 0, 0, w, h);
        g.setColor( MetalLookAndFeel.getControlShadow() );
        g.drawLine( 1, 1, 1, h-2 );
        g.drawLine( 1, 1, w-2, 1 );
        g.translate( -x, -y);
    }
     
    protected void drawFlush3DBorder( Graphics g, int x, int y, int w, int h ) 
     { 
        g.translate( x, y);
        g.setColor( MetalLookAndFeel.getBlack());//  getControlDarkShadow() );
        g.drawRect( 0, 0, w-2, h-2 );
        g.setColor( MetalLookAndFeel.getBlack());//    getControlHighlight() );
        g.drawRect( 1, 1, w-2, h-2 );
        g.setColor( MetalLookAndFeel.getControl() );
        g.drawLine( 0, h-1, 1, h-2 );
        g.drawLine( w-1, 0, w-2, 1 );
        g.translate( -x, -y);
    }
}