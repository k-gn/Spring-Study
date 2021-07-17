package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int cost;
    Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
        if(this.cost < o.cost)
            return -1;
        else if(this.cost == o.cost)
            return 0;
        else
            return 1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", cost=" + cost +
                '}';
    }
}
public class Kruskal {
    public static int[] parent;
    public static ArrayList<Edge> edgeList;

    public static void union(int x, int y) {
        System.out.println("union ==========================");
        x = find(x);
        y = find(y);
        if(x != y)
            System.out.println("not same!");
            System.out.println("x : " + x);
            parent[y] = x;
    }

    public static int find(int x) {
        System.out.println("find =================================");
        System.out.println("x : " + x);
        if(parent[x] == x) {
            System.out.println("return x!");
            return x;
        }
        System.out.println("return parent[x] = find(parent[x])");
        return parent[x] = find(parent[x]); // 노드의 부모값을 찾아 올라감
    }
    public static boolean isSameParent(int x, int y) {
        System.out.println("isSameParent ==========================");
        System.out.println("x : " + x + ", y : " + y);
        x = find(x);
        y = find(y);
        System.out.println("f-x : " + x + ", f-y : " + y);
        if(x == y) return true;
        else return false;
    }
    public static void main(String[] args) {
        edgeList = new ArrayList<Edge>();
        edgeList.add(new Edge(1,4,4));
        edgeList.add(new Edge(1,2,6));
        edgeList.add(new Edge(2,3,5));
        edgeList.add(new Edge(2,4,3));
        edgeList.add(new Edge(2,5,7));
        edgeList.add(new Edge(2,6,8));
        edgeList.add(new Edge(3,6,8));
        edgeList.add(new Edge(4,5,9));
        edgeList.add(new Edge(5,6,11));

        parent = new int[7];
        for(int i = 1; i <= 6; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        System.out.println(edgeList);
        System.out.println(Arrays.toString(parent));

        int sum = 0;
        for(int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            if(!isSameParent(edge.v1, edge.v2)) {
                sum += edge.cost;
                union(edge.v1, edge.v2);
            }
            System.out.println(Arrays.toString(parent));
        }
        System.out.println(sum);
    }
}