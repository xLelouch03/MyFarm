package gui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.Main;

public class MainScreen {
    private JFrame mainFrame;
    private Main player;

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

        JLabel levelLabel = new JLabel("Farmer Level: "); //insert farmer.getLevel();
		levelLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		levelLabel.setBounds(380, 11, 347, 24);
		topPanel.add(levelLabel);

        JLabel expLabel = new JLabel("Farmer XP: "); //insert farmer.getType();
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		expLabel.setBounds(750, 11, 347, 24);
		topPanel.add(expLabel);

        JLabel typeLabel = new JLabel("Farmer Type: "); //insert farmer.getXP();
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		typeLabel.setBounds(1150, 11, 347, 24);
		topPanel.add(typeLabel);

        JLabel availableSpaceLabel = new JLabel("Number of available tiles: " );//insert farm.getSpace() *to be implemented pa
		availableSpaceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availableSpaceLabel.setBounds(10, 43, 237, 24);
		topPanel.add(availableSpaceLabel);

        JLabel farmerNameLabel = new JLabel("Farmer Name: " ); //insert farmer.getName();
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameLabel.setBounds(380, 43, 347, 24);
		topPanel.add(farmerNameLabel);
        
        JLabel objectCoinLabel = new JLabel("Objectcoins: " ); //insert farmer.getCoins();
		objectCoinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		objectCoinLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		objectCoinLabel.setBounds(630, 43, 347, 24);
		topPanel.add(objectCoinLabel);

        JLabel dayLabel = new JLabel("Day: "); //insert farm.getDay();
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLabel.setBounds(1080, 43, 185, 24);
		topPanel.add(dayLabel);
    }

    public void closeFrame() {
        mainFrame.dispose();
    }


    public static void main(String[] args) {
        Main player = new Main();
        MainScreen main = new MainScreen(player);
    }
}
