package main;

import gui.SetupScreen;
import gui.MainScreen;
import java.util.ArrayList;

import seeds.Apple;
import seeds.Carrot;
import seeds.Mango;
import seeds.Potato;
import seeds.Rose;
import seeds.Sunflower;
import seeds.Tulips;
import seeds.Turnip;
import tools.Fertilizer;
import tools.Pickaxe;
import tools.Plow;
import tools.Shovel;
import tools.WateringCan;

public class Main {
    private MyFarm farm;
    private SetupScreen setup;

    public void setupGame(String farmerName) {
        farm = new MyFarm();
        farm.addFarmer(farmerName);

        closeSetupScreen(setup);
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
        for(int i = 0; i < 50; i++)
            farm.addTile();
    }

    public ArrayList<FarmLot> getFarmLots() {
        return farm.getAllFarmLot();
    }

    public FarmLot getTile(int index) {
        return farm.getFarmLot(index);
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
    public static void main(String[] args) {
        Main game = new Main();
        game.openSetupScreen();
    }
}
