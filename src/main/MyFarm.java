package main;
/**
 * This class contains the constructor and different methods that will
 * be used in managing the farm.
 */

import java.util.ArrayList;

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
     * setting the day to 1 and a farmer.
     */
    public MyFarm() {
        this.farmLot = new FarmLot[10][5];
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
        this.farmer = null;
        this.totalSpace = 50;
        //this.availableSpace = 50;
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
        return farmer;
    }

    public String registerFarmer(String farmerType) {
        String registerPrompt = "";
        if(	(farmer.getLevel() < 5 && farmerType.equals("Registered Farmer")) || 
					(farmer.getLevel() < 10 && farmerType.equals("Distinguished Farmer")) ||
					(farmer.getLevel() < 15 && farmerType.equals("Legendary Farmer"))) {
					registerPrompt += "You have not reached the minimum level to register.";
		}
		
        else if((farmer.getLevel() >= 5 && farmer.getCoins() < 200 && farmerType.equals("Registered Farmer")) || 
						(farmer.getLevel() >= 10 && farmer.getCoins() < 300 && farmerType.equals("Distinguished Farmer")) ||
						(farmer.getLevel() >= 15 && farmer.getCoins() < 400 && farmerType.equals("Legendary Farmer"))) {
					registerPrompt += "You have not reached the minimum objectCoins to register.";
		}

	    else if(farmerType.equals("Distinguished Farmer") && !farmer.getType().equals("Registered Farmer")) {
					registerPrompt += "You must register as Registered Farmer first.";
		}

		else if(farmerType.equals("Legendary Farmer") && !farmer.getType().equals("Distinguished Farmer")) {
					registerPrompt += "You must register as Distinguished Farmer first.";
		}
        
        else {
            switch(farmerType){ 
                case "Registered Farmer":
                    if(farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + farmerType;
                    }
                    else if (farmer.getLevel() >= 5 && farmer.getCoins() >= 200){
                        farmer.setFarmerType(farmerType); 
                        farmer.updateObjectCoins(farmer.getCoins() - 200);
                        for(Seed s: seed) {
                            s.setBasePrice(1);
                            s.setCost(1);
                        }
                        registerPrompt += "You have used 200 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 1 objectCoin\n" + 
                                            "1 bonus earnings per produce";
                    } 
                    break;
                
                case "Distinguished Farmer":
                    if(farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + farmerType;
                    }
                    else if (farmer.getLevel() >= 10 && farmer.getCoins() >= 300){
                        farmer.setFarmerType(farmerType); 
                        farmer.updateObjectCoins(farmer.getCoins() - 300);
                        for(Seed s: seed) {
                            s.setBasePrice(2);
                            s.setCost(2);
                        }
                        registerPrompt += "You have used 300 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 2 objectCoins\n" + 
                                            "2 bonus earnings per produce";
                    } 
                    break;
    
                case "Legendary Farmer":
                    if(farmer.getType().equals(farmerType)) {
                        registerPrompt += "You have already registered as " + farmerType;
                    }
                    else if (farmer.getLevel() >= 15 && farmer.getCoins() >= 400){
                        farmer.setFarmerType(farmerType); 
                        farmer.updateObjectCoins(farmer.getCoins() - 400);
                        for(Seed s: seed) {
                            s.setBasePrice(4);
                            s.setCost(3);
                        }
                        registerPrompt += "You have used 400 objectCoins to register\n" + "You are now a " + farmerType + "\nBonuses:\n" +
                                            "Seed cost is reduced with 4 objectCoins\n" + 
                                            "3 bonus earnings per produce";
                    } 
                    break;
            }
        }
        return registerPrompt;
    } 
    
    /**
     * Adds a farmLot to the farm
     */
    public void addTile(int row, int col) {
        this.farmLot[row][col] = new FarmLot();
    }

    /** 
     * Gets a farm lot object / a tile on the farm
     * @param index the index of the tile/farmLot
     * @return the farm lot object / a tile on the farm
     */
    public FarmLot getFarmLot(int row, int col){
        return this.farmLot[row][col];
    }

    /*public ArrayList<FarmLot> getAllFarmLot() {
        return this.farmLot;
    }*/

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

    //TODO getToolInfo -- switch statement
    
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
     * Clear the tool data 
     */
    public void removeTool() {
        this.tool.removeAll(this.tool);
    }
    
    /** 
     * Gets all the content of the tool array list
     * @return the tool arraylist
     */
    public ArrayList<Tool> getAllTool() {
        return tool;
    }
    
    /** 
     * Gets the day the game is currently on
     * @return the day count of the game
     */
    public int getDay() {
        return this.day;
    }

    public void advanceDay() {
        this.day++;
    }

    public int getTotalSpace() {
        return this.totalSpace;
    }

    public int getAvailableSpace() {
        int occupiedTile = 0;

        /*for(FarmLot f: this.farmLot)
            if(f.getSeed() != null)
                occupiedTile++;*/

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.farmLot[i][j].getSeed() != null)
                    occupiedTile++;
            }
        }
        return totalSpace - occupiedTile;
    }

    /**
     * Directs the game to the next day, checks if the crop has grown, is harvestable, or is withered 
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
                            //System.out.println("Because you did not harvest the " + this.farmLot[i][j].getSeed().getName() + ",");
                            nextDayPrompt += this.farmLot[i][j].isWithered(true);
                        }
                        //checks if crop reach the harvest time and meet the minimum requirements to be harvested
                        else if(this.farmLot[i][j].getSeed().getDayGrowth() == this.farmLot[i][j].getSeed().getHarvestTime()) {
                            //if crop did not meet the minimum requirements
                            if(this.farmLot[i][j].isHarvestable() == false) {
                                nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j + 
                                    ") growed but did not get <br>taken care of properly. ";
                                //System.out.println(this.farmLot[i][j].getSeed().getName() + " growed but did not get taken care of properly.");
                                nextDayPrompt += this.farmLot[i][j].isWithered(true);
                            }
                            //crop meets the minimum requirements
                            else 
                                nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j + ") is harvestable";
                                //System.out.println("\n" + (this.farmLot[i][j].getSeed().getName() + " is harvestable"));
                        }
                        //crop grows
                        else
                            nextDayPrompt += "<br>" + this.farmLot[i][j].getSeed().getName() + " in FarmLot (" + i + "," + j +  ") growed.";
                            //System.out.println("\n" + this.farmLot[i][j].getSeed().getName() + " growed.");
                    }
                }
            }
        }
        return nextDayPrompt += "</html>";
    }

    public int getWitherCount() {
        return this.witherCount;
    }
    /*
     * Checks if the game is still running
     * @return the game status
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
     * Defines and adds a seed to the seed array list
     * 
     * @param name   name of the seed
     * @param type   type of seed
     * @param harvestTime   time needed for the seed to be ready for harvest
     * @param waterNeed   amount of water needed for the seed to be ready for harvest
     * @param fertilizerNeed   amount of fertilizer needed for the seed to be ready for harvest
     * @param cost   cost for each seed
     * @param basePrice   base price for selling harvested crop
     * @param xp   experience yielded after harvesting
     */
    //public void addSeeds(String name, String type, int harvestTime, 
                         //int waterNeed, int fertilizerNeed, int cost,
                        // int basePrice, double xp) {
        //this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost, basePrice, xp));
   // }
    public void addSeeds(Seed seed) {
        this.seed.add(seed);
    }
    
    /** 
     * Defines and adds a tool to the tool array list
     * 
     * @param name   Name of the tool
     * @param cost   Cost of the tool
     * @param xp     Experience yielded for each use of the tool
     */
    //public void addTools(String name, int cost, double xp) {
    //    this.tool.add(new Tool(name, cost, xp));
    //}

    public void addTools(Tool tool) {
        this.tool.add(tool);
    }
    /** 
     * To plow the tile
     * @param lot   the tile to plow
     * @param plow   The tool object used to plow the tile
     */
    public String usePlow(FarmLot lot, Tool plow) {
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
                            //System.out.println("\nTile is now plowed.");
                        
                            double currXP = farmer.getXP();
                            double tempXP = farmer.getXP() + plow.getXP();

                            farmer.updateXP(tempXP);
                            plowPrompt += "\nYou have gained " + plow.getXP() + " experience.";
                            //System.out.println("You have gained " + plow.getXP() + " experience.");
                            
                            //checks if the farmer levels up
                            if(tempXP > currXP){
                                farmer.getLevel();
                            }

                        }
                        else
                            plowPrompt += "\nYou have used a wrong tool.";
                            //System.out.println("\nYou have used a wrong tool.");
                    }
                    else   
                        plowPrompt += "This lot contains a withered plant.";
                        //System.out.println("This lot contains a withered plant.");
                }
                else
                    plowPrompt += "This lot is already plowed.";
                    //System.out.println("This lot is already plowed.");
        }
        else
            plowPrompt += "There is a rock on the lot!";
            //System.out.println("There is a rock on the lot!");
        return plowPrompt;
    }
    
    /**
     * Creates and returns an arraylist of farmlots that are adjacent to
     * a specified farmlot
     * Reference: https://www.geeksforgeeks.org/find-all-adjacent-elements-of-given-element-in-a-2d-array-or-matrix/
     * @param farmLot the array of farmlots
     * @param row the row index of the specified farm lot
     * @param col the column index of the specified farm lot
     * @return the arraylist of occupied farm lots
     */
    public ArrayList<FarmLot> getAdjacent(FarmLot[][] farmLot, int row, int col) {
        int n = farmLot.length;
        int m = farmLot[0].length;

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
    
        // Returning the vector array
        return lot;
    }

    /**
     * Gets the number of adjacent tiles that are occupied
     * @param row the row index of the specified farm lot
     * @param col the column index of the specified farm lot
     * @return the adjacent count
     */
    public int getAdjacentCount(int row, int col) {
        ArrayList<FarmLot> adjacentLots = getAdjacent(farmLot, row, col);
        int adjacentCount = 0;

        for(int i = 0; i < adjacentLots.size(); i++) {
            if( adjacentLots.get(i).getOccupied() == true ||
                adjacentLots.get(i).getRockedStatus() == true)
                adjacentCount++;
        }
        return adjacentCount;
    }

    /** 
     * To plant a seed on the tile
     * @param lot   the tile where to plant a seed
     * @param seedName   The name of the seed to be planted
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
                    //System.out.println("\nThis lot is already occupied.");
                //if no seed
                else {
                    //checks if the name of seed passes exists
                    for(Seed s: getAllSeed()){
                        //if found
                        if(s.getName().equalsIgnoreCase(seedName)) {
                            //checks if farmer has enough objectcoins to buy the seed

                            // if seedType == fruit tree
                                // check if the tiles beside the selected tile is occupied
                                // then proceed to if statement in "else"
                            // else:
                            
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
                                        //System.out.println(s.getName() + " has been planted.");

                                        plantPrompt += "\nYou have used " + cost + " objectcoins.";
                                        //System.out.println("You have used " + cost + " objectcoins.");
                                        farmer.updateObjectCoins(farmer.getCoins() - cost);
                                    }
                                }
                                else {
                                    lot.setSeed(new Seed(s));
                                    cost = s.getCost();
                                    result = true;
                                    plantPrompt += s.getName() + " has been planted.";
                                    //System.out.println(s.getName() + " has been planted.");

                                    plantPrompt += "\nYou have used " + cost + " objectcoins.";
                                    //System.out.println("You have used " + cost + " objectcoins.");
                                    farmer.updateObjectCoins(farmer.getCoins() - cost);
                                }
                            }
                            else
                                plantPrompt += "You do not have enough objectcoins";
                                //System.out.println("You do not have enough objectcoins");
                        }
                    }
                    //if seed was planted successfully
                    if(result == true)
                        lot.isOccupied(true);
                }   
            }
            else
                plantPrompt += "This lot is not yet plowed";
                //System.out.println("This lot is not yet plowed");
        }
        else    
            plantPrompt += "This lot contains a withered plant";
            //System.out.println("This lot contains a withered plant.");

        return plantPrompt;
    }

    /** 
     * To water the crop on the tile
     * @param lot   the tile where to water
     * @param waterCan    The tool object to be used to water the tile
     */
    public String useWaterCan(FarmLot lot, Tool waterCan) {
        String waterPrompt = "";
        //checks if the tool exists
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {
                    if(lot.getPlowStatus() != false && lot.getSeed() != null) {
                        lot.increaseWater();
                        waterPrompt += "\nTile has been watered.";
                            //System.out.println("\nTile has been watered.");
            
                        double currXP = farmer.getXP();
                        double tempXP = farmer.getXP() + waterCan.getXP();

                        waterPrompt += "\nYou have gained " + waterCan.getXP() + " experience.\n";
                            //System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                        farmer.updateXP(tempXP);

                        if(tempXP > currXP){
                            farmer.getLevel();
                        }

                        //checks if crop is harvestable
                        if(lot.isHarvestable() == true){
                            waterPrompt += lot.getSeed().getName() + " is ready to harvest!";
                                //System.out.println(lot.getSeed().getName() + " is ready to harvest!");
                        }
                    }
                    else
                        waterPrompt += "\nTile is not yet plowed or no seed has been planted";
                            //System.out.println("\nTile is not yet plowed or no seed has been planted");
                }
                else
                    waterPrompt += "This lot does not have any seed.\n";
                    //System.out.println("This lot does not have any seed.\n");
            }
            else 
                waterPrompt += "This lot contains a withered plant";
               // System.out.println("This lot contains a withered plant");
        return waterPrompt;
    }
    
    /** 
     * To Fertilize the crop on the tile
     * @param lot   the tile where to put fertilizer
     * @param fertilizer   The tool object to be used to fertilize the tile
     */
    public String useFertilizer(FarmLot lot, Tool fertilizer) {
        String fertilizerPrompt = "";
        //checks if farmer has enough objectcoins
        if(farmer.getCoins() >= fertilizer.getCost()){
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {{
                    if(lot.getPlowStatus() != false) {
                        lot.increaseFertilizer();
                            //System.out.println("\nTile is now fertilized ");

                        fertilizerPrompt += "\nTile is now fertilized ";
                        farmer.updateObjectCoins(farmer.getCoins() - fertilizer.getCost());
                        farmer.updateXP(farmer.getXP() + fertilizer.getXP());

                        fertilizerPrompt += "\nYou have used " + fertilizer.getCost() + " objectCoins";
                        fertilizerPrompt += "\nYou have gained " + fertilizer.getXP() + " experience.\n";
                            //System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");

                        if(lot.isHarvestable() == true)
                            fertilizerPrompt += lot.getSeed().getName() + " is ready to harvest!";
                                //System.out.println(lot.getSeed().getName() + " is ready to harvest!");      
                    }
                    else
                        fertilizerPrompt += "\nTile is not yet plowed or no seed has been planted";
                            //System.out.println("\nTile is not yet plowed or no seed has been planted");
                }
            }
            else
                fertilizerPrompt += "This lot does not have any seed.\n";
                    //System.out.println("This lot does not have any seed.\n");
            }
            else 
                fertilizerPrompt += "This lot contains a withered plant";
                //System.out.println("This lot contains a withered plant");
        }
        else 
            fertilizerPrompt += "You do not have enough coins to use the fertilizer!";
            //System.out.println("You do not have enough coins to use the fertilizer!");
        return fertilizerPrompt;
    }
    
    /** 
     * To use the pickaxe to remove rocks on the tile
     * @param lot   the tile to remove a rock
     * @param pickaxe   The tool object to be used on the tile
     */
    public String usePickaxe(FarmLot lot, Tool pickaxe){
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
                //System.out.println("You have used " + pickaxe.getCost() + " objectcoins.");
                //System.out.println("You gained " + pickaxe.getXP() + " experience.");

                if(tempXP > currXP){
                    farmer.getLevel();
                }
                
            }
            else
                pickaxePrompt += "You do not have enough coins to use the pickaxe!";
                //System.out.println("You do not have enough coins to use the pickaxe!");
        } 
        else
            pickaxePrompt += "There is no rock on this tile.\n";
            //System.out.println("There is no rock on this tile.\n");
        return pickaxePrompt;
    }

    /** 
     * To use the shovel used to remove seeds or crops on the tile
     * @param lot   the tile where to use shovel
     * @param shovel   The tool object to be used on the tile
     */
    public String useShovel(FarmLot lot, Tool shovel) {
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
                                //System.out.println("Plant has been removed.");
                            }
                            //reverts the farmLot to its default
                            lot.isPlowed(false);
                            lot.resetFarmLot();
                            shovelPrompt += "\nTile has reverted back to being unplowed";
                            //System.out.println("Tile has reverted back to being unplowed");       
                        }
                    }
                    //if shovel is used on unplowed tile
                    else
                        shovelPrompt += "Nothing happened but... \n";
                        //System.out.println("Nothing happened but... \n");
                }
                //if tile contains a withered plant
                else {
                    //removes the seed and resets the farmlot to its default
                    shovelPrompt += "Withered " + lot.getSeed().getName() +" has been removed.\n";
                    lot.getSeed().resetSeed();
                    lot.setSeed(null);
                    lot.resetFarmLot();
                    shovelPrompt += "Tile is now unplowed.";
                    //System.out.println("Withered plant has been removed.");
                    //System.out.println("Tile is now unplowed.");
                }
            }
            
            double currXP = farmer.getXP();
            double tempXP = farmer.getXP() + shovel.getXP();

            farmer.updateObjectCoins(farmer.getCoins() - shovel.getCost());
            farmer.updateXP(tempXP);
            shovelPrompt += "\nYou have used " + shovel.getCost() + " objectcoins.";
            shovelPrompt += "\nYou gained " + shovel.getXP() + " experience.";
            //System.out.println("You have used " + shovel.getCost() + " objectcoins.");
            //System.out.println("You gained " + shovel.getXP() + " experience.");

            if(tempXP > currXP){
                farmer.getLevel();
            }     
        }
        else
            shovelPrompt += "You do not have enough coins to use the shovel!";
            //System.out.println("You do not have enough coins to use the shovel!");
        return shovelPrompt;
    }
    
    /** 
     * To harvest the crop on the tile
     * @param lot   the tile to harvest a plant
     */
    public String harvestTile(FarmLot lot) {
        String harvestPrompt = "";
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    harvestPrompt += "\n[Harvest Details]";
                    //System.out.println("\n[Harvest Details]");
                    int numProduced = lot.getSeed().getProductProduced();

                    harvestPrompt += "\nThe seed produced " + numProduced + " " +
                    lot.getSeed().getName();
                    //System.out.println("The seed produced " + numProduced + " " +
                    //lot.getSeed().getName());
                    
                    //computes for the total objectcoins to be gained after harvesting
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterCount()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerCount();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;

                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + lot.getSeed().getExperienceYield();
                    
                    //if crop is a flower
                    if(lot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;

                    harvestPrompt += "\nYou have earned " + (float) finalHarvestTotal + " objectCoins";
                    harvestPrompt += "\nYou have earned " + lot.getSeed().getExperienceYield() + " experience";
                    //System.out.println("You have earned " + (float) finalHarvestTotal + " objectCoins");
                    //System.out.println("You have earned " + lot.getSeed().getExperienceYield() + " experience");
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
                    //System.out.println("The crop is not yet harvestable\n");
            }
            else 
                harvestPrompt += "There is no seed in this tile.\n";
                //System.out.println("There is no seed in this tile.\n");
        }
        else 
            harvestPrompt += "This lot contains a withered plant";
            //System.out.println("This lot contains a withered plant");
        return harvestPrompt;
    }

    /**
     *  Displays the information of atile 
     * @param lot   the tile to display information
     */
    public String displayTileInfo(FarmLot lot)  {
        String tileInfo = "\n[Tile Information]\n" + "Plow Status: ";

       // System.out.println("\n[Tile Information]");
        //System.out.print("Plow Status:");
        if(lot.getPlowStatus() == true) {
            tileInfo += "Plowed";
            //System.out.println(" Plowed");
            if(lot.getOccupied() == false) {
                //System.out.println("No seed is planted here.");
                tileInfo += "\nNo seed is planted here";
            }
        
            else {
                tileInfo += "\nSeed planted: " + lot.getSeed().getName() + 
                            "\nWater count: " + lot.getWaterCount() +
                            "\nFertilizer count: " + lot.getFertilizerCount() +
                            "\nDays growed: " + lot.getSeed().getDayGrowth() +
                            "\nHarvestable: ";
                //System.out.println("Seed planted: " + lot.getSeed().getName());
                //System.out.println("Water count: " + lot.getWaterCount());
                //System.out.println("Fertilizer count: " + lot.getFertilizerCount());
                //System.out.println("Days growed: " + lot.getSeed().getDayGrowth());
                //System.out.print("Harvestable:" );
                if(lot.isHarvestable() == true)
                    tileInfo += "Yes";
                    //System.out.println(" Yes");
                else
                    tileInfo += "No";
                    //System.out.println(" No");
                tileInfo += "\nWithered: ";
                //System.out.print("Has a withered plant: ");
                if(lot.getWitherStatus() == true)
                    tileInfo += "Yes";
                    //System.out.println(" Yes");
                else 
                    tileInfo += "No";
                    //System.out.println(" No");
            }
        }
        else 
            tileInfo += "Unplowed";
            //System.out.println(" Unplowed");     
        
        return tileInfo;
    }


    /**
     *  Resets the data of the tile 
     */
    public void resetFarm() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
                this.farmLot[i][j].resetFarmLot();
            }
        }
        //for(FarmLot f: this.farmLot)
            //f.resetFarmLot();
        removeSeed();
        removeTool();
        this.day = 1;
        this.farmer.resetFarmer();
    }
}
