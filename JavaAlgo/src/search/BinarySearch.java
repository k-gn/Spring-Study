package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    public boolean search(List<Integer> data, int value) {
        if(data.size() == 1 && value == data.get(0)) {
            return true;
        }
        if(data.size() == 1 && value != data.get(0)) {
            return false;
        }
        if(data.size() == 0) {
            return false;
        }

        int midIdx = data.size() / 2;

        if(data.get(midIdx) == value) {
            return true;
        }else {

            if(value > data.get(midIdx)) {
                return search(data.subList(midIdx, data.size()), value);
            }else {
                return search(data.subList(0, midIdx), value);
            }
        }
    }

    public static void main(String[] args) {

        List<Integer> sample = new ArrayList<>();

        sample.add(1);
        sample.add(5);
        sample.add(3);
        sample.add(10);
        sample.add(7);
        sample.add(6);
        sample.add(15);
        Collections.sort(sample);

        System.out.println(sample);

        BinarySearch binarySearch = new BinarySearch();
        boolean result = binarySearch.search(sample, 2);
        System.out.println("result : " + result);

    }
}
