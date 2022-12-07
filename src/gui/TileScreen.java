package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.FarmLot;
import main.Main;
import main.Seed;

public class TileScreen {
    private MainScreen mainFrame;
    private Main player;
    private JFrame tileFrame;
    private int row, col, num;
    private String toolName;

    public TileScreen(MainScreen frame, Main player, int row, int col, int num) {
        this.mainFrame = frame;
        this.player = player;
        this.row = row;
        this.col = col;
        this.num = num;
        initialize();
		tileFrame.setVisible(true);
    }

    public void initialize() {
        tileFrame = new JFrame("FarmLot #" + (num+1) + " Screen");
        tileFrame.setBounds(680, 280, 500, 400);
		tileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tileFrame.getContentPane().setLayout(null);

        initPanel();
    }

    public void initPanel() {
        JLabel welcomeLabel = new JLabel("Choose an action to perform on this tile");
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(40, 5, 575, 44);
		tileFrame.getContentPane().add(welcomeLabel);

        JPanel tilePanel = new JPanel();
        tilePanel.setBounds(40,45,400,300);
        tilePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tileFrame.getContentPane().add(tilePanel);
		tilePanel.setLayout(null);

        JButton plantButton = new JButton("Plant a Seed");
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getTile(row, col).getPlowStatus() == true) {
                    if(player.getTile(row, col).getOccupied() == false) {
                        Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");
                        String cropName = (String) crop;

                        Seed seed = player.getFarm().getSeed(cropName);
                        if(seed.getType().equals("Fruit tree")) {
                            if(player.getFarm().getAdjacentCount(row, col) != 0) {
                                JOptionPane.showMessageDialog(tileFrame, "You can't plant " + 
                                player.getFarm().getSeed(cropName).getName() + " because adjacent tiles are occupied");
                            }
                            else {
                                player.getFarm().plantSeed(player.getTile(row, col), cropName);
                                int cost = player.getTile(row, col).getSeed().getCost();
                                
                                JOptionPane.showMessageDialog(tileFrame, player.getTile(row, col).getSeed().getName() +
                                    " has been planted.\n You lost " + cost +
                                    " objectCoins");
                                setFarmStatus();
                            }

                        }
                        else {
                            player.getFarm().plantSeed(player.getTile(row, col), cropName);
                            int cost = player.getTile(row, col).getSeed().getCost();
                            
                            JOptionPane.showMessageDialog(tileFrame, player.getTile(row, col).getSeed().getName() +
                                " has been planted.\n You lost " + cost +
                                " objectCoins");
                            setFarmStatus();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(tileFrame, "This tile already contains a plant!");
                    }
                }
                else 
                    JOptionPane.showMessageDialog(tileFrame, "This tile is not yet plowed");
            }
        });
		plantButton.setBounds(60, 75, 268, 50);
		tilePanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton toolButton = new JButton("Use a Tool");
		toolButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) {
				Object tool = JOptionPane.showInputDialog(null, "Choose a tool", "Tool Selection", JOptionPane.QUESTION_MESSAGE,null, player.getToolNames(), "Plow");
                toolName = (String) tool;

				player.useTool(toolName, row, col);
                toolFeedBack();
                   
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
		toolButton.setBounds(60, 10, 268, 50);
		tilePanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( player.getTile(row, col).getOccupied() == true) {
                    if(player.getTile(row, col).getHarvestStatus() == true) {
                        player.getFarm().harvestTile(player.getFarm().getFarmLot(row, col));
                        JOptionPane.showMessageDialog(tileFrame, player.getTile(row, col).getSeed().getName() + 
                        " has been harvested!\n");
                        //after harvesting, remove the seed and reset the farmlot
                        //player.getTile(index).getSeed().resetSeed();
                        player.getTile(row, col).resetFarmLot();
                    }
                    else {
                        JOptionPane.showMessageDialog(tileFrame, player.getTile(row, col).getSeed().getName() +
                            " is not yet harvestable.");
                    }
                }
                else
                    JOptionPane.showMessageDialog(tileFrame, "There is no seed in this tile.");
                    
				setFarmStatus();
            }
        });
		harvestButton.setBounds(60, 140, 268, 50);
		tilePanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton tileInfoButton = new JButton("Display Tile Information");
        tileInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //JOptionPane.showMessageDialog(null, player.farm.displayTileInfo);
                
            }
        });
        tileInfoButton.setBounds(60, 205, 268, 50);
        tilePanel.add(tileInfoButton);
		tileInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileFrame.dispose();
            }
        });
        closeButton.setBounds(160, 265, 75,30);
        tilePanel.add(closeButton);
        closeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
	}

    public void delay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void toolFeedBack() {
        if(toolName.equals("Pickaxe")) {
            if(player.getTile(row, col).getRockedStatus() == true){
                player.getTile(row, col).isRocked(false);
                delay();
                JOptionPane.showMessageDialog(tileFrame, "You have removed the rock on this tile!" + 
                    "\nYou lost " + player.getFarm().getTool(toolName).getCost() + " objectCoins!" + 
                    "\nYou have earned: " + player.getFarm().getTool(toolName).getXP() + " experience!");
            }
            else if(player.getTile(row, col).getRockedStatus() == false)
                JOptionPane.showMessageDialog(tileFrame, "This tile does not have a rock");
        }

        else if(toolName.equals("Shovel")) {
            
            System.out.println("still not fixed");
                
        }

        else if (!toolName.equals("Pickaxe") &&
                 !toolName.equals("Shovel")) {
            if(player.getTile(row, col).getRockedStatus() == false) {
                if(toolName.equals("Plow")) {
                    if(player.getTile(row, col).getPlowStatus() == false) {
                        player.getTile(row, col).isPlowed(true);
                        delay();
                        JOptionPane.showMessageDialog(tileFrame, "You have plowed this tile." + 
                        "\nYou have earned: " + player.getFarm().getTool(toolName).getXP() + " experience!");
                    }
                    else if(player.getTile(row, col).getPlowStatus() == true){
                        if(player.getTile(row, col).getWitherStatus() == true) 
                            JOptionPane.showMessageDialog(tileFrame, "This tile contains a withered plant!");
                        else 
                            JOptionPane.showMessageDialog(tileFrame, "You have already plowed this tile!");
                    }
                } 
                
                else if(player.getTile(row, col).getPlowStatus() == true) {
                    if(player.getTile(row, col).getOccupied() == true) {
                        if(player.getTile(row, col).getWitherStatus() == false) {
                            if(toolName.equals("Watering can")) {
                                delay();
                                JOptionPane.showMessageDialog(tileFrame, "You have watered this tile!" + 
                                        "\nYou have earned: " + player.getFarm().getTool(toolName).getXP() + " experience!");
                            }
                            else if(toolName.equals("Fertilizer")) {
                                delay();
                                JOptionPane.showMessageDialog(tileFrame, "You have used a fertilizer on this tile!" + 
                                        "\nYou lost " + player.getFarm().getTool(toolName).getCost() + " objectCoins!" + 
                                        "\nYou have earned: " + player.getFarm().getTool(toolName).getXP() + " experience!");
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(tileFrame, "This tile contains a withered plant!");
                    }
                    else 
                        JOptionPane.showMessageDialog(tileFrame, "This tile does not have any plant!");
                }
                else
                    JOptionPane.showMessageDialog(tileFrame, "This tile is not yet plowed!");    
            }
            else 
                JOptionPane.showMessageDialog(tileFrame, "This tile contains a rock");
        }
    }

    public void checkLevel() {
        int tempLevel = player.getFarmerLevel();
        int level = (int) player.getFarmerXP() / 100;
        player.getFarm().getFarmer().setLevel(level);

        if(tempLevel < level)
            JOptionPane.showMessageDialog(tileFrame, "You have leveled up!");
    }

    public void setFarmStatus() { 
        checkLevel();
        mainFrame.getAvailSpace().setText("Number of available tiles: " + player.getFarm().getAvailableSpace());
		mainFrame.getExpLabel().setText("Farmer XP: " + player.getFarmerXP());
		mainFrame.getLevelLabel().setText("Farmer Level: " + player.getFarmerLevel());
		mainFrame.getCoinLabel().setText("Objectcoins: "+ player.getFarmerCoins());

        
        if(!player.getFarm().isRunning()) {
			if(player.getFarm().getWitherCount() == 50) {
				JOptionPane.showMessageDialog(tileFrame, "All of your tiles contain a withered plant");
			}

			else if(player.getFarm().getAvailableSpace() == 50 && player.getFarmerCoins() < 5){
				JOptionPane.showMessageDialog(tileFrame, "You don't have enough objectCoins to buy a seed" +
				"\nYou also don't have any active crops");
			}

            if(JOptionPane.showConfirmDialog(tileFrame, "Do you want to play again?", 
				"Game has ended", JOptionPane.YES_NO_OPTION) == 1) {
                    tileFrame.dispose();
                    mainFrame.closeFrame();
            }
            else {
                tileFrame.dispose();
                mainFrame.closeFrame();
                player.openSetupScreen();
            }
        } 

	}

    
}
