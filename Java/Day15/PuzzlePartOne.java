
import Model.Device;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartOne {

    private static final int LINE = 2000000;
    private static TreeMap<Integer,Character> finalLine = new TreeMap<>();

    private static List<Device> allSensor = new ArrayList<>();
    private static List<Device> allBeacon = new ArrayList<>();

    public static void main(String[] args) {

        boolean flag = false;
        try {

            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNextLine()) {
                String line = sc.nextLine().replaceAll("Sensor at ", "");
                line = line.replaceAll("closest beacon is at ", "");
                line = line.replaceAll("x=", "");
                line = line.replaceAll("y=", "");
                line = line.replaceAll(" ","");

                StringTokenizer strTok = new StringTokenizer(line, ":");

                while(strTok.hasMoreTokens()) {
                    String tok = strTok.nextToken();

                    StringTokenizer coords = new StringTokenizer(tok, ",");

                    int x = Integer.parseInt(coords.nextToken());
                    int y = Integer.parseInt(coords.nextToken());

                    flag = !flag ? allSensor.add(new Device(x, y)) : allBeacon.add(new Device(x, y));
                }

                flag = false;

            }

            for(int i = 0 ; i < allSensor.size(); i++) {
                System.out.println("Sensor " + allSensor.get(i) + " : Beacon " + allBeacon.get(i));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long time = System.currentTimeMillis();
        System.out.println("-----SCAN ON-----");
        for(int i = 0 ; i < allSensor.size() ; i++) {
            int distance =  manhattanDistance(
                    allSensor.get(i).getX(),
                    allSensor.get(i).getY(),
                    allBeacon.get(i).getX(),
                    allBeacon.get(i).getY());

            System.out.println("Sensor " + allSensor.get(i) + " -> radius " + distance);
            manhattan(allSensor.get(i).getX(), allSensor.get(i).getY(), distance);
        }


        long count = finalLine.size()
                -
                allBeacon.stream()
                .filter(i -> i.getY() == LINE).distinct().count();

        System.out.println("\nPosition where the scan not arrived in line " + LINE + " is: " + count);

        System.out.println("Time "+((System.currentTimeMillis()-time)/1000.0)+" seconds");

    }

    private static void manhattan(int x0, int y0, int radius) {

        if(LINE > y0 - radius && LINE < y0 + radius) {
            for (int x = x0 - radius; x <= x0 + radius; x++) {
                if (Math.abs(y0 - LINE) + Math.abs(x0 - x) <= radius) {
                    finalLine.put(x, '#');
                }
            }
        }
    }

    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

