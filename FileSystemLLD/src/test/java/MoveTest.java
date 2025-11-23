package src.test.java;

import src.main.java.org.example.File;
import src.main.java.org.example.Folder;

public class MoveTest {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder source = new Folder("source");
        Folder target = new Folder("target");
        File file = new File("file.txt", 100);

        root.addItem(source);
        root.addItem(target);
        source.addItem(file);

        System.out.println("Before Move:");
        System.out.println("File Path: " + file.getPath());
        System.out.println("Source Size: " + source.getSize());
        System.out.println("Target Size: " + target.getSize());

        // Perform Move
        System.out.println("\nMoving file.txt from source to target...");
        file.move(target);

        System.out.println("\nAfter Move:");
        System.out.println("File Path: " + file.getPath());
        System.out.println("Source Size: " + source.getSize());
        System.out.println("Target Size: " + target.getSize());

        // Verification
        boolean passed = true;
        if (!file.getPath().equals("/root/target/file.txt")) {
            System.out.println("FAIL: Path is incorrect.");
            passed = false;
        }
        if (source.getSize() != 0) {
            System.out.println("FAIL: Source size should be 0.");
            passed = false;
        }
        if (target.getSize() != 100) {
            System.out.println("FAIL: Target size should be 100.");
            passed = false;
        }

        if (passed) {
            System.out.println("\nSUCCESS: Move operation verified.");
        } else {
            System.out.println("\nFAILURE: Move operation failed.");
        }
    }
}
