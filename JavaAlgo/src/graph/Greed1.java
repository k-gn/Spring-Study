package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// 동전 문제
public class Greed1 {

    public static List<Integer> coins = new ArrayList<>();

    public Greed1() {
        coins.add(500);
        coins.add(100);
        coins.add(50);
        coins.add(1);
    }

    public void minCoin(int value, List<Integer> coinList) {

        int totalCount = 0;
        List<List<Integer>> details = new ArrayList<>();
        Collections.sort(coins, Comparator.reverseOrder());

        for(Integer coin : coinList) {
            int count = value / coin;
            totalCount += count;
            value = value % coin;

            List<Integer> detail = new ArrayList<>();
            detail.add(coin);
            detail.add(count);
            details.add(detail);
        }

        System.out.println("details : " + details);
        System.out.println("totalCount : " + totalCount);
    }

    public static void main(String[] args) {

        Greed1 greed1 = new Greed1();
        greed1.minCoin(4720, coins);
    }
}
