import java.util.List;
import java.util.Objects;

public class State {

    private Valve valve;
    private int minute;
    private List<String> openValves;
    private int nrOfOtherPlayers;

    public State(Valve valve, int minute, List<String> openValves, int nrOfOtherPlayers) {
        this.valve = valve;
        this.minute = minute;
        this.openValves = openValves;
        this.nrOfOtherPlayers = nrOfOtherPlayers;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return minute == state.minute && nrOfOtherPlayers == state.nrOfOtherPlayers && valve.equals(state.valve) && openValves.equals(state.openValves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valve, minute, openValves, nrOfOtherPlayers);
    }

    @Override
    public String toString() {
        return "StateNew{" +
                "valve=" + valve +
                ", minute=" + minute +
                ", openValves=" + openValves +
                ", nrOfOtherPlayers=" + nrOfOtherPlayers +
                '}';
    }
}
