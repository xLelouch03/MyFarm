package gui;

import java.util.*;
import main.Seed;

import main.Main;
import main.MyFarm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class SeedInfoScreen {

    // Index to track the current information panel
    private int index = 0;
    private JFrame seedFrame;
    private Main player;
    private JLabel plantNameLabel;
    private JLabel plantTypeLabel;
    private JLabel plantHarvestTimeLabel;
    private JLabel plantDayGrowthLabel;
    private JLabel plantWaterLimitLabel;
    private JLabel plantFertilizerLimitLabel;
    private JLabel plantProductProducedLabel;
    private JLabel plantCostLabel;
    private JLabel plantBasePriceLabel;
    private JLabel plantXPLabel;

    public SeedInfoScreen(Main player) {
        this.player = player;
        initFrame();
        initSeedPanel();
    }  

    public void initFrame() {
        seedFrame = new JFrame();
        seedFrame.setTitle("Seed Encyclopedia");
        seedFrame.setSize(400, 400);
        seedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seedFrame.setVisible(true);

        JButton previousBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");

        // Add action listeners to the buttons
        previousBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decrement the index and update the information panel
                if(index > 1)
                    index--;
                updateSeedPanel();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment the index and update the information panel
                if(index < 7)
                    index++;
                updateSeedPanel();
            }
        });

        // Create a panel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(previousBtn);
        btnPanel.add(nextBtn);

        // Add the buttons panel and initial information panel to the frame
        seedFrame.add(btnPanel, BorderLayout.NORTH);
    }

    public void initSeedPanel() {
        JPanel seedPanel = new JPanel();
        seedPanel.setBounds(50, 50, 190, 140);
        seedPanel.setBorder(new LineBorder(new Color(0,0,0)));
        seedPanel.setBackground(null);
        seedFrame.getContentPane().add(seedPanel);
        seedPanel.setLayout(null);

        plantNameLabel = new JLabel("Crop name: " + player.getFarm().getAllSeed().get(index).getName());
        plantNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantNameLabel.setBounds(55, 55, 200, 44);
        seedPanel.add(plantNameLabel);

        plantTypeLabel = new JLabel("Crop Type: " + player.getFarm().getAllSeed().get(index).getType());
        plantTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantTypeLabel.setBounds(55, 75, 200, 44);
        seedPanel.add(plantTypeLabel);

        plantHarvestTimeLabel = new JLabel("Harvest Time (in days): " + player.getFarm().getAllSeed().get(index).getHarvestTime());
        plantHarvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantHarvestTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantHarvestTimeLabel.setBounds(55, 95, 200, 44);
        seedPanel.add(plantHarvestTimeLabel);

        plantDayGrowthLabel = new JLabel("Day Growth: " + player.getFarm().getAllSeed().get(index).getDayGrowth());
        plantDayGrowthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantDayGrowthLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantDayGrowthLabel.setBounds(55, 115, 200, 44);
        seedPanel.add(plantDayGrowthLabel);

        plantWaterLimitLabel = new JLabel("Water Limit: " + player.getFarm().getAllSeed().get(index).getWaterLimit());
        plantWaterLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantWaterLimitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantWaterLimitLabel.setBounds(55, 135, 200, 44);
        seedPanel.add(plantWaterLimitLabel);

        plantFertilizerLimitLabel = new JLabel("Fertilizer Limit: " + player.getFarm().getAllSeed().get(index).getFertilizerLimit());
        plantFertilizerLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantFertilizerLimitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantFertilizerLimitLabel.setBounds(55, 155, 200, 44);
        seedPanel.add(plantFertilizerLimitLabel);

        plantProductProducedLabel = new JLabel("Product Produced: " + player.getFarm().getAllSeed().get(index).getProductProduced());
        plantProductProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantProductProducedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantProductProducedLabel.setBounds(55, 175, 200, 44);
        seedPanel.add(plantProductProducedLabel);

        plantCostLabel = new JLabel("Cost: " + player.getFarm().getAllSeed().get(index).getCost());
        plantCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantCostLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantCostLabel.setBounds(55, 195, 200, 44);
        seedPanel.add(plantCostLabel);

        plantBasePriceLabel = new JLabel("Base Price: " + player.getFarm().getAllSeed().get(index).getBasePrice());
        plantBasePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantBasePriceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantBasePriceLabel.setBounds(55, 215, 200, 44);
        seedPanel.add(plantBasePriceLabel);

        plantXPLabel = new JLabel("Experience Yield: " + player.getFarm().getAllSeed().get(index).getExperienceYield());
        plantXPLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantXPLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantXPLabel.setBounds(55, 235, 200, 44);
        seedPanel.add(plantXPLabel);

    }

    public void updateSeedPanel() {
        System.out.println(player.getFarm().getAllSeed().get(index).getName());
        plantNameLabel.setText("Crop name: " + player.getFarm().getAllSeed().get(index).getName());
        plantTypeLabel.setText("Crop Type: " + player.getFarm().getAllSeed().get(index).getType());
        plantHarvestTimeLabel.setText("Harvest Time (in days): " + player.getFarm().getAllSeed().get(index).getHarvestTime());
        plantDayGrowthLabel.setText("Day Growth: " + player.getFarm().getAllSeed().get(index).getDayGrowth());
        plantWaterLimitLabel.setText("Water Limit: " + player.getFarm().getAllSeed().get(index).getWaterLimit());
        plantFertilizerLimitLabel.setText("Fertilizer Limit: " + player.getFarm().getAllSeed().get(index).getFertilizerLimit());
        plantProductProducedLabel.setText("Product Produced: " + player.getFarm().getAllSeed().get(index).getProductProduced());
        plantCostLabel.setText("Cost: " + player.getFarm().getAllSeed().get(index).getCost());
        plantBasePriceLabel.setText("Base Price: " + player.getFarm().getAllSeed().get(index).getBasePrice());
        plantXPLabel.setText("Experience Yield: " + player.getFarm().getAllSeed().get(index).getExperienceYield());
        
    }
}