package gui;
import main.Main;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class SeedInfoScreen extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton backButton;
    private JButton nextButton;

    public SeedInfoScreen() {
        initialize();
    }

    public void initialize(){

        int index = 0;
        int temp;

        frame = new JFrame("Seed Encyclopedia");
        frame.setLayout(new BorderLayout(10, 5));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // panel = new JPanel();
        // panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // frame.add(panel, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                if (index != 0){ 
                    index = index - 1;
                } else {
                    index = 0;
                }

            }
        });
        frame.add(backButton, BorderLayout.WEST); // Back Button

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                if (index != 0){
                    index = index + 1;
                } else if (index > 7) { // 7 is the number of seeds to be displayed (7th is the last index)
                    index = 7;
                } else if (index == 0) { // 0th index is the first seed
                    index = 0;
                }

            }
        });
        frame.add(nextButton, BorderLayout.EAST); // Back Button

        // Displaying the text 

        label = new JLabel("Text Here");
        panel.add(label);

        switch(index){
            case 0:
                // add JLabels
                //frame.add()
                break;
            default: 
                index = 0;
        }
        
    }

}
