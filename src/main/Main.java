package main;

import seeds.*;
import tools.*;
import gui.*;

public class Main {
    private MyFarm farm;
    private SetupScreen setup;

    public void setupGame(String farmerName) {
        if(farmerName.length() == 0) 
            setup.warningText("You have not entered a name.");
        else {
            farm = new MyFarm();
            farm.addFarmer(farmerName);
            setupSeeds();
            setupTools();
            prepareTiles();

            closeSetupScreen(setup);
        }
    }

    public void openSetupScreen() {
        setup = new SetupScreen(this);
    }

    public void closeSetupScreen(SetupScreen setup) {
        setup.close();
        openMainScreen();
    }

    public void openMainScreen() {
        new MainScreen(this);
    }

    public void closeMainScreen(MainScreen m) {
        m.closeFrame();
    }

    public void setupTools() {
        farm.addTools(new Plow());
        farm.addTools(new WateringCan());
        farm.addTools(new Fertilizer());
        farm.addTools(new Pickaxe());
        farm.addTools(new Shovel());
    }

    /**  
     * Adds and defines the seeds in the seed array list
     */
    public void setupSeeds() {
        farm.addSeeds(new Turnip());
        farm.addSeeds(new Carrot());
        farm.addSeeds(new Potato());
        farm.addSeeds(new Rose());
        farm.addSeeds(new Tulips());
        farm.addSeeds(new Sunflower());
        farm.addSeeds(new Mango());
        farm.addSeeds(new Apple());
    }
    
    /**
     * Prepares the farm land with 50 tiles in total
     */
    public void prepareTiles() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                farm.addTile(i,j);
            }
        }
    }

    public MyFarm getFarm() {
        return this.farm;
    }

    public FarmLot[][] getFarmLots() {
        return farm.getAllFarmLot();
    }
    //public ArrayList<FarmLot> getFarmLots() {
        //return farm.getAllFarmLot();
    //}

    public FarmLot getTile(int row, int col) {
        return farm.getFarmLot(row, col);
    }

    public int getFarmSpace() {
        return 50;
    }

    public int getDayCount() {
        return farm.getDay();
    }

    public String getFarmerName() {
        return farm.getFarmer().getName();
    }

    public double getFarmerXP() {
        return farm.getFarmer().getXP();
    }

    public double getFarmerCoins() {
        return farm.getFarmer().getCoins();
    }

    public int getFarmerLevel() {
        return farm.getFarmer().getLevel();
    }

    public String getFarmerType() {
        return farm.getFarmer().getType();
    }

    public boolean getOccupied(int row, int col) {
        return farm.getFarmLot(row,col).getOccupied();
    }

    public void nextDay() {
        farm.advanceNextDay();
    }

    public String[] getSeedNames() {
        String[] seedName = new String[farm.getAllSeed().size()];
        seedName[0] = "Turnip";
        seedName[1] = "Carrot";
        seedName[2] = "Potato";
        seedName[3] = "Rose";
        seedName[4] = "Tulips";
        seedName[5] = "Sunflower";
        seedName[6] = "Mango";
        seedName[7] = "Apple";

        return seedName;
    }

    public String[] getToolNames() {
        String[] toolName = new String[farm.getAllTool().size()];
        toolName[0] = "Plow";
        toolName[1] = "Watering can";
        toolName[2] = "Fertilizer";
        toolName[3] = "Pickaxe";
        toolName[4] = "Shovel";

        return toolName;
    }

    public String[] getTypes() {
        String[] type = new String[3];
        type[0] = "Registered Farmer";
        type[1] = "Distinguished Farmer";
        type[2] = "Legendary Farmer";

        return type;
    }

    public void getSeedInfo(String crop){
        

    }

    public void useTool(String toolName, int row, int col) {
        if(toolName.equalsIgnoreCase("plow")) 
            farm.usePlow(getTile(row, col), farm.getTool(toolName));
        else if(toolName.equalsIgnoreCase("watering can"))
            farm.useWaterCan(getTile(row, col), farm.getTool(toolName));
        else if(toolName.equalsIgnoreCase("fertilizer"))
            farm.useFertilizer(getTile(row, col), farm.getTool(toolName));
        else if(toolName.equalsIgnoreCase("pickaxe"))
            farm.usePickaxe(getTile(row, col), farm.getTool(toolName));
        else if(toolName.equalsIgnoreCase("shovel"))
            farm.useShovel(getTile(row, col), farm.getTool(toolName)); 
    }

    public void harvestCrop(int index) {

    }

    public static void main(String[] args) {
        Main game = new Main();

        game.openSetupScreen();
    }
}
