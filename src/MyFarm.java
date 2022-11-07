/**
 * This class contains the constructor and different methods that will
 * be used in managing the farm.
 */

import java.util.ArrayList;

public class MyFarm {
    private Farmer farmer;
    private ArrayList<FarmLot> farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;

    /**
     * Constructs a farm by creating a farmlot, arraylist of seeds and tools,
     * setting the day to 1 and a farmer.
     */
    public MyFarm() {
        this.farmLot = new ArrayList<FarmLot>();
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
        this.farmer = null;
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
    
    /**
     * Adds a farmLot to the farm
     */
    public void addTile() {
        if(this.farmLot.size() <= 50)
            this.farmLot.add(new FarmLot());
        else 
            System.out.println("Farm is already full.");
    }

    /** 
     * Gets a farm lot object / a tile on the farm
     * @param index the index of the tile/farmLot
     * @return the farm lot object / a tile on the farm
     */
    public FarmLot getFarmLot(int index){
        return this.farmLot.get(index);
    }
    
    /** 
     * Gets the whole seed arraylist
     * @return the seed arraylist
     */
    public ArrayList<Seed> getSeed() {
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
        return day;
    }

    /**
     * Directs the game to the next day, checks if the crop has grown, is harvestable, or is withered 
     */
    public void advanceNextDay(){
        this.day++;
        System.out.println("\nDay " + this.day + " of the game.");

        for(FarmLot f : this.farmLot) {
            if(f.getSeed() != null) {
                f.getSeed().grow();
    
                //Checks if crop reached more than the number of days 
                if(f.getSeed().getDayGrowth() > f.getSeed().getHarvestTime()) {
                    System.out.println("Because you did not harvest the " + f.getSeed().getName() + ",");
                    f.isWithered(true);
                }
                //checks if crop reach the harvest time and meet the minimum requirements to be harvested
                else if(f.getSeed().getDayGrowth() == f.getSeed().getHarvestTime()) {
                    //if crop did not meet the minimum requirements
                    if(f.isHarvestable() == false) {
                        System.out.println(f.getSeed().getName() + " growed but did not get taken care of properly.");
                        f.isWithered(true);
                    }
                    //crop meets the minimum requirements
                    else 
                        System.out.println("\n" + (f.getSeed().getName() + " is harvestable"));
                }
                //crop grows
                else
                    System.out.println("\n" + f.getSeed().getName() + " growed.");
            }
        }
    }

    /*
     * Checks if the game is still running
     * @return the game status
     */
    public boolean isRunning() {
        int count1 = 0;
        int count2 = 0;

        //checks how many tiles contain seed
        for(FarmLot f: this.farmLot)
            if(f.getSeed() != null)
                count1++;

        //checks how many tiles contain withered plant
        for(FarmLot f: this.farmLot)
            if(f.getWitherStatus() == true)
                count2++;

        //checks if farmer can no longer buy a seed with no active crop OR the farm tiles contain withered plant
        if((farmer.getCoins() < 5 && count1 == 0) || count2 == 1)
            return false;
        
        return true;
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
    public void addSeeds(String name, String type, int harvestTime, 
                         int waterNeed, int fertilizerNeed, int cost,
                         int basePrice, double xp) {
        this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost, basePrice, xp));
    }

    
    /** 
     * Defines and adds a tool to the tool array list
     * 
     * @param name   Name of the tool
     * @param cost   Cost of the tool
     * @param xp     Experience yielded for each use of the tool
     */
    public void addTools(String name, int cost, double xp) {
        this.tool.add(new Tool(name, cost, xp));
    }

    /** 
     * To plow the tile
     * @param lot   the tile to plow
     * @param plow   The tool object used to plow the tile
     */
    public void usePlow(FarmLot lot, Tool plow) {
        //checks if tool object exists
        if(plow != null) {
            //checks if tile is not yet plowed
            if(lot.getPlowStatus() == false) {
                //checks if tile contains a withered plant
                if(lot.getWitherStatus() == false) {
                    //checks if the tool object passed contains a name "plow"
                    if(plow.getName().equalsIgnoreCase("plow")) {
                        lot.isPlowed(true);
                        System.out.println("\nTile is now plowed.");
                    
                        double currXP = farmer.getXP();
                        double tempXP = farmer.getXP() + plow.getXP();

                        farmer.updateXP(tempXP);
                        System.out.println("You have gained " + plow.getXP() + " experience.");
                        
                        //checks if the farmer levels up
                        if(tempXP > currXP){
                            farmer.getLevel();
                        }

                    }
                    else
                        System.out.println("\nYou have used a wrong tool.");
                }
                else    
                    System.out.println("This lot contains a withered plant.");
            }
            else
                System.out.println("This lot is already plowed.");
        }
        else
            System.out.println("Tool does not exist.");
    }
    
    /** 
     * To plant a seed on the tile
     * @param lot   the tile where to plant a seed
     * @param seedName   The name of the seed to be planted
     */
    public void plantSeed(FarmLot lot, String seedName) {
        boolean result = false;
        boolean found = false;

        //checks if tile contains a withered plant
        if(lot.getWitherStatus() == false) {
            //checks if the tile is plowed
            if(lot.getPlowStatus() == true) {
                //checks if there is a seed planted
                if(lot.getOccupied() == true) 
                    System.out.println("\nThis lot is already occupied.");
                //if no seed
                else {
                    //checks if the name of seed passes exists
                    for(Seed s: getSeed()){
                        //if found
                        if(s.getName().equalsIgnoreCase(seedName)) {
                            found = true;
                            //checks if farmer has enough objectcoins to buy the seed
                            if(farmer.getCoins() >= s.getCost()){
                                lot.setSeed(s);
                                result = true;
                                System.out.println(s.getName() + " has been planted.");
                                System.out.println("You have used " + s.getCost() + " objectcoins.");
                                farmer.updateObjectCoins(farmer.getCoins() - s.getCost());
                            }
                            else
                                System.out.println("You do not have enough objectcoins");
                        }
                    }
                    //if seed was planted successfully
                    if(result == true)
                        lot.isOccupied();
                    //if seed name does not exist in the seed arraylist
                    if(found == false)
                        System.out.println("Seed does not exist.");         
                }   
            }
            else
                System.out.println("This lot is not yet plowed");
        }
        else    
            System.out.println("This lot contains a withered plant.");
    }

    /** 
     * To water the crop on the tile
     * @param lot   the tile where to water
     * @param waterCan    The tool object to be used to water the tile
     */
    public void useWaterCan(FarmLot lot, Tool waterCan) {
        //checks if the tool exists
        if(waterCan != null) {
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {
                    if(waterCan.getName().equalsIgnoreCase("watering can")) {
                        if(lot.getPlowStatus() != false && lot.getSeed() != null) {
                            lot.increaseWater();
                            System.out.println("\nTile has been watered.");
            
                            double currXP = farmer.getXP();
                            double tempXP = farmer.getXP() + waterCan.getXP();

                            System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                            farmer.updateXP(tempXP);

                            if(tempXP > currXP){
                                farmer.getLevel();
                            }

                            //checks if crop is harvestable
                            if(lot.isHarvestable() == true){
                                System.out.println(lot.getSeed().getName() + " is ready to harvest!");
                            }
                        }
                        else
                            System.out.println("\nTile is not yet plowed or no seed has been planted");
                    }
                    else
                        System.out.println("\nYou have used a wrong tool.");
                }
                else
                    System.out.println("This lot does not have any seed.\n");
            }
            else 
                System.out.println("This lot contains a withered plant");
        }
        else
            System.out.println("Tool does not exist.");
    }
    
    /** 
     * To Fertilize the crop on the tile
     * @param lot   the tile where to put fertilizer
     * @param fertilizer   The tool object to be used to fertilize the tile
     */
    public void useFertilizer(FarmLot lot, Tool fertilizer) {
        //checks if farmer has enough objectcoins
        if(farmer.getCoins() >= fertilizer.getCost()){
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {
                    if(fertilizer.getName().equalsIgnoreCase("fertilizer")) {
                        if(lot.getPlowStatus() != false) {
                            lot.increaseFertilizer();
                            System.out.println("\nTile is now fertilized ");
                            
                            farmer.updateObjectCoins(farmer.getCoins() - fertilizer.getCost());
                            farmer.updateXP(farmer.getXP() + fertilizer.getXP());
                            System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");

                            if(lot.isHarvestable() == true)
                                System.out.println(lot.getSeed().getName() + " is ready to harvest!");      
                        }
                        else
                            System.out.println("\nTile is not yet plowed or no seed has been planted");
                    }
                    else
                        System.out.println("\nYou have used a wrong tool.");
                }
                else
                    System.out.println("This lot does not have any seed.\n");
            }
            else 
                System.out.println("This lot contains a withered plant");
        }
        else
            System.out.println("You do not have enough coins to use the fertilizer!");
    }
    
    /** 
     * To use the pickaxe to remove rocks on the tile
     * @param lot   the tile to remove a rock
     * @param pickaxe   The tool object to be used on the tile
     */
    public void usePickaxe(FarmLot lot, Tool pickaxe){
        System.out.println("There is no rock on this tile.\n");
    }
    
    /** 
     * To use the shovel used to remove seeds or crops on the tile
     * @param lot   the tile where to use shovel
     * @param shovel   The tool object to be used on the tile
     */
    public void useShovel(FarmLot lot, Tool shovel) {
        if(farmer.getCoins() >= shovel.getCost()){
            if(shovel.getName().equalsIgnoreCase("shovel")) {
                if(lot.getWitherStatus() == false) {
                    if(lot.getPlowStatus() != false) {
                        if(shovel.getName().equalsIgnoreCase("shovel")) {
                            //if there is a seed on the tile
                            if(lot.getSeed() != null) {
                                //removes the seed from the tile
                                lot.getSeed().resetSeed();
                                lot.setSeed(null);
                                System.out.println("Plant has been removed.");
                            }
                            //reverts the farmLot to its default
                            lot.isPlowed(false);
                            lot.resetFarmLot();
                    
                            System.out.println("Tile has reverted back to being unplowed");       
                        }
                    }
                    //if shovel is used on unplowed tile
                    else
                        System.out.println("Nothing happened but... \n");
                }
                //if tile contains a withered plant
                else {
                    //removes the seed and resets the farmlot to its default
                    lot.getSeed().resetSeed();
                    lot.setSeed(null);
                    lot.resetFarmLot();
                    System.out.println("Withered plant has been removed.");
                    System.out.println("Tile is now unplowed.");
                }
            }
        
            double currXP = farmer.getXP();
            double tempXP = farmer.getXP() + shovel.getXP();

            farmer.updateObjectCoins(farmer.getCoins() - shovel.getCost());
            farmer.updateXP(tempXP);
            System.out.println("You have used " + shovel.getCost() + " objectcoins.");
            System.out.println("You gained " + shovel.getXP() + " experience.");

            if(tempXP > currXP){
                farmer.getLevel();
            }
        }
        else
            System.out.println("You do not have enough coins to use the shovel!");
    }
    
    /** 
     * To harvest the crop on the tile
     * @param lot   the tile to harvest a plant
     */
    public void harvestTile(FarmLot lot) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    System.out.println("\n[Harvest Details]");
                    System.out.println("The seed produced " + lot.getSeed().getProductProduced() + " " +
                    lot.getSeed().getName());
                    
                    //computes for the total objectcoins to be gained after harvesting
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterCount()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerCount();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;

                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + lot.getSeed().getExperienceYield();
                    
                    //if crop is a flower
                    if(lot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;

                    System.out.println("You have earned " + (float) finalHarvestTotal + " objectCoins");
                    System.out.println("You have earned " + lot.getSeed().getExperienceYield() + " experience");
                    farmer.updateObjectCoins(farmer.getCoins() + finalHarvestTotal);
                    farmer.updateXP(tempXP);

                    if(tempXP > currXP){
                        farmer.getLevel();
                    }
                    //after harvesting, remove the seed and reset the farmlot
                    lot.getSeed().resetSeed();
                    lot.resetFarmLot();
                }
                else
                    System.out.println("The crop is not yet harvestable\n");
            }
            else 
                System.out.println("There is no seed in this tile.\n");
        }
        else 
            System.out.println("This lot contains a withered plant");
    }

    /**
     *  Displays the information of atile 
     * @param lot   the tile to display information
     */
    public void displayTileInfo(FarmLot lot)  {
        System.out.println("\n[Tile Information]");
        System.out.print("Plow Status:");
        if(lot.getPlowStatus() == true) {
            System.out.println(" Plowed");
            if(lot.getOccupied() == false)
                System.out.println("No seed is planted here.");
        
            else {
                System.out.println("Seed planted: " + lot.getSeed().getName());
                System.out.println("Water count: " + lot.getWaterCount());
                System.out.println("Fertilizer count: " + lot.getFertilizerCount());
                System.out.println("Days growed: " + lot.getSeed().getDayGrowth());
                System.out.print("Harvestable:" );
                if(lot.isHarvestable() == true)
                    System.out.println(" Yes");
                else 
                    System.out.println(" No");
            }
        }
        else 
            System.out.println(" Unplowed");     
    }


    /**
     *  Resets the data of the tile 
     */
    public void resetFarm() {
        for(FarmLot f: this.farmLot)
            f.resetFarmLot();
        removeSeed();
        removeTool();
        this.day = 1;
        this.farmer.resetFarmer();
    }
}
