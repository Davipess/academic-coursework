package Content;

public record TagClass(String name) implements Tag {

    @Override
    public int compareTo(Tag other) {
        return (this.name()).compareToIgnoreCase(other.name());
    }
}