package gui;

import java.util.*;
import main.Tool;

import main.Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class ToolInfoScreen extends JFrame {

    private int index = 0;
    private Main player;

    private ArrayList<Tool> allTools = player.getFarm().getAllTool();

    /**
     * Array of info panels for tools
     */
    private toolInfoPanel[] panels = new toolInfoPanel[] {
        new toolInfoPanel(allTools.get(index).getName()),
        new toolInfoPanel("Cost: " + allTools.get(index).getCost()),
        new toolInfoPanel("Experience Yield: " + allTools.get(index).getXP()),
    };

    public ToolInfoScreen() {
        // Set the title and size of the frame
        setTitle("Tool Encyclopedia");
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
                updateToolInfoPanel();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment the index and update the information panel
                index++;
                updateToolInfoPanel();
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
    private void updateToolInfoPanel() {
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

    class toolInfoPanel extends JPanel {

        private JLabel label;
    
        public toolInfoPanel(String text) {
            // Create a label with the given text and add it to the panel
            label = new JLabel(text);
            add(label);
        }
    
    }

    
}