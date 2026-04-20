public interface Condition {
    public default boolean isMet() {
        return false;
    }
}
