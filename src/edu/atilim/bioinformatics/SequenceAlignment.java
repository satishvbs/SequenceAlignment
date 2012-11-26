package edu.atilim.bioinformatics;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * SequenceAlignment.java
 * @author Ercument Kisa
 * ercumentkisa@gmail.com
 * @date 21.11.2012
 * @version 1.0
 * 
 * This is the main class.
 *
 */
public class SequenceAlignment
{
	
	public SequenceAlignment()
	{
		System.out.println("Graphical User Interface is initializing...");
		new GUI();
	}
	
	
	public static void main(String[] args)
	{
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		@SuppressWarnings("unused")
		SequenceAlignment seq = new SequenceAlignment();
		
		
		
	}


	
	
	
	
	
	
}
