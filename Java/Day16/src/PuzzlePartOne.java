import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartOne {

    private static final int time = 30;

    private static HashMap<State,Integer> cache = new HashMap<>();

    private static final List<Valve> valves = new ArrayList<>();


    public static void main(String[] args) {

        Valve start = null;
        {
            try {

                Scanner sc = new Scanner(new File("input.txt"));
                while(sc.hasNextLine()) {
                    String str = sc.nextLine();
                    str = str.replace("Valve ", "");
                    str = str.replace(" has flow rate=", "-");
                    str = str.replace(" tunnels lead to valves ", "");
                    str = str.replace(" tunnel leads to valve ", "");
                    str = str.replaceAll(" ", "");

                    StringTokenizer strTok = new StringTokenizer(str,";");

                    StringTokenizer strValve = new StringTokenizer(strTok.nextToken(),"-");

                    String valveName = strValve.nextToken();
                    int rate = Integer.parseInt(strValve.nextToken());

                    StringTokenizer strAdjValve = new StringTokenizer(strTok.nextToken(),",");

                    Valve v = new Valve(valveName,rate);

                    while(strAdjValve.hasMoreTokens())
                        v.addNeighbourName(strAdjValve.nextToken());

                    valves.add(v);

                    if(valveName.equals("AA"))
                        start = v;

                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for(Valve v : valves)
                System.out.println(v);

            System.out.println("===== CALCULATE MAX PRESSURE (WORKING ALONE) =====");
            System.out.println("Max Pressure released is: " + calcMaxPressure(start, time, new ArrayList<>(), valves));

        }
    }

    private static int calcMaxPressure(final Valve start, final int time, final List<String> opened, final List<Valve> valves) {

        if (time == 0) {
            return 0;
        }

        final State state = new State(start, time, opened, 0);

        if (cache.keySet().contains(state)) {
            return cache.get(state);
        }

        int max = 0;
        if (start.getRate() > 0 && !opened.contains(start.getName())) {
            opened.add(start.getName());

            final int val = (time - 1) * start.getRate()
                    + calcMaxPressure(start, time - 1, opened, valves);
            opened.remove(start.getName());
            max = val;
        }

        for (final String n : start.getNeighborsValves()) {
            final Valve neighbour = valves.stream().filter(v -> v.getName().equals(n)).findFirst()
                    .orElseThrow(NoSuchElementException::new);
            max = Math.max(max, calcMaxPressure(neighbour, time - 1, opened, valves));
        }
        cache.put(state, max);

        return max;

    }

}
