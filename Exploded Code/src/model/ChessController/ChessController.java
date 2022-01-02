package model.ChessController;
 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


















import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.*;
import model.pieces.Movable;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.NonActivatablePowerHero;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
 
import model.view.ChessGUI;

public class ChessController implements ActionListener{
private Game Model;
private ChessGUI View;
private Player player1,player2;
private JPanel GP;
private JPanel Top;
private JPanel Dead;
private JPanel ControlMenu;
private JPanel GContainer;
private JLabel PLIN1,PLIN2;
private JProgressBar PLIN1x,PLIN2x;
private JLabel Curr,Curr2,Curr3,Curr4,Curr5;
private ATCKdirectioninput AttackDirectionGUI;
private ATCKdirectioninputRanged ATCKdirectioninputRanged;
private boolean gotAttackerPoint= false;
private int Attackeri,Attackerj ;
private int Targeti,Targetj ;
private Color CellColor;
private boolean GotTargetPoints = false ;
private boolean choosing_attackbool = false ;
private int pi,pj;
private int  YDEAD = 5;
private JPanel Win;
private ArrayList<JButton> BoardCells = new ArrayList<JButton>();

public ChessController(){
	View = new ChessGUI();
	View.getStartButton().addActionListener(this);
	View.setVisible(true);
	pi =90; 
	pj=90;
}
private void StartGame(){
		GContainer  = new JPanel(); 
		View.getStartPanel().setVisible(false);
		GContainer.setLayout(View.getLayout());
	//	GContainer.setSize(View.getLayout());
		View.getContentPane().add(GContainer, BorderLayout.CENTER);
		player1 = new Player(View.getPLXN().getText());
		player2 = new Player(View.getPLXYN().getText());
		Model = new Game(player1,player2);
		GP  = new JPanel(); 
	 	Top = new CUSKMASKNDASLKDNBUTTx1x1(new ImageIcon("ASDFKMD.png").getImage());
	 	Dead = new JPanel(); 
	 	ControlMenu = new JPanel(); 
	 	Dead.setLayout(null);
		Dead.setBackground(Color.GRAY);
		Dead.setLayout(ControlMenu.getLayout());
		ControlMenu.setBackground(Color.DARK_GRAY);
		GP.setLayout(new GridLayout(0, 6));
	    Top.setBackground(Color.GRAY);
		Top.setSize(View.getWidth(), 100);
        GContainer.add(Top, BorderLayout.NORTH);
		GContainer.add(ControlMenu, BorderLayout.EAST);
		GContainer.add(GP, BorderLayout.CENTER);
		AddLabels();
		AddButtons();
		Model.assemblePieces();
		UpdateBoardCELLs();
		View.repaint();
		
}
private void ResetGame(){
	View.removeAll();
	View.setVisible(false);
	new ChessController();
}
private void AddLabels(){
	PLIN1= new JLabel();
	PLIN1.setBounds(5,5,90,30);
	PLIN1.setBackground(Color.BLACK);
	PLIN1.setForeground(Color.YELLOW);
	PLIN1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
	PLIN1.setText("Player 1 :" + Model.getPlayer1().getName());
	//End 1 
	PLIN2= new JLabel();
	PLIN2.setBounds(300,5,90,30);
	PLIN2.setBackground(Color.BLACK);
	PLIN2.setForeground(Color.YELLOW);
	PLIN2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
	PLIN2.setText("Player 2 :"+ Model.getPlayer2().getName());
	//End 2
	PLIN1x= new JProgressBar();
	PLIN1x.setBounds(5,5,90,30);
	PLIN1x.setBackground(Color.BLACK);
	PLIN1x.setForeground(Color.YELLOW);
	PLIN1x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
    //End 1 
	PLIN2x= new JProgressBar();
	PLIN2x.setBounds(5,5,90,30);
	PLIN2x.setBackground(Color.BLACK);
	PLIN2x.setForeground(Color.YELLOW);
	PLIN2x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
	//End 2
	PLIN1x.setMinimum(0);
	PLIN2x.setMinimum(0);
	PLIN1x.setMaximum(6);
	PLIN2x.setMaximum(6);
	PLIN1x.setStringPainted(true);
	PLIN2x.setStringPainted(true);
	Top.add(PLIN1);
	Top.add(PLIN1x);
	Top.add(PLIN2);
	Top.add(PLIN2x);
	Top.validate();
}
private void UpdatePOS(){
	PLIN1x.setValue(Model.getPlayer1().getPayloadPos());
	PLIN2x.setValue(Model.getPlayer2().getPayloadPos());
}
private void UpdateFullData(Player Target){

	Curr.setText("Current Player : " + Model.getCurrentPlayer().getName());
	Curr3.setText("SideKilled : " + Model.getCurrentPlayer().getSideKilled());
	Curr2.setText("Payload Progress : " + Model.getCurrentPlayer().getPayloadPos());
	UpdatePOS();
	Dead.removeAll();
	for (Piece x :Target.getDeadCharacters()){
		AddDead(x);
		YDEAD += 100;
	}
	CheckWinnder_loser();
}
private void CheckWinnder_loser(){
	 int p1s =player1.getPayloadPos();
	 int p2s = player2.getPayloadPos();
	 Win  = new JPanel();  
	 
	ImageIcon CUSKMASKN =  new ImageIcon("ASDFKMD.png");
	Win = new CUSKMASKNDASLKDN(CUSKMASKN.getImage());
	 

 
	 
	 
	if ((p1s > p2s) && (p1s == 6)){//1 win
	 
		Win.setBackground(Color.GRAY);
		JLabel x = new JLabel("Player one wins :  " + player1.getName());
		x.setLayout(null);
		x.setForeground(Color.WHITE);
		x.setBounds(400,400,700,40);
		x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		Win.add(x);
		JLabel y = new JLabel("Player two Lose :  "+ player2.getName());
		y.setLayout(null);
		y.setForeground(Color.WHITE);
		y.setBounds(400,450,700,40);
		y.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		Win.add(y);
		JButton BCX = new JButton("Try Again");
		BCX.setLayout(null);
		BCX.setBackground(Color.DARK_GRAY);
		BCX.setForeground(Color.WHITE);
		BCX.setBounds(500,500,150,40);
		BCX.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		BCX.addActionListener(this);
		Win.add(BCX);
		Win.updateUI();
		
		
		
		View.validate();
		 Win.setVisible(true);
		 Top.setVisible(false);
		 Dead.setVisible(false);
		 GP.setVisible(false);
		 ControlMenu.setVisible(false);
		 GContainer.setVisible(false);
		View.add(Win,BorderLayout.CENTER);
		View.repaint();
	}else if ((p1s < p2s) && (p2s == 6)){//2 win
		View.removeAll();
		Win.setBackground(Color.GRAY);
		JLabel x = new JLabel("Player two wins :  "+ player2.getName());
		x.setLayout(null);

		x.setBounds(400,450,700,40);
		x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		Win.add(x);
		JLabel y = new JLabel("Player one Lose :  "+ player1.getName());
		y.setLayout(null);
		y.setForeground(Color.WHITE);
		y.setBounds(400,400,700,40);
		y.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		Win.add(y);
		
		JButton BCX = new JButton("Try Again");
		BCX.setLayout(null);
		BCX.setBackground(Color.DARK_GRAY);
		BCX.setForeground(Color.WHITE);
		BCX.setBounds(500,500,150,40);
		BCX.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		BCX.addActionListener(this);
		Win.add(BCX);
		
		 Win.setVisible(true);
		 Top.setVisible(false);
		 Dead.setVisible(false);
		 GP.setVisible(false);
		 ControlMenu.setVisible(false);
		 GContainer.setVisible(false);
		View.add(Win,BorderLayout.CENTER);
		View.repaint();
	} 
		
}
private void UpdateCELLFullData(Piece Target){
	if (Target instanceof ActivatablePowerHero){
		Curr5.setText("Activatable Power : Activated");
		if (Target instanceof Medic){
			Curr5.setText("Medic PowerUsed : "+((Medic)Target).isPowerUsed());
		}else if (Target instanceof Ranged){
			Curr5.setText("Ranged PowerUsed : "+((Ranged)Target).isPowerUsed());
		}else if (Target instanceof Super){
			Curr5.setText("Super PowerUsed : "+((Super)Target).isPowerUsed());
		}else if (Target instanceof Tech){
			Curr5.setText("Tech PowerUsed : "+((Tech)Target).isPowerUsed());
		}
	}else if (Target instanceof NonActivatablePowerHero){
		if (Target instanceof Armored){
			Curr5.setText("ArmorUp : "+((Armored)Target).isArmorUp());
		}
	} 
	
	
}
private void UpdateBoardCELLs(){
	UpdateFullData(Model.getCurrentPlayer());
	GP.removeAll();
	int Colorer = 0;  

	for (int i = 0;i<Model.getBoard().length; i++){
		for (int j = 0;j<Model.getBoard()[i].length; j++){
			JButton BoardCELL = new JButton();
			BoardCELL.setHorizontalAlignment(JButton.CENTER);
			BoardCELL.setVerticalAlignment(JButton.CENTER);
			BoardCELL.addActionListener(this);
			if (Colorer %2 !=0){
				CellColor = Color.GRAY;
			}else{
				CellColor = Color.DARK_GRAY;
			}
			 try{
				
				Cell TypeCASTCELL =  (Cell) (Model.getBoard()[i][j] );
				Piece  CELLPiece = (Piece)TypeCASTCELL.getPiece();
				BoardCELL.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				if (CELLPiece instanceof Armored){
					Armored Armored = (Armored) CELLPiece;
					if (Armored.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch5.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
						BoardCELL.setText("p1ch5");
						
					}else{
						Image img2  = (new ImageIcon("p2ch5.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch5");
						}
			 }
			 if (CELLPiece instanceof Medic){
				
				Medic Medic = (Medic) CELLPiece;
					 if (Medic.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch3.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setText("p1ch3");
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
					}else{
						Image img2  = (new ImageIcon("p2ch3.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch3");
						}
			 }
			 if (CELLPiece instanceof Ranged){
				Ranged Ranged = (Ranged) CELLPiece;

					 if (Ranged.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch6.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setText("p1ch6");
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
					}else{
						Image img2  = (new ImageIcon("p2ch3.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch3");
						}
			 }
			 if (CELLPiece instanceof Speedster){
					Speedster Speedster = (Speedster) CELLPiece;

					 if (Speedster.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch4.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setText("p1ch4");
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
					}else{
						Image img2  = (new ImageIcon("p2ch4.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch4");
						}
			 }
			 if (CELLPiece instanceof Super){
					Super Super = (Super) CELLPiece;

					 if (Super.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch2.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setText("p1ch2");
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
					}else{
						Image img2  = (new ImageIcon("p2ch2.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch2");
						}
			 }
			 if (CELLPiece instanceof Tech){
					Tech Tech = (Tech) CELLPiece; 
		
				 if (Tech.getOwner().equals(Model.getCurrentPlayer())){
						Image img2  = (new ImageIcon("p1ch1.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setEnabled(true);
						BoardCELL.setText("p1ch1");
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
					}else{
						Image img2  = (new ImageIcon("p2ch1.png")).getImage() ;  
						Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon2 = new ImageIcon( newimg2 );
						BoardCELL.setIcon(icon2);
						BoardCELL.setText("p2ch1");
					}
				 if (CELLPiece instanceof ActivatablePowerHero){
						ActivatablePowerHero ActivatablePowerHero = (ActivatablePowerHero) CELLPiece;
						 
						if (ActivatablePowerHero.getOwner().equals(Model.getCurrentPlayer())){
					 
						BoardCELL.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
						} 
						 
					}
					 if (CELLPiece instanceof NonActivatablePowerHero){
						NonActivatablePowerHero NonActivatablePowerHero = (NonActivatablePowerHero) CELLPiece;
								 
								if (NonActivatablePowerHero.getOwner().equals(Model.getCurrentPlayer())){
									 
								BoardCELL.setBorder(BorderFactory.createLineBorder(Color.RED,3));
								} 
								 
						 }
			 }
			
				
			}catch (Exception ex){System.out.println("Cell Error : " + ex.getMessage());}
			
			 
			BoardCELL.setBackground(CellColor);
			BoardCELL.setForeground(CellColor);
			BoardCELL.setVisible(true );
			String Position = i+"splitter"+j+"splitter";
			BoardCELL.setName(Position);
			BoardCELL.validate();
			GP.add(BoardCELL);
			GP.validate();
			View.repaint();
			BoardCells.add(BoardCELL);
			Colorer++;
		}
		}
	 
}
private void AddButtons(){
	ControlMenu.setLayout(null);
	 Dimension PRS = new Dimension(300,725);
	ControlMenu.setPreferredSize(PRS);
	Dead.setLocation(0,325);
	Dead.setSize(300, 400);
	Curr = new JLabel();
	
	Curr.setBounds(5,5,300,30);
	Curr.setBackground(Color.BLACK);
	Curr.setForeground(Color.WHITE);
	Curr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	ControlMenu.add(Curr);
	Curr2 = new JLabel();

	Curr2.setBounds(5,35,300,30);
	Curr2.setBackground(Color.BLACK);
	Curr2.setForeground(Color.WHITE);
	Curr2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	ControlMenu.add(Curr2);
	Curr3 = new JLabel();
	
	Curr3.setBounds(5,65,300,30);
	Curr3.setBackground(Color.BLACK);
	Curr3.setForeground(Color.WHITE);
	Curr3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	ControlMenu.add(Curr3);
	JLabel Curr4x = new JLabel("Dead Pieces List");
	
	Curr4x.setBounds(5,300,200,30);
	Curr4x.setBackground(Color.BLACK);
	Curr4x.setForeground(Color.YELLOW);
	Curr4x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
	ControlMenu.add(Curr4x);
	ImageIcon CUSKMASKNDASLKDNBUTTux =  new ImageIcon("UX.png");
	JButton CUSKMASKNDASLKDNBUTTu = new JButton("UP");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTux.getImage());
	CUSKMASKNDASLKDNBUTTu.setName("UP");

	CUSKMASKNDASLKDNBUTTu.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTu.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTu.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTu.addActionListener(this);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTu);
	ImageIcon CUSKMASKNDASLKDNBUTTulx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTul= new JButton("UP LEFT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTulx.getImage());
	CUSKMASKNDASLKDNBUTTul.setName("UP LEFT");

	CUSKMASKNDASLKDNBUTTul.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTul.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTul.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTul.addActionListener(this);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTul);
	ImageIcon CUSKMASKNDASLKDNBUTTurx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTur = new JButton("UP RIGHT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTurx.getImage());
	CUSKMASKNDASLKDNBUTTur.setName("UP RIGHT");

	CUSKMASKNDASLKDNBUTTur.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTur.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTur.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTur.addActionListener(this);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTur);
	ImageIcon CUSKMASKNDASLKDNBUTTdx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTd = new JButton("DOWN");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTdx.getImage());
	CUSKMASKNDASLKDNBUTTd.setName("DOWN");

	CUSKMASKNDASLKDNBUTTd.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTd.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTd.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
    CUSKMASKNDASLKDNBUTTd.addActionListener(this);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTd);
	ImageIcon CUSKMASKNDASLKDNBUTTdlx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTdl = new JButton("DOWN LEFT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTdlx.getImage());
	CUSKMASKNDASLKDNBUTTdl.setName("DOWN LEFT");

	CUSKMASKNDASLKDNBUTTdl.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTdl.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTdl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTdl.addActionListener(this);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTdl);
	ImageIcon CUSKMASKNDASLKDNBUTTdrx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTdr= new JButton("DOWN RIGHT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTdrx.getImage());
	CUSKMASKNDASLKDNBUTTdr.setName("DOWN RIGHT");

	CUSKMASKNDASLKDNBUTTdr.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTdr.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTdr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTdr.addActionListener(this);
    ControlMenu.add(CUSKMASKNDASLKDNBUTTdr);
	ImageIcon CUSKMASKNDASLKDNBUTTrx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTr = new JButton("RIGHT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTrx.getImage());
	CUSKMASKNDASLKDNBUTTr.setName("RIGHT");

	CUSKMASKNDASLKDNBUTTr.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTr.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTr.addActionListener(this);
    ControlMenu.add(CUSKMASKNDASLKDNBUTTr);
	ImageIcon CUSKMASKNDASLKDNBUTTlx =  new ImageIcon("welcome.png");
	JButton CUSKMASKNDASLKDNBUTTl = new JButton("LEFT");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTlx.getImage());
	CUSKMASKNDASLKDNBUTTl.setName("Move LEFT");

	CUSKMASKNDASLKDNBUTTl.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTl.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
	CUSKMASKNDASLKDNBUTTl.addActionListener(this);
	ControlMenu.add(Dead);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTl);
	Curr3.setBounds(5,65,300,30);
	CUSKMASKNDASLKDNBUTTu.setBounds(109,120,90,30);
	CUSKMASKNDASLKDNBUTTul.setBounds(8,100,100,30);
	CUSKMASKNDASLKDNBUTTur.setBounds(200,100,95,30);
	CUSKMASKNDASLKDNBUTTd.setBounds(109,184,90,30);
	CUSKMASKNDASLKDNBUTTdl.setBounds(8,204,100,30);
	CUSKMASKNDASLKDNBUTTdr.setBounds(200,204,95,30);
	CUSKMASKNDASLKDNBUTTr.setBounds(159,152,100,30);
	CUSKMASKNDASLKDNBUTTl.setBounds(50,152,100,30);
	
	Curr4 = new JLabel();

	Curr4.setBounds(5,235,300,30);
	Curr4.setBackground(Color.BLACK);
	Curr4.setForeground(Color.WHITE);
	Curr4.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	ControlMenu.add(Curr4);
	Curr5 = new JLabel();

	Curr5.setBounds(5,230,300,30);
	Curr5.setBackground(Color.BLACK);
	Curr5.setForeground(Color.WHITE);
	Curr5.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	ControlMenu.add(Curr5);
	
	JButton CUSKMASKNDASLKDNBUTTlwefdasd = new JButton("Attack / Use Power");//new CUSKMASKNDASLKDNBUTxor(CUSKMASKNDASLKDNBUTTlx.getImage());
 

	CUSKMASKNDASLKDNBUTTlwefdasd.setBackground(Color.BLACK);
	CUSKMASKNDASLKDNBUTTlwefdasd.setForeground(Color.YELLOW);
	CUSKMASKNDASLKDNBUTTlwefdasd.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
	CUSKMASKNDASLKDNBUTTlwefdasd.addActionListener(this);
	CUSKMASKNDASLKDNBUTTlwefdasd.setBounds(8,260,287,45);
	ControlMenu.add(CUSKMASKNDASLKDNBUTTlwefdasd);
	ControlMenu.repaint();}
private void AddDead(Piece TR){
	
	
	JLabel DeadCell = new JLabel("Dead Charachter : " + TR.getName());
	DeadCell.setForeground(Color.YELLOW);
	DeadCell.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

	//Piece : Armored   :  p1ch5  p2ch5 dead : c15.png
	//Piece : Medic     :  p1ch3  p2ch3 dead : c13.png
	//Piece : Ranged    :  p1ch6  p2ch6 dead : c16.png
	//Piece : Speedster :  p1ch4  p2ch4 dead : c14.png
	//Piece : Super 	:  p1ch2  p2ch2 dead : c12.png
	//Piece : Tech  	:  p1ch1  p2ch1 dead : c11.png
	if (TR instanceof Armored){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c15.png");
		DeadCell.setIcon(CUSKMASKN);
	}else if (TR instanceof Medic){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c13.png");
		DeadCell.setIcon(CUSKMASKN);
	}else if (TR instanceof Ranged){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c16.png");
		DeadCell.setIcon(CUSKMASKN);
	}else if (TR instanceof Speedster){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c14.png");
		DeadCell.setIcon(CUSKMASKN);
	}else if (TR instanceof Super){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c12.png");
		DeadCell.setIcon(CUSKMASKN);
	}else if (TR instanceof Tech){
		ImageIcon CUSKMASKN =  new ImageIcon("dead/c11.png");
		DeadCell.setIcon(CUSKMASKN);
	}
	DeadCell.setBounds(0, YDEAD, 300, 100);
	Dead.add(DeadCell);
	Dead.repaint();
}
 
@Override
public void actionPerformed(ActionEvent arg0) {
	JButton Pressed = (JButton) arg0.getSource();
	if(Pressed.getText().equals("Start Game")){StartGame();}
 
	if(Pressed.getText().equals("Try Again")){ResetGame();}
	if((Pressed.getText().contains("UP")) || (Pressed.getText().contains("DOWN"))|| (Pressed.getText().contains("RIGHT")) || (Pressed.getText().contains("LEFT"))){
		if ((pi == 90) && (pj == 90)){
			new USKMASKNDASLKDNB(" Choose Your Target Piece , Not Choose the Destination ");
			return ;
		}else{
			
			Cell CurrLoopCell =  (Cell) (Model.getBoard()[pi][pj] );
			Piece  CurrentPiece = (Piece)CurrLoopCell.getPiece();
			try {
			if(Pressed.getName().equals("UP")){
				CurrentPiece.moveUp();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("DOWN")){
				CurrentPiece.moveDown();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("RIGHT")){
				CurrentPiece.moveRight();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("LEFT")){
				CurrentPiece.moveLeft();
				UpdateAfterMove();
			}
			 
			if(Pressed.getName().equals("UP RIGHT")){
				CurrentPiece.moveUpRight();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("UP LEFT")){
				CurrentPiece.moveUpLeft();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("DOWN LEFT")){
				CurrentPiece.moveDownLeft();
				UpdateAfterMove();
			}
			if(Pressed.getName().equals("DOWN RIGHT")){
				CurrentPiece.moveDownRight();
				UpdateAfterMove();
			}
			
			} catch (UnallowedMovementException | OccupiedCellException
					| WrongTurnException e) {
				// TODO Auto-generated catch block
				if (e instanceof UnallowedMovementException){
					new USKMASKNDASLKDNB("You Are not Allowed to move to that Cell");
					return ;
				}
				if (e instanceof OccupiedCellException){
					new USKMASKNDASLKDNB("Cell is Occupied");
					return ;
				}
				if (e instanceof WrongTurnException){
					new USKMASKNDASLKDNB("Wrong Turn");
					return ;
				}else{
					new USKMASKNDASLKDNB("Exception : " + e.getMessage());
					return ;
				}
			}
			UpdateAfterMove();
		}
	
}
	if(Pressed.getText().equals("Attack / Use Power")){
		choosing_attackbool = true ;
		new USKMASKNDASLKDNB("Choose Your Attacker Piece that you want to Attack With [Press on it]");
		return;
	}
	
	if (gotAttackerPoint == false && choosing_attackbool == true ){
		getAttackP(Pressed.getName());
		gotAttackerPoint = true ;
		new USKMASKNDASLKDNB("Now Choose Your Target [Press on Target Cell]");
		return;
		}
	
	if (choosing_attackbool == true && gotAttackerPoint == true && GotTargetPoints == false){
		gettargetP(Pressed.getName());
		GotTargetPoints = true;
			
	}
 
	//Getting Target Attacked Cell i and j 
		if (GotTargetPoints == true && gotAttackerPoint == true && choosing_attackbool == true ){
			if (Pressed != null){
				try{
			 				Cell BOARDSELa =  (Cell) (Model.getBoard()[Attackeri][Attackerj] );
				Piece  BOARDSELPiecea = (Piece)BOARDSELa.getPiece();
				new USKMASKNDASLKDNB("Your Attacker Piece : i : " + Attackeri + " j : "+Attackerj);
				new USKMASKNDASLKDNB("Target Piece : i : " + Targeti + " j : "+Targetj);
				this.Attack(BOARDSELPiecea, Targeti, Targetj);
				GotTargetPoints = false ;
				gotAttackerPoint = false ;
				 choosing_attackbool =false;
				return;
				}catch(Exception e){}
			}
		
		}
		 
		//Getting Attacker to Parse to Attack the Target
	
	
	if(Pressed.getText().contains("p1ch") || (Pressed.getText().contains("p2ch"))){
	
			pi = Integer.parseInt(Pressed.getName().split("splitter")[0]);pj = Integer.parseInt(Pressed.getName().split("splitter")[1]);
	
			Cell BOARDSEL =  (Cell) (Model.getBoard()[pi][pj] );
			Piece  BOARDSELPiece = (Piece)BOARDSEL.getPiece();
			if(Model.getCurrentPlayer().getName().equals(BOARDSELPiece.getOwner().getName()) == false){
				new USKMASKNDASLKDNB(" Note : This is  " + Model.getCurrentPlayer().getName() + " `s Turn .");
				pi = 90;
				pj = 90;
				return;
			}
			UpdateCELLFullData(BOARDSELPiece);
		

	}
	
	
	
}
private void getAttackP(String n){
	try{
		pi = Integer.parseInt(n.split("splitter")[0]);pj = Integer.parseInt(n.split("splitter")[1]);
		Attackeri = pi;  Attackerj = pj;
		
	}catch(Exception e){}
	
}
private void gettargetP(String n){
	try{
		pi = Integer.parseInt(n.split("splitter")[0]);pj = Integer.parseInt(n.split("splitter")[1]);
		Targeti = pi;  Targetj = pj;
		
	}catch(Exception e){}
	
}
private void Attack(Piece Attacker,int needi , int needj){
	if (Attacker instanceof Medic){
		choosemedirection(Attacker,needi,needj);
		UpdateAfterAttack();
	 }else if (Attacker instanceof Ranged){
		ChooseRanged(Attacker,needi,needj);
		UpdateAfterAttack();
	}else if (Attacker instanceof Super){
		Choosesuper(Attacker,needi,needj);
		UpdateAfterAttack();
	}else if (Attacker instanceof Tech){
		Choosetech(Attacker,needi,needj);
		UpdateAfterAttack();
	}else if (Attacker instanceof Speedster){
		new USKMASKNDASLKDNB("Your Attacker Piece is Speedster , Not Activatable Power Hero ");
	}
	else if (Attacker instanceof Armored){
		new USKMASKNDASLKDNB("Your Attacker Piece is Armored , Not Activatable Power Hero ");
	}else{
		new USKMASKNDASLKDNB("Error");
	}
	 

}
private void choosemedirection(Piece Attacker,int i , int j){
	AttackDirectionGUI = new ATCKdirectioninput();
	Medic(Attacker,i,j,AttackDirectionGUI.getT());
	AttackDirectionGUI = null;
	
}
private void Medic(Piece Attacker,int i , int j,Direction d){
	if (((Medic)Attacker).isPowerUsed() == false){
		Point newPos = new Point(i,j);
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Medic)Attacker).usePower(d, target, newPos);
		} catch (InvalidPowerUseException | WrongTurnException e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	
	}else{
		new USKMASKNDASLKDNB("Medic Power is Used once");
	}
}
//choose power or attack
private void ChooseRanged(Piece Attacker,int i , int j){
	String OP = "";
	ATCKdirectioninputRanged = new ATCKdirectioninputRanged();
	OP = ATCKdirectioninputRanged.getT();
	if (OP.equals("0")){
		choosemedirectionranged(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}else if (OP.equals("1")){
		RangedAttack(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}
}
private void choosemedirectionranged(Piece Attacker,int i , int j){
	AttackDirectionGUI = new ATCKdirectioninput();
	RangedPowerUse(Attacker,i,j,AttackDirectionGUI.getT());
	AttackDirectionGUI = null;
}
private void RangedPowerUse(Piece Attacker,int i , int j,Direction d){
	if (((Ranged)Attacker).isPowerUsed() == false){
		Point newPos = new Point(i,j);
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Ranged)Attacker).usePower(d, target, newPos);
		} catch (InvalidPowerUseException | WrongTurnException e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	
	}else{
		new USKMASKNDASLKDNB("Ranged Power is Used once");
	}
}
private void RangedAttack(Piece Attacker,int i , int j){
	if (((Ranged)Attacker).isPowerUsed() == false){
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Ranged)Attacker).attack(target);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
		
	}else{
		new USKMASKNDASLKDNB("Medic Power is Used once");
	}
}
 //super
//choose power or attack
private void Choosesuper(Piece Attacker,int i , int j){
	String OP = "";
	ATCKdirectioninputRanged = new ATCKdirectioninputRanged();
	OP = ATCKdirectioninputRanged.getT();
	if (OP.equals("0")){
		choosemedirectionsuper(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}else if (OP.equals("1")){
		superAttack(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}
}
private void choosemedirectionsuper(Piece Attacker,int i , int j){
	AttackDirectionGUI = new ATCKdirectioninput();
	superPowerUse(Attacker,i,j,AttackDirectionGUI.getT());
	AttackDirectionGUI = null;
}
private void superPowerUse(Piece Attacker,int i , int j,Direction d){
	if (((Super)Attacker).isPowerUsed() == false){
		Point newPos = new Point(i,j);
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Super)Attacker).usePower(d, target, newPos);
		} catch (InvalidPowerUseException | WrongTurnException e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	
	}else{
		new USKMASKNDASLKDNB("Ranged Power is Used once");
	}
}
private void superAttack(Piece Attacker,int i , int j){
	if (((Super)Attacker).isPowerUsed() == false){
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Super)Attacker).attack(target);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	 
		UpdateBoardCELLs();
	}else{
		new USKMASKNDASLKDNB("Medic Power is Used once");
	}
}
 //Tech
//choose power or attack
private void Choosetech(Piece Attacker,int i , int j){
	String OP = "";
	ATCKdirectioninputRanged = new ATCKdirectioninputRanged();
	OP = ATCKdirectioninputRanged.getT();
	if (OP.equals("0")){
		choosemedirectiontech(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}else if (OP.equals("1")){
		techAttack(Attacker,i,j);
		ATCKdirectioninputRanged = null;
	}
}
private void choosemedirectiontech(Piece Attacker,int i , int j){
	AttackDirectionGUI = new ATCKdirectioninput();
	techPowerUse(Attacker,i,j,AttackDirectionGUI.getT());
	AttackDirectionGUI = null;
}
private void techPowerUse(Piece Attacker,int i , int j,Direction d){
	if (((Tech)Attacker).isPowerUsed() == false){
		Point newPos = new Point(i,j);
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Tech)Attacker).usePower(d, target, newPos);
		} catch (InvalidPowerUseException | WrongTurnException e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	 
	}else{
		new USKMASKNDASLKDNB("Tech Power is Used once");
	}
}
private void techAttack(Piece Attacker,int i , int j){
	if (((Tech)Attacker).isPowerUsed() == false){
		Cell BOARDSEL =  (Cell) (Model.getBoard()[i][j] );
		Piece  target = (Piece)BOARDSEL.getPiece();
		try {
			((Tech)Attacker).attack(target);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			new USKMASKNDASLKDNB(e.getMessage());
		}
	
		 
	}else{
		new USKMASKNDASLKDNB("Tech Power is Used once");
	}
}
private void UpdateAfterAttack(){
	UpdateBoardCELLs();
	if((Model.getPlayer1().equals(Model.getCurrentPlayer()))){
		Model.setCurrentPlayer(player2);
	}else if((Model.getPlayer2().equals(Model.getCurrentPlayer()))){
		Model.setCurrentPlayer(player1);
	}
	UpdateBoardCELLs();
}
private void UpdateAfterMove(){
	 
	Model.switchTurns();
	UpdateBoardCELLs();
}
public static void main(String[] args) {
	new ChessController();
}
	
	
	
}


class CUSKMASKNDASLKDNBUTT extends JPanel {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDNBUTT(String PATHXOIMG) {

 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDNBUTT(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));
 setPreferredSize(IMGSIZEX);
 setMaximumSize(IMGSIZEX);
 setMinimumSize(IMGSIZEX);
 setSize(IMGSIZEX);
 
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}

class CUSKMASKNDASLKDNBUTTx1 extends JPanel {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDNBUTTx1(String PATHXOIMG) {

 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDNBUTTx1(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));
 setPreferredSize(IMGSIZEX);
 setMaximumSize(IMGSIZEX);
 setMinimumSize(IMGSIZEX);
 setSize(IMGSIZEX);
 
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}

class CUSKMASKNDASLKDNBUTTx1x extends JPanel {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDNBUTTx1x(String PATHXOIMG) {

 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDNBUTTx1x(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));
 setPreferredSize(IMGSIZEX);
 setMaximumSize(IMGSIZEX);
 setMinimumSize(IMGSIZEX);
 setSize(IMGSIZEX);
 
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}
class CUSKMASKNDASLKDNBUTTx1x1 extends JPanel {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDNBUTTx1x1(String PATHXOIMG) {

 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDNBUTTx1x1(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));

 setSize(1200, 100);

 
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}
class CUSKMASKNDASLKDNBUTxor extends JButton {

private Image asjdkasodfmkodsanmfa;

public CUSKMASKNDASLKDNBUTxor(String PATHXOIMG) {

 this(new ImageIcon(PATHXOIMG).getImage());
}

public CUSKMASKNDASLKDNBUTxor(Image PATHXOIMG1) {
 this.asjdkasodfmkodsanmfa = PATHXOIMG1;
 Dimension IMGSIZEX = new Dimension(PATHXOIMG1.getWidth(null), PATHXOIMG1.getHeight(null));
 setPreferredSize(IMGSIZEX);
 setMaximumSize(IMGSIZEX);
 setMinimumSize(IMGSIZEX);
 setSize(IMGSIZEX);
 
}

public void paintComponent(Graphics g) {
 g.drawImage(asjdkasodfmkodsanmfa, 0, 0, null);
}

}
class USKMASKNDASLKDNB{public USKMASKNDASLKDNB(String infoUSKMASKNDASLKDNBMesUSKMASKNDASLKDNBsage)
{JOptionPane.showMessageDialog(null, infoUSKMASKNDASLKDNBMesUSKMASKNDASLKDNBsage, "Chess Game", JOptionPane.INFORMATION_MESSAGE); }}

//Custom MessageInputBox

class ATCKdirectioninput
{

private Direction attackDirection;


public Direction getT() {//place to Damaging spell to use in 
	return  attackDirection;
}


public void SetListener(){
	
}

public ATCKdirectioninput()
{
	String [] Directions = {"Up","Down","Right","Left","Up Left","Up Right","Down Left","Down Right"};
// a jframe here isn't strictly necessary, but it makes the example a little more real
JFrame frame = new JFrame("Choose Direction");

String neededDirection = (String) JOptionPane.showInputDialog(frame, 
        "Enter Direction for Power Use",
        "Using Attack",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        Directions, 
        Directions[0]);
if(neededDirection.equals("Up")){
	  attackDirection = Direction.UP;
}
if(neededDirection.equals("Down")){
	  attackDirection = Direction.DOWN;
}
if(neededDirection.equals("Right")){
	  attackDirection = Direction.RIGHT;
}
	if(neededDirection.equals("Left")){
		 attackDirection = Direction.LEFT;
	}

	if(neededDirection.equals("Up Left")){
		 attackDirection = Direction.UPLEFT;
	}

	if(neededDirection.equals("Up Right")){
		 attackDirection = Direction.UPRIGHT;
	}

	if(neededDirection.equals("Down Left")){
		 attackDirection = Direction.DOWNLEFT;
	}

	if(neededDirection.equals("Down Right")){
		 attackDirection = Direction.DOWNRIGHT;
	}
	if(neededDirection.equals(null)){
	  	new  USKMASKNDASLKDNB("Please Choose One Direction to use ATTACK For");
	}







}
}

//Custom MessageInputBox

class ATCKdirectioninputRanged
{

private String  or1;


public String getT() {//place to Damaging spell to use in 
	return  or1;
}


public void SetListener(){
	
}

public ATCKdirectioninputRanged()
{
	String [] Directions = {"Power Use","Attack"};
//a jframe here isn't strictly necessary, but it makes the example a little more real
JFrame frame = new JFrame("Choose Method");

String neededDirection = (String) JOptionPane.showInputDialog(frame, 
      "Enter Method to use ",
      "Attack / use power",
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      Directions, 
      Directions[0]);
if(neededDirection.equals("Power Use")){
	or1 = "0";
}
if(neededDirection.equals("Attack")){
	or1 ="1";
}
	if(neededDirection.equals(null)){
	  	new  USKMASKNDASLKDNB("Please Choose Power use Or Attack");
	}







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

