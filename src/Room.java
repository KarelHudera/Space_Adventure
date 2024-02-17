import java.util.*;

class Room {
    private final String name;
    private final String description;
    private final List<String> items;

    public Room(String name, String description, List<String> items) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    public void addItem(String item) {
        items.add(item);
    }
}
