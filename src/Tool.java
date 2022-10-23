public class Tool {
    private String name;
    private int toolCost;
    private double experience;

    public Tool(String name, int toolCost, double experience) {
        this.name = name;
        this.toolCost = toolCost;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return toolCost;
    }

    public double getXP() {
        return experience;
    }
}
