import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.Scanner;

public class Acyclicity {
	//@SuppressWarnings("unchecked")
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	
    	int count=0;
    	int V=adj.length;
    	int [] in_degree = new int[V];
    	Arrays.fill(in_degree, 0);
    	for(int u=0;u<V;u++) {
    		for(int v: adj[u])
    			in_degree[v]++;
    	}
    	Queue<Integer> q = new LinkedList<Integer>();
    	for(int i=0;i<V;i++)
    		if(in_degree[i]==0)
    			q.add(i);
    	Vector<Integer>top_order = new Vector<>();
    	while(!q.isEmpty()) {
    		int u =q.poll();
    		top_order.add(u);
    		for(int itr: adj[u])
    			if(-- in_degree[itr]==0) q.add(itr);
    		count++;
    	}
    	if(count!=V) return 1;
    	else
        return 0;
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
            //add edge
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
        scanner.close();
    }
}

