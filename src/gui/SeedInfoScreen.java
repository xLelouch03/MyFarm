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
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JButton backButton;
    private JButton nextButton;
    private JLabel seedNameLabel;
    private JLabel cropTypeLabel;
    private JLabel waterNeedsLabel;
    private JLabel fertilizerNeedsLabel;
    private JLabel productsProducedLabel;
    private JLabel seedCostLabel;
    private JLabel baseSellingPriceLabel;
    private JLabel xpYieldLabel;

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

        bottomPanel = new JPanel(); // panel for the buttons
        frame.add(bottomPanel, BorderLayout.CENTER);

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
        bottomPanel.add(backButton, BorderLayout.WEST); // Back Button

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
        bottomPanel.add(nextButton, BorderLayout.EAST); // Back Button

        // Displaying the text/info

        topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.CENTER);
        midPanel = new JPanel();
        frame.add(midPanel, BorderLayout.CENTER);

        switch(index){
            case 0:

                // seedNameLabel, cropTypeLabel, waterNeedsLabel, fertilizerNeedsLabel, productsProducedLabel,
                // seedCostLabel, baseSellingPriceLabel, xpYieldLabel

                seedNameLabel = new JLabel("Turnip");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Root crop");
                midPanel.add(cropTypeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 1(2)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 0(1)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1-2");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 5");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 6");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 5");
                midPanel.add(xpYieldLabel);
            
                break;

            default: 
                index = 0;
        }
        
    }

}
