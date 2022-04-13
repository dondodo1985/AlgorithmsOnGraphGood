import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	
	private static class AdjListNode{
		int vertex,weight;
		AdjListNode(int v,int w){
			this.vertex =  v;
			this.weight = w;
		}
		int getVertex(){return this.vertex;}
		int getWeight() {return this.weight;}
	}
	
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	int V = adj.length;
    	ArrayList<AdjListNode> [] graph =
    			(ArrayList<AdjListNode> [])new ArrayList[V];
    	//graph  
    	int [] k= new int [V];
    	for (int i = 0; i < V; i++) {
    		graph[i] = new ArrayList<AdjListNode>();
    		for (int j=0;j<adj[i].size();j++)
    				graph[i].add(new AdjListNode(adj[i].get(j),
    						cost[i].get(j)));
        }
    	   	
    	int [] dist = new int [V];
    	for(int i=0;i<V;i++) {
    		dist[i] = Integer.MAX_VALUE;
    	}
    	dist[s]=0;
    	
    	PriorityQueue<AdjListNode> pq = new PriorityQueue<>(
    			(v1,v2)-> v1.getWeight()-v2.getWeight());
	    pq.add(new AdjListNode(s,0));
	    while(!pq.isEmpty()) {
	    	// extract min
	    	AdjListNode u = pq.poll();
	    	for(AdjListNode v: graph[u.getVertex()]) {
	    		if(dist[v.getVertex()]>dist[u.getVertex()]+v.getWeight()) {
	    			dist[v.getVertex()] = dist[u.getVertex()]+v.weight;
	    			pq.add(new AdjListNode(v.getVertex(),dist[v.getVertex()]));
	    		}
	       	}
	    }
	    if(dist[t] == Integer.MAX_VALUE) return -1;
        return dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
        scanner.close();
    }
}

