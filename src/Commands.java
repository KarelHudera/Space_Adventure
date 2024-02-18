public enum Commands {
    help("help"),
    goTo("goTo"),
    whereAmI("whereAmI"),
    exit("exit"),
    map("map"),
    search("search"),
    pick("pick"),
    inventory("inventory"),
    startRocket("startRocket");

    private final String description;

    Commands(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
