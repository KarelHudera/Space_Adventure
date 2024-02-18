import java.util.*;

public class Room {
    private final String name;
    private final String description;
    private final List<String> items;

    public Room(String name, String description, List<String> items) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>(items);
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}