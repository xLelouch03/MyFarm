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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.Main;

public class MainScreen{
    private JFrame mainFrame;
    private Main player;
	private JButton nextDayButton;
	private JButton harvesButton;
	private JButton plantButton;
	private JButton toolButton;
	private JButton[] tileButtons;
	private JLabel dayLabel;
	private int tileNum = 0;

    public MainScreen(Main player) {
        this.player = player;
		initialize();
		mainFrame.setVisible(true);
    }

    public void initialize() {
        mainFrame = new JFrame("My Farm - Main Screen");
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

        JLabel levelLabel = new JLabel("Farmer Name: "+ player.getFarmerName()); //insert farmer.getLevel();
		levelLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		levelLabel.setBounds(380, 11, 347, 24);
		topPanel.add(levelLabel);

        JLabel expLabel = new JLabel("Farmer XP: " + player.getFarmerXP()); //insert farmer.getType();
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		expLabel.setBounds(750, 11, 347, 24);
		topPanel.add(expLabel);

        JLabel typeLabel = new JLabel("Farmer Type: "); //insert farmer.getXP();
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		typeLabel.setBounds(1150, 11, 347, 24);
		topPanel.add(typeLabel);

        JLabel availableSpaceLabel = new JLabel("Number of available tiles: " + player.getFarmSpace());//insert farm.getSpace() *to be implemented pa
		availableSpaceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availableSpaceLabel.setBounds(10, 43, 237, 24);
		topPanel.add(availableSpaceLabel);

        JLabel farmerNameLabel = new JLabel("Farmer Level: " + player.getFarmerLevel()); //insert farmer.getName();
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameLabel.setBounds(380, 43, 347, 24);
		topPanel.add(farmerNameLabel);
        
        JLabel objectCoinLabel = new JLabel("Objectcoins: "+ player.getFarmerCoins());
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
		//nextDayButton.addActionListener(this);
		nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.nextDay();
				dayLabel.setText("Day: " + player.getDayCount());
            }
        });
		nextDayButton.setBounds(35, 11, 268, 50);
		leftPanel.add(nextDayButton);
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton plantButton = new JButton("Plant a Seed");
		//plantButton.addActionListener(this);
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Enter a seed: ");
            }
        });
		plantButton.setBounds(35, 90, 268, 50);
		leftPanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton toolButton = new JButton("Use a Tool");
		//toolButton.addActionListener(this);
		/*toolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });*/
		toolButton.setBounds(35, 175, 268, 50);
		leftPanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		//harvestButton.addActionListener(this);
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(leftPanel, tileNum);
                player.getFarm().harvestTile(player.getFarm().getFarmLot(tileNum));
            }
        });
		harvestButton.setBounds(35, 255, 268, 50);
		leftPanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

	}

	public void initRightPanel() {
		int i;
		JPanel rightPanel = new JPanel();
        rightPanel.setBounds(375,100,1135,670);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		rightPanel.setLayout(new GridLayout(10,5));
		mainFrame.getContentPane().add(rightPanel);

		tileButtons = new JButton[50];

		for(i = 0; i < 50; i++) {
			int num = i;
			tileButtons[i] = new JButton(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\farmlot.jpg"));
			//tileButtons[i].addActionListener(this);
			tileButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					player.getTile(num);
					tileNum = num;
					if(player.getTile(num).getSeed() != null) {
						JOptionPane.showMessageDialog(rightPanel, "FarmLot#" + (num+1) + "Information" + "/n" +
					"Plow Status: " + player.getTile(num).getPlowStatus() + "\nSeed Planted: " + player.getTile(num).getSeed().getName() +
					"\nWater Count: " + player.getTile(num).getWaterCount() + "\nFertilizer Count: " + player.getTile(num).getFertilizerCount() +
					"\nDays Growed: " + player.getTile(num).getSeed().getDayGrowth() + "\nHarvestable: " + player.getTile(num).isHarvestable() +
					"\nHas a withered plant: " + player.getTile(num).getWitherStatus());
					}
					else {
						JOptionPane.showMessageDialog(rightPanel, "FarmLot#" + (num+1) + " Information" + "\nPlow Status: " + player.getTile(num).getPlowStatus());
					}
				}
			});
			rightPanel.add(tileButtons[i]);
			
		}

	}
	
    public void closeFrame() {
        mainFrame.dispose();
    }
}
