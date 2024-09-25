public class Calculator implements Runnable {
    private int start;
    private int end;
    private double sum = 0.0;


    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;

    }

    @Override
    public void run() {
        for (double i = start; i < end; i++) {
            sum += 1.0 /  (i * i);
        }
    }

    public double getSum() {
        return sum;
    }
}
