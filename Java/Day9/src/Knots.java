public class Knots {

    private int x;
    private int y;

    public Knots(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Knots)) {
            return false;
        }

        Knots k = (Knots) obj;

        return k.getX() == x && k.getY() == y;
    }


    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Knots{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
