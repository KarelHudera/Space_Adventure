import java.util.List;

/**
 * The Player class represents the player in the game, managing inventory size and items.
 */
public class Player {
    private static Integer inventorySize;
    private static List<String> inventory;

    /**
     * Retrieves the maximum size of the player's inventory.
     *
     * @return The maximum number of items the player can carry.
     */
    public static Integer getInventorySize() {
        return inventorySize;
    }

    /**
     * Sets the maximum size of the player's inventory.
     *
     * @param inventorySize The maximum number of items the player can carry.
     */
    public static void setInventorySize(Integer inventorySize) {
        Player.inventorySize = inventorySize;
    }

    /**
     * Retrieves the list of items currently in the player's inventory.
     *
     * @return A List containing names of items in the player's inventory.
     */
    public static List<String> getInventory() {
        return inventory;
    }

    /**
     * Sets the list of items in the player's inventory.
     *
     * @param inventory A List containing names of items to be added to the player's inventory.
     */
    public static void setInventory(List<String> inventory) {
        Player.inventory = inventory;
    }
}