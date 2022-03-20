/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plot;

import Calculus.CalculusRNGDiff;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class PlotSSCCE extends JPanel {
    private static BufferedImage bi=new BufferedImage(768,1024,BufferedImage.TYPE_INT_RGB);
    ImageIcon icon= new ImageIcon(bi);
    public static JFrame frame = new JFrame("SSCCE");   
   public PlotSSCCE(int sizex, int sizey){
       add(new JLabel(icon));
       
       for(int y=0; y < sizey ; y++){
           for (int x=0; x < sizex; x++){
                Color color=Color.BLACK;
                int colorValue=color.getRGB();
                bi.setRGB(x, y, 0);
          }
       }
       
   } 
   public static void createAndShowGUI(int sizex, int sizey){
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new PlotSSCCE(sizex,sizey));
       frame.setLocationByPlatform(true);
       frame.pack();
       frame.setVisible(true);
   }
   public static void showPixel(int x,int y,int Mycolor){
       bi.setRGB(x, y, Mycolor);
    }
   public static void main(String[] args){
       EventQueue.invokeLater(new Runnable(){
       public void run(){
           createAndShowGUI(768,1024);
       }
   });
   }
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       g.drawLine(CalculusRNGDiff.x, CalculusRNGDiff.y, CalculusRNGDiff.x, CalculusRNGDiff.y);
   }
}
