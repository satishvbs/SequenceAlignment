package edu.atilim.bioinformatics;

import java.util.ArrayList;

/**
 * Aligner.java
 * @author Ercument Kisa
 * ercumentkisa@gmail.com
 *
 * This class is for computing the alignment process by applying the principles of dynamic programming approach.
 */
public class DynamicAligner
{
	
	@SuppressWarnings("unused")
	private GUI gui;
	
	private String sequence1;
	private String sequence2;
	
	private String alignedSequence1;
	private String alignedSequence2;
	
	private int gapPenalty;
	
	// If 0 the matrix is Blosum62, If 1 the matrix is PAM120
	private int selectedSubstitutionMatrix;
	
	private int numberOfRows;
	private int numberOfColumns;
	
	private int scoreOfTheAlignment;
	
	BLOSUM62 blosum62;
	PAM120 pam120;
	
	@SuppressWarnings("rawtypes")
	ArrayList<ArrayList> rows = new ArrayList<ArrayList>();
	
	
	
	public DynamicAligner(GUI gui)
	{
		this.gui = gui;

		sequence1 = gui.getSequence1();
		sequence2 = gui.getSequence2();
		
		gapPenalty = gui.getGapPenalty();
		selectedSubstitutionMatrix = gui.getSelectedSubstitutionMatrix();
		
		numberOfRows = sequence1.length()+1;
		numberOfColumns = sequence2.length()+1;
		
		if(selectedSubstitutionMatrix == 0)
			blosum62 = new BLOSUM62();
		else
			pam120 = new PAM120();
		
		try
		{
			setInitialMatrix();
			System.out.println("Initial matrix has successfully set. \nComputation started...");

			fillMatrix();
			System.out.println("Matrix filled successfully. Reading the arrows backwards...");
		
			traceBack();
		
			gui.setResults(alignedSequence1, alignedSequence2, scoreOfTheAlignment);
		
			System.out.println("Done!");
		}
		catch(InvalidAminoAcidException e)
		{
			System.err.println("Error while reading the values from Substitution Matrix.");
			gui.setjLabelErrorText("It seems an invalid amino acid is entered. Please check your sequences and try again.");
			gui.repaint();
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Sets the first row and the first column of the matrix
	 */
	@SuppressWarnings("unchecked")
	public void setInitialMatrix()
	{
		for(int i=0; i<numberOfRows; i++)
		{
			ArrayList<MatrixElement> columns = new ArrayList<MatrixElement>();
			columns.add(new MatrixElement(i*gapPenalty, MatrixElement.ARROW_UP));
			rows.add(columns);
		}
		
		for(int j=1; j<=numberOfColumns; j++)
		{
			rows.get(0).add(new MatrixElement(j*gapPenalty,MatrixElement.ARROW_LEFT));
		}
		
		// Set the arrow of the beginning element of the matrix to NONE. 
		((MatrixElement) rows.get(0).get(0)).setArrow(MatrixElement.ARROW_NONE);
	}
	
	/**
	 * Fills the cells in the matrix with corresponding numbers and arrows
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void fillMatrix() throws InvalidAminoAcidException
	{
		for(int i=1; i<numberOfRows; i++)
		{
			for(int j=1; j<numberOfColumns; j++)
			{
				// Get the values of three neighbor cells
				int a = 0,b = 0,c = 0;
				

				a = ((MatrixElement) rows.get(i).get(j-1)).getValue() + gapPenalty;
				b = ((MatrixElement) rows.get(i-1).get(j)).getValue() + gapPenalty;
				
				if(selectedSubstitutionMatrix == 0)
					c = ((MatrixElement) rows.get(i-1).get(j-1)).getValue() + blosum62.getDistance(sequence1.charAt(i-1), sequence2.charAt(j-1));
				else
					c = ((MatrixElement) rows.get(i-1).get(j-1)).getValue() + pam120.getDistance(sequence1.charAt(i-1), sequence2.charAt(j-1));
			
				
				// Find the maximum number and put it in the appropriate cell
				if(a>b && a>=c)
				{
					rows.get(i).add(new MatrixElement(a, MatrixElement.ARROW_LEFT));
				}
				else if(b>c && b>=a)
				{
					rows.get(i).add(new MatrixElement(b, MatrixElement.ARROW_UP));
				}
				else if(c>a && c>=b)
				{
					rows.get(i).add(new MatrixElement(c, MatrixElement.ARROW_CROSS));
				}
				
			}
		}
		
	}

	
	/**
	 * Starts from the right bottom of the matrix and follows the arrows.
	 * Keeps the reversed alignments in char arrays.
	 */
	public void traceBack()
	{
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		
		int i = numberOfRows-1;
		int j = numberOfColumns-1;
		
		while(((MatrixElement) rows.get(i).get(j)).getArrow() != MatrixElement.ARROW_NONE)
		{
				// Set the letters with respect to the arrows
				if(((MatrixElement) rows.get(i).get(j)).getArrow() == MatrixElement.ARROW_UP)
				{
					builder2.append("-");
				}
				else
				{
					builder2.append(sequence2.charAt(j-1));
				}
				
				if(((MatrixElement) rows.get(i).get(j)).getArrow() == MatrixElement.ARROW_LEFT)
				{
					builder1.append("-");
				}
				else
				{
					builder1.append(sequence1.charAt(i-1));
				}
				
				
				// Set the indices
				if(((MatrixElement) rows.get(i).get(j)).getArrow() == MatrixElement.ARROW_CROSS)
				{
					i=i-1;
					j=j-1;
				}
				else if(((MatrixElement) rows.get(i).get(j)).getArrow() == MatrixElement.ARROW_LEFT)
				{
					j=j-1;
				}
				else if(((MatrixElement) rows.get(i).get(j)).getArrow() == MatrixElement.ARROW_UP)
				{
					i=i-1;
				}
		}
		
		// Put the results into the strings
		alignedSequence1 = builder1.reverse().toString();
		alignedSequence2 = builder2.reverse().toString();
	   
		scoreOfTheAlignment = ((MatrixElement) rows.get(numberOfRows-1).get(numberOfColumns-1)).getValue();
	}
	
}
