package main;
import javax.swing.*;

import java.awt.*;

public class ShapePainter extends JFrame{
    private static int width = SquareArtMain.width;
    private static int[][] art = new int[width][width];
    private static int pixelSize = 4; // change the size of the image produced
    
    // create the window:
	public ShapePainter(){
          setSize(750,750);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setVisible(true);
     }
	
	
     public static void main(String a[]){
 		art = SquareArtMain.mainFunction(); // generate the art
 		new ShapePainter(); // create the window
     }
     
     // paint the image
     public void paint(Graphics g){
    	 for (int i=0; i<width;i++){ 
    		for (int j=0; j<width;j++){ // for each square...
    			if(art[i][j]==5){ //if the square is filled, paint a pixel in that position
    				g.fillRect(j*pixelSize+12, i*pixelSize+28, pixelSize, pixelSize);
    			}
    		}
    	 }
     }
}