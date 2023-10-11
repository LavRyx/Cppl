package Task10;

public class Candy implements Comparable<Candy> {
    public String name;
    public double price;
    public Candy(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public int compareTo(Candy other) {
        return Double.compare(other.price, this.price);
    }

    @Override
    public String toString() {
        return this.name + " - " + this.price;
    }
}
