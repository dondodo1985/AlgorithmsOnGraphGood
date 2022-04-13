import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
    	int V=adj.length;
        int used[] = new int[V];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        for (int i=0;i<V;i++) {
        	order= (ArrayList<Integer>)adj[i];
        	for(int node :order) {
        		used[node]++;
        	}
        }
        Queue<Integer> q=new LinkedList<Integer>();
        for(int i=0;i<V;i++) {
        	if(used[i]==0) q.add(i);
        }
        
        int count = 0;
        Vector<Integer> topOrder = new Vector<Integer>();
        while(!q.isEmpty()) {
        	int u = q.poll();
        	topOrder.add(u);
        	for(int node: adj[u]) {
        		if(--used[node] == 0) q.add(node);
        	}
        	count++;
        }
        
        if(count != V) return null;
        ArrayList<Integer>result= new ArrayList<Integer>();
        for (int i=0;i<topOrder.size();i++) {
        	result.add(topOrder.get(i));
        }
        return result;
    }

   // private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
   // }

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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
        scanner.close();
        }
}

