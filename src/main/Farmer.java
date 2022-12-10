/**
 * Belongs to package main
 */
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
        return this.name;
    }

    /** 
     * Get the number of objectCoins 
     * @return double objectCoins   Get number of objectCoins
     */
    public double getCoins() {
        return this.objectCoins;
    }

    /**
     * Gets the registered type of the farmer
     * @return the farmer type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Updates the objectCoin by assigning the input coin as the new
     * value of objectCoins
     * 
     * @param coin the updated total value of objectCoins
     */
    public void updateObjectCoins(double coin){
        this.objectCoins = coin;
    }

    /** 
     * Get farmer's level
     * @return the farmer level
     */
    public int getLevel() {
        return this.level;
    } 

    /**
     * Updates the farmer level
     * @param level the new level of the farmer
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /** 
     * Gets the farmer XP 
     * @return the experience of the farmer
    */
    public double getXP() {
        return this.experience;
    }

    /** 
     * Updates farmer experience 
     * @param xp the updated farmer experience
    */
    public void updateXP(double xp) {
        this.experience = xp;
    } 

    /**
     * Updates the farmer type after registering to higher types
     * @param farmerType the updated farmer type
     */
    public void setFarmerType(String farmerType){
        this.type = farmerType;
    }

    /**
     * Resets the stats of the farmer
     */
    public void resetFarmer(){
        this.name = null;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
    }
}
