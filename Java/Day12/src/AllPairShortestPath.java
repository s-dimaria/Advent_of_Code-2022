import java.lang.*;
import java.util.*;

class AllPairShortestPath {
    final static int INF = 9999;

    void floydWarshall(ArrayList<Node> arr, char map[][], int dist[][], Node start, Node end)
    {

        int V = map.length * map[0].length;
        int i, j, k;

		/* Add all vertices one by one
		to the set of intermediate
		vertices.
		---> Before start of an iteration,
			we have shortest
			distances between all pairs
			of vertices such that
			the shortest distances consider
			only the vertices in
			set {0, 1, 2, .. k-1} as
			intermediate vertices.
		----> After the end of an iteration,
				vertex no. k is added
				to the set of intermediate
				vertices and the set
				becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            System.out.println("Processing row " + (k + 1) + " of (" + V + ")");
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        // printSolution(dist, V);

        int res1 = 0, res2 = 9999;
        for (i = 0; i < dist.length; ++i) {
            for (j = 0; j < dist[i].length; ++j) {

                if(start.equals(arr.get(i)) && end.equals(arr.get(j)))
                    res1 = dist[i][j];

                if('a' == map[arr.get(i).getX()][arr.get(i).getY()] && end.equals(arr.get(j)))
                    res2 = Math.min(res2,dist[i][j]);

            }

        }

        System.out.println("Solution from start: " + res1 + "\nSolution from start as low as possibile: " + res2);

    }

    void printSolution(int dist[][], int V)
    {
        System.out.println(
                "The following matrix shows the shortest "
                        + "distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

}

