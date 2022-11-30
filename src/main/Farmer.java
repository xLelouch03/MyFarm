package main;
/**
 * This class contains the constructor and methods about the farmer
 */
public class Farmer {

    private double objectCoins;
    private int level;
    private double experience;
    private String name;
    private String type;

    /**
     * The Farmer object where the player statistics such as name, 
     * object coins, level, and experience are stored
     * @param name Name of the player to be used for the farmer
     */
    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
        this.type = "Farmer";
    }

    /**
     * Get the name of the player to be used for the farmer 
     * @return String name  Get the player name
     */
    public String getName() {
        return name;
    }

    /** 
     * Get the number of objectCoins 
     * @return double objectCoins   Get number of objectCoins
     */
    public double getCoins() {
        return objectCoins;
    }

    public String getType() {
        return type;
    }

    /** Update the number of objectCoins */
    public void updateObjectCoins(double coin){
        this.objectCoins = coin;
    }

    /** 
     * Get player's level
    `* The player will level up for every 100 XPs 
     * @return int level    Get level number
     */
    public int getLevel() {
        int tempLevel = level;
        level = (int) getXP() / 100;

        if(tempLevel < level)
            System.out.println("\nYou have leveled up!\n");

        return level;
    } 

    /** 
     * Get XP count 
     * @return double experience    Get XP count
    */
    public double getXP() {
        return experience;
    }

    /** 
     * Update XP count 
     * @param XP XP count dependent on the action/tool type
    */
    public void updateXP(double xp) {
        this.experience = xp;
    } 

    /**
     * Allow farmer to register to higher farmerTypes 
     */

    public void registerFarmerType(String farmerType){

        switch(farmerType){

            case "Registered Farmer":
                if (this.getLevel() <= 5 && this.getCoins() <= 200){
                    this.type = "Registered Farmer";
                    this.objectCoins = this.getCoins() - 200;
                } 
                break;
            
            case "Distinguished Farmer":
                if (this.getLevel() <= 10 && this.getCoins() <= 300){
                    this.type = "Registered Farmer";
                    this.objectCoins = this.getCoins() - 300;
                } 
                break;

            case "Legendary Farmer":
                if (this.getLevel() <= 15 && this.getCoins() <= 400){
                    this.type = "Registered Farmer";
                    this.objectCoins = this.getCoins() - 400;
                } 
                break;

            default:
                this.type = this.getType();

        }

    }

    /* Display the game statistics/summary */
    public void displayStats() {
        System.out.println("\n[DISPLAYING FARMER INFORMATION]");
        System.out.println("Farmer Name: " + getName());
        System.out.println("Farmer Type: " + getType());
        System.out.println("You are level: " + getLevel());
        System.out.println("You have " + (float) getCoins() + " objectcoins");
        System.out.println("You have " + getXP() + " experience.");
    }

    /* Resets the farmer/player statistics */
    public void resetFarmer(){
        this.name = null;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
    }

}
