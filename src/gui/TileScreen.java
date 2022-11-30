package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.Main;

public class TileScreen {
    private MainScreen mainFrame;
    private Main player;
    private JFrame tileFrame;
    private int index;

    public TileScreen(MainScreen frame, Main player, int index) {
        this.mainFrame = frame;
        this.player = player;
        this.index = index;
        initialize();
		tileFrame.setVisible(true);
    }

    public void initialize() {
        tileFrame = new JFrame("FarmLot #" + (index+1) + " Screen");
        tileFrame.setBounds(0, 0, 500, 400);
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

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(40,45,400,300);
        leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tileFrame.getContentPane().add(leftPanel);
		leftPanel.setLayout(null);

        JButton plantButton = new JButton("Plant a Seed");
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");
				String cropName = (String) crop;
				player.selectSeed(cropName, index);
				setFarmStatus();
            }
        });
		plantButton.setBounds(50, 30, 268, 50);
		leftPanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton toolButton = new JButton("Use a Tool");
		toolButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) {
				Object tool = JOptionPane.showInputDialog(null, "Choose a tool", "Tool Selection", JOptionPane.QUESTION_MESSAGE,null, player.getToolNames(), "Plow");
                String toolName = (String) tool;

				player.useTool(toolName, index);
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
		toolButton.setBounds(50, 95, 268, 50);
		leftPanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getFarm().harvestTile(player.getFarm().getFarmLot(index));
				setFarmStatus();
            }
        });
		harvestButton.setBounds(50, 160, 268, 50);
		leftPanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton tileInfoButton = new JButton("Display Tile Information");
        tileInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tileInfoButton.setBounds(50, 225, 268, 50);
        leftPanel.add(tileInfoButton);
		tileInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

	}

    public void setFarmStatus() {
		mainFrame.getExpLabel().setText("Farmer XP: " + player.getFarmerXP());
		mainFrame.getLevelLabel().setText("Farmer Level: " + player.getFarmerLevel());
		mainFrame.getCoinLabel().setText("Objectcoins: "+ player.getFarmerCoins());
	}

    
}
