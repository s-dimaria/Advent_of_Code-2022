package Model;

public class Range {
    private int min;
    private int max;
    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int size() {
        return 1+max-min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String toString() {
        return "Range is "+min+", "+max;
    }
}