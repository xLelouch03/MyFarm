/**
 * Belongs to package main and imports java.io, and gui
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import gui.*;

/**
 * This class is the driver/controller for the model and view of the project
 */
public class Main {
    private MyFarm farm;
    private SetupScreen setup;

    /**
     * Setups the game after pressing the Start button
     * @param farmerName  the name to be used in creating a farmer object
     * @param rockNumber  the number of rocks to be loaded in the farm
     */
    public void setupGame(String farmerName, int rockNumber) {
        if(farmerName.length() == 0) //displays a warning text if player did not enter a name
            setup.warningText("You have not entered a name.");
        else if(rockNumber < 10 || rockNumber > 30) { 
            setup.warningText("Please enter a number between 10 to 30");
        }
        else {
            farm = new MyFarm();
            farm.addFarmer(farmerName);
            farm.prepareSeeds();
            farm.prepareTools();
            prepareTiles();
            try {
                importFile(rockNumber);
            } catch (IOException e) {
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
     * @param rocks the number of rocks to be loaded
     * @throws IOException if file does not exist
     */
    public void importFile(int rocks) throws IOException{
        int row, col;
        URL loc = this.getClass().getResource("/main/rocks2.txt");
        File f = new File(loc.getPath());

        BufferedReader br = null;
        int totalLines = 0;

        for(int i = 1; i <= rocks; i++) {
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
    }

    /**
     * The main class
     * @param args arguments
     */
    public static void main(String[] args) {
        Main game = new Main();

        game.openSetupScreen();
    }
}
