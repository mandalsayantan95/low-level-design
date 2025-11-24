package src.main.java.org.example;

public class CDTest {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder home = new Folder("home");
        Folder user = new Folder("user");
        Folder docs = new Folder("docs");

        root.addItem(home);
        home.addItem(user);
        user.addItem(docs);

        FileSystem fs = new FileSystem(root);

        System.out.println("Initial Path: " + fs.getCurrentPath());

        // Test 1: Relative Path
        System.out.println("\nTest 1: cd home/user");
        fs.cd("home/user");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root/home/user")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        // Test 2: Relative Path (One step)
        System.out.println("\nTest 2: cd docs");
        fs.cd("docs");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root/home/user/docs")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        // Test 3: Parent Path (..)
        System.out.println("\nTest 3: cd ..");
        fs.cd("..");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root/home/user")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        // Test 4: Absolute Path
        System.out.println("\nTest 4: cd /root/home");
        fs.cd("/root/home");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root/home")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        // Test 5: Root Path
        System.out.println("\nTest 5: cd /");
        fs.cd("/");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

        // Test 6: Invalid Path
        System.out.println("\nTest 6: cd invalid");
        fs.cd("invalid");
        System.out.println("Current Path: " + fs.getCurrentPath());
        if (fs.getCurrentPath().equals("/root")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}
