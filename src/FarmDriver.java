import java.util.Scanner;

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

    public void clearScreen() {
        System.out.print("\033\143");
    }

    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();

        app.setupSeeds();
        app.setupTools();

        MyFarm farm = app.getFarm();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter \"Start\" to begin.");
        String text = scan.next();
        app.clearScreen();

        if(text.equalsIgnoreCase("start")) {
            System.out.print("\nEnter your name: ");
            String name = scan.next();
            farm.addFarmer(name);
            app.clearScreen();

            System.out.println("Welcome to your farm, " + farm.getFarmer().getName() + "!");
            System.out.println("As starters, your stats are set to its default: ");
            farm.getFarmer().displayStats();

            while(farm.getFarmer().getCoins() >= 5 && farm.getFarmLot().getWitherStatus() == false) {
                System.out.println("\nDay " + farm.getDay() + " of the game.");
                System.out.println("\nChoose an action to do: ");
                System.out.println("1: Plow a tile");
                System.out.println("2: Plant a seed");
                System.out.println("3: Water a tile");
                System.out.println("4: Fertilize a tile");
                System.out.println("5: Harvest a crop");
                System.out.println("6: Advance to next day");
                System.out.println("7: Display Stats");
                System.out.print("Action: ");
                int nOpt = scan.nextInt();

                switch(nOpt) {
                    case 1:
                        System.out.println("\nWhat tool would you like to use?");
                        for(Tool t : farm.getAllTool())
                            System.out.println(t.getName());
                        System.out.print("\nTool name: ");
                        scan.nextLine();
                        String tool = scan.nextLine();
                        farm.plowTile(farm.getFarmLot(), farm.getTool(tool));
                        break;
                    
                    case 2:
                        if(farm.getFarmLot().getOccupied() == false) {
                            if(farm.getFarmLot().getPlowStatus() == true) {
                                System.out.println("\nWhat seed would you like to plant?");
                                for(Seed s : farm.getSeed())
                                    System.out.println(s.getName());
                                System.out.print("\nSeed name: ");
                                String seed = scan.next();
                                farm.plantSeed(farm.getFarmLot(), seed);
                                break;
                            }
                            else 
                                System.out.println("This lot is not yet plowed.");
                        }
                        else 
                            System.out.println("This lot is already occupied");
                        break;
                        

                    case 3:
                        if( farm.getFarmLot().getPlowStatus() == true &&
                            farm.getFarmLot().getSeed() != null) {
                            System.out.println("\nWhat tool would you like to use?");
                            for(Tool t : farm.getAllTool())
                                System.out.println(t.getName());
                            System.out.print("\nTool name: ");
                            scan.nextLine();
                            tool = scan.nextLine();
                            farm.waterTile(farm.getFarmLot(), farm.getTool(tool));
                            break;
                        }
                        else 
                            System.out.println("This lot does not have any seed");
                        break;

                    case 4: 
                        if( farm.getFarmLot().getPlowStatus() == true &&
                            farm.getFarmLot().getSeed() != null) {
                            System.out.println("\nWhat tool would you like to use?");
                            for(Tool t : farm.getAllTool())
                                System.out.println(t.getName());
                            System.out.print("\nTool name: ");
                            scan.nextLine();
                            tool = scan.nextLine();
                            farm.fertilizeTile(farm.getFarmLot(), farm.getTool(tool));
                            break;
                        }
                        else 
                            System.out.println("This lot does not have any seed");
                        break;
                    
                    case 5:
                        farm.harvestTile(farm.getFarmLot());
                        break;

                    case 6:
                        farm.advanceNextDay();
                        //app.clearScreen();
                        break;

                    case 7:
                        farm.getFarmer().displayStats();
                        break;

                }
            }
        }
    }
}
