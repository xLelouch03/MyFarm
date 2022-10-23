public class Seed {
    private String name;
    private String type;
    private int harvestTime;
    private int waterNeed;
    private int fertilizerNeed;
    private int productProduced;
    private int cost;
    private double sellingPrice;
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
}
