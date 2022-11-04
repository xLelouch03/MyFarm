public class FarmLot {
    private boolean plowed;
    private int waterCount;
    private int fertilizeCount;
    private boolean wither;
    private boolean harvestable;
    private boolean occupied;
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
    }
  
    
    /** 
     * Get the seed data
     * @return Seed   The seed data
     */
    public Seed getSeed() {
        return this.seed;
    }
  
    
    /** 
     * Set the seed data
     * @param temp   The seed data
     */
    public void setSeed(Seed temp) {
        this.seed = temp;
    }
  
    
    /** 
     * Get the plow status of the tile
     * @return boolean   The plow status of the tile
     */
    public boolean getPlowStatus() {
        return this.plowed;
    }
  
    
    /** 
     * Sets the plow status of the tile
     * @param opt   The plow status of the tile
     */
    public void isPlowed(boolean opt) {
        this.plowed = opt;
    }
  
    
    /** 
     * Get the water count of the tile / how many times the tile has been watered
     * @return int   The water count of the tile / how many times the tile has been watered
     */
    public int getWaterCount() {
        return waterCount;
    }
  
    /* Increments the water count for the tile */
    public void increaseWater() {
        // if (this.seed.getWaterNeed() != waterCount)
            waterCount += 1;
    }
  
    
    /** 
     * Get the fertillizer count of the tile / how many times the tile has been fertilized
     * @return int   The fertilizer count of the tile / how many times the tile has been fertilized
     */
    public int getFertilizerCount() {
        return fertilizeCount;
    }
  
    /* Increments the fertilizer count for the tile */
    public void increaseFertilizer() {
        // if (this.seed.getFertilizerNeed() != fertilizeCount)
          fertilizeCount += 1;
    }
  
    
    /** 
     * Get the wither status of the the crop on the tile
     * @return boolean   The wither status of the crop on the tile
     */
    public boolean getWitherStatus() {
        return this.wither;
    }
  
    
    /** 
     * Check if the crop on the tile is withered
     * @param withered   THe wither status of the the crop on the tile
     */
    public void isWithered(boolean withered) {
        this.wither = withered;
        System.out.println(this.seed.getName() + " has withered.");
    }
  
    
    /** 
     * Get the harvest status of the crop on the tile
     * @return boolean   The harvest status of the crop on the tile
     */
    public boolean getHarvestStatus() {
        return harvestable;
    }
    
    
    /** 
     * Check if the crop on the tile is harvestable
     * @return boolean   The harvest status of the crop on the tile
     */
    public boolean isHarvestable() { 
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
     * Checks the occupation status of the tile
     * @return boolean   The occupation status of the tile
     */
    public boolean getOccupied() {
        return occupied;
    }
  
    /* Checks if the tile already has a crop planted on it/is already occupied */
    public void isOccupied() {
        this.occupied = true;
    }
    
    /* Displays the information of the tile */
    public void displayTileInfo()  {
        System.out.println("\n[Tile Information]");
        System.out.print("Plow Status:");
        if(getPlowStatus() == true) {
            System.out.println(" Plowed");
            if(getOccupied() == false)
                System.out.println("No seed is planted here.");
        
            else {
                System.out.println("Seed planted: " + this.seed.getName());
                System.out.println("Water count: " + getWaterCount());
                System.out.println("Fertilizer count: " + getFertilizerCount());
                System.out.println("Days growed: " + this.seed.getDayGrowth());
                System.out.print("Harvestable:" );
                if(isHarvestable() == true)
                    System.out.println(" Yes");
                else 
                    System.out.println(" No");
            }
        }
        else 
            System.out.println(" Unplowed");
        
    }

    /* Resets the Farm Lot/Tile statistics */
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
  