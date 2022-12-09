package gui;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.*;

public class ToolInfoScreen extends JFrame {

    private JFrame frame;

    public ToolInfoScreen() {
        initialize();
    }

    public void initialize(){

        frame = new JFrame("Tool Menu");

        JPanel scrollPanel = new JPanel();   
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        String[][] infoString = {
            {"Plow", "Converts an unplowed tile to a plowed tile. Can only be performed on an unplowed tile.", "0", "0.5"},
            {"Watering Can", "Adds to the total number of tiles times a crop has been watered. Can only be performed on a plowed tile with a crop.", "0", "0.5"},
            {"Fertilizer", "Adds to the total number of tiles times a crop has been applied with fertilizer. Can only be performed on a plowed tile with a crop.", "10", "4"},
            {"Pickaxe", "Removes a rock from a tile. Can only be applied to tiles with a rock.", "50", "15"},
            {"Shovel", "Removes a withered plant from a tile. Can be used on any tile/crop with varying effects, as described above.", "7", "2"}
        };
        
        String[] infoCols = {"Tool", "Function", "Cost of Usage", "Experienced Gain from Use"};
            
        JTable infoTable = new JTable(infoString, infoCols);
        infoTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
        infoTable.setFillsViewportHeight(true);

        frame.add(new JScrollPane(infoTable));
        frame.setSize(1300,140);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }

}
