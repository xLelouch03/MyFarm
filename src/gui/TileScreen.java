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
                Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");
                String cropName = (String) crop;

                JOptionPane.showMessageDialog(tileFrame, player.getFarm().plantSeed(cropName, row, col));
                setFarmStatus();
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

                useTool();
				
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
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().harvestTile(player.getTile(row, col)));
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
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().displayTileInfo(player.getTile(row, col)));
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
    
    public void useTool() {
        delay();
        if(toolName.equals("Pickaxe")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePickaxe(player.getTile(row, col), 
                                                        player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Shovel")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useShovel(player.getTile(row, col), 
                                                        player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Plow")){
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePlow(player.getTile(row, col), 
            player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Watering can")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useWaterCan(player.getTile(row, col), 
            player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Fertilizer")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useFertilizer(player.getTile(row, col), 
            player.getFarm().getTool(toolName)));
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
