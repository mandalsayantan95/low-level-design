package src.main.java.org.example;

public class PrintVisitor implements FileSystemVisitor {
    private int indentLevel = 0;

    @Override
    public void visit(File file) {
        String indent = "  ".repeat(indentLevel);
        System.out.println(indent + "- " + file.getName() + " (" + file.getSize() + " KB)");
    }

    @Override
    public void visit(Folder folder) {
        String indent = "  ".repeat(indentLevel);
        System.out.println(indent + "+ " + folder.getName() + "/");

        indentLevel++;
        for (FileSystemItem child : folder.getChildren()) {
            child.accept(this);
        }
        indentLevel--;
    }
}
