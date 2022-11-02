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
        tempFarm.addSeeds("Turnip", "Root crop", 2, 2, 1, 90,6,5);
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

        String opt2 = "no";
        do {
          System.out.println("Enter \"Start\" to begin.");
          String text = scan.next();
          
          if(text.equalsIgnoreCase("start")) {
            app.clearScreen();
                 
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
                        break;
    
                      case 5: // 5: Display stats
                        farm.getFarmer().displayStats();
                        break;
    
                      default:
                        System.out.println("Please choose an option from the list!");                      
                  }
              if(farm.getFarmer().getCoins() < 5 ||
                       farm.getFarmLot().getWitherStatus() == true) {
                    System.out.println("\nYou have reached the end of the game.");
                    System.out.println("Some of the reasons include: ");
                    System.out.println("1) You no longer have any active crops");
                    System.out.println("2) You can no longer buy any seeds");
                    System.out.println("3) All your tiles contain withered plant");
                    System.out.println("\nDo you want to play again?");
                    System.out.println("Enter [Yes] or [No]");
                    opt2 = scan.next();
                    if(opt2.equalsIgnoreCase("no")){

                        // reset stats
                        farm.getFarmLot().getSeed().resetSeed();
                        farm.getFarmLot().resetFarmLot();

                    }
                    else if(opt2.equalsIgnoreCase("yes"))

                        // clear screen
                        app.clearScreen();
                        
                        // reset stats
                        farm.getFarmLot().getSeed().resetSeed();
                        farm.getFarmLot().resetFarmLot();

                        // clear screen
                        app.clearScreen();
              }
            }
          }
        } while(!opt2.equalsIgnoreCase("no"));          
    }
}