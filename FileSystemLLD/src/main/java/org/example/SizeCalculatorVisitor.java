package src.main.java.org.example;

public class SizeCalculatorVisitor implements FileSystemVisitor {
    private int totalSize = 0;

    @Override
    public void visit(File file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(Folder folder) {
        for (FileSystemItem child : folder.getChildren()) {
            child.accept(this);
        }
    }

    public int getTotalSize() {
        return totalSize;
    }
}
