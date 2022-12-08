import Classes.Directory;
import Classes.Files;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Long.parseLong;


public class PuzzlePartOne {

    static final long MAX_VALUE_SIZE = 100000;

    static List<Directory> dir = new ArrayList<>();

    static Directory cur;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));

        ConcurrentLinkedQueue<Object> list = new ConcurrentLinkedQueue<Object>();
        Directory root = new Directory("/", null, list);
        cur = root;
        dir.add(cur);

        while(sc.hasNextLine()) {
            command(sc);
        }

        sc.close();

        int sum = 0;

        for (Directory d: dir) {
            if(d.getSize() <= MAX_VALUE_SIZE){
                sum += d.getSize();
            }
        }

        System.out.println("By deleting the chosen directories you can save " + sum + " space.");

    }

    private static void command(Scanner sc) throws IOException {

        String command = sc.next();

        switch(command) {
            case "$": {
                String op = sc.next();
                //System.out.println("Operation: " + command + " " + op);
                if(op.equals("cd")){
                    String label = sc.next();

                    if(label.equals("..")) {
                        //System.out.println("Moving up to directory " + cur.parent.getDirName());
                        cur = cur.parent;
                    }
                    else {
                        for(Object obj: cur.getArrayList())
                            if(obj.getClass()==Directory.class)
                                if(((Directory) obj).getDirName().equals(label)) {
                                    //System.out.println("Moving in to directory " + ((Directory) obj).getDirName());
                                    cur = (Directory) obj;
                                    break;
                                }
                    }
                }
                break;
            }

            case "dir": {
                String dirName = sc.next();
                //System.out.println("New Directory found in " + cur.getDirName() + " called " + dirName);
                ConcurrentLinkedQueue<Object> list = new ConcurrentLinkedQueue<Object>();
                Directory d = new Directory(dirName, cur, list);
                cur.getArrayList().add(d);
                dir.add(d);
                break;
            }
            default: {
                //System.out.println("Found a file of size " + command + ", which will be added to directory " + cur.getDirName());
                long size = parseLong(command);
                String name = sc.next();
                Files f = new Files(name, size);
                cur.getArrayList().add(f);
                break;
            }
        }

    }

}
