public class FarmDriver {
    
    private MyFarm farm;

    public FarmDriver() {
        farm = new MyFarm();
    }

    public void setupTools() {
        farm.addTools("Plow", 0, 0.5);
        farm.addTools("Watering Can", 0, 0.5);
        farm.addTools("Fertilizer", 10, 4);
        farm.addTools("Pickaxe", 50, 15);
        farm.addTools("Shovel", 7, 2);
    }

    public void setupSeeds() {
        farm.addSeeds("Turnip", "Root crop", 2, 2, 1, 5,6,5);
        farm.addSeeds("Carrot", "Root crop", 3, 2, 1, 10,9,7.5);
        farm.addSeeds("Potato", "Root crop", 5, 4, 2, 20,3,12.5);
        farm.addSeeds("Rose", "Flower", 1, 2, 1, 5,5,2.5);
        farm.addSeeds("Tulips", "Flower", 2, 3, 1, 10,9,5);
        farm.addSeeds("Sunflower", "Flower", 3, 3, 2, 20,19,7.5);
        farm.addSeeds("Mango", "Fruit tree", 10, 7, 4, 100,8,25);
        farm.addSeeds("Apple", "Fruit tree", 10, 7, 5, 200,5,25);

    }

    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();

        app.setupTools();
        app.setupSeeds();

        app.farm.displayCoinXP();

        //app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.plowTile(app.farm.getTool("plow"));
        
        //app.farm.waterTile(app.farm.getTool("watering can"));

        //app.farm.waterTile(app.farm.getTool("watering can"));

        //app.farm.fertilizeTile(app.farm.getTool("fertilizer"));
    
        app.farm.plantSeed("turnip");

        //app.farm.waterTile(app.farm.getTool("watering can"));


        //app.farm.waterTile(app.farm.getTool("watering can"));

        //app.farm.fertilizeTile(app.farm.getTool("fertilizer"));
        
        //app.farm.plantSeed("carrot");

        //app.farm.displayCoinXP();

        app.farm.advanceNextDay();
        app.farm.advanceNextDay();
        app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.fertilizeTile(app.farm.getTool("fertilizer"));
        app.farm.advanceNextDay();
        app.farm.harvestTile();
        //app.farm.advanceNextDay();
        //app.farm.harvestTile();
        //app.farm.plantSeed("carrot");
        /*app.farm.plowTile(app.farm.getTool("plow"));
        app.farm.plantSeed("carrot");
        app.farm.waterTile(app.farm.getTool("watering can"));
        app.farm.fertilizeTile(app.farm.getTool("fertilizer"));
        app.farm.advanceNextDay();
        app.farm.advanceNextDay();
        app.farm.advanceNextDay();
        app.farm.harvestTile(); */
        //app.farm.plantSeed("turnip");
    }
}
