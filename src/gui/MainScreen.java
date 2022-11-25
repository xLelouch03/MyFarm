package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import main.Main;

public class MainScreen {
    private JFrame mainFrame;
    private Main player;
	private JButton[][] tileButtons;

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

        JLabel dayLabel = new JLabel("Day: " + player.getDayCount()); 
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
                //farm.advanceNextDay();
            }
        });

		nextDayButton.setBounds(35, 11, 268, 50);
		leftPanel.add(nextDayButton);
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton plantButton = new JButton("Plant a Seed");
		plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //farm.plantSeed(null, null);;
            }
        });
		plantButton.setBounds(35, 90, 268, 50);
		leftPanel.add(plantButton);
		plantButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton toolButton = new JButton("Use a Tool");
		toolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //farm.plantSeed(null, null);;
            }
        });
		toolButton.setBounds(35, 175, 268, 50);
		leftPanel.add(toolButton);
		toolButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton harvestButton = new JButton("Harvest a crop");
		harvestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //farm.plantSeed(null, null);;
            }
        });
		harvestButton.setBounds(35, 255, 268, 50);
		leftPanel.add(harvestButton);
		harvestButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

	}

	public void initRightPanel() {
		JPanel rightPanel = new JPanel();
        rightPanel.setBounds(375,100,1135,670);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		rightPanel.setLayout(new GridLayout(10,5));
		mainFrame.getContentPane().add(rightPanel);

		tileButtons = new JButton[10][5];
		Icon icon = new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\farmlot.jpeg");

		int count = 1;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 5; j++) {
				tileButtons[i][j] = new JButton("FarmLot #" + count);
				tileButtons[i][j].setBounds(i+100, i+200, 250, 100);
				tileButtons[i][j].setIcon(icon);
				rightPanel.add(tileButtons[i][j]);
				count++;
			}
		}
	}
	
    public void closeFrame() {
        mainFrame.dispose();
    }
}
