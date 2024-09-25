//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        int size = 10_000_000;
        Thread[] threads = new Thread[NUM_THREADS + 1];
        Calculator[] calculators = new Calculator[NUM_THREADS + 1];

        int partSize = size / NUM_THREADS;

        for (int i = 0; i <= NUM_THREADS; i++) {
            int start = i * partSize;
            int end = (i == NUM_THREADS - 1) ? size : start + partSize;
            calculators[i] = new Calculator(start + 1, end);
            threads[i] = new Thread(calculators[i]);
            threads[i].start();
        }

        for(int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        double sum = 0.0;

        for(Calculator tasks: calculators) {
            sum+=tasks.getSum();
        }

        System.out.println("Сумма ряда " + sum);
    }
}