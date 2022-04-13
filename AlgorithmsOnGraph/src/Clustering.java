import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
	
	private static void makeSet(int i, Nodes[] nodes, int[] x,int[] y) {
		nodes[i] = new Nodes(x[i],y[i],i);
	}
	
	private static int Find(int i, Nodes[] nodes) {
		if(i != nodes[i].parent) nodes[i].parent =  Find(nodes[i].parent,nodes);
		return nodes[i].parent;
	}
	
	private static void Union(int u, int v, Nodes[] nodes) {
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
	
	
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
    	double result = 0;
    	int V = x.length;
        
        Nodes[] nodes = new Nodes [V];
        for(int i=0; i<V;i++) {
        	makeSet(i,nodes,x,y);
        }
                     
        PriorityQueue<Edges> pq = new PriorityQueue<Edges>(new EdgeCompare());
        for (int i=0; i<V;i++) {
        	for(int j =i+1;j<V;j++) {
        		Edges e = new Edges(i,j,x,y);
        		pq.add(e);        	}
        	}
        while(!pq.isEmpty()) {
        	Edges e = pq.poll();
        	int u = e.u;
        	int v = e.v;
        	if(Find(u,nodes) != Find(v,nodes)) {
        		result ++;
        		Union(u,v,nodes);
        	}
        	if(result > V - k) return e.dist;
        }
        return -1;		
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
        scanner.close();   
        }
}

	class EdgeCompare implements Comparator<Edges> {
		@Override
		public int compare(Edges e1,Edges e2) {
			return e1.dist< e2.dist ?-1:1;
		}
		
	}
	 
	 class Nodes{
		 int x;
		 int y;
		 int parent;
		 int rank;
		 
		 public Nodes(int x,int y,int z) {
			 this.x = x;
			 this.y = y;
			 this.parent = z;
			 rank = 0;
		 }
	 }
	
	
	 class Edges {
			int u;
			int v;
			double dist;
			public Edges(int u,int v,int[]x, int []y) {
				this.u = u;
				this.v = v;
				
				this.dist = Math.sqrt(Math.pow(x[u]- x[v],2)+Math.pow(y[u]-y[v], 2));
			}
			
		}
