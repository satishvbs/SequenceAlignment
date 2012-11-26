package edu.atilim.bioinformatics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GUI extends JFrame
{
	
	private String sequence1;
	private String sequence2;
	private String alignedSequence1;
	private String alignedSequence2;
	
	private int gapPenalty;
	
	// If 0 the matrix is Blosum62, If 1 the matrix is PAM120. Default BLOSUM - 62 selected.
	private int selectedSubstitutionMatrix = 0;
	
	
	private JButton jButtonCompute;
    @SuppressWarnings("rawtypes")
	private JComboBox jComboBoxMatrix;
    private JLabel jLabelAlignedSeq1;
    private JLabel jLabelAlignedSeq2;
    private JLabel jLabelDetails;
    private JLabel jLabelError;
    private JLabel jLabelGap;
    private JLabel jLabelMatrix;
    private JLabel jLabelSeq1;
    private JLabel jLabelSeq2;
    private JLabel jLabelWelcome;
    private JLabel jLabelAuthor;
    private JPanel jPanel;
    private JTextField jTextFieldGap;
    private JTextField jTextFieldResult1;
    private JTextField jTextFieldResult2;
    private JTextField jTextFieldSeq1;
    private JTextField jTextFieldSeq2;
    private JSeparator jSeparator;
    
    public GUI()
    {
    	initComponents();
    }
    
    
    
    
    /**
	 * Initializing GUI components.
	 */
	public void initComponents()
	{
		jPanel = new javax.swing.JPanel();
        jLabelWelcome = new javax.swing.JLabel();
        jLabelDetails = new javax.swing.JLabel();
        jLabelSeq1 = new javax.swing.JLabel();
        jLabelSeq2 = new javax.swing.JLabel();
        jLabelGap = new javax.swing.JLabel();
        jLabelMatrix = new javax.swing.JLabel();
        jTextFieldSeq1 = new javax.swing.JTextField();
        jTextFieldSeq2 = new javax.swing.JTextField();
        jTextFieldGap = new javax.swing.JTextField();
        jComboBoxMatrix = new javax.swing.JComboBox();
        jButtonCompute = new javax.swing.JButton();
        jLabelAlignedSeq1 = new javax.swing.JLabel();
        jLabelAlignedSeq2 = new javax.swing.JLabel();
        jTextFieldResult1 = new javax.swing.JTextField();
        jTextFieldResult2 = new javax.swing.JTextField();
        jLabelError = new javax.swing.JLabel();
        jLabelAuthor = new javax.swing.JLabel();
        jSeparator = new javax.swing.JSeparator();
        
        setTitle("Sequence Aligner");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelWelcome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWelcome.setText("Welcome to Sequence Aligner!");

        jLabelDetails.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabelDetails.setText("Input the details appropriately and hit the Compute button.");
        
        jLabelAuthor.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabelAuthor.setForeground(new java.awt.Color(153, 153, 153));
        jLabelAuthor.setText("Ercüment Kýsa - ercumentkisa@gmail.com");
        
        jSeparator.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabelSeq1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelSeq1.setText("Sequence 1:");

        jLabelSeq2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelSeq2.setText("Sequence 2:");

        jLabelGap.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelGap.setText("Gap Penalty (E):");

        jLabelMatrix.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelMatrix.setText("Substitution Matrix:");

        DocumentFilter filter = new UppercaseDocumentFilter();
        
        jTextFieldSeq1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ((AbstractDocument) jTextFieldSeq1.getDocument()).setDocumentFilter(filter);

        jTextFieldSeq2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ((AbstractDocument) jTextFieldSeq2.getDocument()).setDocumentFilter(filter);

        jTextFieldGap.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jComboBoxMatrix.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxMatrix.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BLOSUM 62", "PAM 120", "Percent Identity" }));
        jComboBoxMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	selectedSubstitutionMatrix = jComboBoxMatrix.getSelectedIndex();
            }
        });

        jButtonCompute.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonCompute.setText("Compute");
        jButtonCompute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				sequence1 = jTextFieldSeq1.getText();
				sequence2 = jTextFieldSeq2.getText();
				
				
				if(sequence1.equals("") || sequence2.equals("") || jTextFieldGap.getText().equals(""))
				{
					jLabelError.setText("ERROR: Check Your Inputs! No Empty Sequences or Empty Gap Penalty Allowed...");
					repaint();
				}
				else
				{
					gapPenalty = -1*Integer.parseInt(jTextFieldGap.getText());
					jLabelError.setText("");
					repaint();			
					
					startComputing();
				}
			}
		});

        jLabelAlignedSeq1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelAlignedSeq1.setText("Aligned Sequence 1:");

        jLabelAlignedSeq2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelAlignedSeq2.setText("Aligned Sequence 2:");

        jTextFieldResult1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldResult1.setEditable(false);

        jTextFieldResult2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldResult2.setEditable(false);

        jLabelError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelError.setForeground(new java.awt.Color(255, 0, 0));
        jLabelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelAlignedSeq1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelGap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelSeq2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelSeq1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelAlignedSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldSeq1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldSeq2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                    .addComponent(jTextFieldGap)
                                    .addGap(79, 79, 79)
                                    .addComponent(jLabelMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBoxMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextFieldResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelDetails)
                        .addGap(233, 233, 233))
                    .addComponent(jLabelAuthor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jButtonCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDetails)
                .addGap(47, 47, 47)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeq1)
                    .addComponent(jTextFieldSeq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeq2)
                    .addComponent(jTextFieldSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGap)
                    .addComponent(jTextFieldGap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMatrix))
                .addGap(37, 37, 37)
                .addComponent(jButtonCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlignedSeq1)
                    .addComponent(jTextFieldResult1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlignedSeq2)
                    .addComponent(jTextFieldResult2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabelAuthor))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
		
	}
	
	
	public void startComputing()
	{
		System.out.println("Drawing Matrices and computing the Sequence Alignment...");
		
		if(selectedSubstitutionMatrix == 3)
			new PercentIdentityAlign(this);
		else
			new DynamicAligner(this);
	}
	
	
	public void setResults(String result1, String result2, int score)
	{
		jTextFieldResult1.setText(result1.toUpperCase());
		jTextFieldResult2.setText(result2.toUpperCase());
		jLabelError.setText("Score of the alignment is: " + score);
		repaint();
	}
	
	
	public int getSelectedSubstitutionMatrix() {
		return selectedSubstitutionMatrix;
	}


	public void setSelectedSubstitutionMatrix(int selectedSubstitutionMatrix) {
		this.selectedSubstitutionMatrix = selectedSubstitutionMatrix;
	}


	public String getSequence1() {
		return sequence1;
	}


	public void setSequence1(String sequence1) {
		this.sequence1 = sequence1;
	}


	public String getSequence2() {
		return sequence2;
	}


	public void setSequence2(String sequence2) {
		this.sequence2 = sequence2;
	}


	public int getGapPenalty() {
		return gapPenalty;
	}


	public void setGapPenalty(int gapPenalty) {
		this.gapPenalty = gapPenalty;
	}


	public void setjLabelErrorText(String error) {
		jLabelError.setText(error);
	}
	
	
}


/**
 * This class is for making the input text uppercase.
 * @author Ercument Kisa
 * ercumentkisa@gmail.com
 *
 */
class UppercaseDocumentFilter extends DocumentFilter
{
	  public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
	      AttributeSet attr) throws BadLocationException {

	    fb.insertString(offset, text.toUpperCase(), attr);
	  }

	  public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
	      AttributeSet attrs) throws BadLocationException {

	    fb.replace(offset, length, text.toUpperCase(), attrs);
	  }
}


