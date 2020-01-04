/**
 * LevelNodes
 */
import java.util.*;

public class LevelNodes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        List<List<Integer>> adjLists = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            adjLists.add(i, new ArrayList<>());
        }
        for (int i = 0; i < nodes-1; i++) {
            int src = sc.nextInt();
            int des = sc.nextInt();
            adjLists.get(src-1).add(des-1);
            adjLists.get(des-1).add(src-1);
        }
        
          //for(List list:adjLists){ System.out.println(list); }
         
        int level = sc.nextInt();
        System.out.println(getNumberofNodes(adjLists, nodes, level));
    }

    public static int getNumberofNodes(List<List<Integer>> adjList, int nodes, int level) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        boolean visited[] = new boolean[nodes];
        visited[0] = true;
        int totelnodes[] = new int[nodes];
    
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int x : adjList.get(v)) {
                if (!visited[x]) {
                    q.add(x);
                    visited[x] = true;
                    totelnodes[x] = totelnodes[v] + 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i <nodes-1; i++) {
            if (level-1 == totelnodes[i]) {
                count++;
            }
        }
        return count;
    }
}
