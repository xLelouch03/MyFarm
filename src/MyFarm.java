import java.util.ArrayList;

public class MyFarm {
    private Farmer farmer;
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;
    private int availableSpace;

    public MyFarm() {
        this.farmLot = new FarmLot();
        this.availableSpace = 1;
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
        this.farmer = null;
    }
  
    public void addFarmer(String name) {
        this.farmer = new Farmer(name);
    }
  
    public Farmer getFarmer() {
        return farmer;
    }

    public FarmLot getFarmLot(){
        return this.farmLot;
    }

    public int getSpace() {
      return availableSpace;
    }

    public void updateSpace(int num) {
      availableSpace = num;
    }
  
    public ArrayList<Seed> getSeed() {
        return this.seed;
    }

    public Tool getTool(String name){
        Tool temp = null;
        
        for(Tool t : tool){
            if(t.getName().equalsIgnoreCase(name))
                temp = t;
        }
        
      return temp;
    }

    public ArrayList<Tool> getAllTool() {
        return tool;
    }

    public int getDay() {
        return day;
    }

    public void advanceNextDay(){
        this.day++;
        
        if(this.farmLot.getSeed() != null) {
            this.farmLot.getSeed().grow();

            if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime()) {
                if(this.farmLot.isHarvestable() == false)
                    this.farmLot.isWithered();
            }
            else if (this.farmLot.isHarvestable() == true)
                System.out.println(this.farmLot.getSeed().getName() + " is now harvestable");
            else
                System.out.println(this.farmLot.getSeed().getName() + " growed.");
        }
    }

    public void addSeeds(String name, String type, int harvestTime, 
                         int waterNeed, int fertilizerNeed, int cost,
                         int basePrice, double xp) {
        this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost, basePrice, xp));
    }

    public void addTools(String name, int cost, double xp) {
        this.tool.add(new Tool(name, cost, xp));
    }

    public void usePlow(FarmLot lot, Tool plow) {
      if(plow != null) {
        if(lot.getPlowStatus() == false) {
            if(lot.getWitherStatus() == false) {
                if(plow.getName().equalsIgnoreCase("plow")) {
                    lot.isPlowed(true);
                    System.out.println("\nTile is now plowed.");
                    
                    double tempXP = farmer.getXP() + plow.getXP();
                    farmer.updateXP(tempXP);
                    System.out.println("You have gained " + plow.getXP() + " experience.\n");
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
                                   
                  farmer.updateObjectCoins(farmer.getCoins() - s.getCost());
                }
                else
                  System.out.println("You do not have enough objectcoins");
              }
            }
          
            if(result == true){
              lot.isOccupied();
              updateSpace(availableSpace - 1);
            }
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

    public void useWaterCan(FarmLot lot, Tool waterCan) {
      if(waterCan != null) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(waterCan.getName().equalsIgnoreCase("watering can")) {
                    if(lot.getPlowStatus() != false && this.seed != null) {
                        lot.increaseWater();
                        System.out.println("\nTile has been watered.");
        
                        System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                        farmer.updateXP(farmer.getXP() + waterCan.getXP());
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
        System.out.println("You do not have enough coins to use the fertilizer!");
      }
  
    public void usePickaxe(FarmLot lot, Tool pickaxe){
      System.out.println("There is no rock on this tile.\n");
    }
  
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
                    updateSpace(availableSpace + 1);
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
                updateSpace(availableSpace + 1);
                System.out.println("Withered plant has been removed.");
                System.out.println("Tile is now unplowed.");
            }
        }
        farmer.updateObjectCoins(farmer.getCoins() - shovel.getCost());
        farmer.updateXP(farmer.getXP() + shovel.getXP());
        System.out.println("You have used " + shovel.getCost() + " objectcoins.");
        System.out.println("You gained " + shovel.getXP() + " experience.");
    }
    else
        System.out.println("You do not have enough coins to use the shovel!");
    }

    public void harvestTile(FarmLot lot) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    System.out.println("The seed produced " + lot.getSeed().getProductProduced() + " " +
                    lot.getSeed().getName());
    
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterCount()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerCount();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;
                    
                    if(lot.getSeed().getType().equals("Flower"))
                        finalHarvestTotal *= 1.1;
                    System.out.println("You have earned " + finalHarvestTotal + " objectCoins");
                    System.out.println("You have earned " + lot.getSeed().getExperienceYield() + " experience");
                    farmer.updateObjectCoins(farmer.getCoins() + finalHarvestTotal);
                    farmer.updateXP(farmer.getXP() + lot.getSeed().getExperienceYield());
                    
                    lot.getSeed().resetSeed();
                    this.farmLot.resetFarmLot();
                    updateSpace(availableSpace + 1);
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
}
