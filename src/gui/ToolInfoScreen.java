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

public class ToolInfoScreen {

    // Index to track the current information panel
    private int index = 0;
    private JFrame toolFrame;
    private Main player;
    private JLabel toolNameLabel;
    private JLabel toolCostLabel;
    private JLabel toolXPLabel;
    private JLabel toolIconLabel;
    private ImageIcon image;

    public ToolInfoScreen(Main player) {
        this.player = player;
        initFrame();
        initToolPanel();
    }  

    /**
     * Tbis initializes the overall frame, as well as the previous and next buttons. 
     */
    public void initFrame() {
        toolFrame = new JFrame();
        toolFrame.setTitle("Tool Encyclopedia");
        toolFrame.setSize(350, 300);
        toolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        toolFrame.setVisible(true);

        JButton previousBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");

        // Add action listeners to the buttons
        previousBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decrement the index and update the information panel
                if(index > 1)
                    index--;
                updateToolPanel();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment the index and update the information panel
                if(index < 5)
                    index++;
                updateToolPanel();
            }
        });

        // Create a panel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(previousBtn);
        btnPanel.add(nextBtn);

        // Add the buttons panel and initial information panel to the frame
        toolFrame.add(btnPanel, BorderLayout.NORTH);
    }

    /**
     * These initialize the toolPanel, where the text are to be stored and displayed.
     */
    public void initToolPanel() {
        JPanel toolPanel = new JPanel();
        toolPanel.setBounds(50, 50, 190, 140);
        toolPanel.setBorder(new LineBorder(new Color(0,0,0)));
        toolPanel.setBackground(null);
        toolFrame.getContentPane().add(toolPanel);
        toolPanel.setLayout(null);

        image = new ImageIcon(this.getClass().getResource("/assets/planted plow.png"));

        // toolIconLabel = new JLabel(image);
        // toolIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // toolIconLabel.setBounds(140, 20, 40, 40);
        // toolPanel.add(toolIconLabel);

        toolNameLabel = new JLabel("Tool name: " + player.getFarm().getAllTool().get(index).getName());
        toolNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        toolNameLabel.setBounds(55, 60, 200, 44);
        toolPanel.add(toolNameLabel);

        toolCostLabel = new JLabel("Tool name: " + player.getFarm().getAllTool().get(index).getCost());
        toolCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolCostLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        toolCostLabel.setBounds(55, 85, 200, 44);
        toolPanel.add(toolCostLabel);

        toolXPLabel = new JLabel("Experience Yield: " + player.getFarm().getAllTool().get(index).getXP());
        toolXPLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolXPLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        toolXPLabel.setBounds(55, 110, 200, 44);
        toolPanel.add(toolXPLabel);

    }

    /**
     * This updates the text and images on the frame based on the index
     */
    public void updateToolPanel() {
        if (index == 1){
            image = new ImageIcon(this.getClass().getResource("/assets/planted plow.png"));
        } else if (index == 2){
            image = new ImageIcon(this.getClass().getResource("/assets/planted watering can.png"));
        } else if (index == 3){
            image = new ImageIcon(this.getClass().getResource("/assets/planted fertilizer.png"));
        } else if (index == 4){
            image = new ImageIcon(this.getClass().getResource("/assets/planted shovel.png"));
        } else if (index == 5){
            image = new ImageIcon(this.getClass().getResource("/assets/planted pickaxe.png"));
        }

        toolNameLabel.setText("Tool name: " + player.getFarm().getAllTool().get(index).getName());
        toolCostLabel.setText("Cost: " + player.getFarm().getAllTool().get(index).getCost());
        toolXPLabel.setText("Experience Yield " + player.getFarm().getAllTool().get(index).getXP());
    }
}