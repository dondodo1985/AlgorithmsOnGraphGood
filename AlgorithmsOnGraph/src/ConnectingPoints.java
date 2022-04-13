import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {
	
	private static void makeSet(int i, Node[] nodes, int[] x,int[] y) {
		nodes[i] = new Node(x[i],y[i],i);
	}
	
	private static int Find(int i, Node[] nodes) {
		if(i!=nodes[i].parent) nodes[i].parent =  Find(nodes[i].parent,nodes);
		return nodes[i].parent;
	}
	
	private static void Union(int u, int v, Node[] nodes) {
		int m = Find(u,nodes);
		int n = Find(v,nodes);
		if(m != n) {
			if(nodes[m].rank > nodes[n].rank) 
				nodes[n].parent = m;
			else {
				nodes[m].parent = n;
				if(nodes[m].rank == nodes[n].rank)
					nodes[n].rank++;
			}
		}
	}
	
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        int V = x.length;
        
        Node[] nodes = new Node [V];
        for(int i=0; i<V;i++) {
        	makeSet(i,nodes,x,y);
        }
                     
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new EdgeComparator());
        for (int i=0; i<V;i++) {
        	for(int j =i+1;j<V;j++) {
        		Edge e = new Edge(i,j,x,y);
        		pq.add(e);        	}
        	}
        while(!pq.isEmpty()) {
        	Edge e = pq.poll();
        	int u = e.u;
        	int v = e.v;
        	if(Find(u,nodes) != Find(v,nodes)) {
        		result += e.dist;
        		//System.out.println(result);
        		Union(u,v,nodes);
        	}
        }
        return result;		
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
        scanner.close();
    }
}

	class Edge {
		int u;
		int v;
		double dist;
		public Edge(int u,int v,int[]x, int []y) {
			this.u = u;
			this.v = v;
			
			this.dist = Math.sqrt(Math.pow(x[u]- x[v],2)+Math.pow(y[u]-y[v], 2));
		}
		
	}

	 class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge e1,Edge e2) {
			return e1.dist< e2.dist ?-1:1;
		}
		
	}
	 
	 class Node{
		 int x;
		 int y;
		 int parent;
		 int rank;
		 
		 public Node(int x,int y,int z) {
			 this.x = x;
			 this.y = y;
			 this.parent = z;
			 rank = 0;
		 }
	 }


