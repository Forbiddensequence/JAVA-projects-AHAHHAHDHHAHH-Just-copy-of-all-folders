package Graph;

public class StartGraph {
    public static void main() {
        Graph G1 = new Graph();
        G1.addevertex("A");
        G1.addevertex("B");
        G1.addevertex("C");
        G1.addevertex("D");
        G1.addevertex("E");
        G1.addevertex("F");
        G1.addevertex("F");
        G1.insertedge("F", "A", 3);
        G1.deleteedge("F", "A");
        G1.insertedge("A", "A", 3);
        G1.insertedge("A", "B", 7);
        G1.insertedge("A", "C", 9);
        G1.insertedge("A", "F", 14);
        G1.insertedge("B", "D", 15);
        G1.insertedge("B", "C", 10);
        G1.insertedge("C", "F", 2);
        G1.insertedge("C", "D", 11);
        G1.insertedge("E", "F", 9);
        G1.insertedge("E", "D", 6);
        //G1.addevertex("H");
        //G1.addevertex("G");
        //G1.insertedge("H", "G", 10);

        G1.showGraph();

        if (G1.graphtravesing()) {
            for (int j = 0; j < G1.getGraphlength(); j++) {
                String currentvertex = (String) G1.getVertexes().get(j);
                double[] resultarray = G1.Dijkstra(currentvertex);
                for (int i = 0; i < resultarray.length; i++) {
                    System.out.println("Shortest way from " + currentvertex + " to " + G1.getVertexes().get(i) + " is equal to " + resultarray[i] + " ");
                }
            }
        }
    }
}
