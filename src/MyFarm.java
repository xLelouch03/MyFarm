/**
 * This class contains the constructor and different methods that will
 * be used in managing the farm.
 */

import java.util.ArrayList;

public class MyFarm {
    private Farmer farmer;
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;

    /**
     * Constructs a farm by creating a farmlot, arraylist of seeds and tools,
     * setting the day to 1 and a farmer.
     */
    public MyFarm() {
        this.farmLot = new FarmLot();
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
     * Gets a farm lot object / a tile on the farm
     * @return the farm lot object / a tile on the farm
     */
    public FarmLot getFarmLot(){
        return this.farmLot;
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

        if(this.farmLot.getSeed() != null) {
            this.farmLot.getSeed().grow();

            //Checks if crop reached more than the number of days 
            if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime()) {
                System.out.println("Because you did not harvest the " + this.farmLot.getSeed().getName() + ",");
                this.farmLot.isWithered(true);
            }
            //checks if crop reach the harvest time and meet the minimum requirements to be harvested
            else if(this.farmLot.getSeed().getDayGrowth() == this.farmLot.getSeed().getHarvestTime()) {
                //if crop did not meet the minimum requirements
                if(this.farmLot.isHarvestable() == false) {
                    System.out.println(this.farmLot.getSeed().getName() + " growed but did not get taken care of properly.");
                    this.farmLot.isWithered(true);
                }
                //crop meets the minimum requirements
                else 
                    System.out.println("\n" + (this.farmLot.getSeed().getName() + " is harvestable"));
            }
            //crop grows
            else
                System.out.println("\n" + this.farmLot.getSeed().getName() + " growed.");
        }
    }

    /*
     * Checks if the game is still running
     * @return the game status
     */
    public boolean isRunning() {
        //checks if farmer can no longer buy a seed with no active crop OR the farm tiles contain withered plant
        if((farmer.getCoins() < 5 && this.farmLot.getSeed() == null) || this.farmLot.getWitherStatus() == true)
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
     * 
     * @param plow   The tool object used to plow the tile
     */
    public void usePlow(Tool plow) {
        //checks if tool object exists
        if(plow != null) {
            //checks if tile is not yet plowed
            if(this.farmLot.getPlowStatus() == false) {
                //checks if tile contains a withered plant
                if(this.farmLot.getWitherStatus() == false) {
                    //checks if the tool object passed contains a name "plow"
                    if(plow.getName().equalsIgnoreCase("plow")) {
                        this.farmLot.isPlowed(true);
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
     * 
     * @param seedName   The name of the seed to be planted
     */
    public void plantSeed(String seedName) {
        boolean result = false;
        boolean found = false;

        //checks if tile contains a withered plant
        if(this.farmLot.getWitherStatus() == false) {
            //checks if the tile is plowed
            if(this.farmLot.getPlowStatus() == true) {
                //checks if there is a seed planted
                if(this.farmLot.getOccupied() == true) 
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
                                this.farmLot.setSeed(s);
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
                    this.farmLot.isOccupied();
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
     * 
     * @param waterCan    The tool object to be used to water the tile
     */
    public void useWaterCan(Tool waterCan) {
        //checks if the tool exists
        if(waterCan != null) {
            if(this.farmLot.getWitherStatus() == false) {
                if(this.farmLot.getSeed() != null) {
                    if(waterCan.getName().equalsIgnoreCase("watering can")) {
                        if(this.farmLot.getPlowStatus() != false && this.seed != null) {
                            this.farmLot.increaseWater();
                            System.out.println("\nTile has been watered.");
            
                            double currXP = farmer.getXP();
                            double tempXP = farmer.getXP() + waterCan.getXP();

                            System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                            farmer.updateXP(tempXP);

                            if(tempXP > currXP){
                                farmer.getLevel();
                            }

                            //checks if crop is harvestable
                            if(this.farmLot.isHarvestable() == true){
                                System.out.println(this.farmLot.getSeed().getName() + " is ready to harvest!");
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
     * 
     * @param fertilizer   The tool object to be used to fertilize the tile
     */
    public void useFertilizer(Tool fertilizer) {
        //checks if farmer has enough objectcoins
        if(farmer.getCoins() >= fertilizer.getCost()){
            if(this.farmLot.getWitherStatus() == false) {
                if(this.farmLot.getSeed() != null) {
                    if(fertilizer.getName().equalsIgnoreCase("fertilizer")) {
                        if(this.farmLot.getPlowStatus() != false && this.seed != null) {
                            this.farmLot.increaseFertilizer();
                            System.out.println("\nTile is now fertilized ");
                            
                            farmer.updateObjectCoins(farmer.getCoins() - fertilizer.getCost());
                            farmer.updateXP(farmer.getXP() + fertilizer.getXP());
                            System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");

                            if(this.farmLot.isHarvestable() == true)
                                System.out.println(this.farmLot.getSeed().getName() + " is ready to harvest!");      
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
     * 
     * @param pickaxe   The tool object to be used on the tile
     */
    public void usePickaxe(Tool pickaxe){
        System.out.println("There is no rock on this tile.\n");
    }
    
    /** 
     * To use the shovel used to remove seeds or crops on the tile
     * 
     * @param shovel   The tool object to be used on the tile
     */
    public void useShovel(Tool shovel) {
        if(farmer.getCoins() >= shovel.getCost()){
            if(shovel.getName().equalsIgnoreCase("shovel")) {
                if(this.farmLot.getWitherStatus() == false) {
                    if(this.farmLot.getPlowStatus() != false) {
                        if(shovel.getName().equalsIgnoreCase("shovel")) {
                            //if there is a seed on the tile
                            if(this.farmLot.getSeed() != null) {
                                //removes the seed from the tile
                                this.farmLot.getSeed().resetSeed();
                                this.farmLot.setSeed(null);
                                System.out.println("Plant has been removed.");
                            }
                            //reverts the farmLot to its default
                            this.farmLot.isPlowed(false);
                            this.farmLot.resetFarmLot();
                    
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
                    this.farmLot.getSeed().resetSeed();
                    this.farmLot.setSeed(null);
                    this.farmLot.resetFarmLot();
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
     */
    public void harvestTile() {
        if(this.farmLot.getWitherStatus() == false) {
            if(this.farmLot.getSeed() != null) {
                if(this.farmLot.isHarvestable() == true) {
                    System.out.println("\n[Harvest Details]");
                    System.out.println("The seed produced " + this.farmLot.getSeed().getProductProduced() + " " +
                    this.farmLot.getSeed().getName());
                    
                    //computes for the total objectcoins to be gained after harvesting
                    double waterBonus = this.farmLot.getSeed().getHarvestTotal() * 0.2 * (this.farmLot.getWaterCount()-1);
                    double fertilizerBonus = this.farmLot.getSeed().getHarvestTotal() * 0.5 * this.farmLot.getFertilizerCount();
                    double finalHarvestTotal = this.farmLot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;

                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + this.farmLot.getSeed().getExperienceYield();
                    
                    //if crop is a flower
                    if(this.farmLot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;

                    System.out.println("You have earned " + (float) finalHarvestTotal + " objectCoins");
                    System.out.println("You have earned " + this.farmLot.getSeed().getExperienceYield() + " experience");
                    farmer.updateObjectCoins(farmer.getCoins() + finalHarvestTotal);
                    farmer.updateXP(tempXP);

                    if(tempXP > currXP){
                        farmer.getLevel();
                    }
                    //after harvesting, remove the seed and reset the farmlot
                    this.farmLot.getSeed().resetSeed();
                    this.farmLot.resetFarmLot();
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
     *  Resets the data of the tile 
     */
    public void resetFarm() {
        this.farmLot.resetFarmLot();
        removeSeed();
        removeTool();
        this.day = 1;
        this.farmer.resetFarmer();
    }
}
