import java.util.Scanner;

public class FarmDriver {

    private MyFarm tempFarm; 
    
    public FarmDriver() {
        tempFarm = new MyFarm();
    }

    /* Adds and defines the tools in the tool array list */
    public void setupTools() {
        tempFarm.addTools("Plow", 0, 0.5);
        tempFarm.addTools("Watering Can", 0, 0.5);
        tempFarm.addTools("Fertilizer", 10, 4);
        tempFarm.addTools("Pickaxe", 50, 15);
        tempFarm.addTools("Shovel", 7, 2);
    }

    /* Adds and defines the seeds in the seed array list */
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

    
    /** 
     * Returns the farm object
     * @return MyFarm tempFarm 
     */
    public MyFarm getFarm() {
        return tempFarm;
    }

    /* Clears the screen */
    public void clearScreen() {
        System.out.print("\033\143");
    }

    
    /** 
     * Main class to stitch the whole program together
     * @param args
     */
    public static void main(String[] args) {
        FarmDriver app = new FarmDriver();
        Scanner scan = new Scanner(System.in);
    
        String opt2 = "yes";

        do {     
            System.out.println("Enter \"Start\" to begin.");
            String text = scan.next();
          
            while(text.equalsIgnoreCase("start") && opt2.equalsIgnoreCase("yes")) {
                app.setupSeeds();
                app.setupTools();
        
                MyFarm farm = app.getFarm();
                app.clearScreen();
                 
                System.out.print("\nEnter your name: ");
                String name = scan.next();
                farm.addFarmer(name);
                app.clearScreen();
    
                System.out.println("Welcome to your farm, " + farm.getFarmer().getName() + "!");
                System.out.println("As starters, your stats are set to its default: ");
                farm.getFarmer().displayStats();
                System.out.println("\nDay " + farm.getDay() + " of the game.");
                
                while(farm.isRunning()) {
                    System.out.println("\nChoose an action to do: ");
                    System.out.println("1: Use a tool");
                    System.out.println("2: Plant a seed");
                    System.out.println("3: Harvest a crop");
                    System.out.println("4: Advance to next day");
                    System.out.println("5: Display stats");
                    System.out.print("\nAction: ");
                    int nOpt = scan.nextInt();
                  
                    switch(nOpt) {
                        case 1: // 1: Use a tool
                            System.out.println("\nWhat tool would you like to use?");
                            for(Tool t : farm.getAllTool())
                                System.out.println(t.getName());
                            
                            System.out.print("\nTool name: ");
                            scan.nextLine();
                            String tool = scan.nextLine();
                          
                            switch(tool.toLowerCase()) { 
                                case "plow":
                                    if(farm.getFarmLot().getPlowStatus() == false)
                                        farm.usePlow(farm.getFarmLot(), farm.getTool(tool));
                                    else
                                        System.out.println("This lot is already plowed.");
                                break;
                              
                                case "watering can":
                                    if( farm.getFarmLot().getPlowStatus() == true &&
                                        farm.getFarmLot().getSeed() != null) {
                                        farm.useWaterCan(farm.getFarmLot(), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break;
                              
                                case "fertilizer":
                                    if( farm.getFarmLot().getPlowStatus() == true &&
                                        farm.getFarmLot().getSeed() != null) {
                                        farm.useFertilizer(farm.getFarmLot(), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break; 
                              
                                case "pickaxe":
                                    farm.usePickaxe(farm.getFarmLot(), farm.getTool(tool)); 
                                break;
                                  
                                case "shovel":
                                    farm.useShovel(farm.getFarmLot(), farm.getTool(tool));
                                break;
      
                                default:
                                    System.out.println("Tool does not exist!");
                                break;
                            }
                      
                        break; 
                      
                        case 2: // 2: Plant a seed
                            if(farm.getFarmLot().getOccupied() == false) {
                                if(farm.getFarmLot().getPlowStatus() == true) {
                                    System.out.println("\nWhat seed would you like to plant?");
                                    for(Seed s : farm.getSeed())
                                        System.out.println(s.getName());
                                    
                                    System.out.print("\nSeed name: ");
                                    String seed = scan.next();
                                    farm.plantSeed(farm.getFarmLot(), seed);
                              
                                }
                            else 
                                System.out.println("This lot is not yet plowed.");
                            }
                            
                            else 
                              System.out.println("This lot is already occupied");
                        break;
                        
                        case 3: // 3: Harvest a crop
                            farm.harvestTile(farm.getFarmLot());
                        break;
      
                        case 4: // 4: Advance to next day
                            farm.advanceNextDay();
                            //app.clearScreen();
                        break;
      
                        case 5: // 5: Display stats
                            farm.getFarmer().displayStats();
                        break;
      
                        default:
                            System.out.println("Please choose an option from the list!");                      
                    }
                }
                
                if(!farm.isRunning()) {
                    System.out.println("\nYou have reached the end of the game.");
                    
                    if(farm.getFarmer().getCoins() < 5 && farm.getFarmLot().getSeed() == null) {
                        System.out.print("You only have " + farm.getFarmer().getCoins() + " objectcoins left");
                        System.out.println(" and can no longer buy any seed.");
                    }
                  
                    else if(farm.getFarmLot().getWitherStatus() == true)
                        System.out.println("All your tiles contain withered plant");
                  
                    System.out.println("\nDo you want to play again?");
                    System.out.println("Enter [Yes] or [No]");
                    opt2 = scan.next();
                    if(opt2.equalsIgnoreCase("no"))
                        break;

                    else if(opt2.equalsIgnoreCase("yes")) {
                        // reset stats
                        farm.resetFarm();
                        
                        // clear screen
                        app.clearScreen();
                    }
                }
            }
        } while(!opt2.equalsIgnoreCase("no"));      
        scan.close();    
    }
}