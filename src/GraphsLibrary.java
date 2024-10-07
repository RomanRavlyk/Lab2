import java.util.ArrayList;
import java.util.LinkedList;

public class GraphsLibrary {
    public static void main(String[] args) {
        GraphNode node1 = new GraphNode("Node 1", 5);
        GraphNode node2 = new GraphNode("Node 2", 25);
        GraphNode node3 = new GraphNode("Node 3", 57);

        Graph graph = new Graph();

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        try {
            graph.createNewArc(node1, node2);
            graph.createNewArc(node2, node3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        graph.printGraph();
    }

    public static class Graph {
        public LinkedList <GraphNode> nodes = null;

        public Graph() {
            this.nodes = new LinkedList<>();
        }

        public Graph(final LinkedList <GraphNode> nodes) {
            this.nodes = nodes;
        }

        public void printGraph() {
            for (GraphNode node : nodes) {
                System.out.print("(Node: " + node.getName() + " Data: " + node.getNodeData() + " Neighbours: ");

                if (node.getNeighbourNodes().isEmpty()) {
                    System.out.print("None");
                } else {
                    for (GraphNode neighbor : node.getNeighbourNodes()) {
                        System.out.print(neighbor.getName() + " ");
                    }
                }

                System.out.print(") | ");
            }
            System.out.println();
        }

        public void addNode (GraphNode node){
            nodes.add(node);
        }

        public void removeNode (int index) {
            nodes.remove(index);
        }

        public void createNewArc (GraphNode node1, GraphNode node2) throws Exception {
            boolean findNode1 = false;
            boolean findNode2 = false;

            for (GraphNode node: nodes) {
                if (node.equals(node1)) {
                    findNode1 = true;
                }
                if ((node.equals(node2))) {
                    findNode2 = true;
                }
            }

            if (!findNode1 || ! findNode2) {
                throw new Exception("Node not in graph!");
            }

            node1.addNeighbour(node2);
            node2.addNeighbour(node1);
        }
    }

    public static class GraphNode {
        public String name = " ";
        public int data = 0;
        public ArrayList <GraphNode> neighbourNodes = null;

        public GraphNode(String name, int node) {
            this.name = name;
            this.data = node;
            this.neighbourNodes = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNodeData () {
            return this.data;
        }

        public void setNodeData(int node) {
            this.data = node;
        }

        public ArrayList <GraphNode> getNeighbourNodes() {
            return neighbourNodes;
        }

        public void addNeighbour(GraphNode Node) throws Exception {
            for (GraphNode node : neighbourNodes) {
                if (node.equals(Node)) {
                    throw new Exception("This arc already exists in the graph!");
                }
            }
            neighbourNodes.add(Node);
        }

    }
}
