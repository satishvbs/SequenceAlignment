package edu.atilim.bioinformatics;

/**
 * PercentIdentityAlign.java
 * 
 * @author Ercument Kisa
 * ercumentkisa@gmail.com
 * 
 * This class is for the alignment calculations when "Percent Identity" selected.
 *
 */
public class PercentIdentityAlign
{
	
	private GUI gui;
	
	private String sequence1;
	private String sequence2;
	
	private String alignedSequence1;
	private String alignedSequence2;
	
	private int gapPenalty;
	
	
	public PercentIdentityAlign(GUI gui)
	{
		this.gui = gui;
		
		sequence1 = gui.getSequence1();
		sequence2 = gui.getSequence2();
		
		gapPenalty = gui.getGapPenalty();
		
	}

}
