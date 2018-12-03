package pavel.ua.sorting.strategy;

abstract class AbstractSortingStrategy implements ISortingStrategy {
    protected static int i = 0;
    public int getI() {
        return i;
    };

    public void refresh() {
        i = 0;
    };
}
