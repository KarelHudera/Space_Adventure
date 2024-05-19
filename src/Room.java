import java.util.*;

public record Room(String name, String description, List<String> items) {
    public Room(String name, String description, List<String> items) {
        this.name = name.toLowerCase();
        this.description = description;
        this.items = new ArrayList<>(items);
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    @Override
    public List<String> items() {
        return new ArrayList<>(items);
    }
}