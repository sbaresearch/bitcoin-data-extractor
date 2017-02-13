package thesis.model;

public abstract class Entity {

    public String getDisplayName() {
        return this.getClass().getSimpleName();
    }
}
