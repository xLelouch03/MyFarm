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
  
    public void isWithered() {
      this.wither = true;
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
        System.out.println(this.seed.getName() + " is harvestable");
      }
      return harvestable;
    }
  
    public boolean getOccupied() {
      return occupied;
    }
  
    public void isOccupied() {
      this.occupied = true;
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
  