import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class UtilityMethods {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private FilePaths filePaths;
    private int[] result;

    Thread[] pool = new Thread[NUM_THREADS];

    public UtilityMethods(String[] args) {
        this.filePaths = new FilePaths(args);
        this.result = new int[2]; // 0 - четные, 1 - нечетные
    }

    public void algorithm(ExecutorService executor, String[] args) throws IOException, InterruptedException {
        String inputFileContent = FileReader.readFile(filePaths.getInputPath()[0]);
        String[] lines = inputFileContent.split("\n");

        int chunkSize = lines.length / NUM_THREADS;
        //  int[] chunks = new int[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end = (i == NUM_THREADS - 1) ? lines.length : (i + 1) * chunkSize;
            Runnable task = new MultiThreading(lines, start, end, result);
            pool[i] = new Thread(task);
            pool[i].start();
        }

        // Создаем и запускаем потоки
        for (int i = 0; i < NUM_THREADS; i++) {
            pool[i].join();
        }

        // Ожидаем завершения всех потоков
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        // Выводим результаты
        System.out.println(STR."Четных чисел: \{result[0]}");
        System.out.println(STR."Нечетных чисел: \{result[1]}");

        // Записываем результат в выходной файл
        FileWriter.writeFile(filePaths.getOutputPath()[0],
                STR."""
Четных чисел: \{result[0]}
Нечетных чисел: \{result[1]}""");
    }
}

