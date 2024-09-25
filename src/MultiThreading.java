public class MultiThreading implements Runnable {
    private String[] lines;
    private int start;
    private int end;
    private int[] result;

    public MultiThreading(String[] lines, int start, int end, int[] result) {
        this.lines = lines;
        this.start = start;
        this.end = end;
        this.result = result;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            String line = lines[i];
            String[] numbersInLine = line.trim().split("\\s+");

            for (String numberString : numbersInLine) {
                try {
                    int number = Integer.parseInt(numberString);
                    synchronized (MultiThreading.class) {
                        if (number % 2 == 0) {
                            result[0]++; // Увеличиваем счетчик четных
                        } else {
                            result[1]++; // Увеличиваем счетчик нечетных
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println(STR."Ошибка преобразования строки в число: \{numberString}");
                    // Здесь можно обработать ошибку по вашему усмотрению
                }
            }
        }
    }
}
