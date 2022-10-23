public class FarmLot {
    private boolean plowed;
    private int waterCount ;
    private int fertilizeCount;
    private boolean wither;
    private Seed seed;
    private boolean harvestable;

    public FarmLot(){
        this.plowed = false;
        this.waterCount = 0;
        this.fertilizeCount = 0;
        this.wither = false;
        this.seed = null;
    }

    public void plantSeed(MyFarm farm, String seedName) {
        for(Seed s: farm.getSeed()){
            if(s.getName().equalsIgnoreCase(seedName)) {
                this.seed = s;
                System.out.println(this.seed.getName() + " has been planted.");
                int tempMoney = farm.getCoins() - s.getCost();
                farm.updateObjectCoins(tempMoney);
                System.out.println("You now have " + farm.getCoins() + " objectcoins");
            }
        }
    }

    public void plowTile(MyFarm farm,  Tool plow) {
        this.plowed = true;
        System.out.println("\nTile is now plowed.");
        
        double tempXP = farm.getXP() + plow.getXP();
        farm.updateXP(tempXP);
        System.out.println("You now have " + farm.getXP() + " experience.");
    }

    public void waterTile(MyFarm farm, Tool waterCan) {
        if(this.plowed != false && this.seed != null) {
            waterCount++;
            System.out.println("\nTile is now watered " + waterCount + " times.");
            double tempXP = farm.getXP() + waterCan.getXP();

            farm.updateXP(tempXP);
            System.out.println("You now have " + farm.getXP() + " experience.");
        }

        else
            System.out.println("\nTile is not yet plowed or no seed has been planted");
    }

    public void fertilizeTile(MyFarm farm, Tool fertilizer) {
        if(this.plowed != false && this.seed != null) {
            fertilizeCount++;
            System.out.println("\nTile is now fertilized " + fertilizeCount + " times.");
            int tempMoney = farm.getCoins() - fertilizer.getCost();
            double tempXP = farm.getXP() + fertilizer.getXP();
            farm.updateObjectCoins(tempMoney);
            System.out.println("You now have " + farm.getCoins() + " objectcoins");
            farm.updateXP(tempXP);
            System.out.println("You now have " + farm.getXP() + " experience.");
        }
        else
            System.out.println("\nTile is not yet plowed or no seed has been planted");
    }
    
}