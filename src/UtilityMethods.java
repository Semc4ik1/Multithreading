import java.io.IOException;

public class UtilityMethods {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private FilePaths filePaths;
    private int[] result;

    Thread[] pool = new Thread[NUM_THREADS];

    public UtilityMethods(String[] args) {
        this.filePaths = new FilePaths(args);
        this.result = new int[2];
    }

    public void algorithm() throws IOException, InterruptedException {
        String inputFileContent = FileReader.readFile(filePaths.getInputPath()[0]);
        String[] lines = inputFileContent.split("\n");

        int chunkSize = lines.length / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end = (i == NUM_THREADS - 1) ? lines.length : (i + 1) * chunkSize;
            Runnable task = new MultiThreading(lines, start, end, result);
            pool[i] = new Thread(task);
            pool[i].start();
        }


        for (int i = 0; i < NUM_THREADS; i++) {
            pool[i].join();
        }

        System.out.println(STR."Четных чисел: \{result[0]}");
        System.out.println(STR."Нечетных чисел: \{result[1]}");


        FileWriter.writeFile(filePaths.getOutputPath()[0],
                STR."""
Четных чисел: \{result[0]}
Нечетных чисел: \{result[1]}""");
    }
}

