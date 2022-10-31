public class Farmer {
    private int objectCoins;
    private int level;
    private double experience;
    private String name;

    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return objectCoins;
    }

    public void updateObjectCoins(int coin){
        this.objectCoins = coin;
    }

    public int getLevel() {
        int tempLevel = level;
        level = (int) getXP() / 100;

        if(tempLevel < level)
            System.out.println("You have leveled up!");

        return level;
    } 

    public double getXP() {
        return experience;
    }

    public void updateXP(double xp) {
        this.experience = xp;
    } 

    public void displayStats() {
        System.out.println("\nYou are level: " + getLevel());
        System.out.println("You have " + getCoins() + " objectcoins");
        System.out.println("You have " + getXP() + " experience.\n");
    }

}
