package com.loan.agent.mvc.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import org.apache.log4j.*;
 
public class GenCertPicCode {
	private final static Logger LOG = Logger.getLogger(GenCertPicCode.class);
	private final static int EIGHT_CHARS=8;
	private final static int SIX_CHARS=6;
	private final static int FOUR_CHARS=4;
	
	private final static  char mapTable[]={
			 'a','b','c','d','e','f',
			 'g','h','i','j','k','l',
			 'm','n','o','p','q','r',
			 's','t','u','v','w','x',
			 'y','z',
			 'A','B','C','D','E','F',
			 'G','H','I','J','K','L',
			 'M','N','O','P','Q','R',
			 'S','T','U','V','W','X',
			 'Y','Z',
			 '0','1','2','3',
			 '4','5','6','7','8','9'//,
			// '@','&','%'
			 };
	/**
	 * Create Random Colors 
	 * @param fc
	 * @param bc
	 * @return
	 */
	public static Color getRandColor(int fc,int bc){ 
		Random random = new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
	public static String genCertPicCode(int charCount,OutputStream os) {
		int width = 220;
		int height= 120;
		int maskLines = 300;
		int charTop = 60;
		if (charCount==EIGHT_CHARS) {
			width = 210;
			height= 120;
			maskLines = 300;
			 charTop = 60;
		} else if (charCount==SIX_CHARS) {
			width = 180;
			height= 90;
			maskLines = 225;
			 charTop = 50;
		} else	if (charCount==FOUR_CHARS) {
			width = 140;
			height= 90;
			maskLines = 150;
			charTop = 40;
		} else {
			LOG.info("charCount must be 8,6,4");
			return null;
		}
		// Create Graphic Image in RAM
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Get Graphics
		Graphics g = image.getGraphics();

		//Create random class
		Random random = new Random();

		// Set Background Color
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);

		//Set initial Font
		g.setFont(new Font("Times New Roman",Font.PLAIN,28));

		//Draw Rectangle Box Border
		g.drawRect(0,0,width-1,height-1);
		
		// Get Certificate Code by charsCount
		String sRand="";
		for (int i=0;i<charCount;i++){
		    // Current Random Character	
			String rand=String.valueOf(mapTable[(int) (mapTable.length*Math.random())]);
            // Cumulative Random String
			sRand += rand;		 
			// Display Certificate Code in graphics
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			// Adjust distance between Characters
			int x = 26*i+10;
			if (i==0) {
				x=10;
			}
		   /**
		    *  Make Character Jump up and down
		    */
			g.drawString(rand,x,charTop+(int) (16*Math.random()));
		
			/**
			 *  Randomly Change Font and Size
			 */
		
			int current = (int) (8*Math.random());
			switch (current) {
			case 1:
				g.setFont(new Font("Times New Roman",Font.BOLD,40));
				break;
			case 2:
				g.setFont(new Font("Arial",Font.BOLD,30));
				break;
			case 3:
				g.setFont(new Font("Blackoak Std",Font.BOLD,34));
				break;	
			case 4:
				g.setFont(new Font("Castellar",Font.BOLD,34));
				break;
			case 5:
				g.setFont(new Font("Arial Black",Font.BOLD,34));
				break;
			case 6:
				g.setFont(new Font("Century",Font.BOLD,34));
				break;
			case 7: 
				g.setFont(new Font("Elephant",Font.BOLD,34));
				break;
			}
		}
		//Randomly create disturbing lines and Ovals , which make the graphic characters be hard to detected
		g.setColor(getRandColor(160,200));
		for (int i=0;i<maskLines;i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(28);
			int yl = random.nextInt(28);
			g.drawLine(x,y,x+xl,y+yl);
			g.drawOval(x,y,2,2);
		}
		 try{
    		 // Send graphic to OutputStream
			 ImageIO.write(image, "JPEG", os);

    	 }catch (IOException e){
    		 LOG.info("Fail to send graphics to OutputStream");
    		 return "";
    	 }
    	System.out.println("Certificate Picture Code:"+sRand);
		return sRand;
	}
	
}
