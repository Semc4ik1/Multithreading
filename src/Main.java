import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Пожалуйста, укажите два аргумента командной строки:");
            System.err.println("1. Путь к входному файлу");
            System.err.println("2. Путь к выходному файлу");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        UtilityMethods utilityMethods = new UtilityMethods(args);

        try {
            utilityMethods.algorithm();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }
}

