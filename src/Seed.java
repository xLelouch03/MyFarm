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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDayGrowth() {
        return dayGrowth;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public void grow() {
        dayGrowth++;
    }

    public int remainingDay() {
        return harvestTime - dayGrowth;
    }

    public int getWaterNeed() {
        return waterNeed;
    }

    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    public int getCost() {
        return cost;
    }

    public int getHarvestTotal() {
        return harvestTotal;
    }
    
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

    public double getExperienceYield() {
        return this.xp;
    }

    public void resetSeed() {
        dayGrowth = 0;
        productProduced = 0;
    }
}
