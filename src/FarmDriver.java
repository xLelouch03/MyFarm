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

    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();
        
        app.setupTools();;

        System.out.println(app.farm.getCoins());
        System.out.println(app.farm.getXP());

        app.farm.getFarm().plowTile(app.farm, app.farm.getTool("plow"));
        
        System.out.println(app.farm.getCoins());
        System.out.println(app.farm.getXP());
        
    }
    
}
