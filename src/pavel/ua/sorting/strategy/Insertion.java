package pavel.ua.sorting.strategy;

import pavel.ua.sorting.Algorithm;

public class Insertion extends AbstractSortingStrategy {
    public Insertion() {
        this(0);
    }

    public Insertion(int threshhold) {
        i = threshhold;
    }

    @Override
    public void calculate(int[] array) {
        if (i < array.length) {
            if (i == 0) {
                i++;
            }

            if (i < array.length) {
                for (int j = i; j > 0; j--) {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                }
            }

            i++;
        }
        Algorithm.progressForward(i);
    }
}
