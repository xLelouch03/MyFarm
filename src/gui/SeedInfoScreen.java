package gui;

import java.util.*;
import main.Seed;

import main.Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class SeedInfoScreen extends JFrame {

    // Index to track the current information panel
    private int index = 0;
    private Main player;

    private ArrayList<Seed> allSeeds = player.getFarm().getAllSeed();

    // Array of information panels to be displayed
    private InformationPanel[] panels = new InformationPanel[] {
        new InformationPanel(allSeeds.get(index).getName()),
        new InformationPanel("Crop Type: " + allSeeds.get(index).getType()),
        new InformationPanel("Harvest Time (in days): " + allSeeds.get(index).getHarvestTime()),
        new InformationPanel("Day Growth: " + allSeeds.get(index).getDayGrowth()),
        new InformationPanel("Water Limit: " + allSeeds.get(index).getWaterLimit()),
        new InformationPanel("Fertilizer Limit: " + allSeeds.get(index).getFertilizerLimit()),
        new InformationPanel("Product Produced: " + allSeeds.get(index).getProductProduced()),
        new InformationPanel("Cost: " + allSeeds.get(index).getCost()),
        new InformationPanel("Base Price: " + allSeeds.get(index).getBasePrice()),
        new InformationPanel("Experience Yield: " + allSeeds.get(index).getExperienceYield())
    };

    public SeedInfoScreen() {
        // Set the title and size of the frame
        setTitle("Seed Encyclopedia");
        setSize(400, 200);
        setVisible(true);
        setLocationRelativeTo(null);

        // Create the Previous and Next buttons
        JButton previousBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");

        // Add action listeners to the buttons
        previousBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decrement the index and update the information panel
                index--;
                updateInformationPanel();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment the index and update the information panel
                index++;
                updateInformationPanel();
            }
        });

        // Create a panel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(previousBtn);
        btnPanel.add(nextBtn);

        // Add the buttons panel and initial information panel to the frame
        add(btnPanel, BorderLayout.NORTH);
        add(panels[index], BorderLayout.CENTER);
    }

    // Method to update the information panel based on the current index
    private void updateInformationPanel() {
        // Check if the index is within the bounds of the panels array
        if (index < 0) {
            index = 0;
        } else if (index >= panels.length) {
            index = panels.length - 1;
        }

        // Remove the current information panel from the frame
        remove(panels[index]);

        // Add the new information panel to the frame
        add(panels[index], BorderLayout.CENTER);

        // Update the frame to display the new panel
        revalidate();
        repaint();
    }

    class InformationPanel extends JPanel {

        private JLabel label;
    
        public InformationPanel(String text) {
            // Create a label with the given text and add it to the panel
            label = new JLabel(text);
            add(label);
        }
    
    }

    
}