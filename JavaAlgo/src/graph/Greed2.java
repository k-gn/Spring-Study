package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 배낭 문제
class Product implements Comparable {

    private int weight;
    private int value;

    public Product(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                "weight = " + weight +
                ", value = " + value +
                '}';
    }

    @Override
    public int compareTo(Object o) {

        double compare = (double) this.value / this.weight;
        if(o instanceof Product) {
            Product p = (Product) o;
            double pCompare = (double) p.getValue() / p.weight;
            if(compare > pCompare) {
                return -1;
            }else {
                return 1;
            }
        }
        return 0;
    }
}

public class Greed2 {

    public static List<Product> datas = new ArrayList<>();

    public Greed2() {
        datas.add(new Product(10, 10));
        datas.add(new Product(15, 12));
        datas.add(new Product(20, 10));
        datas.add(new Product(25, 8));
        datas.add(new Product(30, 5));

    }

    public void getMaxValue(List<Product> products, int capacity) {
        Collections.sort(products);

        List<List<Double>> details = new ArrayList<>();
        double totalValue = 0;

        for(Product p : products) {
            if(capacity - p.getWeight() >= 0) {
                capacity -= p.getWeight();
                totalValue += p.getValue();

                List<Double> doubleList = new ArrayList<>();
                doubleList.add((double) p.getWeight());
                doubleList.add((double) p.getValue());
                doubleList.add(1.0);
                details.add(doubleList);
            }else {
                double fraction = (double) capacity / p.getWeight();
                totalValue += p.getValue() * fraction;

                List<Double> doubleList = new ArrayList<>();
                doubleList.add((double) p.getWeight());
                doubleList.add((double) p.getValue());
                doubleList.add(fraction);
                details.add(doubleList);
                break;
            }
        }

        System.out.println("details : " + details);
        System.out.println("totalValue : " + totalValue);
    }

    public static void main(String[] args) {

        Greed2 greed = new Greed2();

        greed.getMaxValue(datas, 30);

    }
}
