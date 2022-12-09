package gui;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class SeedInfoScreen extends JFrame {

    private JFrame frame;

    public SeedInfoScreen() {
        initialize();
    }

    public void initialize(){

        frame = new JFrame("Seed Menu");

        JPanel scrollPanel = new JPanel();   
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        String[][] infoString = {
            {"Turnip", "Root Crop", "2", "1(2)", "0(1)", "1-2", "5", "6", "5"},
            {"Carrot", "Root Crop", "3", "1(2)", "0(1)", "1-2", "10", "9", "7.5"},
            {"Potato", "Root Crop", "5", "3(4)", "1(2)", "1-10", "20", "3", "12.5"},
            {"Rose", "Flower", "1", "1(2)", "0(1)", "1", "5", "5", "2.5"},
            {"Tulips", "Flower", "2", "2(3)", "0(1)", "1", "10", "9", "5"},
            {"Sunflower", "Flower", "3", "2(3)", "1(2)", "1", "20", "19", "7.5"},
            {"Mango", "Fruit Tree", "10", "7(7)", "4(4)", "5-15", "100", "8", "25"},
            {"Apple", "Fruit Tree", "10", "7(7)", "5(5)", "10-15", "200", "5", "25"}
        };

        //or(int i = 0; i < )
        
        String[] infoCols = {"Seed Name", "Crop Type", "Harvest Time in Days", "Water Needs (bonus limit)", "Fertilizer Needs (bonus limit)", "Products Produced", 
                                "Seed Cost", "Base Selling Price per Piece", "Experience Yield"};
                                
        JTable infoTable = new JTable(infoString, infoCols);
        infoTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
        infoTable.setFillsViewportHeight(true);
        
        frame.add(new JScrollPane(infoTable));
        frame.setSize(1900,190);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }

}
