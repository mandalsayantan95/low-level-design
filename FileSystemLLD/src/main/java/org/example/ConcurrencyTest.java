package src.main.java.org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {
    public static void main(String[] args) throws InterruptedException {
        Folder root = new Folder("Root");
        int numThreads = 10;
        int numFilesPerThread = 100;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Writer threads: Add files
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < numFilesPerThread; j++) {
                    root.addItem(new File("File-" + Thread.currentThread().getId() + "-" + j, 1));
                }
            });
        }

        // Reader threads: Calculate size concurrently
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("Current Size: " + root.getSize());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        int expectedSize = numThreads * numFilesPerThread; // Each file is 1KB
        int actualSize = root.getSize();

        System.out.println("Expected Size: " + expectedSize);
        System.out.println("Actual Size: " + actualSize);

        if (expectedSize == actualSize) {
            System.out.println("SUCCESS: Thread safety verified.");
        } else {
            System.out.println("FAILURE: Size mismatch.");
        }
    }
}
