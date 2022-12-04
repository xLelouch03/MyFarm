package main;

/**
 * This class contains the constructor and methods in managing a farm lot
 */
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
     * @param temp seed object passed to the method
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
     * Gets the water count of the tile / how many times the tile has been watered
     * @return the water count of the tile / how many times the tile has been watered
     */
    public int getWaterCount() {
        return waterCount;
    }
  
    /**
     *  Increments the water count for the tile
     */
    public void increaseWater() {
        // if (this.seed.getWaterNeed() != waterCount)
            waterCount += 1;
    }
    
    /** 
     * Gets the fertillizer count of the tile / how many times the tile has been fertilized
     * @return the fertilizer count of the tile / how many times the tile has been fertilized
     */
    public int getFertilizerCount() {
        return fertilizeCount;
    }
  
    /**
     *  Increments the fertilizer count for the tile
     */
    public void increaseFertilizer() {
        // if (this.seed.getFertilizerNeed() != fertilizeCount)
          fertilizeCount += 1;
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
    public void isWithered(boolean withered) {
        this.wither = withered;
        System.out.println(this.seed.getName() + " has withered.");
    }
    
    /** 
     * Gets the harvest status of the crop on the tile
     * @return the harvest status of the crop on the tile
     */
    public boolean getHarvestStatus() {
        return harvestable;
    }
    
    /** 
     * Checks if the crop on the tile is harvestable
     * @return the harvest status of the crop on the tile
     */
    public boolean isHarvestable() { 
        //Checks if the crop meets the minimum requirements to be harvested
        if (this.seed.getDayGrowth() == this.seed.getHarvestTime() &&
            this.seed.getWaterNeed() - 1 <= getWaterCount() &&
            this.seed.getFertilizerNeed() - 1 <= getFertilizerCount()) {
            this.harvestable = true;
        }
        else {
            this.harvestable = false;
        }
        return harvestable;
    }
    
    /** 
     * Gets the status of the tile whether occupied or not
     * @return the occupation status of the tile
     */
    public boolean getOccupied() {
        return occupied;
    }
  
    /**
     *  Checks if the tile already has a crop planted on it/is already occupied 
     */
    public void isOccupied() {
        this.occupied = true;
    }

    public boolean getRockedStatus() {
        return rocked;
    }

    public void isRocked() {
        this.rocked = true;
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
    }
    
  }
  