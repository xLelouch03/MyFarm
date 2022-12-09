package gui;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeedInfoScreen extends JFrame {

    private JFrame frame;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JButton backButton;
    private JButton nextButton;
    private JLabel seedNameLabel;
    private JLabel harvestTimeLabel;
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

                seedNameLabel = new JLabel("Turnip");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Root crop\n");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 2 Days\n");
                midPanel.add(harvestTimeLabel);

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
            
            case 1:

                seedNameLabel = new JLabel("Carrot");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Root crop");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 3 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 1(2)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 0(1)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1-2");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 10");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 9");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 7.5");
                midPanel.add(xpYieldLabel);
            
                break;

            case 2:

                seedNameLabel = new JLabel("Potato");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Root crop");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 5 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 3(4)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 1(2)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1-10");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 20");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 3");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 12.5");
                midPanel.add(xpYieldLabel);
            
                break;

            case 3:

                seedNameLabel = new JLabel("Rose");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Flower");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 1 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 1(2)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 0(1)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 5");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 5");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 2.5");
                midPanel.add(xpYieldLabel);
            
                break;

            case 4:

                seedNameLabel = new JLabel("Tulips");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Flower");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 2 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 2(3)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 0(1)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 10");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 9");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 5");
                midPanel.add(xpYieldLabel);
            
                break;

            case 5:

                seedNameLabel = new JLabel("Sunflower");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Flower");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 3 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 2(3)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 1(2)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 1");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 20");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 19");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 7.5");
                midPanel.add(xpYieldLabel);
            
                break;

            case 6: 

                seedNameLabel = new JLabel("Mango");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Fruit Tree");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 10 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 7(7)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 4(4)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 5-15");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 100");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 8");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 25");
                midPanel.add(xpYieldLabel);
            
                break;

            case 7:

                seedNameLabel = new JLabel("Apple");
                seedNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                topPanel.add(seedNameLabel, BorderLayout.CENTER);

                cropTypeLabel = new JLabel("Crop Type: Fruit Tree");
                midPanel.add(cropTypeLabel);

                harvestTimeLabel = new JLabel("Harvest Time: 10 Days");
                midPanel.add(harvestTimeLabel);

                waterNeedsLabel = new JLabel("Water Needs: 7(7)");
                midPanel.add(waterNeedsLabel);

                fertilizerNeedsLabel = new JLabel("Fertilizer Needs: 5(5)");
                midPanel.add(fertilizerNeedsLabel);

                productsProducedLabel = new JLabel("Products Produced: 10-15");
                midPanel.add(productsProducedLabel);

                seedCostLabel = new JLabel("Seed Cost: 200");
                midPanel.add(seedCostLabel);

                baseSellingPriceLabel = new JLabel("Base Selling Price: 5");
                midPanel.add(baseSellingPriceLabel);

                xpYieldLabel = new JLabel("Experience Yield: 25");
                midPanel.add(xpYieldLabel);
            
                break;

        }
        
    }

}
