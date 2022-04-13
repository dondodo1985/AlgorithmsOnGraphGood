import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

public class StronglyConnectedTest {
	private static int Time;
	//public static ArrayList<Integer>[] adj;
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	int result =0;
    	int V = adj.length; 
    	int [] disc = new int[V];
    	int [] low = new int[V];
    	for(int i = 0; i < V;i++) {
    		 disc[i] = -1;
    		 low[i] = -1;
    	}
    	
    	boolean [] stackMember = new boolean [V];
    	Stack<Integer> st = new Stack<Integer>();
    	
    	for (int i=0; i < V;i++) {
    		if(disc[i] == -1)
    		result=SCCUtil(i,low,disc,stackMember, st,adj);
    	}
    	
        return result;
    }
    
    private static int SCCUtil(int u, int [] low,int []disc, boolean [] stackMember,Stack<Integer> st,ArrayList<Integer>[] adj) {
    	int result=0;
    	disc[u] = Time;
    	low[u] = Time;
    	Time +=1;
    	stackMember[u] = true;
    	st.push(u);
    	int n;
    	Iterator<Integer> i= adj[u].iterator();
    	while(i.hasNext()) {
    		n = i.next();
    		if (disc[n] == -1) {
    			SCCUtil(n,low, disc, stackMember, st,adj);
    			low[u] = Math.min(low[u], low[n]);
    		} else if(stackMember[n] == true) {
    			low[u] = Math.min(low[u], disc[n]);
    		}
    	}
    	int w = -1,count=1;
    	if(low[u] == disc[u]) {
    		if(w == u) count++;
    		while(w !=u) {
    			w = (int)st.pop();
    			System.out.print(w+1+" ");
    			result= w+1;
    			stackMember[w] =false;
    		}
    		System.out.println();
    	}
    	return count++;
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
        scanner.close();    }
}

