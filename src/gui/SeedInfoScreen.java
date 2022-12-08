package gui;
import main.Main;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeedInfoScreen extends JFrame{

    //private Main player;
    private JFrame frame;

    public void initSeedInfoScreen(){
        initialize();
    }

    public void initialize(){
        frame.setTitle("Seed Encyclopedia");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
}
