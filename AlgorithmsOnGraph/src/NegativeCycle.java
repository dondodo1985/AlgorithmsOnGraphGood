import java.util.ArrayList;
import java.util.Scanner;


public class NegativeCycle {
	
    public static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int V = adj.length;
        int[] dist = new int[V];
        // BZ: run the Bellman-Ford algorithm with distances initialized to zero.
        for (int i = 1; i < V; i++)    dist[i] = 0;//0Integer.MAX_VALUE;
        for (int i = 1; i < V; i++) {  // BZ: repeat |V| - 1 times.
            for (int u = 0; u < V; u++)  // Relax on each edge.
                relax(u, adj[u], cost[u], dist);
        }
        for (int i = 0; i < V; i++) //System.out.print(i + ": " + dist[i] + "\t");
        //System.out.println();
        // The |V|-th iteration: above Bellman-Ford run guarantees
        // shortest distances if graph doesn't contain negative weight cycle.
        // But there's a cycle if we get a shorter path this time.
        for (int u = 0; u < V; u++) {
            if (relax(u, adj[u], cost[u], dist)) return 1;
        }
        return 0;
    }

    private static boolean relax(int u, ArrayList<Integer> adjU, ArrayList<Integer> costU, int[] dist) {
        // Relax all edges coming out from u.
        boolean distChanged = false;
        for (int i = 0; i < adjU.size(); i++) {
            int v = adjU.get(i);
            if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + costU.get(i)) {
                dist[v] = dist[u] + costU.get(i);
                distChanged = true;
            }
        }
        return distChanged;
    }
    
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
        scanner.close();
    }
}

