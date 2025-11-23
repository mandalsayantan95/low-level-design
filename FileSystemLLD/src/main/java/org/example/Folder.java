package src.main.java.org.example;

import java.util.*;

public class Folder implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();
    private final java.util.concurrent.locks.ReadWriteLock lock = new java.util.concurrent.locks.ReentrantReadWriteLock();

    public Folder(String name) {
        this.name = name;
    }

    public void addItem(FileSystemItem item) {
        lock.writeLock().lock();
        try {
            children.add(item);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int getSize() {
        lock.readLock().lock();
        try {
            int total = 0;
            for (FileSystemItem item : children) {
                total += item.getSize();
            }
            return total;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void printStructure(String indent) {
        lock.readLock().lock();
        try {
            System.out.println(indent + "+ " + name + "/");
            for (FileSystemItem item : children) {
                item.printStructure(indent + "  ");
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void delete() {
        lock.writeLock().lock();
        try {
            for (FileSystemItem item : children) {
                item.delete();
            }
            children.clear(); // Actually remove children from the list
            System.out.println("Deleting folder: " + name);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
