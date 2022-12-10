/**
 * Belongs to package gui and imports Main.java, java.awt and java.swing
 */

package gui;

import main.Main;
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
import javax.swing.ImageIcon;


/**
 * This class contains the seed information to be displayed upon pressing View All Seeds button
 */
public class SeedInfoScreen {

    private int index = 0;
    private JFrame seedFrame;
    private Main player;
    private JLabel plantNameLabel;
    private JLabel plantTypeLabel;
    private JLabel plantHarvestTimeLabel;
    private JLabel plantDayGrowthLabel;
    private JLabel plantWaterNeedLabel;
    private JLabel plantFertilizerNeedLabel;
    private JLabel plantProductProducedLabel;
    private JLabel plantCostLabel;
    private JLabel plantBasePriceLabel;
    private JLabel plantXPLabel;
    private JLabel plantIconLabel;
    private ImageIcon image;

    /**
     * Constructs the seed information screen given controller input
     * @param player the controller 
     */
    public SeedInfoScreen(Main player) {
        this.player = player;
        initFrame();
        initSeedPanel();
    }  

    /**
     * Tbis initializes the overall frame, as well as the previous and next buttons. 
     */
    public void initFrame() {
        seedFrame = new JFrame();
        seedFrame.setTitle("Seed Encyclopedia");
        seedFrame.setSize(350, 400);
        seedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seedFrame.setVisible(true);

        JButton previousBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");

        // Add action listeners to the buttons
        previousBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decrement the index and update the information panel
                if(index > 0)
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

    /**
     * These initialize the seedPanel, where the text are to be stored and displayed.
     */
    public void initSeedPanel() {
        JPanel seedPanel = new JPanel();
        seedPanel.setBounds(50, 50, 190, 140);
        seedPanel.setBorder(new LineBorder(new Color(0,0,0)));
        seedPanel.setBackground(null);
        seedFrame.getContentPane().add(seedPanel);
        seedPanel.setLayout(null);

        image = new ImageIcon(this.getClass().getResource("/assets/planted turnip.png"));

        plantIconLabel = new JLabel(image);
        plantIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantIconLabel.setBounds(50, -20, 200, 160);
        seedPanel.add(plantIconLabel);

        plantNameLabel = new JLabel("Crop name: " + player.getFarm().getAllSeed().get(index).getName());
        plantNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantNameLabel.setBounds(55, 85, 200, 44);
        seedPanel.add(plantNameLabel);

        plantTypeLabel = new JLabel("Crop Type: " + player.getFarm().getAllSeed().get(index).getType());
        plantTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantTypeLabel.setBounds(55, 105, 200, 44);
        seedPanel.add(plantTypeLabel);

        plantHarvestTimeLabel = new JLabel("Harvest Time (in days): " + player.getFarm().getAllSeed().get(index).getHarvestTime());
        plantHarvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantHarvestTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantHarvestTimeLabel.setBounds(55, 125, 200, 44);
        seedPanel.add(plantHarvestTimeLabel);

        plantDayGrowthLabel = new JLabel("Day Growth: " + player.getFarm().getAllSeed().get(index).getDayGrowth());
        plantDayGrowthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantDayGrowthLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantDayGrowthLabel.setBounds(55, 145, 200, 44);
        seedPanel.add(plantDayGrowthLabel);

        plantWaterNeedLabel = new JLabel("Water Need: " + player.getFarm().getAllSeed().get(index).getWaterNeed() + 
        "(" + player.getFarm().getAllSeed().get(index).getWaterLimit() + ")");
        plantWaterNeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantWaterNeedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantWaterNeedLabel.setBounds(55, 165, 200, 44);
        seedPanel.add(plantWaterNeedLabel);

        plantFertilizerNeedLabel = new JLabel("Fertilizer Need: " + player.getFarm().getAllSeed().get(index).getFertilizerNeed() + 
        "(" + player.getFarm().getAllSeed().get(index).getFertilizerLimit() +")");
        plantFertilizerNeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantFertilizerNeedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantFertilizerNeedLabel.setBounds(55, 185, 200, 44);
        seedPanel.add(plantFertilizerNeedLabel);

        plantProductProducedLabel = new JLabel("Product Produced: " + player.getFarm().getAllSeed().get(index).getProductProducedMinToMax());
        plantProductProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantProductProducedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantProductProducedLabel.setBounds(55, 205, 200, 44);
        seedPanel.add(plantProductProducedLabel);

        plantCostLabel = new JLabel("Cost: " + player.getFarm().getAllSeed().get(index).getCost());
        plantCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantCostLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantCostLabel.setBounds(55, 225, 200, 44);
        seedPanel.add(plantCostLabel);

        plantBasePriceLabel = new JLabel("Base Price: " + player.getFarm().getAllSeed().get(index).getBasePrice());
        plantBasePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantBasePriceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantBasePriceLabel.setBounds(55, 245, 200, 44);
        seedPanel.add(plantBasePriceLabel);

        plantXPLabel = new JLabel("Experience Yield: " + player.getFarm().getAllSeed().get(index).getExperienceYield());
        plantXPLabel.setHorizontalAlignment(SwingConstants.CENTER);
        plantXPLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        plantXPLabel.setBounds(55, 265, 200, 44);
        seedPanel.add(plantXPLabel);

    }

    /**
     * This updates the text and images on the frame based on the index
     */
    public void updateSeedPanel() {
        if(index == 0){
            image = new ImageIcon(this.getClass().getResource("/assets/planted turnip.png"));
        } else if (index == 1){
            image = new ImageIcon(this.getClass().getResource("/assets/planted carrot.png"));
        } else if (index == 2){
            image = new ImageIcon(this.getClass().getResource("/assets/planted potato.png"));
        } else if (index == 3){
            image = new ImageIcon(this.getClass().getResource("/assets/planted rose.png"));
        } else if (index == 4){
            image = new ImageIcon(this.getClass().getResource("/assets/planted tulips.png"));
        } else if (index == 5){
            image = new ImageIcon(this.getClass().getResource("/assets/planted sunflower.png"));
        } else if (index == 6){
            image = new ImageIcon(this.getClass().getResource("/assets/planted mango.png"));
        } else if (index == 7){
            image = new ImageIcon(this.getClass().getResource("/assets/planted apple.png"));
        }

        plantIconLabel.setIcon(image);
        plantNameLabel.setText("Crop name: " + player.getFarm().getAllSeed().get(index).getName());
        plantTypeLabel.setText("Crop Type: " + player.getFarm().getAllSeed().get(index).getType());
        plantHarvestTimeLabel.setText("Harvest Time (in days): " + player.getFarm().getAllSeed().get(index).getHarvestTime());
        plantDayGrowthLabel.setText("Day Growth: " + player.getFarm().getAllSeed().get(index).getDayGrowth());
        plantWaterNeedLabel.setText("Water Need: " + player.getFarm().getAllSeed().get(index).getWaterNeed() + 
        "(" + player.getFarm().getAllSeed().get(index).getWaterLimit() + ")");
        plantFertilizerNeedLabel.setText("Fertilizer Need: " + player.getFarm().getAllSeed().get(index).getFertilizerNeed() + 
        "(" + player.getFarm().getAllSeed().get(index).getFertilizerLimit() +")");
        plantProductProducedLabel.setText("Product Produced: " + player.getFarm().getAllSeed().get(index).getProductProducedMinToMax());
        plantCostLabel.setText("Cost: " + player.getFarm().getAllSeed().get(index).getCost());
        plantBasePriceLabel.setText("Base Price: " + player.getFarm().getAllSeed().get(index).getBasePrice());
        plantXPLabel.setText("Experience Yield: " + player.getFarm().getAllSeed().get(index).getExperienceYield());
    }
}