/*
 * This class contains the GUI implementation of the Starting Screen to be used for the game
 */

package gui;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupScreen {
    private Main player;
    private JFrame setupFrame;
    private JLabel warningLabel;
    private JTextField farmerNameTextField;
    private JTextField rockNumberTF;
    int rockNumber = 0;

    /**
     * Constructs the setup screen given the main object as input
     * @param player the controller
     */
    public SetupScreen(Main player) {
        this.player = player;
        initialize();
        setupFrame.setVisible(true);
    }

    /**
     * Initializes the Setup Frame, welcome label, and farmer name label, warning label, text fields, and start buttons.
     */
    public void initialize() {
        setupFrame = new JFrame("My Farm - Farming Simulation Game");
        setupFrame.setBounds(500, 100, 600, 280);
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupFrame.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/assets/farm.jpg"))));
		setupFrame.getContentPane().setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to your farm!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(10, 30, 575, 44);
		setupFrame.getContentPane().add(welcomeLabel);

        JLabel farmerNameLabel = new JLabel("What is your name?");
		farmerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		farmerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		farmerNameLabel.setBounds(35, 80, 575, 44);
		setupFrame.getContentPane().add(farmerNameLabel);

        warningLabel = new JLabel();
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 15));
        warningLabel.setBounds(35, 160, 500, 27);
		setupFrame.getContentPane().add(warningLabel);

        farmerNameTextField = new JTextField();
		farmerNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameTextField.setBounds(235, 85, 300, 39);
		setupFrame.getContentPane().add(farmerNameTextField);

        JLabel rockNumberLabel = new JLabel("How many rocks do you want for the farm?");
        rockNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rockNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		rockNumberLabel.setBounds(35, 120, 575, 44);
		setupFrame.getContentPane().add(rockNumberLabel);

        rockNumberTF = new JTextField();
		rockNumberTF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rockNumberTF.setBounds(356, 127, 50, 30);
		setupFrame.getContentPane().add(rockNumberTF);
    
        
        JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                if(rockNumberTF.getText().length() > 0)
                    rockNumber = Integer.valueOf(rockNumberTF.getText());
                player.setupGame(farmerNameTextField.getText(), rockNumber);
			}
		});

        startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		startButton.setBounds(215, 190, 160, 45);
		setupFrame.getContentPane().add(startButton);
    }

    /**
     * Closes the setup/welcoming frame
     */
    public void close() {
        setupFrame.dispose();
    }

    /**
     * shows a warning message when a name and number of rocks input is erroneous
     * @param warningMessage
     */
    public void warningText(String warningMessage) {
        if(warningMessage == "")
            warningLabel.setText("");
        else if(farmerNameTextField.getText().length() == 0)
            warningLabel.setText(warningMessage);
        else if(rockNumber == 0 || rockNumber != 0)
            warningLabel.setText(warningMessage);
    }
}