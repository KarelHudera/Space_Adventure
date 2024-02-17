public enum Commands {
    goTo("goTo"),
    whereAmI("whereAmI"),
    exit("exit");

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
