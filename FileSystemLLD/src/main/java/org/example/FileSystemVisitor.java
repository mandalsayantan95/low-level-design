package src.main.java.org.example;

public interface FileSystemVisitor {
    void visit(File file);

    void visit(Folder folder);
}
