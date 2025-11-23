package org.example;

public interface FileSystemItem {
    int getSize();
    void printStructure(String indent);
    void delete();
}
