package src.main.java.org.example;

public class FileSystem {
    private final Folder root;
    private Folder currentDirectory;

    public FileSystem(Folder root) {
        this.root = root;
        this.currentDirectory = root;
    }

    public void cd(String path) {
        if (path == null || path.isEmpty()) {
            return;
        }

        Folder startNode;
        String cleanPath;

        if (path.startsWith("/")) {
            startNode = root;
            cleanPath = path.substring(1);
            // If path includes root name (e.g., "root/home"), strip "root/"
            if (cleanPath.startsWith(root.getName() + "/")) {
                cleanPath = cleanPath.substring(root.getName().length() + 1);
            } else if (cleanPath.equals(root.getName())) {
                cleanPath = "";
            }
        } else {
            startNode = currentDirectory;
            cleanPath = path;
        }

        FileSystemItem target = startNode.resolve(cleanPath);

        if (target instanceof Folder) {
            currentDirectory = (Folder) target;
        } else if (target == null) {
            System.out.println("Error: Directory not found: " + path);
        } else {
            System.out.println("Error: Not a directory: " + path);
        }
    }

    public String getCurrentPath() {
        return currentDirectory.getPath();
    }
}
