public class FarmLot {
    private boolean plowed;
    private int waterCount;
    private int fertilizeCount;
    private boolean wither;
    private boolean harvestable;
    private boolean occupied;
    private Seed seed;
  
    public FarmLot() {
        this.plowed = false;
        this.waterCount = 0;
        this.fertilizeCount = 0;
        this.wither = false;
        this.harvestable = false;
        this.seed = null;
        this.occupied = false;
    }
  
    public Seed getSeed() {
        return this.seed;
    }
  
    public void setSeed(Seed temp) {
        this.seed = temp;
    }
  
    public boolean getPlowStatus() {
        return this.plowed;
    }
  
    public void isPlowed(boolean opt) {
        this.plowed = opt;
    }
  
    public int getWaterCount() {
        return waterCount;
    }
  
    public void increaseWater() {
        if (this.seed.getWaterNeed() != waterCount)
            waterCount += 1;
    }
  
    public int getFertilizerCount() {
        return fertilizeCount;
    }
  
    public void increaseFertilizer() {
        if (this.seed.getFertilizerNeed() != fertilizeCount)
          fertilizeCount += 1;
    }
  
    public boolean getWitherStatus() {
        return this.wither;
    }
  
    public void isWithered(boolean withered) {
        this.wither = withered;
        System.out.println(this.seed.getName() + " has withered.");
    }
  
    public boolean getHarvestStatus() {
        return harvestable;
    }
    
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
  
    public boolean getOccupied() {
        return occupied;
    }
  
    public void isOccupied() {
        this.occupied = true;
    }
    
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
  