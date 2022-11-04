public class Farmer {

    private double objectCoins;
    private int level;
    private double experience;
    private String name;

    /**
     * The Farmer object where the player statistics are stored
     * @param name Name of the player to be used for the farmer
     */
    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
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

    /* Display the game statistics/summary */
    public void displayStats() {
        System.out.println("\nFarmer Name: " + getName());
        System.out.println("You are level: " + getLevel());
        System.out.println("You have " + (float) getCoins() + " objectcoins");
        System.out.println("You have " + getXP() + " experience.\n");
    }

    public void resetFarmer(){
        this.name = null;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
    }

}
