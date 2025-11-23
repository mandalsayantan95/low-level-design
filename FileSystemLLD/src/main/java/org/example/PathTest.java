package src.main.java.org.example;

public class PathTest {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder home = new Folder("home");
        Folder user = new Folder("user");
        File doc = new File("doc.txt", 10);

        root.addItem(home);
        home.addItem(user);
        user.addItem(doc);

        // Test getPath
        System.out.println("Testing getPath()...");
        String expectedPath = "/root/home/user/doc.txt";
        String actualPath = doc.getPath();
        if (expectedPath.equals(actualPath)) {
            System.out.println("PASS: getPath() -> " + actualPath);
        } else {
            System.out.println("FAIL: getPath() -> " + actualPath + ", expected: " + expectedPath);
        }

        // Test resolve
        System.out.println("\nTesting resolve()...");
        FileSystemItem resolvedItem = root.resolve("home/user/doc.txt");
        if (resolvedItem == doc) {
            System.out.println("PASS: resolve('home/user/doc.txt') found correct item.");
        } else {
            System.out.println("FAIL: resolve('home/user/doc.txt') returned: " + resolvedItem);
        }

        // Test resolve with ..
        System.out.println("\nTesting resolve() with .. ...");
        FileSystemItem resolvedParent = user.resolve("../");
        if (resolvedParent == home) {
            System.out.println("PASS: resolve('../') from user found home.");
        } else {
            System.out.println("FAIL: resolve('../') from user returned: " + resolvedParent);
        }

        // Test resolve invalid
        System.out.println("\nTesting resolve() invalid...");
        FileSystemItem invalid = root.resolve("home/invalid");
        if (invalid == null) {
            System.out.println("PASS: resolve('home/invalid') returned null.");
        } else {
            System.out.println("FAIL: resolve('home/invalid') returned: " + invalid);
        }
    }
}
