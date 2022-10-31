public class FarmDriver {
    private MyFarm tempFarm; 
    
    public FarmDriver() {
        tempFarm = new MyFarm();
    }

    public void setupTools() {
        tempFarm.addTools("Plow", 0, 0.5);
        tempFarm.addTools("Watering Can", 0, 0.5);
        tempFarm.addTools("Fertilizer", 10, 4);
        tempFarm.addTools("Pickaxe", 50, 15);
        tempFarm.addTools("Shovel", 7, 2);
    }

    public void setupSeeds() {
        tempFarm.addSeeds("Turnip", "Root crop", 2, 2, 1, 5,6,5);
        tempFarm.addSeeds("Carrot", "Root crop", 3, 2, 1, 10,9,7.5);
        tempFarm.addSeeds("Potato", "Root crop", 5, 4, 2, 20,3,12.5);
        tempFarm.addSeeds("Rose", "Flower", 1, 2, 1, 5,5,2.5);
        tempFarm.addSeeds("Tulips", "Flower", 2, 3, 1, 10,9,5);
        tempFarm.addSeeds("Sunflower", "Flower", 3, 3, 2, 20,19,7.5);
        tempFarm.addSeeds("Mango", "Fruit tree", 10, 7, 4, 100,8,25);
        tempFarm.addSeeds("Apple", "Fruit tree", 10, 7, 5, 200,5,25);

    }

    public MyFarm getFarm() {
        return tempFarm;
    }

    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();

        app.setupSeeds();
        app.setupTools();

        MyFarm farm = app.getFarm();

        farm.plowTile(farm.getFarmLot(), farm.getTool("plow"));
        farm.plantSeed(farm.getFarmLot(),"tulips");
        farm.waterTile(farm.getFarmLot(), farm.getTool("watering can"));
        farm.waterTile(farm.getFarmLot(), farm.getTool("watering can"));
        farm.fertilizeTile(farm.getFarmLot(), farm.getTool("fertilizer"));
        
        farm.advanceNextDay();
        farm.advanceNextDay();
        //farm.advanceNextDay();
        farm.harvestTile(farm.getFarmLot());
        farm.getFarmer().displayCoinXP();

        System.out.println("You are level " + farm.getFarmer().getLevel());

        farm.plowTile(farm.getFarmLot(), farm.getTool("plow"));
        farm.plantSeed(farm.getFarmLot(),"tulips");
        farm.waterTile(farm.getFarmLot(), farm.getTool("watering can"));
        farm.waterTile(farm.getFarmLot(), farm.getTool("watering can"));
        farm.fertilizeTile(farm.getFarmLot(), farm.getTool("fertilizer"));
        System.out.println(farm.getFarmLot().getSeed().getDayGrowth());
        System.out.println(farm.getFarmLot().getWaterCount());
        System.out.println(farm.getFarmLot().getFertilizerCount());        
        //farm.advanceNextDay();
        //farm.advanceNextDay();
        //farm.advanceNextDay();
        //farm.harvestTile(farm.getFarmLot());
        //System.out.println("You are level " + farm.getFarmer().getLevel());
        //farm.plantSeed(farm.getFarmLot(),"tulips");
        //farm.plowTile(farm.getFarmLot(), farm.getTool("plow"));
        //farm.waterTile(farm.getFarmLot(), farm.getTool("watering can"));
        //farm.fertilizeTile(farm.getFarmLot(), farm.getTool("fertilizer"));

    }
}
