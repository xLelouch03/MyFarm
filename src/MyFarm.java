import java.util.ArrayList;

public class MyFarm {
    private Farmer farmer;
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;

    public MyFarm() {
        this.farmLot = new FarmLot();
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
        this.farmer = null;
    }
  
    
    /** 
     * Add a farmer/player to the game
     * @param name   Name to be assigned to the farmer
     */
    public void addFarmer(String name) {
        this.farmer = new Farmer(name);
    }
  
    
    /** 
     * 
     * @return Farmer
     */
    public Farmer getFarmer() {
        return farmer;
    }

    
    /** 
     * 
     * @return FarmLot
     */
    public FarmLot getFarmLot(){
        return this.farmLot;
    }
    
    /** 
     * @return ArrayList<Seed>
     */
    public ArrayList<Seed> getSeed() {
        return this.seed;
    }

    
    /** 
     * @param name
     * @return Tool
     */
    public Tool getTool(String name){
        Tool temp = null;
        
        for(Tool t : tool){
            if(t.getName().equalsIgnoreCase(name))
                temp = t;
        }
        
      return temp;
    }

    public void removeSeed() {
        this.seed.removeAll(this.seed);

            
    }

    public void removeTool() {
        this.tool.removeAll(this.tool);

    }
    
    /** 
     * @return ArrayList<Tool>
     */
    public ArrayList<Tool> getAllTool() {
        return tool;
    }

    
    /** 
     * @return int
     */
    public int getDay() {
        return day;
    }

    public void advanceNextDay(){
        this.day++;
        System.out.println("\nDay " + this.day + " of the game.");
        if(this.farmLot.getSeed() != null) {
            this.farmLot.getSeed().grow();

            if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime()) {
                if(this.farmLot.isHarvestable() == false) {
                    System.out.println("Because you did not harvest the " + this.farmLot.getSeed().getName() + " ,");
                    this.farmLot.isWithered(true);
                }
            }
            else if(this.farmLot.getSeed().getDayGrowth() == this.farmLot.getSeed().getHarvestTime()) {
                if(this.farmLot.isHarvestable() == false) {
                    System.out.println(this.farmLot.getSeed().getName() + " growed but did not get taken care of properly.");
                    this.farmLot.isWithered(true);
                }
                else 
                    System.out.println("\n" + (this.farmLot.getSeed().getName() + " is harvestable"));
            }
            //else if (this.farmLot.isHarvestable() == true)
                //System.out.println("\n" + (this.farmLot.getSeed().getName() + " is harvestable"));
            else
                System.out.println("\n" + this.farmLot.getSeed().getName() + " growed.");
        }
    }

    public boolean isRunning() {
        if((farmer.getCoins() < 5 && this.farmLot.getSeed() == null) || this.farmLot.getWitherStatus() == true)
            return false;
        
        return true;
    }
    
    /** 
     * Defines and adds a seed to the seed array list
     * @param name   Name of the seed
     * @param type   Type of seed
     * @param harvestTime   Time needed for the seed to be ready for harvest
     * @param waterNeed   Amount of water needed for the seed to be ready for harvest
     * @param fertilizerNeed   Amount of fertilizer needed for the seed to be ready for harvest
     * @param cost   Cost for each seed
     * @param basePrice   Base price for each crop produced
     * @param xp   Experience yielded for each crop harvested
     */
    public void addSeeds(String name, String type, int harvestTime, 
                         int waterNeed, int fertilizerNeed, int cost,
                         int basePrice, double xp) {
        this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost, basePrice, xp));
    }

    
    /** 
     * Defines and adds a tool to the tool array list
     * @param name   Name of the tool
     * @param cost   Cost of the tool
     * @param xp     Experience yielded for each use of the tool
     */
    public void addTools(String name, int cost, double xp) {
        this.tool.add(new Tool(name, cost, xp));
    }

    
    /** 
     * To plow the tile
     * @param lot
     * @param plow
     */
    public void usePlow(FarmLot lot, Tool plow) {
        if(plow != null) {
            if(lot.getPlowStatus() == false) {
                if(lot.getWitherStatus() == false) {
                    if(plow.getName().equalsIgnoreCase("plow")) {
                        lot.isPlowed(true);
                        System.out.println("\nTile is now plowed.");
                    
                        double currXP = farmer.getXP();
                        double tempXP = farmer.getXP() + plow.getXP();

                        farmer.updateXP(tempXP);
                        System.out.println("You have gained " + plow.getXP() + " experience.");

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
     * @param lot
     * @param seedName
     */
    public void plantSeed(FarmLot lot, String seedName) {
        boolean result = false;
        boolean found = false;
        if(lot.getWitherStatus() == false) {
            if(lot.getPlowStatus() == true) {
                if(lot.getOccupied() == true) 
                    System.out.println("\nThis lot is already occupied.");
                else {
                    for(Seed s: getSeed()){
                        if(s.getName().equalsIgnoreCase(seedName)) {
                            found = true;
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
          
            if(result == true)
                lot.isOccupied();
            
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
     * @param lot
     * @param waterCan
     */
    public void useWaterCan(FarmLot lot, Tool waterCan) {
      if(waterCan != null) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(waterCan.getName().equalsIgnoreCase("watering can")) {
                    if(lot.getPlowStatus() != false && this.seed != null) {
                        lot.increaseWater();
                        System.out.println("\nTile has been watered.");
        
                        double currXP = farmer.getXP();
                        double tempXP = farmer.getXP() + waterCan.getXP();

                        System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                        farmer.updateXP(tempXP);

                        if(tempXP > currXP){
                            farmer.getLevel();
                        }

                        if(lot.isHarvestable() == true){
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
     * @param lot
     * @param fertilizer
     */
    public void useFertilizer(FarmLot lot, Tool fertilizer) {
        if(farmer.getCoins() >= fertilizer.getCost()){
            if(lot.getWitherStatus() == false) {
                if(lot.getSeed() != null) {
                    if(fertilizer.getName().equalsIgnoreCase("fertilizer")) {
                        if(lot.getPlowStatus() != false && this.seed != null) {
                            lot.increaseFertilizer();
                            System.out.println("\nTile is now fertilized ");
                            
                            farmer.updateObjectCoins(farmer.getCoins() - fertilizer.getCost());
                            farmer.updateXP(farmer.getXP() + fertilizer.getXP());
                            System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");
                            if(lot.isHarvestable() == true)
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
     * To use the pickaxe used to remove rocks on the tile
     * @param lot
     * @param pickaxe
     */
    public void usePickaxe(FarmLot lot, Tool pickaxe){
        System.out.println("There is no rock on this tile.\n");
    }
  
    
    /** 
     * To use the shovel used to remove seeds or crops on the tile
     * @param lot
     * @param shovel
     */
    public void useShovel(FarmLot lot, Tool shovel) {
        if(farmer.getCoins() >= shovel.getCost()){
            if(shovel.getName().equalsIgnoreCase("shovel")) {
                if(lot.getWitherStatus() == false) {
                    if(lot.getPlowStatus() != false) {
                        if(shovel.getName().equalsIgnoreCase("shovel")) {
                            if(lot.getSeed() != null) {
                                lot.getSeed().resetSeed();
                                lot.setSeed(null);
                                System.out.println("Plant has been removed.");
                            }
                            
                            lot.isPlowed(false);
                            lot.resetFarmLot();
                    
                            System.out.println("Tile has reverted back to being unplowed");       
                        }
                    }
                    else
                        System.out.println("Nothing happened but... \n");
                }
                else {
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
     * @param lot
     */
    public void harvestTile(FarmLot lot) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    System.out.println("The seed produced " + lot.getSeed().getProductProduced() + " " +
                    lot.getSeed().getName());
    
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterCount()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerCount();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;

                    double currXP = farmer.getXP();
                    double tempXP = farmer.getXP() + lot.getSeed().getExperienceYield();
                    
                    if(lot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;
                    System.out.println("You have earned " + finalHarvestTotal + " objectCoins");
                    System.out.println("You have earned " + lot.getSeed().getExperienceYield() + " experience");
                    farmer.updateObjectCoins(farmer.getCoins() + finalHarvestTotal);
                    farmer.updateXP(tempXP);

                    if(tempXP > currXP){
                        farmer.getLevel();
                    }
                    
                    lot.getSeed().resetSeed();
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

    public void resetFarm() {
        this.farmLot.resetFarmLot();
        removeSeed();
        removeTool();
        this.day = 1;
        this.farmer.resetFarmer();
    }
}
