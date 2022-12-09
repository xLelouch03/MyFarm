/**
 * This class is the driver/controller for the model and view of the project
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gui.*;

public class Main {
    private MyFarm farm;
    private SetupScreen setup;

    /**
     * Setups the game after pressing the Start button
     * @param farmerName the name to be used in creating a farmer object
     */
    public void setupGame(String farmerName) {
        if(farmerName.length() == 0) //displays a warning text if player did not enter a name
            setup.warningText("You have not entered a name.");
        else {
            farm = new MyFarm();
            farm.addFarmer(farmerName);
            farm.prepareSeeds();
            farm.prepareTools();
            prepareTiles();
            try {
                importFile();
                //exportFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            closeSetupScreen(setup);
        }
    }

    /**
     * Launches the setup screen
     */
    public void openSetupScreen() {
        setup = new SetupScreen(this);
    }

    /**
     * Closes the setup screen and opens the main screen
     * @param setup the setup screen
     */
    public void closeSetupScreen(SetupScreen setup) {
        setup.close();
        openMainScreen();
    }

    /**
     * Opens the main screen by creating an instance
     */
    public void openMainScreen() {
        new MainScreen(this);
    }

    /**
     * Closes the main screen
     * @param m the main screen
     */
    public void closeMainScreen(MainScreen m) {
        m.closeFrame();
    }
    
    /**
     * Creates the 10x5 farm lots and adding
     * them to the farm
     */
    public void prepareTiles() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                farm.addTile(i,j);
            }
        }
    }

    /**
     * Gets the farm instance
     * @return the farm
     */
    public MyFarm getFarm() {
        return this.farm;
    }

    /**
     * Creates and returns an array of seed names
     * @return the array of seed names
     */
    public String[] getSeedNames() {
        String[] seedName = new String[farm.getAllSeed().size()];

        for(int i = 0; i < farm.getAllSeed().size(); i++) {
            seedName[i] = farm.getAllSeed().get(i).getName();
        }
        return seedName;
    }

    /**
     * Creates and returns the array of tool names
     * @return the array of tool names
     */
    public String[] getToolNames() {
        String[] toolName = new String[farm.getAllTool().size()];

        for(int i = 0; i < farm.getAllTool().size(); i++) {
            toolName[i] = farm.getAllTool().get(i).getName();
        }
        return toolName;
    }

    /**
     * Creates and returns the array of farmer types;
     * @return the array of farmer types
     */
    public String[] getTypes() {
        String[] type = new String[3];
        type[0] = "Registered Farmer";
        type[1] = "Distinguished Farmer";
        type[2] = "Legendary Farmer";

        return type;
    }

    /**
     * Reads a file for the scattering of rocks to the farm
     * @throws IOException if file does not exist
     */
    public void importFile() throws IOException{
        int row, col;
        URL loc = this.getClass().getResource("/main/rocks2.txt");
        File f = new File(loc.getPath());

        BufferedReader br = null;
        int totalLines = 0;

        for(int i = 1; i <= 15; i++) {
            try {
                br = new BufferedReader(new FileReader(f));
    
                while ((br.readLine()) != null) {
                    totalLines++;
                }
                br.close();

                br = new BufferedReader(new FileReader(f));
                Random random = new Random();
                int randomInt = random.nextInt(totalLines);
                int count=0;
                String coordinate;
                while ( (coordinate = br.readLine()) != null) {               
                    if (count == randomInt) {
                        String[] split = coordinate.split(" ");
                        row = Integer.parseInt(split[0]);
                        col = Integer.parseInt(split[1]);
                        if(farm.getFarmLot(row, col).getRockedStatus() == false)
                            farm.getFarmLot(row, col).isRocked(true);
                        else
                            i--;
                        split[0] = null;
                        split[1] = null;
                    }
                    count++;
                }
                totalLines = 0;
                br.close();
    
            } catch (IOException e) {
                  e.printStackTrace();      
            } 
        }
        
        /*Scanner read = new Scanner(f);
        while(read.hasNext()) {
            row = read.nextInt();
            col = read.nextInt();
            farm.getFarmLot(row, col).isRocked(true);
        }
        read.close();*/
    }

    public void exportFile() {
        try {
            FileWriter file = new FileWriter("rocks2.txt");

            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 5; j++) {
                    if(i == 9 && j == 4)
                        file.write(i + " " + j);
                    else
                        file.write(i + " " + j + "\n");
                }
            }
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Main game = new Main();

        game.openSetupScreen();
    }
}
