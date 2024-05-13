import java.util.List;

public class Player {
    private static Integer inventorySize;
    private static List<String> inventory;

    public static Integer getInventorySize() {
        return inventorySize;
    }

    public static void setInventorySize(Integer inventorySize) {
        Player.inventorySize = inventorySize;
    }

    public static List<String> getInventory() {
        return inventory;
    }

    public static void setInventory(List<String> inventory) {
        Player.inventory = inventory;
    }
}