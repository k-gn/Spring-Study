package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 친구 네트워크
public class basic08 {

    public static int[] parent; // 각각의 노드가 가리키는 부모노드 
    public static int[] level; // 각각의 노드들의 네트워크 크기

    public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 노드의 부모값을 찾아 올라감
    }

    public static int union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parent[y] = x;
            level[x] += level[y];
        }
        return level[x];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());
            parent = new int[n * 2];
            level = new int[n * 2];

            for (int i = 0; i < n * 2; i++) {
                parent[i] = i;
            }
            Arrays.fill(level, 1);

            Map<String, Integer> map = new HashMap<>();
            int idx = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!map.containsKey(a)) {
                    map.put(a, idx++);
                }

                if (!map.containsKey(b)) {
                    map.put(b, idx++);
                }
                sb.append(union(map.get(a), map.get(b)) + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}
