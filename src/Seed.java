public class Seed {
    private String name;
    private String type;
    private int harvestTime;
    private int waterNeed;
    private int fertilizerNeed;
    private int productProduced;
    private int cost;
    private int sellingPrice;
    private double experience;

    public Seed(String name, String type, int harvestTime, int waterNeed, int fertilizerNeed, int cost) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertilizerNeed;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHarvestTime() {
        return harvestTime;
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

    public int getProductProduced() {
        int min, max;

        if(this.type.equalsIgnoreCase("root crop")) {
            if( this.name.equalsIgnoreCase("turnip") || 
                this.name.equalsIgnoreCase("carrot")) {
                min = 1;
                max = 2;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
            else if(this.name.equalsIgnoreCase("potato")) {
                min = 1;
                max = 10;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
        }
        
        else if(this.type.equalsIgnoreCase("flower")) {
            productProduced = 1;
        }

        else if(this.type.equalsIgnoreCase("fruit tree")) {
            if(this.name.equalsIgnoreCase("mango")) {
                min = 5;
                max = 15;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
            
            else if(this.name.equalsIgnoreCase("apple")) {
                min = 10;
                max = 15;
                productProduced = (int) Math.floor(Math.random()*(max-min+1)+min);
            }
        }
        return productProduced;
    }

    public int getSellingPrice() {
        if(this.type.equalsIgnoreCase("root crop")) {
            if(this.name.equalsIgnoreCase("turnip")) 
                sellingPrice = 6 * getProductProduced();
            
            else if(this.name.equalsIgnoreCase("carrot"))
                sellingPrice = 9 * getProductProduced();
            
            else if(this.name.equalsIgnoreCase("potato"))
                sellingPrice = 3 * getProductProduced();
        }

        else if(this.type.equalsIgnoreCase("flower")) {
            if(this.name.equalsIgnoreCase("rose")) 
                sellingPrice = 5;
            
            else if(this.name.equalsIgnoreCase("turnips"))
                sellingPrice = 9;
            
            else if(this.name.equalsIgnoreCase("sunflower"))
                sellingPrice = 19;
        }

        else if(this.type.equalsIgnoreCase("fruit tree")) {
            if(this.name.equalsIgnoreCase("mango")) 
                sellingPrice = 8 * getProductProduced();
            
            else if(this.name.equalsIgnoreCase("apple")) 
                sellingPrice = 5 * getProductProduced();
        }
        
        return sellingPrice;
    }

    public double getExperienceYield() {
        if(this.name.equalsIgnoreCase("rose"))
            experience = 2.5;
        
        else if(this.name.equalsIgnoreCase("turnip") ||
                this.name.equalsIgnoreCase("turnips"))
            experience = 5;

        else if(this.name.equalsIgnoreCase("carrot") ||
                this.name.equalsIgnoreCase("sunflower"))
            experience = 7.5;

        else if(this.name.equalsIgnoreCase("potato"))
            experience = 12.5;

        else if(this.name.equalsIgnoreCase("mango") || 
                this.name.equalsIgnoreCase("apple"))
            experience = 25;
            
        return experience;
    }
}
