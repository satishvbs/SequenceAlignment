package edu.atilim.bioinformatics;

import java.lang.*;
/**
 * PAM 120 substitution matrix
*    A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V
* A  3 -3 -1  0 -3 -1  0  1 -3 -1 -3 -2 -2 -4  1  1  1 -7 -4  0
* R -3  6 -1 -3 -4  1 -3 -4  1 -2 -4  2 -1 -5 -1 -1 -2  1 -5 -3
* N -1 -1  4  2 -5  0  1  0  2 -2 -4  1 -3 -4 -2  1  0 -4 -2 -3
* D  0 -3  2  5 -7  1  3  0  0 -3 -5 -1 -4 -7 -3  0 -1 -8 -5 -3
* C -3 -4 -5 -7  9 -7 -7 -4 -4 -3 -7 -7 -6 -6 -4  0 -3 -8 -1 -3
* Q -1  1  0  1 -7  6  2 -3  3 -3 -2  0 -1 -6  0 -2 -2 -6 -5 -3
* E  0 -3  1  3 -7  2  5 -1 -1 -3 -4 -1 -3 -7 -2 -1 -2 -8 -5 -3
* G  1 -4  0  0 -4 -3 -1  5 -4 -4 -5 -3 -4 -5 -2  1 -1 -8 -6 -2
* H -3  1  2  0 -4  3 -1 -4  7 -4 -3 -2 -4 -3 -1 -2 -3 -3 -1 -3
* I -1 -2 -2 -3 -3 -3 -3 -4 -4  6  1 -3  1  0 -3 -2  0 -6 -2  3
* L -3 -4 -4 -5 -7 -2 -4 -5 -3  1  5 -4  3  0 -3 -4 -3 -3 -2  1
* K -2  2  1 -1 -7  0 -1 -3 -2 -3 -4  5  0 -7 -2 -1 -1 -5 -5 -4
* M -2 -1 -3 -4 -6 -1 -3 -4 -4  1  3  0  8 -1 -3 -2 -1 -6 -4  1
* F -4 -5 -4 -7 -6 -6 -7 -5 -3  0  0 -7 -1  8 -5 -3 -4 -1  4 -3
* P  1 -1 -2 -3 -4  0 -2 -2 -1 -3 -3 -2 -3 -5  6  1 -1 -7 -6 -2
* S  1 -1  1  0  0 -2 -1  1 -2 -2 -4 -1 -2 -3  1  3  2 -2 -3 -2
* T  1 -2  0 -1 -3 -2 -2 -1 -3  0 -3 -1 -1 -4 -1  2  4 -6 -3  0
* W -7  1 -4 -8 -8 -6 -8 -8 -3 -6 -3 -5 -6 -1 -7 -2 -6 12 -2 -8
* Y -4 -5 -2 -5 -1 -5 -5 -6 -1 -2 -2 -5 -4  4 -6 -3 -3 -2  8 -3
* V  0 -3 -3 -3 -3 -3 -3 -2 -3  3  1 -4  1 -3 -2 -2  0 -8 -3  5
*/


final public class PAM120
{

    /*
     * Array representation of PAM 120 matrix 
     * Refer to above matrix for corrseponding amino acids
     * i.e. score(A, R) corresponds to  matrix[0][1]=matrix[1][0]=-3
    */  
    private static final int[][] matrix = {
	{ 3, -3, -1,  0, -3, -1,  0,  1, -3, -1, -3, -2, -2, -4,  1,  1,  1, -7, -4,  0},
	{-3,  6, -1, -3, -4,  1, -3, -4,  1, -2, -4,  2, -1, -5, -1, -1, -2,  1, -5, -3},
	{ 0, -3,  2,  5, -7,  1,  3,  0,  0, -3, -5, -1, -4, -7, -3,  0, -1, -8, -5, -3},
	{-3, -4, -5, -7,  9, -7, -7, -4, -4, -3, -7, -7, -6, -6, -4,  0, -3, -8, -1, -3},
	{-1, 1,   0,  1, -7,  6,  2, -3,  3, -3, -2,  0, -1, -6,  0, -2, -2, -6, -5, -3},
	{ 0, -3,  1,  3, -7,  2,  5, -1, -1, -3, -4, -1, -3, -7, -2, -1, -2, -8, -5, -3},
	{ 1, -4,  0,  0, -4, -3, -1,  5, -4, -4, -5, -3, -4, -5, -2,  1, -1, -8, -6, -2},
	{-3,  1,  2,  0, -4,  3, -1, -4,  7, -4, -3, -2, -4, -3, -1, -2, -3, -3, -1, -3},
	{-3,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3},
	{-1, -2, -2, -3, -3, -3, -3, -4, -4,  6,  1, -3,  1,  0, -3, -2,  0, -6, -2,  3},
	{-3, -4, -4, -5, -7, -2, -4, -5, -3,  1,  5, -4,  3,  0, -3, -4, -3, -3, -2,  1},
	{-2,  2,  1, -1, -7,  0, -1, -3, -2, -3, -4,  5,  0, -7, -2, -1, -1, -5, -5, -4},
	{-2, -1, -3, -4, -6, -1, -3, -4, -4,  1,  3,  0,  8, -1, -3, -2, -1, -6, -4,  1},
	{-4, -5, -4, -7, -6, -6, -7, -5, -3,  0,  0, -7, -1,  8, -5, -3, -4, -1,  4, -3},
	{ 1, -1, -2, -3, -4,  0, -2, -2, -1, -3, -3, -2, -3, -5,  6,  1, -1, -7, -6, -2},
	{ 1, -1,  1,  0,  0, -2, -1,  1, -2, -2, -4, -1, -2, -3,  1,  3,  2, -2, -3, -2},
	{-7, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0},
	{-7,  1, -4, -8, -8, -6, -8, -8, -3, -6, -3, -5, -6, -1, -7, -2, -6, 12, -2, -8},
	{-4, -5, -2, -5, -1, -5, -5, -6, -1, -2, -2, -5, -4,  4, -6, -3, -3, -2,  8, -3},
	{ 0, -3, -3, -3, -3, -3, -3, -2, -3,  3,  1, -4,  1, -3, -2, -2,  0, -8, -3,  5}};

	// quick and dirty equivalent of typesafe enum pattern, can also use HashMap
    // or even better, EnumMap in Java 5. 
    // This code is for Java 1.4.2, so we will stick to the simple implementation
    private static int getIndex(char a) throws InvalidAminoAcidException {
    	// check for upper and lowercase characters
    	switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
	    	case 'A': return 0;
	    	case 'R': return 1;
	    	case 'N': return 2;
	    	case 'D': return 3;
	    	case 'C': return 4;
	    	case 'Q': return 5;
	    	case 'E': return 6;
	    	case 'G': return 7;
	    	case 'H': return 8;
	    	case 'I': return 9;
	    	case 'L': return 10;
	    	case 'K': return 11;
	    	case 'M': return 12;
	    	case 'F': return 13;
	    	case 'P': return 14;
	    	case 'S': return 15;
	    	case 'T': return 16;
	    	case 'W': return 17;
	    	case 'Y': return 18;
	    	case 'V': return 19;
	    	default: throw new InvalidAminoAcidException("Invalid amino acid character");
    	}
    }
    
    private static int getDistance(int i, int j) throws InvalidAminoAcidException {
    	if (i < 0 || i > matrix[0].length) {
    		throw new InvalidAminoAcidException("Invalid amino acid character at string1, position " + i);
    	}
    	if (j < 0 || j > matrix[0].length) {
    		throw new InvalidAminoAcidException("Invalid amino acid character at string2, position " + j);
    	}
    	
    	return matrix[i][j];
    }

    public static int getDistance(char a1, char a2) throws InvalidAminoAcidException {
    	// toUpper
    	return getDistance(getIndex(a1), getIndex(a2));  	
    }
}