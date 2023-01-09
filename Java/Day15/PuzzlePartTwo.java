
import Model.Device;
import Model.Range;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartTwo {

    private static final int RANGE = 4000000;
    private static int LINE = 0;
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

            for(int i = 0 ; i < allSensor.size() ; i++) {
                System.out.println(allSensor.get(i) + " : " + allBeacon.get(i));
            }


        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }

        boolean test = true;

        System.out.println("-----SCAN ON-----");
        while(test && LINE <= RANGE) {
            /*System.out.println(LINE);*/
            List<Range> ranges = new ArrayList<>();
            for (int i = 0; i < allSensor.size(); i++) {
                int distance = manhattanDistance(
                        allSensor.get(i).getX(),
                        allSensor.get(i).getY(),
                        allBeacon.get(i).getX(),
                        allBeacon.get(i).getY());

                ranges.add(manhattan(allSensor.get(i).getX(), allSensor.get(i).getY(), distance));

            }

            ranges = ranges.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingInt(r -> r.getMin()))
                    .toList();

            Stack<Range> combined = new Stack<>();

            // Merge ranges
            for(Range current: ranges) {
                if( combined.empty() || combined.peek().getMax() < current.getMin()) {
                    combined.push(current);
                }
                else {
                    combined.peek().setMax(Math.max(combined.peek().getMax(), current.getMax()));
                }
            }

            /*System.out.println(combined);*/
            combined.get(0).setMin(Math.max(0, combined.get(0).getMin()));
            combined.peek().setMax(Math.min(RANGE, combined.peek().getMax()));

            long total = combined.stream()
                    .mapToInt(Range::size)
                    .sum();


            if(total != RANGE+1) {
                for( int ind=0; ind<combined.size()-1; ind++ ) {
                    if(combined.get(ind).getMax() < combined.get(ind + 1).getMin()) {
                        System.out.println("On row "+LINE+" gap is "+ combined.get(ind).getMax() +" - "+ combined.get(ind + 1).getMin());
                        System.out.println("Only possible position for the distress beacon is: " + (combined.get(ind).getMax() + 1));
                        System.out.println("Tuning Frequency = "+(4000000L*(combined.get(ind).getMax() + 1) + LINE));
                    }
                }
                break;
            }

            LINE++;

        }


    }

    private static Range manhattan(int x0, int y0, int radius) {

        if(LINE > y0 - radius && LINE < y0 + radius)
            return new Range(x0 - (radius - Math.abs(y0-LINE)),
                    x0 + (radius - Math.abs(y0-LINE)));

        return null;

    }

    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

