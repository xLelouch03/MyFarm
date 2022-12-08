package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
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
        tileFrame.setContentPane(new JLabel(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\farm lot pic.jpg")));
		tileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tileFrame.getContentPane().setLayout(null);

        initPanel();
    }

    public void initPanel() {
        JPanel tilePanel = new JPanel();
        tilePanel.setBounds(40,15,400,330);
        tilePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        tilePanel.setBackground(new Color(255,228,196));
		tileFrame.getContentPane().add(tilePanel);
		tilePanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Choose an action to perform on this tile");
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		welcomeLabel.setBounds(40, 15, 575, 44);
		tilePanel.add(welcomeLabel);

        JButton plantButton = new JButton("Plant a Seed");
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object crop = JOptionPane.showInputDialog(null, "Choose a seed", "Seed Selection", JOptionPane.QUESTION_MESSAGE,null, player.getSeedNames(), "Turnip");
                String cropName = (String) crop;

                JOptionPane.showMessageDialog(tileFrame, player.getFarm().plantSeed(cropName, row, col));
                mainFrame.setFarmStatus();
                checkGameCondition();
            }
        });
		plantButton.setBounds(60, 120, 268, 50);
		tilePanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton toolButton = new JButton("Use a Tool");
		toolButton.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) {
				Object tool = JOptionPane.showInputDialog(null, "Choose a tool", "Tool Selection", JOptionPane.QUESTION_MESSAGE,null, player.getToolNames(), "Plow");
                toolName = (String) tool;

                useTool();
				mainFrame.setFarmStatus();
                checkGameCondition();
            }
        });
		toolButton.setBounds(60, 65, 268, 50);
		tilePanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().harvestTile(player.getFarm().getFarmLot(row, col)));
				mainFrame.setFarmStatus();
                checkGameCondition();
            }
        });
		harvestButton.setBounds(60, 175, 268, 50);
		tilePanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton tileInfoButton = new JButton("Display Tile Information");
        tileInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().displayTileInfo(player.getFarm().getFarmLot(row, col)));
            }
        });
        tileInfoButton.setBounds(60, 230, 268, 50);
        tilePanel.add(tileInfoButton);
		tileInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileFrame.dispose();
            }
        });
        closeButton.setBounds(160, 285, 75,30);
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
            if(player.getFarm().getFarmLot(row, col).getRockedStatus() == true)
                mainFrame.getTileButton(row, col).setIcon(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\tile.png"));

            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePickaxe(player.getFarm().getFarmLot(row, col), 
            player.getFarm().getTool(toolName)));
             
        }

        else if(toolName.equals("Shovel")) {
            if(player.getFarm().getFarmLot(row, col).getSeed() != null)
                mainFrame.getTileButton(row, col).setIcon(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\tile.png"));
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useShovel(player.getFarm().getFarmLot(row, col), 
                                                        player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Plow")){
            //mainFrame.getTileButton(row, col).setBackground(new Color(139,69,19));
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePlow(player.getFarm().getFarmLot(row, col), 
            player.getFarm().getTool(toolName)));
            if(player.getFarm().getFarmLot(row, col).getRockedStatus() == false && 
                player.getFarm().getFarmLot(row, col).getOccupied() == false)
                mainFrame.getTileButton(row, col).setIcon(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\plowedTile.png"));

        }

        else if(toolName.equals("Watering can")) {
            if(player.getFarm().getFarmLot(row, col).getSeed() != null)
                mainFrame.getTileButton(row, col).setIcon(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\wateredTile.png"));

            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useWaterCan(player.getFarm().getFarmLot(row, col), 
            player.getFarm().getTool(toolName)));
        }

        else if(toolName.equals("Fertilizer")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useFertilizer(player.getFarm().getFarmLot(row, col), 
            player.getFarm().getTool(toolName)));
        }
    }

    public void checkLevel() {
        int tempLevel = player.getFarm().getFarmer().getLevel();
        //int level = (int) player.getFarm().getFarmer().getLevel() / 100;
        player.getFarm().getFarmer().setLevel((int) player.getFarm().getFarmer().getLevel() / 100);

        if(tempLevel < player.getFarm().getFarmer().getLevel())
            JOptionPane.showMessageDialog(tileFrame, "You have leveled up!");
    }

    public void closeTileFrame() {
        tileFrame.dispose();
    }

    public void checkGameCondition() {      
        if(!player.getFarm().isRunning()) {
			JOptionPane.showMessageDialog(tileFrame, player.getFarm().gameEnded());

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
