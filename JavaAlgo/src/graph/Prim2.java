package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Edge_prim implements Comparable<Edge_prim>
{
    int weight;
    char from;
    char to;
    Edge_prim (int weight, char from, char to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return this.weight +" : "+this.from + " - " + this.to;
    }

    @Override
    public int compareTo(Edge_prim o) {
        return this.weight < o.weight ? -1 : 1;
    }
}

public class Prim2 {
    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {

    }

}
