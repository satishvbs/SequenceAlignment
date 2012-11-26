package edu.atilim.bioinformatics;

public class MatrixElement
{
	private int value;
	private int arrow;
	
	public final static int ARROW_LEFT = 1;
	public final static int ARROW_UP = 2;
	public final static int ARROW_CROSS = 3;	
	public final static int ARROW_NONE = -1;	
		
	public MatrixElement(int value, int arrow) {
		super();
		this.value = value;
		this.arrow = arrow;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getArrow() {
		return arrow;
	}
	public void setArrow(int arrow) {
		this.arrow = arrow;
	}
	
	
	
	

}
