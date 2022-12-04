package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JLabel expLabel;
	private JLabel dayLabel;
	private JLabel levelLabel;
	private JLabel typeLabel;
	private JLabel availableSpaceLabel;
	private JLabel farmerNameLabel;
	private JLabel objectCoinLabel;
	private int tileNum = 0;

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

        farmerNameLabel = new JLabel("Farmer Name: "+ player.getFarmerName()); //insert farmer.getLevel();
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameLabel.setBounds(380, 11, 347, 24);
		topPanel.add(farmerNameLabel);

        expLabel = new JLabel("Farmer XP: " + player.getFarmerXP()); //insert farmer.getType();
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		expLabel.setBounds(750, 11, 347, 24);
		topPanel.add(expLabel);

		typeLabel = new JLabel("Farmer Type: " + player.getFarmerType()); //insert farmer.getXP();
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		typeLabel.setBounds(1150, 11, 347, 24);
		topPanel.add(typeLabel);

        availableSpaceLabel = new JLabel("Number of available tiles: " + player.getFarmSpace());//insert farm.getSpace() *to be implemented pa
		availableSpaceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availableSpaceLabel.setBounds(10, 43, 237, 24);
		topPanel.add(availableSpaceLabel);

        levelLabel = new JLabel("Farmer Level: " + player.getFarmerLevel()); //insert farmer.getName();
		levelLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		levelLabel.setBounds(380, 43, 347, 24);
		topPanel.add(levelLabel);
        
        objectCoinLabel = new JLabel("Objectcoins: "+ player.getFarmerCoins());
		objectCoinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		objectCoinLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		objectCoinLabel.setBounds(630, 43, 347, 24);
		topPanel.add(objectCoinLabel);

        dayLabel = new JLabel("Day: " + player.getDayCount()); 
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
				dayLabel.setText("Day: " + player.getDayCount()); //changes what is displayed on the mainFrame
            }
        });
		nextDayButton.setBounds(35, 11, 268, 50);
		leftPanel.add(nextDayButton);
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		//replace with View All Seeds
		JButton plantButton = new JButton("View all Seeds");
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");

				String cropName = (String) crop;
				player.selectSeed(cropName, tileNum);
				setFarmStatus();

            }
        });
		plantButton.setBounds(35, 90, 268, 50);
		leftPanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		// TODO: "View All Seeds"
			// Plant name lists (dropdown) [/]
				// Show "see seed info" and "plant" buttons
					// do action based on button selected
					// see seed info = show a pop up w the seed info

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

		JButton plantSeedButton = new JButton("Plant Seed");
		seedInfoButton.addActionListener(new ActionListener() {

			//

		});
		plantButton.setBounds(10, 40, 268, 50);
		leftPanel.add(plantSeedButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));


		//replace with View All Tools
		JButton toolButton = new JButton("View all Tools");
		toolButton.addActionListener(new ActionListener() { 
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
		toolButton.setBounds(35, 175, 268, 50);
		leftPanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		//remove
		/*
		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getFarm().harvestTile(player.getFarm().getFarmLot(tileNum));
				setFarmStatus();
            }
        });
		harvestButton.setBounds(35, 255, 268, 50);
		leftPanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		*/

	}

	public void initRightPanel() {
		int i,j;
		JPanel rightPanel = new JPanel();
        rightPanel.setBounds(375,100,1135,670);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		rightPanel.setLayout(new GridLayout(10,5));
		mainFrame.getContentPane().add(rightPanel);

		tileButtons = new JButton[10][5];
		int index = 0;
		for(i = 0; i < 10; i++) {
			for(j = 0; j < 5; j++) {
				int num = index;
				tileButtons[i][j] = new JButton(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\farmlot.jpg"));
				tileButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new TileScreen(getMain(),player, num);
						/*player.getTile(num);
						tileNum = num;
						if(player.getTile(num).getSeed() != null) {
							JOptionPane.showMessageDialog(rightPanel, "FarmLot#" + (num+1) + " Information" + "\nPlow Status: " + player.getTile(num).getPlowStatus() + "\nSeed Planted: " + player.getTile(num).getSeed().getName() +
						"\nWater Count: " + player.getTile(num).getWaterCount() + "\nFertilizer Count: " + player.getTile(num).getFertilizerCount() +
						"\nDays Growed: " + player.getTile(num).getSeed().getDayGrowth() + "\nHarvestable: " + player.getTile(num).isHarvestable() +
						"\nHas a withered plant: " + player.getTile(num).getWitherStatus());
						}
						else {
							JOptionPane.showMessageDialog(rightPanel, "FarmLot#" + (num+1) + " Information" + "\nPlow Status: " + player.getTile(num).getPlowStatus());
						}*/
					}
				});
				rightPanel.add(tileButtons[i][j]);
				index++;
			}
		}
	}
	
	//updates what is being displayed on the mainFrame Farm Status top panel
	public void setFarmStatus() {
		expLabel.setText("Farmer XP: " + player.getFarmerXP());
		levelLabel.setText("Farmer Level: " + player.getFarmerLevel());
		objectCoinLabel.setText("Objectcoins: "+ player.getFarmerCoins());
	}

	//closes the frame
    public void closeFrame() {
        mainFrame.dispose();
    }

	//returns this instance of MainScreen
	public MainScreen getMain() {
		return this;
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

	public void displayPlow() {
		
	}
}
