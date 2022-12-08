package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

// import java.awt.event.*;
// import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen{
    private JFrame mainFrame;
    private Main player;
	private JButton[][] tileButtons;
	private TileScreen[][] tileScreenArray;
	private JLabel expLabel;
	private JLabel dayLabel;
	private JLabel levelLabel;
	private JLabel typeLabel;
	private JLabel availableSpaceLabel;
	private JLabel farmerNameLabel;
	private JLabel objectCoinLabel;	
	private int rowIndex, colIndex;	

    public MainScreen(Main player) {
        this.player = player;
		initialize();
		mainFrame.setVisible(true);
    }

    public void initialize() {
        mainFrame = new JFrame("My Farm - Main Screen"); //instantiates a frame
        mainFrame.setBounds(0, 0, 1540, 823); 
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		mainFrame.getContentPane().setLayout(null);

		initTopPanel();
		initLeftPanel();
		initRightPanel();
    }

	public void initTopPanel() {
		JPanel topPanel = new JPanel();
        topPanel.setBounds(10,11,1500,80);
        topPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainFrame.getContentPane().add(topPanel);
		topPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Farm Status");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setBounds(10, 11, 185, 24);
		topPanel.add(titleLabel);

        farmerNameLabel = new JLabel("Farmer Name: "+ player.getFarm().getFarmer().getName()); //insert farmer.getLevel();
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameLabel.setBounds(380, 11, 347, 24);
		topPanel.add(farmerNameLabel);

        expLabel = new JLabel("Farmer XP: " + player.getFarm().getFarmer().getXP()); //insert farmer.getType();
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		expLabel.setBounds(750, 11, 347, 24);
		topPanel.add(expLabel);

		typeLabel = new JLabel("Farmer Type: " + player.getFarm().getFarmer().getType()); //insert farmer.getXP();
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		typeLabel.setBounds(1150, 11, 347, 24);
		topPanel.add(typeLabel);

        availableSpaceLabel = new JLabel("Number of available tiles: " + player.getFarm().getAvailableSpace());//insert farm.getSpace() *to be implemented pa
		availableSpaceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availableSpaceLabel.setBounds(10, 43, 237, 24);
		topPanel.add(availableSpaceLabel);

        levelLabel = new JLabel("Farmer Level: " + player.getFarm().getFarmer().getLevel()); 
		levelLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		levelLabel.setBounds(380, 43, 347, 24);
		topPanel.add(levelLabel);
        
        objectCoinLabel = new JLabel("Objectcoins: "+ player.getFarm().getFarmer().getCoins());
		objectCoinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		objectCoinLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		objectCoinLabel.setBounds(630, 43, 347, 24);
		topPanel.add(objectCoinLabel);

        dayLabel = new JLabel("Day: " + player.getFarm().getDay()); 
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLabel.setBounds(1080, 43, 185, 24);
		topPanel.add(dayLabel);
	}

	public void initLeftPanel() {
		JPanel leftPanel = new JPanel();
        leftPanel.setBounds(10,100,350,670);
        leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainFrame.getContentPane().add(leftPanel);
		leftPanel.setLayout(null);

		JButton nextDayButton = new JButton("Advance to Next Day");
		nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.nextDay();
				dayLabel.setText("Day: " + player.getFarm().getDay()); //changes what is displayed on the mainFrame
				setFarmStatus();
            }
        });
		nextDayButton.setBounds(35, 11, 268, 50);
		leftPanel.add(nextDayButton);
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		//replace with View All Seeds
		JButton viewPlantButton = new JButton("View all Seeds");
		viewPlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

				SeedInfoScreen seedInfo = new SeedInfoScreen();
				
            }
        });
		viewPlantButton.setBounds(35, 90, 268, 50);
		leftPanel.add(viewPlantButton);
		viewPlantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		// TODO: "View All Seeds"
			// Plant name lists (dropdown) [/]
				// Show "see seed info" and "plant" buttons
					// do action based on button selected
					// see seed info = show a pop up w the seed info

		/* 
		JButton seedInfoButton = new JButton("Seed Info");
		seedInfoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){

				Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");
				String cropName = (String) crop;

				switch (cropName) {
					
					case "Turnip":
						// create pop up window with details
						break;
					
					case "Rose":
						//
						break;

					// ++
						
				}

			}

		});
		plantButton.setBounds(10, 40, 268, 50);
		leftPanel.add(seedInfoButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		*/

		/*
		JButton plantSeedButton = new JButton("Plant Seed");
		seedInfoButton.addActionListener(new ActionListener() {

			//

		});
		plantButton.setBounds(10, 40, 268, 50);
		leftPanel.add(plantSeedButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		*/

		//replace with View All Tools
		JButton viewToolButton = new JButton("View all Tools");
		viewToolButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) {
				//Object tool = JOptionPane.showInputDialog(null, "Choose a tool", "Tool Selection", JOptionPane.QUESTION_MESSAGE,null, player.getToolNames(), "Plow");
                //string toolName = (String) tool;

				//player.useTool(toolName, tileNum);
				//toolName = JOptionPane.showInputDialog("What tool do you want to use?");
				/*if(toolName.equalsIgnoreCase("plow")) {
					player.getFarm().usePlow(player.getTile(tileNum), player.getFarm().getTool(toolName));
				}
				else if(toolName.equalsIgnoreCase("watering can"))
					player.getFarm().useWaterCan(player.getTile(tileNum), player.getFarm().getTool(toolName));
				else if(toolName.equalsIgnoreCase("fertilizer"))
					player.getFarm().useFertilizer(player.getTile(tileNum), player.getFarm().getTool(toolName));*/
				
				setFarmStatus();
            }
        });
		viewToolButton.setBounds(35, 175, 268, 50);
		leftPanel.add(viewToolButton);
		viewToolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton registerButton = new JButton("Register Farmer");
		registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Object type = JOptionPane.showInputDialog(null, "Register Farmer", "Farmer Type Selection", 
				JOptionPane.QUESTION_MESSAGE,null, player.getTypes(), "Registered Farmer");

				String farmerType = (String) type;

				JOptionPane.showMessageDialog(mainFrame,  player.getFarm().registerFarmer(farmerType));
				setFarmStatus();
            }
        });
		registerButton.setBounds(35, 255, 268, 50);
		leftPanel.add(registerButton);
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	}

	public void initRightPanel() {
		int i,j;
		JPanel rightPanel = new JPanel();
        rightPanel.setBounds(375,100,1135,670);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		rightPanel.setLayout(new GridLayout(5,10));
		mainFrame.getContentPane().add(rightPanel);
		int tileNum = 0;
		tileButtons = new JButton[10][5];
		tileScreenArray = new TileScreen[10][5];
		for(i = 0; i < 10; i++) {
			for(j = 0; j < 5; j++) {
				int row = i;
				int col = j;
				int num = tileNum;
				
				if(player.getFarm().getFarmLot(row, col).getRockedStatus() == true) {
					ImageIcon icon = new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\tile with rock.png");
					tileButtons[i][j] = new JButton(""+(num+1), icon);
					tileButtons[i][j].setVerticalTextPosition(JButton.TOP);
    				tileButtons[i][j].setHorizontalTextPosition(JButton.CENTER);
					tileButtons[i][j].setBorderPainted(true);
					//tileButtons[i][j].setIcon(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\tile with rock.png"));
				}
				else {
					tileButtons[i][j] = new JButton(""+(num+1));
					tileButtons[i][j].setBackground(new Color(255,222,173));
				}
				tileButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tileScreenArray[row][col] = new TileScreen(getMain(), player, row, col, num);
						rowIndex = row;
						colIndex = col;
					}
				});
				rightPanel.add(tileButtons[i][j]);
				tileNum++;
			}
		}
	}

	public JButton getTileButton(int row, int col) {
		return tileButtons[row][col];
	}

	public void closeTileScreen(int rowIndex, int colIndex) {
		tileScreenArray[rowIndex][colIndex].closeTileFrame();		
	}

	public void mainSetEnabled(boolean b) {
		mainFrame.setEnabled(b);
	}
	
	public void checkLevel() {
        int tempLevel = player.getFarm().getFarmer().getLevel();
        
		player.getFarm().getFarmer().setLevel((int)player.getFarm().getFarmer().getXP() / 100); 
        //level = (int) player.getFarm().getFarmer().getXP() / 100;

        if(tempLevel < player.getFarm().getFarmer().getLevel())
            JOptionPane.showMessageDialog(mainFrame, "You have leveled up!");
    }

	//updates what is being displayed on the mainFrame Farm Status top panel
	public void setFarmStatus() {
		checkLevel();
		typeLabel.setText("Farmer Type: " + player.getFarm().getFarmer().getType());
		expLabel.setText("Farmer XP: " + player.getFarm().getFarmer().getXP());
		levelLabel.setText("Farmer Level: " + player.getFarm().getFarmer().getLevel());
		objectCoinLabel.setText("Objectcoins: "+ player.getFarm().getFarmer().getCoins());
		availableSpaceLabel.setText("Number of available tiles: " + player.getFarm().getAvailableSpace());

		if(!player.getFarm().isRunning()) {
			JOptionPane.showMessageDialog(mainFrame, player.getFarm().gameEnded());
            if(JOptionPane.showConfirmDialog(mainFrame, "Do you want to play again?", 
				"Game has ended", JOptionPane.YES_NO_OPTION) == 1) {
				closeTileScreen(rowIndex, colIndex);
                closeFrame();
				
            }
            else {
				closeTileScreen(rowIndex,colIndex);
                closeFrame();
                player.openSetupScreen();
            }
        } 
	}

	//closes the frame
    public void closeFrame() {
        mainFrame.dispose();
    }

	//returns this instance of MainScreen
	public MainScreen getMain() {
		return this;
	}

	public JLabel getAvailSpace() {
		return availableSpaceLabel;
	}
	//returns the exp label
	public JLabel getExpLabel() {
		return expLabel;
	}

	//returns the level label
	public JLabel getLevelLabel() {
		return levelLabel;
	}

	//returns the objectcoin label
	public JLabel getCoinLabel() {
		return objectCoinLabel;
	}
}
