package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupScreen {
    private JFrame setupFrame;

    public SetupScreen() {
        initialize();
    }

    public void initialize() {
        setupFrame = new JFrame("My Farm - Farming Simulation Game");
        setupFrame.setBounds(100, 100, 600, 325);
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.getContentPane().setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to your farm!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(10, 0, 575, 44);
		setupFrame.getContentPane().add(welcomeLabel);

        JLabel farmerNameLabel = new JLabel("What is your name?");
		farmerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		farmerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		farmerNameLabel.setBounds(20, 45, 575, 44);
		setupFrame.getContentPane().add(farmerNameLabel);

        JTextField farmerNameTextField = new JTextField();
		farmerNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameTextField.setBounds(225, 50, 300, 39);
        farmerNameTextField.setColumns(5);
		setupFrame.getContentPane().add(farmerNameTextField);

        JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
	
			}
		});

        startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		startButton.setBounds(215, 125, 160, 45);
		setupFrame.getContentPane().add(startButton);

        JLabel authorLabel = new JLabel();
        setupFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SetupScreen setup = new SetupScreen();
    }
}