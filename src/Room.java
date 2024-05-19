import java.util.*;

/**
 * The Room class represents a location in the game with a name, description, and items.
 */
public record Room(String name, String description, List<String> items) {

    /**
     * Constructs a new Room with the specified name, description, and initial items.
     *
     * @param name        The name of the room.
     * @param description The description of the room.
     * @param items       The initial items present in the room.
     */
    public Room(String name, String description, List<String> items) {
        this.name = name.toLowerCase();
        this.description = description;
        this.items = new ArrayList<>(items);
    }

    /**
     * Adds an item to the room's list of items.
     *
     * @param item The name of the item to add.
     */
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Removes an item from the room's list of items.
     *
     * @param item The name of the item to remove.
     */
    public void removeItem(String item) {
        items.remove(item);
    }

    /**
     * Returns a copy of the list of items in the room.
     *
     * @return A new ArrayList containing names of items in the room.
     */
    @Override
    public List<String> items() {
        return new ArrayList<>(items);
    }
}