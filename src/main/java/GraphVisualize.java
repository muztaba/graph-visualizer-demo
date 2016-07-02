import org.graphstream.graph.*;
import org.graphstream.graph.implementations.DefaultGraph;

/**
 * Created by seal on 7/1/16.
 */
public class GraphVisualize {
    public void display() {
        Graph _graph = new Graph();
        org.graphstream.graph.Graph graph = new DefaultGraph("Baler Graph");
        final int graphSize = 30;
        Node[] nodes = new Node[graphSize];

        _graph.fillNodeGraph(graph, nodes, graphSize);
        _graph.makeGraph(graph, nodes, graphSize);

        graph.display();

    }
}
