package src.main.java.org.example;

public class VisitorTest {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder home = new Folder("home");
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);

        root.addItem(home);
        home.addItem(file1);
        root.addItem(file2);

        System.out.println("Testing PrintVisitor...");
        PrintVisitor printVisitor = new PrintVisitor();
        root.accept(printVisitor);

        System.out.println("\nTesting SizeCalculatorVisitor...");
        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);
        int size = sizeVisitor.getTotalSize();
        System.out.println("Total Size: " + size);

        if (size == 300) {
            System.out.println("SUCCESS: Size calculation correct.");
        } else {
            System.out.println("FAILURE: Size calculation incorrect. Expected 300, got " + size);
        }
    }
}
