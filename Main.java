import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Graph> graphs = new ArrayList<>();

        Graph g1 = new Graph(
            Arrays.asList("A", "B", "C", "D", "E"),
            Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
            )
        );

        Graph g2 = new Graph(
            Arrays.asList("A", "B", "C", "D"),
            Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
            )
        );

        graphs.add(g1);
        graphs.add(g2);


        int id = 1;
        for (Graph g : graphs) {
            System.out.println("Graph " + id++);
            runAlgorithms(g);
            System.out.println();
        }
    }

    public static void runAlgorithms(Graph g) {
        var prim = PrimAlgorithm.run(g);
        var kruskal = KruskalAlgorithm.run(g);

        System.out.println("Prim's Algorithm:");
        for (Edge e : prim.mstEdges)
            System.out.println("  " + e.from + " - " + e.to + " (" + e.weight + ")");
        System.out.println("Total cost: " + prim.totalCost);
        System.out.println("Operations: " + prim.operations);
        System.out.println("Time (ms): " + prim.timeMs);

        System.out.println();

        System.out.println("Kruskal's Algorithm:");
        for (Edge e : kruskal.mstEdges)
            System.out.println("  " + e.from + " - " + e.to + " (" + e.weight + ")");
        System.out.println("Total cost: " + kruskal.totalCost);
        System.out.println("Operations: " + kruskal.operations);
        System.out.println("Time (ms): " + kruskal.timeMs);

        if (prim.totalCost == kruskal.totalCost)
            System.out.println("\nBoth algorithms produce the same MST total cost.");
        else
            System.out.println("\nDifferent MST costs");
    }
}
