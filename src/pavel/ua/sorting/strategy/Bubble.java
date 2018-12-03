package pavel.ua.sorting.strategy;

import pavel.ua.sorting.Algorithm;

public class Bubble extends AbstractSortingStrategy {
    public Bubble() {
        this(0);
    }

    public Bubble(int threshhold) {
        i = threshhold;
    }

    public void calculate(int[] array) {
        if (i < array.length) {
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            i++;
        }
        Algorithm.progressBackward(i);
    }
}
