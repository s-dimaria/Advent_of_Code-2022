import java.util.*;

public class Valve implements Comparable<Valve>{

    private String name;

    private int rate;

    private Set<String> neighborsValves = new HashSet<>();


    public Valve(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Set<String> getNeighborsValves() {
        return neighborsValves;
    }

    public void addNeighbourName(String neighbourName) {
        neighborsValves.add(neighbourName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Valve other = (Valve) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int compareTo(final Valve o) {
        return Integer.compare(this.rate, o.rate);
    }


    @Override
    public String toString() {
        return "Valve{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", neighborsValves=" + neighborsValves +
                '}';
    }
}
