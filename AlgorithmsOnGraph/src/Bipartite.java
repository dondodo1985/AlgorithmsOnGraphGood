import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
	private static class Pair{
		int first,second;
		Pair(int first,int second){
			this.first = first;
			this.second = second;
		}
	}
		
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
    	int V = adj.length;
		int [] dist = new int[V];
		Arrays.fill(dist, -1);
		Queue<Pair> queue = new LinkedList<Pair>();
		
		for (int i =0;i<V;i++) {
			if (dist[i] == -1) {
				queue.add(new Pair(i,0));
				dist[i] = 0;
				
				while(!queue.isEmpty()) {
					Pair p = queue.peek();
					queue.poll();
					
					int u = p.first;
					int c= p.second;
					
					for (int v: adj[u]) {
						if (dist[v] == c)
							return 0;
						if(dist[v] == -1) {
							dist[v] = (c == 1) ? 0 : 1;
							queue.add(new Pair(v, dist[v]));
						}
					}
				}
			}
		}
		return 1;
    }

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
        System.out.println(bipartite(adj));
        scanner.close();;
    }
}

