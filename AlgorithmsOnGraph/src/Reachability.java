import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
    	Stack<Integer> s = new Stack<Integer>();
    	Set<Integer> e = new HashSet<Integer>();
    	s.push(x); 
    	
    	while(!s.isEmpty()) {
    		
    		int vertex =  s.pop();
    		if(e.contains(vertex)) continue;
    		if(vertex==y) return 1;
    		e.add(vertex);
    		adj[vertex].forEach(s::push);
    		
    		}
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
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
        scanner.close();
    }
}

