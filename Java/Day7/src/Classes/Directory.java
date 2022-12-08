package Classes;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory{

    private String dirName;
    public Directory parent;
    private ConcurrentLinkedQueue<Object> listItems;

    public Directory(String dirName, Directory parent, ConcurrentLinkedQueue<Object> listItems) {
        this.dirName = dirName;
        this.parent = parent;
        this.listItems = listItems;
    }

    public void setArrayList(ConcurrentLinkedQueue<Object> listItems) {
        this.listItems = listItems;
    }

    public ConcurrentLinkedQueue<Object> getArrayList() { return this.listItems; }

    public String getDirName() {
        return dirName;
    }

    public long getSize() {
        long total = 0;
        for(Object obj : listItems) {
            if(obj.getClass()==Files.class)
                total += ((Files) obj).getFileSize();
        }

        for(Object obj : listItems) {
            if(obj.getClass()==Directory.class)
                total += ((Directory) obj).getSize();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "dirName='" + dirName + '\'' +
                ", listItems=" + listItems +
                '}';
    }
}
