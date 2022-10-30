import java.util.ArrayList;

public class MyFarm {
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tool;
    private int objectCoins;
    private int level;
    private double experience;
    private int day;

    public MyFarm() {
        this.farmLot = new FarmLot();
        this.seed = new ArrayList<Seed>();
        this.tool = new ArrayList<Tool>();
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
        this.day = 1;
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

    public int getCoins() {
        return objectCoins;
    }

    public void updateObjectCoins(int coin){
        this.objectCoins = coin;
    }

    public int getLevel() {
        return level;
    } 

    public double getXP() {
        return experience;
    }

    public void updateXP(double xp) {
        this.experience = xp;
    }

    public int getDay() {
        return day;
    }

    public void advanceNextDay(){
        this.day++;
        System.out.println("\nDay " + this.day + " of the game");

        if(this.farmLot.getSeed() != null) {
            this.farmLot.getSeed().grow();

            if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime())
                this.farmLot.isWithered();
            else
                System.out.println(this.farmLot.getSeed().getName() + " growed.");
        }
    }

    public void addSeeds(String name, String type, int harvestTime, 
                         int waterNeed, int fertilizerNeed, int cost) {
        this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost));
    }

    public void addTools(String name, int cost, double xp) {
        this.tool.add(new Tool(name, cost, xp));
    }

    public void plowTile(Tool plow) {
        if(plow.getName().equalsIgnoreCase("plow")) {
            this.farmLot.isPlowed(true);
            System.out.println("\nTile is now plowed.");
            
            double tempXP = getXP() + plow.getXP();
            updateXP(tempXP);
            System.out.println("You have gained " + plow.getXP() + " experience.\n");
            displayCoinXP();
        }
        else
            System.out.println("\nYou have used a wrong tool.");

    }

    public void plantSeed(String seedName) {
        if(this.farmLot.getPlowStatus() == true) {
            if(this.farmLot.getWitherStatus() == false) {
                if(this.farmLot.getOccupied() == true) 
                    System.out.println("\nThis lot is already occupied.");
    
                else {
                    for(Seed s: getSeed()){
                        if(s.getName().equalsIgnoreCase(seedName)) {
                            this.farmLot.setSeed(s);
                            System.out.println(s.getName() + " has been planted.");
                            
                            int tempMoney = getCoins() - s.getCost();
                            updateObjectCoins(tempMoney);
                            displayCoinXP();
                        }
                    }
                        this.farmLot.isOccupied();
                }
            }
            else    
                System.out.println("This lot contains withered plant.");
        }
        else
            System.out.println("This lot is not yet plowed");
    }

    public void waterTile(Tool waterCan) {
        if(this.farmLot.getSeed() != null) {
            if(waterCan.getName().equalsIgnoreCase("watering can")) {
                if(this.farmLot.getPlowStatus() != false && this.seed != null) {
                    this.farmLot.increaseWater();
                    System.out.println("\nTile is now watered " + this.farmLot.getWaterCount() + " times.");
    
                    double tempXP = getXP() + waterCan.getXP();
                    System.out.println("You have gained " + waterCan.getXP() + " experience.\n");
                    updateXP(tempXP);
                    displayCoinXP();
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

    public void fertilizeTile(Tool fertilizer) {
        if(this.farmLot.getSeed() != null) {
            if(fertilizer.getName().equalsIgnoreCase("fertilizer")) {
                if(this.farmLot.getPlowStatus() != false && this.seed != null) {
                    this.farmLot.increaseFertilizer();
                    System.out.println("\nTile is now fertilized " + this.farmLot.getFertilizerCount() + " times.");
                    
                    int tempMoney = getCoins() - fertilizer.getCost();
                    double tempXP = getXP() + fertilizer.getXP();

                    updateObjectCoins(tempMoney);
                    updateXP(tempXP);
                    System.out.println("You have gained " + fertilizer.getXP() + " experience.\n");
                    displayCoinXP();
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

    public void displayCoinXP() {
        System.out.println("\nYou have " + getCoins() + " objectcoins");
        System.out.println("You have " + getXP() + " experience.\n");
    }
}
