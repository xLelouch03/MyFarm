/**
 * This class contains the constructor and different methods that will
 * be used in managing the farm.
 */

package main;
import java.util.ArrayList;

import seeds.*;
import tools.*;

public class MyFarm {
    private Farmer farmer;
    private FarmLot[][] farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;
    private int totalSpace;
    private int witherCount;

    /**
     * Constructs a farm by creating a farmlot, arraylist of seeds and tools,
     * setting the day to 1, a farmer, and the total space.
     */
    public MyFarm() {
        this.farmLot = new FarmLot[10][5];
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
        this.farmer = null;
        this.totalSpace = 50;
    }
    
    /** 
     * Adds a farmer/player to the game
     * @param name  a string that will be passed to construct a farmer object
     */
    public void addFarmer(String name) {
        this.farmer = new Farmer(name);
    }

    /** 
     * Gets the farmer object
     * @return the farmer object
     */
    public Farmer getFarmer() {
        return this.farmer;
    }

    /**
     * Registers a farmer to a higher farmer type
     * @param farmerType
     * @return the string prompt to be displayed in the GUI
     */
    public String registerFarmer(String farmerType) {
        String registerPrompt = "";
        if(	(this.farmer.getLevel() < 5 && farmerType.equals("Registered Farmer")) || 
					(this.farmer.getLevel() < 10 && farmerType.equals("Distinguished Farmer")) ||
					(this.farmer.getLevel() < 15 && farmerType.equals("Legendary Farmer"))) {
			registerPrompt += "You have not reached the minimum level to register.";
		}
		
        else if((this.farmer.getLevel() >= 5 && this.farmer.getCoins() < 200 && farmerType.equals("Registered Farmer")) || 
		(this.farmer.getLevel() >= 10 && this.farmer.getCoins() < 300 && farmerType.equals("Distinguished Farmer")) ||
		(this.farmer.getLevel() >= 15 && this.farmer.getCoins() < 400 && farmerType.equals("Legendary Farmer"))) {
			registerPrompt += "You have not reached the minimum objectCoins to register.";
		}

        else if(farmerType.equals("Registered Farmer") && (this.farmer.getType().equals("Distinguished Farmer") || 
        this.farmer.getType().equals("Legendary Farmer"))) {
            registerPrompt += "You have already registered as " + this.farmer.getType();
        }

        else if(farmerType.equals("Distinguished Farmer") && this.farmer.getType().equals("Legendary Farmer")) {
            registerPrompt += "You have already registered as " + this.farmer.getType();
        }
        
        else if(farmerType.equals("Distinguished Farmer") && !this.farmer.getType().equals("Registered Farmer")) {
			registerPrompt += "You must register as Registered Farmer first.";
		}

		else if(farmerType.equals("Legendary Farmer") && !this.farmer.getType().equals("Distinguished Farmer")) {
			registerPrompt += "You must register as Distinguished Farmer first.";
		}
        
        else {
            switch(farmerType){ 
                case "Registered Farmer":
                    if(this.farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + this.farmer.getType();
                    }
                    else if (this.farmer.getLevel() >= 5 && this.farmer.getCoins() >= 200){
                        farmer.setFarmerType(farmerType); 
                        farmer.updateObjectCoins(this.farmer.getCoins() - 200);
                        for(Seed s: seed) {
                            s.updateBasePrice(1);
                            s.updateCost(1);
                        }
                        registerPrompt += "You have used 200 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 1 objectCoin\n" + 
                                            "1 bonus earnings per produce";
                    } 
                    break;
                
                case "Distinguished Farmer":
                    if(this.farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + this.farmer.getType();
                    }
                    else if (this.farmer.getLevel() >= 10 && this.farmer.getCoins() >= 300){
                        this.farmer.setFarmerType(farmerType); 
                        this.farmer.updateObjectCoins(this.farmer.getCoins() - 300);
                        for(Seed s: seed) {
                            s.updateBasePrice(2);
                            s.updateCost(2);
                            s.updateWaterLimit(1);
                        }
                        registerPrompt += "You have used 300 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 2 objectCoins\n" + 
                                            "2 bonus earnings per produce\n" + "Increased water limit by 1";
                    } 
                    break;
    
                case "Legendary Farmer":
                    if(this.farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + this.farmer.getType();
                    }

                    else if (this.farmer.getLevel() >= 15 && this.farmer.getCoins() >= 400){
                        this.farmer.setFarmerType(farmerType); 
                        this.farmer.updateObjectCoins(this.farmer.getCoins() - 400);
                        for(Seed s: seed) {
                            s.updateBasePrice(4);
                            s.updateCost(3);
                            s.updateFertilizerLimit(1);
                            s.updateWaterLimit(2);
                        }
                        registerPrompt += "You have used 400 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 4 objectCoins\n" + 
                                            "3 bonus earnings per produce" + "\nIncreased water limit by 1" + "\nIncreased fertilizer limit by 2";
                    } 
                    break;
            }
        }
        return registerPrompt;
    } 
    
    /**
     * Creates a new instance of a farm lot given 
     * the row and col indices
     * @param row the row index
     * @param col the col index
     */
    public void addTile(int row, int col) {
        this.farmLot[row][col] = new FarmLot();
    }

    /**
     * Gets a specific farm lot given its row and column indices
     * @param row the row index
     * @param col the col index
     * @return the farm lot instance
     */
    public FarmLot getFarmLot(int row, int col){
        return this.farmLot[row][col];
    }

    /**
     * Gets the array of farm lots
     * @return the array of farm lots
     */
    public FarmLot[][] getAllFarmLot(){
        return this.farmLot;
    }
    
    /** 
     * Gets the whole seed arraylist
     * @return the seed arraylist
     */
    public ArrayList<Seed> getAllSeed() {
        return this.seed;
    }
    
    /** 
     * Gets a tool object
     * @param name   The name of the tool
     * @return the tool object
     */
    public Tool getTool(String name){
        Tool temp = null;
        
        //searching if tool exist
        for(Tool t : tool){
            if(t.getName().equalsIgnoreCase(name))
                temp = t;
        }
      return temp;
    }

    /**
     * Gets a seed object
     * @param seedName the name of the seed
     * @return the seed
     */
    public Seed getSeed(String seedName) {
        Seed temp = null;

        for(Seed s: seed) {
            if(s.getName().equals(seedName))   
                temp = s;
        }
        return temp;
    }

    /**
     *  Clears the seed arraylist 
     */
    public void removeSeed() {
        this.seed.removeAll(this.seed);      
    }

    /** 
     * Clear the tool arraylist 
     */
    public void removeTool() {
        this.tool.removeAll(this.tool);
    }
    
    /** 
     * Gets the arraylist of tools
     * @return the tool arraylist
     */
    public ArrayList<Tool> getAllTool() {
        return tool;
    }
    
    /** 
     * Gets the current day of the game
     * @return the day count of the game
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Increments the day count
     */
    public void advanceDay() {
        this.day++;
    }

    /**
     * Gets the total space of the farm
     * @return
     */
    public int getTotalSpace() {
        return this.totalSpace;
    }

    /**
     * Gets the available space of the farm
     * @return
     */
    public int getAvailableSpace() {
        int occupiedTile = 0;

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.farmLot[i][j].getSeed() != null)
                    occupiedTile++;
            }
        }
        return totalSpace - occupiedTile;
    }

    /**
     * Advancing to the next day where it will allow all planted crops to grow
     * and will also check if crops are harvestable, or will wither
     * @return the string prompt to be displayed in the GUI
     */
    public String advanceNextDay(){
        String nextDayPrompt = "";
        advanceDay();
        
        nextDayPrompt += "<html>You advanced to the next day...<br>";
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.farmLot[i][j].getSeed() != null) {
                    if(this.farmLot[i][j].getWitherStatus() == false) {
                        this.farmLot[i][j].getSeed().grow();

                        //Checks if crop reached more than the number of days 
                        if(this.farmLot[i][j].getSeed().getDayGrowth() > this.farmLot[i][j].getSeed().getHarvestTime()) {
                            nextDayPrompt += "<br>Because you did not harvest the " + this.farmLot[i][j].getSeed().getName() + " <br>in FarmLot (" + i + "," + j + "), ";
                            nextDayPrompt += this.farmLot[i][j].isWithered(true);
                        }

                        //checks if crop reach the harvest time and meet the minimum requirements to be harvested
                        else if(this.farmLot[i][j].getSeed().getDayGrowth() == this.farmLot[i][j].getSeed().getHarvestTime()) {

                            //if crop did not meet the minimum requirements
                            if(this.farmLot[i][j].isHarvestable() == false) {
                                nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j + 
                                    ") growed but did not get <br>taken care of properly. ";
                                nextDayPrompt += this.farmLot[i][j].isWithered(true);
                            }

                            //crop meets the minimum requirements
                            else 
                                nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j + ") is harvestable";
                        }

                        //crop grows
                        else
                            nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j +  ") growed.";
                    }
                }
            }
        }
        return nextDayPrompt += "</html>";
    }

    /**
     * Gets the number of tiles with withered plant
     * @return the wither count
     */
    public int getWitherCount() {
        return this.witherCount;
    }

    /**
     * Checks the game condition if it is still going to continue
     * @return the boolean value 
     */
    public boolean isRunning() {
        witherCount = 0;

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.farmLot[i][j].getWitherStatus() == true)
                    witherCount++;
            }
        }

        //checks if farmer can no longer buy a seed with no active crop OR the farm tiles contain withered plant
        if((farmer.getCoins() < 5 && getAvailableSpace() == 50) || witherCount == 50)
            return false;
        
        return true;
    }
    
    /**
     * Gets the string prompt once the game is ended
     * @return the string prompt to be displayed in the GUI
     */
    public String gameEnded() {
        String gameEndedPrompt = "";

        if(!isRunning()) {
            if(getWitherCount() == 50) {
                gameEndedPrompt += "All of your tiles contain a withered plant";
            }
    
            else if(getAvailableSpace() == 50 && farmer.getCoins() < 5){
                gameEndedPrompt += "You don't have enough objectCoins to buy a seed" +
                "\nYou also don't have any active crops";
            }
        }
        return gameEndedPrompt;
    }

    /**
     * Creates and adds all the seeds to the arraylist of seeds
     */
    public void prepareSeeds() {
        this.seed.add(new Turnip());
        this.seed.add(new Carrot());
        this.seed.add(new Potato());
        this.seed.add(new Rose());
        this.seed.add(new Tulips());
        this.seed.add(new Sunflower());
        this.seed.add(new Mango());
        this.seed.add(new Apple());
    }
    
    /**
     * Creates and adds all the tools to the arraylist of tools
     */
    public void prepareTools() {
        this.tool.add(new Plow());
        this.tool.add(new WateringCan());
        this.tool.add(new Fertilizer());
        this.tool.add(new Pickaxe());
        this.tool.add(new Shovel());
    }
    
    /**
     * Uses plow tool
     * @param row the row index of the farm lot to plow
     * @param col the column index of the farm lot to plow
     * @param toolname the plow name
     * @return the string to display in the GUI
     */
    public String usePlow(int row, int col, String toolname) {
        FarmLot lot = farmLot[row][col];
        Tool plow = getTool(toolname);
        String plowPrompt = "";
        
        //checks if there is a rock on the tile
        if(lot.getRockedStatus() == false){
                //checks if tile is not yet plowed
                if(lot.getPlowStatus() == false) {
                    //checks if tile contains a withered plant
                    if(lot.getWitherStatus() == false) {
                        //checks if the tool object passed contains a name "plow"
                        if(plow.getName().equalsIgnoreCase("plow")) {
                            lot.isPlowed(true);
                            plowPrompt += "\nTile is now plowed.";
                        
                            double currXP = farmer.getXP();
                            double tempXP = farmer.getXP() + plow.getXP();

                            farmer.updateXP(tempXP);
                            plowPrompt += "\nYou have gained " + plow.getXP() + " experience.";
                            
                            //checks if the farmer levels up
                            if(tempXP > currXP){
                                farmer.getLevel();
                            }
                        }
                        else
                            plowPrompt += "\nYou have used a wrong tool.";
                    }
                    else   
                        plowPrompt += "This lot contains a withered plant.";
                }
                else
                    plowPrompt += "This lot is already plowed.";
        }
        else
            plowPrompt += "There is a rock on the lot!";
        
        return plowPrompt;
    }
    
    /**
     * Creates and returns an arraylist of farmlots that are adjacent to
     * a specified farmlot
     * Reference: https://www.geeksforgeeks.org/find-all-adjacent-elements-of-given-element-in-a-2d-array-or-matrix/
     * @param row the row index of the specified farm lot
     * @param col the column index of the specified farm lot
     * @return the arraylist of occupied farm lots
     */
    public ArrayList<FarmLot> getAdjacent(int row, int col) {
        int n = this.farmLot.length;
        int m = this.farmLot[0].length;

        ArrayList<FarmLot> lot = new ArrayList<FarmLot>();

        for (int dx = (row > 0 ? -1 : 0); dx <= (row < n ? 1 : 0); ++dx) {
 
            // Deviation of the column that
            // gets adjusted according to
            // the provided position
            for (int dy = (col > 0 ? -1 : 0); dy <= (col < m ? 1 : 0); ++dy) {
                if (dx != 0 || dy != 0) {
                    lot.add(farmLot[row + dx][col + dy]);
                }
            }
        }
    
        // Returning the array
        return lot;
    }

    /**
     * Gets the number of adjacent tiles that are occupied
     * @param row the row index of the specified farm lot
     * @param col the column index of the specified farm lot
     * @return the adjacent count
     */
    public int getAdjacentCount(int row, int col) {
        ArrayList<FarmLot> adjacentLots = getAdjacent(row, col);
        int adjacentCount = 0;

        for(int i = 0; i < adjacentLots.size(); i++) {
            if( adjacentLots.get(i).getOccupied() == true ||
                adjacentLots.get(i).getRockedStatus() == true)
                adjacentCount++;
        }
        return adjacentCount;
    }

    /**
     * Plants a seed to a farm lot
     * @param seedName the plant name to be planted
     * @param row the row index of the specified farm lot
     * @param col the column index of the specified farm lot
     * @return the string to display in the GUI
     */
    public String plantSeed(String seedName, int row, int col) {
        boolean result = false;
        int cost;
        String plantPrompt = "";
        FarmLot lot = getFarmLot(row, col);

        //checks if tile contains a withered plant
        if(lot.getWitherStatus() == false) {
            //checks if the tile is plowed
            if(lot.getPlowStatus() == true) {
                //checks if there is a seed planted
                if(lot.getOccupied() == true) 
                    plantPrompt += "This lot is already occupied.";

                //if no seed
                else {
                    //checks if the name of seed passes exists
                    for(Seed s: getAllSeed()){
                        //if found
                        if(s.getName().equalsIgnoreCase(seedName)) {

                            //checks if farmer has enough objectcoins to buy the seed
                            if(farmer.getCoins() >= s.getCost()){
                                if(s.getType().equals("Fruit tree")) {
                                    if( (row <= 9 && (col == 0 || col == 4)) || ( (row == 0 || row == 9) && col < 5)  ) {
                                        plantPrompt += "You can't plant " + s.getName() + " on the corner/ far sides of the farm";
                                    }
                                    else if(getAdjacentCount(row, col) != 0) {
                                        plantPrompt += "You can't plant " + s.getName() + " because adjacent tiles are occupied";
                                    }
                                    else {
                                        lot.setSeed(new Seed(s));
                                        cost = s.getCost();
                                        result = true;
                                        plantPrompt += s.getName() + " has been planted.";

                                        plantPrompt += "\nYou have used " + cost + " objectcoins.";
                                        farmer.updateObjectCoins(farmer.getCoins() - cost);
                                    }
                                }
                                else {
                                    lot.setSeed(new Seed(s));
                                    cost = s.getCost();
                                    result = true;
                                    plantPrompt += s.getName() + " has been planted.";

                                    plantPrompt += "\nYou have used " + cost + " objectcoins.";
                                    farmer.updateObjectCoins(farmer.getCoins() - cost);
                                }
                            }
                            else
                                plantPrompt += "You do not have enough objectcoins";
                        }
                    }
                    //if seed was planted successfully
                    if(result == true)
                        lot.isOccupied(true);
                }   
            }
            else
                plantPrompt += "This lot is not yet plowed";
        }
        else    
            plantPrompt += "This lot contains a withered plant";

        return plantPrompt;
    }

    /**
     * Uses watering can tool
     * @param row the row index of the farm lot to water
     * @param col the column index of the farm lot to water
     * @param toolname the watercan name
     * @return the string to display in the GUI
     */
    public String useWaterCan(int row, int col, String toolname) {
        FarmLot lot = this.farmLot[row][col];
        Tool waterCan = getTool(toolname);
        String waterPrompt = "";

        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.getPlowStatus() != false) {
                    lot.increaseWater();
                    waterPrompt += "\nTile has been watered.";
            
                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + waterCan.getXP();

                    waterPrompt += "\nYou have gained " + waterCan.getXP() + " experience.\n";
                    farmer.updateXP(tempXP);

                    if(tempXP > currXP){
                        farmer.getLevel();
                    }

                    //checks if crop is harvestable
                    if(lot.isHarvestable() == true){
                        waterPrompt += lot.getSeed().getName() + " is ready to harvest!";
                    }
                }
                else
                    waterPrompt += "\nTile is not yet plowed or no seed has been planted";
            }
            else
                waterPrompt += "This lot does not have any seed.\n";
        }
        else 
            waterPrompt += "This lot contains a withered plant";

        return waterPrompt;
    }
    
    /**
     * Uses plow tool
     * @param row the row index of the farm lot to fertilize
     * @param col the column index of the farm lot to fertilize
     * @param toolname the fertilizer name
     * @return the string to display in the GUI
     */
    public String useFertilizer(int row, int col, String toolname) {
        FarmLot lot = farmLot[row][col];
        Tool fertilizer = getTool(toolname);
        String fertilizerPrompt = "";

        //checks if farmer has enough objectcoins
        if(farmer.getCoins() >= fertilizer.getCost()){
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {{
                    if(lot.getPlowStatus() != false) {
                        lot.increaseFertilizer();

                        fertilizerPrompt += "\nTile is now fertilized ";
                        farmer.updateObjectCoins(farmer.getCoins() - fertilizer.getCost());
                        farmer.updateXP(farmer.getXP() + fertilizer.getXP());

                        fertilizerPrompt += "\nYou have used " + fertilizer.getCost() + " objectCoins";
                        fertilizerPrompt += "\nYou have gained " + fertilizer.getXP() + " experience.\n";

                        if(lot.isHarvestable() == true)
                            fertilizerPrompt += lot.getSeed().getName() + " is ready to harvest!";
                    }
                    else
                        fertilizerPrompt += "\nTile is not yet plowed or no seed has been planted";
                }
            }
            else
                fertilizerPrompt += "This lot does not have any seed.\n";
            }
            else 
                fertilizerPrompt += "This lot contains a withered plant";
        }
        else 
            fertilizerPrompt += "You do not have enough coins to use the fertilizer!";

        return fertilizerPrompt;
    }
    
    /**
     * Uses pickaxe tool
     * @param row the row index of the farm lot that has a rock
     * @param col the column index of the farm lot that has a rock
     * @param toolname the pickaxe name
     * @return the string to display in the GUI
     */
    public String usePickaxe(int row, int col, String toolname) {
        FarmLot lot = farmLot[row][col];
        Tool pickaxe = getTool(toolname);
        String pickaxePrompt = "";

        // check if there are rocks on the tile
        if(lot.getRockedStatus() == true){
            // check if you have enough object coins
            if(farmer.getCoins() >= pickaxe.getCost()){
                // remove rock
                lot.isRocked(false);
                // update player stats
                double currXP = farmer.getXP();
                double tempXP = farmer.getXP() + pickaxe.getXP();

                farmer.updateObjectCoins(farmer.getCoins() - pickaxe.getCost());
                farmer.updateXP(tempXP);
                pickaxePrompt += "\nYou have used " + pickaxe.getCost() + " objectcoins.";
                pickaxePrompt += "\nYou gained " + pickaxe.getXP() + " experience.";

                if(tempXP > currXP){
                    farmer.getLevel();
                }
            }
            else
                pickaxePrompt += "You do not have enough coins to use the pickaxe!";
        } 
        else
            pickaxePrompt += "There is no rock on this tile.\n";

        return pickaxePrompt;
    }

    /**
     * Uses shovel tool
     * @param row the row index of the farm lot 
     * @param col the column index of the farm lot 
     * @param toolname the shovel name
     * @return the string to display in the GUI
     */
    public String useShovel(int row, int col, String toolname) {
        FarmLot lot = farmLot[row][col];
        Tool shovel = getTool(toolname);
        String shovelPrompt = "";

        if(farmer.getCoins() >= shovel.getCost()){
            if(shovel.getName().equalsIgnoreCase("shovel")) {
                if(lot.getWitherStatus() == false) {
                    if(lot.getPlowStatus() != false) {
                        if(shovel.getName().equalsIgnoreCase("shovel")) {
                            //if there is a seed on the tile
                            if(lot.getSeed() != null) {
                                //removes the seed from the tile
                                shovelPrompt += lot.getSeed().getName() + " has been removed.";
                                lot.getSeed().resetSeed();
                                lot.setSeed(null);
                            }
                            //reverts the farmLot to its default
                            lot.isPlowed(false);
                            lot.resetFarmLot();
                            shovelPrompt += "\nTile has reverted back to being unplowed";
                        }
                    }
                    //if shovel is used on unplowed tile
                    else
                        shovelPrompt += "Nothing happened but... \n";
                }
                //if tile contains a withered plant
                else {
                    //removes the seed and resets the farmlot to its default
                    shovelPrompt += "Withered " + lot.getSeed().getName() +" has been removed.\n";
                    lot.getSeed().resetSeed();
                    lot.setSeed(null);
                    lot.resetFarmLot();
                    shovelPrompt += "Tile is now unplowed.";
                }
            }
            
            double currXP = farmer.getXP();
            double tempXP = farmer.getXP() + shovel.getXP();

            farmer.updateObjectCoins(farmer.getCoins() - shovel.getCost());
            farmer.updateXP(tempXP);
            shovelPrompt += "\nYou have used " + shovel.getCost() + " objectcoins.";
            shovelPrompt += "\nYou gained " + shovel.getXP() + " experience.";

            if(tempXP > currXP){
                farmer.getLevel();
            }     
        }
        else
            shovelPrompt += "You do not have enough coins to use the shovel!";

        return shovelPrompt;
    }
    
    /**
     * Harvests the crop in a specified farm lot
     * @param row the row index of the farm lot
     * @param col the column index of the farm lot
     * @return the string to display in the GUI
     */
    public String harvestTile(int row, int col) {
        FarmLot lot = this.farmLot[row][col];
        String harvestPrompt = "";

        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    harvestPrompt += "\n[Harvest Details]";
                    int numProduced = lot.getSeed().getProductProduced();

                    harvestPrompt += "\nThe seed produced " + numProduced + " " +
                    lot.getSeed().getName();
                    
                    //computes for the total objectcoins to be gained after harvesting
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterNeed()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerNeed();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;

                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + lot.getSeed().getExperienceYield();
                    
                    //if crop is a flower
                    if(lot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;

                    harvestPrompt += "\nYou have earned " + (float) finalHarvestTotal + " objectCoins";
                    harvestPrompt += "\nYou have earned " + lot.getSeed().getExperienceYield() + " experience";
                    farmer.updateObjectCoins(farmer.getCoins() + finalHarvestTotal);
                    farmer.updateXP(tempXP);

                    if(tempXP > currXP){
                        farmer.getLevel();
                    }

                    lot.getSeed().resetSeed();
                    lot.resetFarmLot();
                }
                else
                    harvestPrompt += "The crop is not yet harvestable\n";
            }
            else 
                harvestPrompt += "There is no seed in this tile.\n";
        }
        else 
            harvestPrompt += "This lot contains a withered plant";

        return harvestPrompt;
    }

    /**
     * Displays the tile information
     * @param row the row index of the farm lot
     * @param col the column index of the farm lot
     * @return the string to display in the GUI
     */
    public String displayTileInfo(int row, int col)  {
        FarmLot lot = this.farmLot[row][col];
        String tileInfo = "\n[Tile Information]\n" + "Plow Status: ";

        if(lot.getPlowStatus() == true) {
            tileInfo += "Plowed";
            if(lot.getOccupied() == false) {
                tileInfo += "\nNo seed is planted here";
            }
        
            else {
                tileInfo += "\nSeed planted: " + lot.getSeed().getName() + 
                            "\nWater count: " + lot.getTotalWaterCount() +
                            "\nFertilizer count: " + lot.getTotalFertilizerCount() +
                            "\nDays growed: " + lot.getSeed().getDayGrowth() +
                            "\nHarvestable: ";

                if(lot.isHarvestable() == true)
                    tileInfo += "Yes";
                else
                    tileInfo += "No";

                tileInfo += "\nWithered: ";
                if(lot.getWitherStatus() == true)
                    tileInfo += "Yes";
                else 
                    tileInfo += "No";
            }
        }
        else 
            tileInfo += "Unplowed";
        
        return tileInfo;
    }

    /**
     *  Resets the data of the tile 
     */
    public void resetFarm() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                this.farmLot[i][j].resetFarmLot();
            }
        }
        removeSeed();
        removeTool();
        this.day = 1;
        this.farmer.resetFarmer();
    }
}
