/**
 * This program is a farm simulation game with the player as the farmer.
 * It involves the preparing a land, buying and planting of seed, advancing days
 * where a crop can either grow or wither. It also involves harvesting of the crop.
 */
package main;
import java.util.Scanner;

import main.MyFarm;
import main.Seed;

import seeds.Apple;
import seeds.Carrot;
import seeds.Mango;
import seeds.Potato;
import seeds.Rose;
import seeds.Sunflower;
import seeds.Tulips;
import seeds.Turnip;
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
        tempFarm.addTools(new Plow());
        tempFarm.addTools(new WateringCan());
        tempFarm.addTools(new Fertilizer());
        tempFarm.addTools(new Pickaxe());
        tempFarm.addTools(new Shovel());
    }

    /**  
     * Adds and defines the seeds in the seed array list
     */
    public void setupSeeds() {
        tempFarm.addSeeds(new Turnip());
        tempFarm.addSeeds(new Carrot());
        tempFarm.addSeeds(new Potato());
        tempFarm.addSeeds(new Rose());
        tempFarm.addSeeds(new Tulips());
        tempFarm.addSeeds(new Sunflower());
        tempFarm.addSeeds(new Mango());
        tempFarm.addSeeds(new Apple());
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
                            //for(Tool t : farm.getAllTool())
                              //  System.out.println(t.getName()); //displaying all available tools
                            
                            System.out.print("\nTool name: ");
                            scan.nextLine();
                            String tool = scan.nextLine();
                          
                            System.out.println("Which tile do you want to use the tool?: ");
                            int tileNum = scan.nextInt();

                            switch(tool.toLowerCase()) { 
                                case "plow":
                                    if(farm.getFarmLot(tileNum).getPlowStatus() == false)
                                        farm.usePlow(farm.getFarmLot(tileNum), farm.getTool(tool));
                                    else
                                        System.out.println("This lot is already plowed.");
                                break;
                              
                                case "watering can":
                                    if( farm.getFarmLot(tileNum).getPlowStatus() == true &&
                                        farm.getFarmLot(tileNum).getSeed() != null) {
                                        farm.useWaterCan(farm.getFarmLot(tileNum), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break;
                              
                                case "fertilizer":
                                    if( farm.getFarmLot(tileNum).getPlowStatus() == true &&
                                        farm.getFarmLot(tileNum).getSeed() != null) {
                                        farm.useFertilizer(farm.getFarmLot(tileNum), farm.getTool(tool));
                                    }
                                    else 
                                        System.out.println("This lot does not have any seed");
                                break; 
                              
                                case "pickaxe":
                                    farm.usePickaxe(farm.getFarmLot(tileNum), farm.getTool(tool)); 
                                break;
                                  
                                case "shovel":
                                    farm.useShovel(farm.getFarmLot(tileNum), farm.getTool(tool));
                                break;
      
                                default:
                                    System.out.println("Tool does not exist!");
                                break;
                            }
                      
                        break; 
                      
                        case 2: // 2: Plant a seed
                            System.out.println("Which tile do you want to plant a seed?: ");
                            tileNum = scan.nextInt();
                            if(farm.getFarmLot(tileNum).getOccupied() == false) {
                                if(farm.getFarmLot(tileNum).getPlowStatus() == true) {
                                    System.out.println("\nWhat seed would you like to plant?");
                                    for(Seed s : farm.getSeed())
                                        System.out.println(s.getName()); //displaying all available seeds
                                    
                                    System.out.print("\nSeed name: ");
                                    String seed = scan.next();
                                    //farm.plantSeed(farm.getFarmLot(tileNum), seed); //setting a seed object to the farmlot
                              
                                }
                            else 
                                System.out.println("This lot is not yet plowed.");
                            }
                            
                            else 
                              System.out.println("This lot is already occupied");
                        break;
                        
                        case 3: // 3: Harvest a crop
                            System.out.println("Which tile do you want to harvest?: ");
                            tileNum = scan.nextInt();
                            farm.harvestTile(farm.getFarmLot(tileNum));
                        break;
      
                        case 4: // 4: Advance to next day
                            System.out.println("You have advanced to the next day...");
                            farm.advanceNextDay();
                        break;
      
                        case 5: // 5: Display stats
                            farm.getFarmer().displayStats();
                        break;
                        
                        case 6: // 6: Display tile information
                            System.out.println("Which tile do you want to harvest?: ");
                            tileNum = scan.nextInt();
                            farm.displayTileInfo(farm.getFarmLot(tileNum));
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