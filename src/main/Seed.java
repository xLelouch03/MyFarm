/**
 * Belongs to package main
 */

package main;

/**
 * This class contains the information and methods about seeds
 */
public class Seed {
    private String name;
    private String type;
    private int harvestTime;
    private int dayGrowth = 0;
    private int waterNeed;
    private int waterLimit;
    private int fertilizerNeed;
    private int fertilizerLimit;
    private int productProduced = 0;
    private int cost;
    private int basePrice;
    private int updatedSellingPrice;
    private int updatedCost;
    private int updatedWaterLimit;
    private int updatedFertilizerLimit;
    private int harvestTotal;
    private double xp;
    private String MinToMax;

    /**
     * Constructs the seed with the given name, type, harvest time, 
     * number of water and fertilizer needs, cost, base price, and experience
     * 
     * @param name  the name of the seed
     * @param type  the type of the seed
     * @param harvestTime  the number of days it needs to grow
     * @param waterNeed  the number of times it needs to be watered
     * @param waterLimit  the maximum for the water bonus
     * @param fertilizerNeed the number of times it needs to be fertilized
     * @param fertilizerLimit  the maximum for fertilizer bonus
     * @param cost  the cost of the seed
     * @param basePrice  the base selling price of the seed 
     * @param xp  the experience gained after harvesting the seed
     */
    public Seed(String name, String type, int harvestTime, int waterNeed, int waterLimit, 
                int fertilizerNeed, int fertilizerLimit, int cost, int basePrice, double xp) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.waterLimit = waterLimit;
        this.fertilizerNeed = fertilizerNeed;
        this.fertilizerLimit = fertilizerLimit;
        this.cost = cost;
        this.updatedCost = cost;
        this.basePrice = basePrice;
        this.xp = xp;
    }
    
    /**
     * Another constructor that will be used in planting a seed
     * @param seed the seed object
     */
    public Seed(Seed seed) {
        name = seed.getName();
        type = seed.getType();
        harvestTime = seed.getHarvestTime();
        waterLimit = seed.getWaterLimit();
        fertilizerLimit = seed.getFertilizerLimit();
        cost = seed.getCost();
        basePrice = seed.getBasePrice();
        xp = seed.getExperienceYield();
    }

    /** 
     * Gets the name of the seed
     * @return the name of the seed
     */
    public String getName() {
        return this.name;
    }
    
    /** 
     * Gets the crop type
     * @return the type of the crop
     */
    public String getType() {
        return this.type;
    }
    
    /** 
     * Gets the number of days the crop has been growing
     * @return the number of days the crop growed
     */
    public int getDayGrowth() {
        return this.dayGrowth;
    }
    
    /** 
     * Gets the number of days the crop needs to grow to be ready for harvest
     * @return the harvest time
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }

    /**
     *  Increments the number of days the seed has growed
     */
    public void grow() {
        this.dayGrowth++;
    }
    
    /** 
     * Gets the number of times the crop needs to be watered to be ready for harvest
     * @return the number of times the crop needs to watered
     */
    public int getWaterLimit() {
        if(this.waterLimit < updatedWaterLimit)
            return updatedWaterLimit;
        return this.waterLimit;
    }

    /**
     * Gets the water need of the seed
     * @return the water need
     */
    public int getWaterNeed() {
        return this.waterNeed;
    }

    /**
     * Updates the water limit once the farmer registers to higher type
     * @param num the bonus water limit
     */
    public void updateWaterLimit(int num) {
        updatedWaterLimit = this.waterLimit + num;
    }

    /**
     * Gets the fertilizer need of the seed
     * @return the fertilizer need
     */
    public int getFertilizerNeed() {
        return this.fertilizerNeed;
    }
    
    /** 
     * Gets the number of times the crop needs to fertilized to be ready for harvest
     * @return the number of times the crop needs to fertilized
     */
    public int getFertilizerLimit() {
        if(this.fertilizerLimit < updatedFertilizerLimit)
            return updatedFertilizerLimit;
        return this.fertilizerLimit;
    }

    /**
     * Updates the water limit once the farmer register to higher type
     * @param num the bonus fertilizer limit
     */
    public void updateFertilizerLimit(int num) {
        updatedFertilizerLimit = this.fertilizerLimit + num;
    }

    /**
     * Gets the base price of the seed
     * @return the base price
     */
    public int getBasePrice() {
        if(basePrice < updatedSellingPrice)
            return updatedSellingPrice;
        return this.basePrice;
    }

    /**
     * Updates the bonus earnings once the farmer registers to a higher type
     * @param bonusEarning the bonus earning per produce
     */
    public void updateBasePrice(int bonusEarning) {
        updatedSellingPrice = basePrice + bonusEarning;
    }

    /** 
     * Gets the cost of the seed
     * @return the cost of the seed
     */
    public int getCost() {
        if(this.cost < updatedCost)
            return this.cost;
        return updatedCost;
    }
    
    /**
     * Updates the cost of the seed after the farmer registers to a higher type
     * @param reduction the reduction to the cost
     */
    public void updateCost(int reduction) {
        updatedCost = this.cost - reduction;
    }

    /** 
     * Gets the computed harvest total from the seed
     * 
     * @return the harvest total
     */
    public int getHarvestTotal() {
        return this.harvestTotal;
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

        if(basePrice < updatedSellingPrice)
            harvestTotal = updatedSellingPrice * productProduced;
        else
            harvestTotal = this.basePrice * productProduced;

        return productProduced;
    }

    /** 
     * Gets the number of products produced by the seed as well as computing for the
     * harvest total.
     * @return the number of product produced
     */
    public String getProductProducedMinToMax() {

        if(this.type.equalsIgnoreCase("root crop")) {
            if( this.name.equalsIgnoreCase("turnip") || 
                this.name.equalsIgnoreCase("carrot")) {
                MinToMax = "1-2";
            }
            
            else if(this.name.equalsIgnoreCase("potato")) {
                MinToMax = "1-10";
            }
        }
        
        else if(this.type.equalsIgnoreCase("flower")) 
                MinToMax = "1";
        
        else if(this.type.equalsIgnoreCase("fruit tree")) {
            if(this.name.equalsIgnoreCase("mango")) {
                MinToMax = "5-15";
            }
            
            else if(this.name.equalsIgnoreCase("apple")) {
                MinToMax = "10-15";
            }
        }
        return MinToMax;
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
        this.dayGrowth = 0;
        this.productProduced = 0;
    }

}
