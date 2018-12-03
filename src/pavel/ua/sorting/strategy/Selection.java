package pavel.ua.sorting.strategy;

import pavel.ua.sorting.Algorithm;

public class Selection extends AbstractSortingStrategy {
    public Selection() {
        this(0);
    }

    public Selection(int threshhold) {
        i = threshhold;
    }

    @Override
    public void calculate(int[] array) {
        if (i < array.length) {
            int max = array[0];
            int maxindex = 0;
            for (int j = 0; j < array.length - i; j++) {

                if (max < array[j]) {
                    max = array[j];
                    maxindex = j;
                }
            }
            int temp = array[array.length - i - 1];
            array[array.length - i - 1] = max;
            array[maxindex] = temp;

            i++;

            Algorithm.progressBackward(i);
        }
    }
}
