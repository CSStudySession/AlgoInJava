package TopologicalSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for Directed graph.
 */
public class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }

}
