package main;

/**
 * This class contains the information and methods about seeds
 */
public class Seed {
    private String name;
    private String type;
    private int harvestTime;
    private int dayGrowth = 0;
    private int waterLimit;
    private int fertilizerLimit;
    private int productProduced = 0;
    private int cost;
    private int basePrice;
    private int harvestTotal;
    private double xp;

    /**
     * Constructs the seed with the given name, type, harvest time, 
     * number of water and fertilizer needs, cost, base price, and experience
     * 
     * @param name the name of the seed
     * @param type the type of the seed
     * @param harvestTime the number of days it needs to grow
     * @param waterNeed the number of times it needs to be watered
     * @param fertilizerNeed the number of times it needs to be fertilized
     * @param cost the cost of the seed
     * @param basePrice the base selling price of the seed 
     * @param xp the experience gained after harvesting the seed
     */
    public Seed(String name, String type, int harvestTime, int waterLimit, int fertilizerLimit, int cost, int basePrice, double xp) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterLimit = waterLimit;
        this.fertilizerLimit = fertilizerLimit;
        this.cost = cost;
        this.basePrice = basePrice;
        this.xp = xp;
    }
    
    public Seed(Seed seed) {
        name = seed.getName();
        type = seed.getType();
        harvestTime = seed.getHarvestTime();
        waterLimit = seed.getWaterLimit();
        fertilizerLimit = seed.getFertilizerLimit();
        cost = seed.getCost();
        basePrice = seed.getBasePrice();
        xp = seed.getXP();
    }

    /** 
     * Gets the name of the seed
     * @return the name of the seed
     */
    public String getName() {
        return name;
    }
    
    /** 
     * Gets the crop type
     * @return the type of the crop
     */
    public String getType() {
        return type;
    }
    
    /** 
     * Gets the number of days the crop has been growing
     * @return the number of days the crop growed
     */
    public int getDayGrowth() {
        return dayGrowth;
    }
    
    /** 
     * Gets the number of days the crop needs to grow to be ready for harvest
     * @return the harvest time
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     *  Increments the number of days the seed has growed
     */
    public void grow() {
        dayGrowth++;
    }
    
    /** 
     * Gets the number of times the crop needs to be watered to be ready for harvest
     * @return the number of times the crop needs to watered
     */
    public int getWaterLimit() {
        return waterLimit;
    }

    public void updateWaterLimit(int num) {
        waterLimit += num;
    }

    /** 
     * Gets the number of times the crop needs to fertilized to be ready for harvest
     * @return the number of times the crop needs to fertilized
     */
    public int getFertilizerLimit() {
        return fertilizerLimit;
    }

    public void updateFertilizerLimit(int num) {
        fertilizerLimit += num;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void updateBasePrice(int bonusEarning) {
        basePrice += bonusEarning;
    }

    public double getXP() {
        return xp;
    }

    /** 
     * Gets the cost of the seed
     * @return the cost of the seed
     */
    public int getCost() {
        return cost;
    }
    
    public void updateCost(int reduction) {
        cost -= reduction;
    }
    /** 
     * Gets the computed harvest total from the seed
     * 
     * @return the harvest total
     */
    public int getHarvestTotal() {
        return harvestTotal;
    }
    
    /** 
     * Gets the number of products produced by the seed as well as computing for the
     * harvest total.
     * @return the number of product produced
     */
    public int getProductProduced() {
        int min, max;

        if(this.type.equalsIgnoreCase("root crop")) {
            min = 1;
            if( this.name.equalsIgnoreCase("turnip") || 
                this.name.equalsIgnoreCase("carrot")) {
                max = 2;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
            
            else if(this.name.equalsIgnoreCase("potato")) {
                max = 10;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
        }
        
        else if(this.type.equalsIgnoreCase("flower")) 
            productProduced = 1;
        
        else if(this.type.equalsIgnoreCase("fruit tree")) {
            max = 15;
            if(this.name.equalsIgnoreCase("mango")) {
                min = 5;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
            
            else if(this.name.equalsIgnoreCase("apple")) {
                min = 10;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
        }

        harvestTotal = basePrice * productProduced;

        return productProduced;
    }
 
    /** 
     * Get the XP yielded by harvesting the product
     * @return the XP yielded by harvesting the product
     */
    public double getExperienceYield() {
        return this.xp;
    }

    /**
     * Resets the number of days a seed growed and the number of product produced
     */
    public void resetSeed() {
        dayGrowth = 0;
        productProduced = 0;
    }

}
