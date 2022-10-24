import java.util.*;

public class MyFarm {
    private FarmLot farmLot;
    private ArrayList<Seed> seed;
    private ArrayList<Tool> tools;
    private int objectCoins;
    private int level;
    private double experience;
    private int day;

    public MyFarm() {
        this.farmLot = new FarmLot();
        this.seed = new ArrayList<Seed>();
        this.tools = new ArrayList<Tool>();
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
        this.day = 1;
    }

    public FarmLot getFarmLot(){
        return farmLot;
    }

    public Tool getTool(String name){
        Tool temp = null;

        for(Tool t : tools){
            if(t.getName().equalsIgnoreCase(name))
                temp = t;
        }
        return temp;
    }

    public ArrayList<Seed> getSeed() {
        return this.seed;
    }

    public int getCoins(){
        return objectCoins;
    }

    public double getXP(){
        return experience;
    }

    public void updateObjectCoins(int coin){
        this.objectCoins = coin;
    }
    
    public void updateXP(double xp) {
        this.experience = xp;
    }

    public void advanceNextDay(){
        this.day++;
        System.out.println("\nDay " + this.day + " of the game");
        this.farmLot.getSeed().grow();

        if(this.farmLot.getSeed().getDayGrowth() > this.farmLot.getSeed().getHarvestTime())
            this.farmLot.witherLot();
        else
            System.out.println(this.farmLot.getSeed().getName() + " growed.");
    }

    public void addSeeds(String name, String type, int harvestTime, 
                         int waterNeed, int fertilizerNeed, int cost) {
        this.seed.add(new Seed(name, type, harvestTime, waterNeed, fertilizerNeed, cost));
    }

    public void addTools(String name, int cost, double xp) {
        this.tools.add(new Tool(name, cost, xp));
    }
}
