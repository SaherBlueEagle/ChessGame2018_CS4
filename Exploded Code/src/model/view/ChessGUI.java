package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;


public class ChessGUI extends JFrame{

 


private JPanel StartPanel;


private JTextArea PLXN,PLXYN;
private JButton StartButton;
public JTextArea getPLXN() {
	return PLXN;
}
public JTextArea getPLXYN() {
	return PLXYN;
}
 
 
public JPanel getStartPanel() {
	return StartPanel;
}

public ChessGUI(){
	setTitle("Chess Game");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setBounds(50, 0,1200,725);
 
	
	
   

	 
	ImageIcon CUSKMASKN =  new ImageIcon("ASDFKMD.png");
	StartPanel = new CUSKMASKNDASLKDN(CUSKMASKN.getImage());
 
	StartPanel.setBackground(Color.BLACK);
	JLabel PLX = new JLabel("Player One [Human One] : ");
	PLX.setBounds(350,250,250,30);
	PLX.setForeground(Color.GREEN);
	PLX.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	JLabel PLY = new JLabel("Player Two [Human Two] : ");
	PLY.setBounds(350,290,250,30);
	PLY.setForeground(Color.GREEN);
	PLY.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	PLXN = new JTextArea();
	PLXN.setText("Human");
	PLXN.setBounds(590,250,250,30);
	PLXN.setForeground(Color.BLACK);
	PLXN.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	PLXYN = new JTextArea();
	PLXYN.setText("Human 2");
	PLXYN.setBounds(590,290,250,30);
	PLXYN.setForeground(Color.BLACK);
	PLXYN.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	StartButton = new JButton();
	StartButton.setBounds(515, 350, 150, 30);
	StartButton.setText("Start Game");
	StartButton.setForeground(Color.GREEN);
	StartButton.setBackground(Color.darkGray);
	StartPanel.add(PLX);
	StartPanel.add(PLY);
	StartPanel.add(PLXN);
	StartPanel.add(PLXYN);
	StartPanel.add(StartButton);
	add(StartPanel, BorderLayout.CENTER);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	setResizable(false);
}
public JButton getStartButton() {
	return StartButton;
}





}

class CUSKMASKNDASLKDN extends JPanel {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDN(String PATHXOIMG) {
 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDN(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));
 setPreferredSize(IMGSIZEX);
 setMaximumSize(IMGSIZEX);
 setMinimumSize(IMGSIZEX);
setSize(IMGSIZEX);
 setLayout(null);
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}