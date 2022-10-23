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

    public FarmLot getFarm(){
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
    
    public void buySeed(Seed seed){

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
    }

    public void addTools(String name, int cost, double xp) {
        this.tools.add(new Tool(name, cost, xp));
    }
}
