import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartTwo {

    public static final int time = 26;

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

            System.out.println("===== CALCULATE MAX PRESSURE (WORKING WITH MORE PLAYER) =====");
            System.out.println("Max Pressure released is: " + calcMaxPressure(start, time, new ArrayList<>(), valves, 1));


        }
    }

    private static int calcMaxPressure(final Valve start, final int time, final List<String> opened, final List<Valve> valves, final int nrOrMorePlayer) {

        if (time == 0) {
            final Valve aa = valves.stream().filter(f -> f.getName().equals("AA")).findFirst()
                    .orElseThrow(NoSuchElementException::new);
            return nrOrMorePlayer > 0 ? calcMaxPressure(aa, PuzzlePartTwo.time, opened, valves, nrOrMorePlayer - 1) : 0;
        }

        final State state = new State(start, time, opened, nrOrMorePlayer);

        if (cache.keySet().contains(state)) {
            return cache.get(state);
        }

        int max = 0;
        if (start.getRate() > 0 && !opened.contains(start.getName())) {
            opened.add(start.getName());

            Collections.sort(opened);
            final int val = (time - 1) * start.getRate()
                    + calcMaxPressure(start, time - 1, opened, valves, nrOrMorePlayer);
            opened.remove(start.getName());
            max = val;
        }

        for (final String n : start.getNeighborsValves()) {
            final Valve neighbour = valves.stream().filter(v -> v.getName().equals(n)).findFirst()
                    .orElseThrow(NoSuchElementException::new);
            max = Math.max(max, calcMaxPressure(neighbour, time - 1, opened, valves, nrOrMorePlayer));
        }
        cache.put(state, max);

        return max;

    }

}
