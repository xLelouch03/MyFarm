/**
 * This program is a farm simulation game with the player as the farmer.
 * It involves the preparing a land, buying and planting of seed, advancing days
 * where a crop can either grow or wither. It also involves harvesting of the crop.
 */
package main;
import java.util.Scanner;

import tools.Fertilizer;
import tools.Pickaxe;
import tools.Plow;
import tools.Shovel;
import tools.WateringCan;

public class FarmDriver {
    private MyFarm tempFarm; 
    
    /**
     * Constructor for the driver 
     */
    public FarmDriver() {
        tempFarm = new MyFarm();
    }

    /**
     * Setting up the tools by adding them to the farm
     */
    public void setupTools() {
        Plow plow = new Plow();
        WateringCan wateringcan = new WateringCan();
        Fertilizer fertilizer = new Fertilizer();
        Pickaxe pickaxe = new Pickaxe();
        Shovel shovel = new Shovel();

        tempFarm.addTools(plow);
        tempFarm.addTools(wateringcan);
        tempFarm.addTools(fertilizer);
        tempFarm.addTools(pickaxe);
        tempFarm.addTools(shovel);
        

        /*tempFarm.addTools("Plow", 0, 0.5);
        tempFarm.addTools("Watering Can", 0, 0.5);
        tempFarm.addTools("Fertilizer", 10, 4);
        tempFarm.addTools("Pickaxe", 50, 15);
        tempFarm.addTools("Shovel", 7, 2);*/
    }

    /**  
     * Adds and defines the seeds in the seed array list
     */
    public void setupSeeds() {
        tempFarm.addSeeds("Turnip", "Root crop", 2, 2, 1, 5,6,5);
        tempFarm.addSeeds("Carrot", "Root crop", 3, 2, 1, 10,9,7.5);
        tempFarm.addSeeds("Potato", "Root crop", 5, 4, 2, 20,3,12.5);
        tempFarm.addSeeds("Rose", "Flower", 1, 2, 1, 5,5,2.5);
        tempFarm.addSeeds("Tulips", "Flower", 2, 3, 1, 10,9,5);
        tempFarm.addSeeds("Sunflower", "Flower", 3, 3, 2, 20,19,7.5);
        tempFarm.addSeeds("Mango", "Fruit tree", 10, 7, 4, 10,8,25);
        tempFarm.addSeeds("Apple", "Fruit tree", 10, 7, 5, 20,5,25);
    }
    
    /**
     * Prepares the farm land with 50 tiles in total
     */
    public void prepareTiles() {
        for(int i = 0; i < 50; i++)
            tempFarm.addTile();
    }

    /** 
     * Returns the farm object
     * @return MyFarm tempFarm 
     */
    public MyFarm getFarm() {
        return tempFarm;
    }

    /**  
     * Clears the screen
     */ 
    public void clearScreen() {
        System.out.print("\033\143");
    }

    public static void main(String[] args) {
        //instantiating the driver object
        FarmDriver app = new FarmDriver();

        //instantiating a scanner object
        Scanner scan = new Scanner(System.in);
    
        String opt2 = "yes";

        do {     
            System.out.println("Enter \"Start\" to begin.");
            String text = scan.next();
          
            while(text.equalsIgnoreCase("start") && opt2.equalsIgnoreCase("yes")) {            
                //instantiating the farm object
                MyFarm farm = app.getFarm();

                //adding the seeds and tools to the farm
                app.setupSeeds();
                app.setupTools();
                app.prepareTiles();
                app.clearScreen();
                 
                System.out.print("\nEnter your name: ");
                String name = scan.next();

                //instantiating a farmer object given String name
                farm.addFarmer(name);
                app.clearScreen();
    
                System.out.println("Welcome to your farm, " + farm.getFarmer().getName() + "!");
                System.out.println("As starters, your stats are set to its default: ");
                farm.getFarmer().displayStats();
                System.out.println("\nDay " + farm.getDay() + " of the game.");
                
                //loop to keep the game running
                while(farm.isRunning()) {
                    System.out.println("\nChoose an action to do: ");
                    System.out.println("1: Use a tool");
                    System.out.println("2: Plant a seed");
                    System.out.println("3: Harvest a crop");
                    System.out.println("4: Advance to next day");
                    System.out.println("5: Display stats");
                    System.out.println("6: Display Tile Information");
                    System.out.print("\nAction: ");
                    int nOpt = scan.nextInt();
                    
                    //options that the player can choose from to perform some actions
                    switch(nOpt) {
                        case 1: // 1: Use a tool
                            System.out.println("\nWhat tool would you like to use?");
                            for(Tool t : farm.getAllTool())
                                System.out.println(t.getName()); //displaying all available tools
                            
                            System.out.print("\nTool name: ");
                            scan.nextLine();
                            String tool = scan.nextLine();
                          
                            switch(tool.toLowerCase()) { 
                                case "plow":
                                    if(farm.getFarmLot(0).getPlowStatus() == false)
                                        farm.usePlow(farm.getFarmLot(0), farm.getTool(tool));
                                    else
                                        System.out.println("This lot is already plowed.");
                                break;
                              
                                case "watering can":
                                    if( farm.getFarmLot(0).getPlowStatus() == true &&
                                        farm.getFarmLot(0).getSeed() != null) {
                                        farm.useWaterCan(farm.getFarmLot(0), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break;
                              
                                case "fertilizer":
                                    if( farm.getFarmLot(0).getPlowStatus() == true &&
                                        farm.getFarmLot(0).getSeed() != null) {
                                        farm.useFertilizer(farm.getFarmLot(0), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break; 
                              
                                case "pickaxe":
                                    farm.usePickaxe(farm.getFarmLot(0), farm.getTool(tool)); 
                                break;
                                  
                                case "shovel":
                                    farm.useShovel(farm.getFarmLot(0), farm.getTool(tool));
                                break;
      
                                default:
                                    System.out.println("Tool does not exist!");
                                break;
                            }
                      
                        break; 
                      
                        case 2: // 2: Plant a seed
                            if(farm.getFarmLot(0).getOccupied() == false) {
                                if(farm.getFarmLot(0).getPlowStatus() == true) {
                                    System.out.println("\nWhat seed would you like to plant?");
                                    for(Seed s : farm.getSeed())
                                        System.out.println(s.getName()); //displaying all available seeds
                                    
                                    System.out.print("\nSeed name: ");
                                    String seed = scan.next();
                                    farm.plantSeed(farm.getFarmLot(0), seed); //setting a seed object to the farmlot
                              
                                }
                            else 
                                System.out.println("This lot is not yet plowed.");
                            }
                            
                            else 
                              System.out.println("This lot is already occupied");
                        break;
                        
                        case 3: // 3: Harvest a crop
                            farm.harvestTile(farm.getFarmLot(0));
                        break;
      
                        case 4: // 4: Advance to next day
                            System.out.println("You have advanced to the next day...");
                            farm.advanceNextDay();
                        break;
      
                        case 5: // 5: Display stats
                            farm.getFarmer().displayStats();
                        break;
                        
                        case 6: // 6: Display tile information
                            farm.displayTileInfo(farm.getFarmLot(0));
                        break;
                        
                        default:
                            System.out.println("Please choose an option from the list!");                      
                    }
                }
                
                //if game is no longer running
                if(!farm.isRunning()) {
                    System.out.println("\nYou have reached the end of the game.");
                    
                    //game ended because can no longer buy a seed and has no active crop
                    if(farm.getFarmer().getCoins() < 5 && farm.getFarmLot(0).getSeed() == null) {
                        System.out.print("You only have " + farm.getFarmer().getCoins() + " objectcoins left");
                        System.out.println(" and can no longer buy any seed.");
                    }
                    
                    //game ended because all tile contains a withered plant
                    else if(farm.getFarmLot(0).getWitherStatus() == true)
                        System.out.println("All your tiles contain withered plant");
                  
                    System.out.println("\nDo you want to play again?");
                    System.out.println("Enter [Yes] or [No]");
                    opt2 = scan.next();

                    //player chose to exit the program
                    if(opt2.equalsIgnoreCase("no"))
                        break;
                    
                    //player chose to start a new game
                    else if(opt2.equalsIgnoreCase("yes")) {
                        // reset stats
                        farm.resetFarm();
                        
                        // clear screen
                        app.clearScreen();
                    }
                }
            }
        } while(!opt2.equalsIgnoreCase("no"));      
        scan.close();    //closing the scanner object
    }
}