package src.main.java.org.example;

import java.util.*;

public class Folder implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();
    private final java.util.concurrent.locks.ReadWriteLock lock = new java.util.concurrent.locks.ReentrantReadWriteLock();
    private Folder parent;

    public Folder(String name) {
        this.name = name;
    }

    public void addItem(FileSystemItem item) {
        lock.writeLock().lock();
        try {
            item.setParent(this);
            children.add(item);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeItem(FileSystemItem item) {
        lock.writeLock().lock();
        try {
            children.remove(item);
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

    public FileSystemItem resolve(String path) {
        if (path == null || path.isEmpty() || path.equals(".")) {
            return this;
        }

        String[] parts = path.split("/", 2);
        String currentPart = parts[0];
        String remainingPart = parts.length > 1 ? parts[1] : "";

        if (currentPart.equals("..")) {
            return parent != null ? parent.resolve(remainingPart) : null;
        }

        lock.readLock().lock();
        try {
            for (FileSystemItem item : children) {
                if (item.getName().equals(currentPart)) {
                    if (remainingPart.isEmpty()) {
                        return item;
                    } else if (item instanceof Folder) {
                        return ((Folder) item).resolve(remainingPart);
                    } else {
                        return null; // File cannot have children
                    }
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return null; // Not found
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    public List<FileSystemItem> getChildren() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(children);
        } finally {
            lock.readLock().unlock();
        }
    }
}
