import java.util.ArrayList;

public class MyFarm {
    private Farmer farmer;
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int day;

    public MyFarm() {
        this.farmer = new Farmer();
        this.farmLot = new FarmLot();
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.day = 1;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public FarmLot getFarmLot(){
        return this.farmLot;
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

    public int getDay() {
        return day;
    }

    public void advanceNextDay(){
        this.day++;
        System.out.println("\nDay " + this.day + " of the game");

        if(this.farmLot.getSeed() != null) {
            this.farmLot.getSeed().grow();

            if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime()) {
                if(this.farmLot.isHarvestable() == false)
                    this.farmLot.isWithered();
            }
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

    public void plowTile(FarmLot lot, Tool plow) {
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

    public void plantSeed(FarmLot lot, String seedName) {
        if(lot.getWitherStatus() == false) {
            if(lot.getPlowStatus() == true) {
                if(lot.getOccupied() == true) 
                    System.out.println("\nThis lot is already occupied.");
        
                else {
                    for(Seed s: getSeed()){
                        if(s.getName().equalsIgnoreCase(seedName)) {
                            lot.setSeed(s);
                            System.out.println(s.getName() + " has been planted.");
                                
                            int tempMoney = farmer.getCoins() - s.getCost();
                            farmer.updateObjectCoins(tempMoney);
                        }
                    }
                            lot.isOccupied();
                }
            }
            else
                System.out.println("This lot is not yet plowed");
        }
        else    
            System.out.println("This lot contains a withered plant.");
    }

    public void waterTile(FarmLot lot, Tool waterCan) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(waterCan.getName().equalsIgnoreCase("watering can")) {
                    if(lot.getPlowStatus() != false && this.seed != null) {
                        lot.increaseWater();
                        System.out.println("\nTile has been watered.");
        
                        double tempXP = farmer.getXP() + waterCan.getXP();
                        System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                        farmer.updateXP(tempXP);
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

    public void fertilizeTile(FarmLot lot, Tool fertilizer) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(fertilizer.getName().equalsIgnoreCase("fertilizer")) {
                    if(lot.getPlowStatus() != false && this.seed != null) {
                        lot.increaseFertilizer();
                        System.out.println("\nTile is now fertilized ");
                        
                        int tempMoney = farmer.getCoins() - fertilizer.getCost();
                        double tempXP = farmer.getXP() + fertilizer.getXP();
    
                        farmer.updateObjectCoins(tempMoney);
                        farmer.updateXP(tempXP);
                        System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");
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

    public void harvestTile(FarmLot lot) {
        if(lot.getWitherStatus() == false) {
            if(lot.getSeed() != null) {
                if(lot.isHarvestable() == true) {
                    System.out.println("The seed produced " + lot.getSeed().getProductProduced() + " " +
                    lot.getSeed().getName());
    
                    double waterBonus = lot.getSeed().getHarvestTotal() * 0.2 * (lot.getWaterCount()-1);
                    double fertilizerBonus = lot.getSeed().getHarvestTotal() * 0.5 * lot.getFertilizerCount();
                    double finalHarvestTotal = lot.getSeed().getHarvestTotal() + waterBonus + fertilizerBonus;
                    
                    System.out.println("You have earned " + finalHarvestTotal + " objectCoins");
                    System.out.println("You have earned " + lot.getSeed().getExperienceYield() + " experience");
                    double tempMoney = farmer.getCoins() + finalHarvestTotal;
                    double tempXP = 99+ farmer.getXP() + lot.getSeed().getExperienceYield();
                    farmer.updateObjectCoins((int)tempMoney);
                    farmer.updateXP(tempXP);
                    
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
}
