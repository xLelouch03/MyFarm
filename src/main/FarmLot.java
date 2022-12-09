/**
 * This class contains the constructor and methods in managing a farm lot
 */

package main;

public class FarmLot {
    private boolean plowed;
    private int waterCount;
    private int fertilizeCount;
    private boolean wither;
    private boolean harvestable;
    private boolean occupied;
    private boolean rocked;
    private Seed seed;
  
    /**
     * Constructs a farm lot with the necessary information such as plow status,
     * water count, fertilize count, wither status, harvest status, seed information,
     * and farm lot/tile occupation status.
     */
    public FarmLot() {
        this.plowed = false;
        this.waterCount = 0;
        this.fertilizeCount = 0;
        this.wither = false;
        this.harvestable = false;
        this.seed = null;
        this.occupied = false;
        this.rocked = false;
    }
  
    /** 
     * Gets the seed planted in the farmlot
     * @return the object seed
     */
    public Seed getSeed() {
        return this.seed;
    }
    
    /** 
     * Sets the seed to the farmlot
     * @param temp the seed to be planted
     */
    public void setSeed(Seed temp) {
        this.seed = temp;
    }
    
    /** 
     * Gets the plow status of the tile
     * @return the plow status of the tile
     */
    public boolean getPlowStatus() {
        return this.plowed;
    }
    
    /** 
     * Sets the plow status of the tile
     * @param opt a boolean value passed to the method
     */
    public void isPlowed(boolean opt) {
        this.plowed = opt;
    }
    
    /** 
     * Gets the water count of how many times the tile has been watered
     * @return the water count of the tile 
     */
    public int getTotalWaterCount() {
        return this.waterCount;
    }

    /**
     * Gets the water count needed for computing water bonus
     * @return the water count of the tile
     */
    public int getWaterNeed() {
        if(waterCount > this.seed.getWaterLimit()) {
            return this.seed.getWaterLimit();
        }
        else
            return this.waterCount;
    }
  
    /**
     *  Increments the water count for the tile
     */
    public void increaseWater() {
        this.waterCount += 1;
    }
    
    /** 
     * Gets the fertillizer count of how many times the tile has been fertilized
     * @return the fertilizer count of the tile
     */
    public int getTotalFertilizerCount() {
        return this.fertilizeCount;
    }
    
    /**
     * Gets the fertilizer count needed for computing water bonus
     * @return the fertilizer count of the tile
     */
    public int getFertilizerNeed() {
        if(fertilizeCount > this.seed.getFertilizerLimit()) {
            return this.seed.getFertilizerLimit();
        }
        else
            return this.fertilizeCount;
    }

    /**
     *  Increments the fertilizer count for the tile
     */
    public void increaseFertilizer() {
        this.fertilizeCount += 1;
    }
    
    /** 
     * Gets the wither status of the the crop on the tile
     * @return the wither status of the crop on the tile
     */
    public boolean getWitherStatus() {
        return this.wither;
    }
    
    /** 
     * Sets a boolean value if the crop on the tile is withered
     * @param withered a boolean value passed to the method
     */
    public String isWithered(boolean withered) {
        this.wither = withered;
        return this.seed.getName() + " has withered.";
    }
    
    /** 
     * Gets the harvest status of the crop on the tile
     * @return the harvest status of the crop on the tile
     */
    public boolean getHarvestStatus() {
        return this.harvestable;
    }
    
    /** 
     * Checks if the crop on the tile is harvestable
     * @return the harvest status of the crop on the tile
     */
    public boolean isHarvestable() { 
        //Checks if the crop meets the minimum requirements to be harvested
        if (this.seed.getDayGrowth() == this.seed.getHarvestTime() &&
            this.seed.getWaterLimit() - 1 <= getTotalWaterCount() &&
            this.seed.getFertilizerLimit() - 1 <= getTotalFertilizerCount()) {
            this.harvestable = true;
        }
        else {
            this.harvestable = false;
        }
        return this.harvestable;
    }
    
    /** 
     * Gets the status of the tile whether occupied or not
     * @return the occupation status of the tile
     */
    public boolean getOccupied() {
        return this.occupied;
    }
  
    /**
     *  Checks if the tile already has a crop planted on it/is already occupied 
     */
    public void isOccupied(boolean occ) {
        this.occupied = occ;
    }

    /**
     * Gets the boolean value whether the tile has rock or not
     * @return the boolean value 
     */
    public boolean getRockedStatus() {
        return this.rocked;
    }

    /**
     * Sets the tile's rock and occupation status to the input boolean value
     * @param rock the boolean value to be assigned to rocked and occupied
     */
    public void isRocked(boolean rock) {
        this.rocked = rock;
        this.occupied = rock;
    }
    
    /**
     *  Resets the Farm Lot/Tile  
     */
    public void resetFarmLot() {
        this.plowed = false;
        this.waterCount = 0;
        this.fertilizeCount = 0;
        this.wither = false;
        this.harvestable = false;
        this.seed = null;
        this.occupied = false;
        this.rocked = false;
    }
  }
  