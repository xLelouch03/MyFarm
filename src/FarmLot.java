public class FarmLot {
    private boolean plowed;
    private int waterCount ;
    private int fertilizeCount;
    private boolean wither;


    public FarmLot(){
        this.plowed = false;
        this.waterCount = 0;
        this.fertilizeCount = 0;
        this.wither = false;
    }

    public void plowTile(MyFarm farm,  Tool plow) {
        int tempMoney = farm.getCoins() - plow.getCost();
        double tempXP = farm.getXP() + plow.getXP();

        farm.updateObjectCoins(tempMoney);
        farm.updateXP(tempXP);

        this.plowed = true;

        System.out.println("Tile is now plowed.");
    }

    public void waterTile(Tool waterCan) {
        waterCount++;
    }

    public void fertilizeTile(Tool fertilizer) {
        fertilizeCount++;
    }
    
}