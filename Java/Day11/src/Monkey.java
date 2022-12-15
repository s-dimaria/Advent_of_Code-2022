import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Monkey {

    private Deque<Long> items;

    private String operation;

    private int test;

    private String isTrue;

    private String isFalse;

    private int inspectsItem;


    public Monkey() {
        this.items = new LinkedBlockingDeque<>();
        this.operation = "";
        this.test = 0;
        this.isTrue = "";
        this.isFalse = "";
        this.inspectsItem = 0;
    }


    public int getInspectsItem() {
        return inspectsItem;
    }

    public void incrementInspectsItem() {
        this.inspectsItem++;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public String getIsFalse() {
        return isFalse;
    }

    public void setIsFalse(String isFalse) {
        this.isFalse = isFalse;
    }

    public Deque<Long> getItems() {
        return items;
    }

    public void setItems(Deque<Long> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                ", operation='" + operation + '\'' +
                ", test='" + test + '\'' +
                ", isTrue='" + isTrue + '\'' +
                ", isFalse='" + isFalse + '\'' +
                ", inspectsItem=" + inspectsItem +
                '}';
    }
}
