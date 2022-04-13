import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS {
	private static int distance(ArrayList<Integer>[] adj, int s, int t) {
		int result =-1;
		if (s == t) result=0;
		int[] dist = new int[adj.length];
		for(int i=0; i<dist.length;i++){
			dist[i]=Integer.MAX_VALUE;
		}
		dist[s] = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
	    queue.add(s);
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v: adj[u]) {
				if (dist[v] == Integer.MAX_VALUE) {
					queue.add(v);
                    dist[v] = dist[u] + 1;
				}
			}
		}
		
		if(dist[t] != Integer.MAX_VALUE) {
			result= dist[t];
		}
		
        return result;
	}
    //FLIGHT SHORTEST PATH
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
        scanner.close();
        }
}

