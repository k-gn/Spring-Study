package graph;

import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.*;

class Nodes {
    public int val;
    LinkedList<Nodes> adjacent;
    boolean marked;
    public static int num = 0;

    Nodes() {
        this.val = ++num;
        this.marked = false;
        adjacent = new LinkedList<Nodes>();
    }
}

public class Graph01 {

    public List<Nodes> nodes;
    Graph01(){
        nodes = new ArrayList<>();
    }

    //두 노드의 관계를 저장
    void addEdge(int i1, int i2){
        Nodes n1 = nodes.get(i1);
        Nodes n2 = nodes.get(i2);

        //상대방이 있는지 확인하고 없으면 추가
        if(!n1.adjacent.contains(n2)){
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
    }

    public List<Integer> bfs(int index) {
        if(nodes.get(index) == null) return null;
        Nodes root = nodes.get(index);

        Queue<Nodes> queue = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();
        queue.offer(root);
        root.marked = true;
        visited.add(root.val);

        while(!queue.isEmpty()){
            Nodes r = queue.poll();
            for(Nodes n : r.adjacent){
                if(n.marked == false){
                    n.marked = true;
                    queue.offer(n);
                    visited.add(n.val);
                }
            }
        }
        return visited;
    }

    public void dfsR(int index){
        Nodes r = nodes.get(index);
        dfsR(r);
    }

    public void dfsR(Nodes node) {
        if(node == null) return;
        node.marked = true;
        System.out.print(node.val + " ");
        for(Nodes n : node.adjacent){
            if(n.marked == false){
                dfsR(n);
            }
        }
    }

    public static void main(String[] args) {

        Graph01 g = new Graph01();

        // 1, 2, 3, 4, 5, 6, 7, 8, 9
        for(int i=0; i<9; i++) {
            g.nodes.add(new Nodes());
        }

        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,5);
        g.addEdge(2,6);
        g.addEdge(3,7);
        g.addEdge(3,8);

//        System.out.println(g.bfs(0));
        g.dfsR(0);
    }
}
