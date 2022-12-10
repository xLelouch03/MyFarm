/*
 * This class contains the GUI implementation of the Tile Screen to be used for the game
 * These are what pop up when the tile buttons are clicked.
 */

package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
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


public class TileScreen extends JFrame{
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
        tileFrame.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/assets/farm lot pic.jpg"))));
		tileFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tileFrame.getContentPane().setLayout(null);
        mainFrame.mainSetEnabled(false);
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
                
                if(crop != null) {
                    String cropName = (String) crop;

                    JOptionPane.showMessageDialog(tileFrame, player.getFarm().plantSeed(cropName, row, col));
                    mainFrame.setFarmStatus();
                    if(player.getFarm().getFarmLot(row, col).getSeed() != null)
                        try {
                            checkPlantOnTile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                }
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
                
                if(tool != null) {
                    toolName = (String) tool;

                    useTool();
                    mainFrame.setFarmStatus();         
                } 
            }
        });
		toolButton.setBounds(60, 65, 268, 50);
		tilePanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getFarm().getFarmLot(row, col).getHarvestStatus() == true) {
                    mainFrame.getTileButton(row, col).setIcon(null);
                    mainFrame.getTileButton(row, col).setBackground(new Color(255,222,173));
                    mainFrame.getTileButton(row, col).setText(""+(num+1));
                }
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().harvestTile(row,col));
				mainFrame.setFarmStatus();
            }
        });
		harvestButton.setBounds(60, 175, 268, 50);
		tilePanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton tileInfoButton = new JButton("Display Tile Information");
        tileInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(tileFrame, player.getFarm().displayTileInfo(row,col));
            }
        });
        tileInfoButton.setBounds(60, 230, 268, 50);
        tilePanel.add(tileInfoButton);
		tileInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.mainSetEnabled(true);
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
            e.printStackTrace();
        }
    }
    
    public void useTool() {
        delay();
        if(toolName.equals("Pickaxe")) {
            if(player.getFarm().getFarmLot(row, col).getRockedStatus() == true) {
                mainFrame.getTileButton(row, col).setIcon(null);
                mainFrame.getTileButton(row, col).setBackground(new Color(255,222,173));
                mainFrame.getTileButton(row, col).setText(""+(num+1));
            }
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePickaxe(row,col,toolName));
             
        }

        else if(toolName.equals("Shovel")) {
            if(player.getFarm().getFarmLot(row, col).getSeed() != null ||
                player.getFarm().getFarmLot(row, col).getPlowStatus() == true) {
                mainFrame.getTileButton(row, col).setIcon(null);
                mainFrame.getTileButton(row, col).setBackground(new Color(255,222,173));
                mainFrame.getTileButton(row, col).setText(""+(num+1));
            }
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useShovel(row,col,toolName));
        }

        else if(toolName.equals("Plow")){
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().usePlow(row,col,toolName));
            if(player.getFarm().getFarmLot(row, col).getRockedStatus() == false && 
                player.getFarm().getFarmLot(row, col).getOccupied() == false) {
                    mainFrame.getTileButton(row, col).setIcon(null);
                    mainFrame.getTileButton(row, col).setBackground(new Color(205,133,63));
                    mainFrame.getTileButton(row, col).setText(""+(num+1));
                }
        }

        else if(toolName.equals("Watering Can")) {
            if(player.getFarm().getFarmLot(row, col).getSeed() != null) {
                mainFrame.getTileButton(row, col).setBackground(new Color(160,82,45));
                mainFrame.getTileButton(row, col).setText(""+(num+1));
            }

            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useWaterCan(row,col,toolName));
        }

        else if(toolName.equals("Fertilizer")) {
            JOptionPane.showMessageDialog(tileFrame, player.getFarm().useFertilizer(row,col,toolName));
        }
    }

    public void checkPlantOnTile() throws IOException {
        ImageIcon image = null;

        if(player.getFarm().getFarmLot(row, col).getSeed() != null) {
            if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Turnip")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted turnip.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Carrot")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted carrot.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Potato")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted potato.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Rose")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted rose.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Tulips")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted tulips.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Sunflower")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted sunflower.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Mango")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted mango.png"));
            }
            else if(player.getFarm().getFarmLot(row, col).getSeed().getName().equals("Apple")) {
                image = new ImageIcon(this.getClass().getResource("/assets/planted apple.png"));
            }
            image.getImage();
            mainFrame.getTileButton(row, col).setIcon(image);
        }
    }

    public void checkLevel() {
        int tempLevel = player.getFarm().getFarmer().getLevel();
        player.getFarm().getFarmer().setLevel((int) player.getFarm().getFarmer().getLevel() / 100);

        if(tempLevel < player.getFarm().getFarmer().getLevel())
            JOptionPane.showMessageDialog(tileFrame, "You have leveled up!");
    }

    public void closeTileFrame() {
        tileFrame.dispose();
    }

}
