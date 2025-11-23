package src.main.java.org.example;

public class File implements FileSystemItem {
    private final String name;
    private final int size;
    private Folder parent;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    }

    @Override
    public void delete() {
        System.out.println("Deleting file: " + name);
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public Folder getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return (parent != null ? parent.getPath() : "") + "/" + name;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
