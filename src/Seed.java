public class Seed {
    private String name;
    private String type;
    private int harvestTime;
    private int dayGrowth = 0;
    private int waterNeed;
    private int fertilizerNeed;
    private int productProduced = 0;
    private int cost;
    private int basePrice;
    private int harvestTotal;
    private double xp;

    public Seed(String name, String type, int harvestTime, int waterNeed, int fertilizerNeed, int cost, int basePrice, double xp) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertilizerNeed;
        this.cost = cost;
        this.basePrice = basePrice;
        this.xp = xp;
    }

    
    /** 
     * Get the name of the seed
     * @return String name   Name of the seed
     */
    public String getName() {
        return name;
    }

    
    /** 
     * Get the crop type
     * @return String type   Name of the crop
     */
    public String getType() {
        return type;
    }

    
    /** 
     * Get the number of days the crop has been growing
     * @return int dayGrowth   Growth time in days
     */
    public int getDayGrowth() {
        return dayGrowth;
    }

    
    /** 
     * Get the number of days the crop needs to be ready for harvest
     * @return int harvestTime   Harvest time in days
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /* Increments the dayGrowth */
    public void grow() {
        dayGrowth++;
    }

    
    /** 
     * Get the number of times the crop needs to be watered to be ready for harvest
     * @return int waterNeed   Number of times the crop needs to watered to be ready for harvest
     */
    public int getWaterNeed() {
        return waterNeed;
    }

    
    /** 
     * Get the number of times the crop needs to fertilized to be ready for harvest
     * @return int fertilizerNeed   Number of times the crop needs to fertilized to be ready for harvest
     */
    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    
    /** 
     * Get cost of the seed
     * @return int cost   Get cost of the seed
     */
    public int getCost() {
        return cost;
    }

    
    /** 
     * 
     * @return int
     */
    public int getHarvestTotal() {
        return harvestTotal;
    }
    
    
    /** 
     * Get the number of profucts produced by the seed
     * @return int productProduced   Number of profucts produced by the seed
     */
    //to revise
    public int getProductProduced() {
        int min, max;

        if(this.type.equalsIgnoreCase("root crop")) {
            min = 1;
            if( this.name.equalsIgnoreCase("turnip") || 
                this.name.equalsIgnoreCase("carrot")) {
                max = 2;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
                
                if(this.name.equalsIgnoreCase("turnip"))
                    harvestTotal = basePrice * productProduced;
                
                if(this.name.equalsIgnoreCase("carrot")) 
                    harvestTotal = basePrice * productProduced;

            }
            else if(this.name.equalsIgnoreCase("potato")) {
                max = 10;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
                harvestTotal = basePrice * productProduced;
            }
        }
        
        else if(this.type.equalsIgnoreCase("flower")) {
            productProduced = 1;

            if(this.name.equalsIgnoreCase("tulips"))
                harvestTotal = basePrice;
            else if(this.name.equalsIgnoreCase("rose"))
                harvestTotal = basePrice;
            else if(this.name.equalsIgnoreCase("sunflower"))
                harvestTotal = basePrice;
        }

        else if(this.type.equalsIgnoreCase("fruit tree")) {
            max = 15;
            if(this.name.equalsIgnoreCase("mango")) {
                min = 5;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
                harvestTotal = basePrice * productProduced;
            }
            
            else if(this.name.equalsIgnoreCase("apple")) {
                min = 10;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
                harvestTotal = basePrice * productProduced;
            }
        }
        return productProduced;
    }

    
    /** 
     * Get the XP yielded by harvesting the product
     * @return double this.xp   Get the XP yielded by harvesting the product
     */
    public double getExperienceYield() {
        return this.xp;
    }

    /* Reset the seed statistics back to 0 */
    public void resetSeed() {
        dayGrowth = 0;
        productProduced = 0;
    }
}
