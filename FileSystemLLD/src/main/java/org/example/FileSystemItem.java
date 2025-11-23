package src.main.java.org.example;

public interface FileSystemItem {
    int getSize();

    void printStructure(String indent);

    void delete();

    // New methods for Path Resolution & Parent Pointers
    void setParent(Folder parent);

    Folder getParent();

    String getName();

    String getPath();

    default void move(Folder targetFolder) {
        if (getParent() != null) {
            getParent().removeItem(this);
        }
        targetFolder.addItem(this);
    }
}
