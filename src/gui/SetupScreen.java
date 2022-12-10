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
        setupFrame.setContentPane(new JLabel(new ImageIcon("D:\\User\\Documents\\GitHub\\MyFarm\\src\\assets\\farm.jpg")));
		setupFrame.getContentPane().setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to your farm!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(10, 30, 575, 44);
		setupFrame.getContentPane().add(welcomeLabel);

        JLabel farmerNameLabel = new JLabel("What is your name?");
		farmerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		farmerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		farmerNameLabel.setBounds(35, 90, 575, 44);
		setupFrame.getContentPane().add(farmerNameLabel);

        warningLabel = new JLabel();
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 16));
        warningLabel.setBounds(35, 135, 500, 27);
		setupFrame.getContentPane().add(warningLabel);

        JTextField farmerNameTextField = new JTextField();
		farmerNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmerNameTextField.setBounds(235, 95, 300, 39);
		setupFrame.getContentPane().add(farmerNameTextField);

        JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                player.setupGame(farmerNameTextField.getText());
			}
		});

        startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		startButton.setBounds(215, 170, 160, 45);
		setupFrame.getContentPane().add(startButton);
    }

    /**
     * Closes the setup/welcoming frame
     */
    public void close() {
        setupFrame.dispose();
    }

    /**
     * shows a warning message when a name input is not found
     * @param warningMessage
     */
    public void warningText(String warningMessage) {
        if(warningMessage == "")
            warningLabel.setText("");
        else
            warningLabel.setText(warningMessage + " Please enter a name to proceed.");
    }

}