package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph01 {

    public static void main(String[] args) {

        List<List<Integer>> graph = new ArrayList<>();
        int n = 4; // 정점의 개수 == 노드 갯수
        int m = 5; // 간선의 개수

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) { // 간선의 갯수만큼 반복
            int n1 = (int) (Math.random() * 10) + 1;
            int n2 = (int) (Math.random() * 10) + 1; // 노드 2

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

    }
}
