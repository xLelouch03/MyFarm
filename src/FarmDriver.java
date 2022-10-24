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
        farm.addSeeds("Turnip", "Root crop", 2, 1, 0, 5);
        farm.addSeeds("Carrot", "Root crop", 3, 1, 0, 10);
        farm.addSeeds("Potato", "Root crop", 5, 3, 1, 20);
        farm.addSeeds("Rose", "Flower", 1, 1, 0, 5);
        farm.addSeeds("Turnips", "Flower", 2, 2, 0, 10);
        farm.addSeeds("Sunflower", "Flower", 3, 2, 1, 20);
        farm.addSeeds("Mango", "Fruit tree", 10, 7, 4, 100);
        farm.addSeeds("Apple", "Fruit tree", 10, 7, 5, 200);

    }

    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();
        
        app.setupTools();
        app.setupSeeds();

        System.out.println("You currently have " + app.farm.getCoins() + " objectcoins.");
        System.out.println("You now have " + app.farm.getXP() + " experience.");

        app.farm.getFarmLot().waterTile(app.farm, app.farm.getTool("watering can"));
        app.farm.getFarmLot().plowTile(app.farm, app.farm.getTool("plow"));
        
        app.farm.getFarmLot().waterTile(app.farm, app.farm.getTool("watering can"));

        app.farm.getFarmLot().waterTile(app.farm, app.farm.getTool("watering can"));

        app.farm.getFarmLot().fertilizeTile(app.farm, app.farm.getTool("fertilizer"));
    
        app.farm.getFarmLot().plantSeed(app.farm, "turnip");

        app.farm.getFarmLot().waterTile(app.farm, app.farm.getTool("watering can"));


        app.farm.getFarmLot().waterTile(app.farm, app.farm.getTool("watering can"));

        app.farm.getFarmLot().fertilizeTile(app.farm, app.farm.getTool("fertilizer"));
        
        app.farm.getFarmLot().plantSeed(app.farm, "carrot");
        System.out.println("You currently have " + app.farm.getCoins() + " objectcoins.");
        System.out.println("You now have " + app.farm.getXP() + " experience.");

        app.farm.advanceNextDay();
        app.farm.getFarmLot().plantSeed(app.farm, "turnip");
        //app.farm.advanceNextDay();
        //app.farm.advanceNextDay();
        //app.farm.getFarmLot().plantSeed(app.farm, "turnip");
    }
    
}
