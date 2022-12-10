package main;
/**
 * This class contains the information and methods about tools
 */
public abstract class Tool {
    private String name;
    private int toolCost;
    private double experience;

    /**
     * Constructs a tool with the given name, cost, and its experience
     * 
     * @param name  the name of the tool
     * @param toolCost  the cost of the tool
     * @param experience  the experience after using the tool
     */
    public Tool(String name, int toolCost, double experience) {
        this.name = name;
        this.toolCost = toolCost;
        this.experience = experience;
    }

    /**
     * Gets the name of the tool
     * @return the tool name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the cost of the tool
     * @return the cost of the tool
     */
    public int getCost() {
        return toolCost;
    }

    /**
     * Gets the experience gained after using the tool
     * @return the experience
     */
    public double getXP() {
        return experience;
    }
}
