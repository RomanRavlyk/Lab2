import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;

public class GraphsLibrary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean orientedGraph = false;

        System.out.println("Is your graph oriented? yes or no: ");
        String answer = scanner.nextLine();

        if (answer.equals("yes")) {
            orientedGraph = true;
        } else if (!answer.equals("no")) {
            System.out.println("Invalid input, exiting...");
            System.exit(1);
        }

        System.out.println("How many nodes will it have?: ");
        int numberOfNodes = scanner.nextInt();
        scanner.nextLine();

        Graph graph;
        if (orientedGraph) {
            graph = new OrientedGraph();
        } else {
            graph = new Graph();
        }

        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println("Enter name for node " + (i + 1) + ": ");
            String nodeName = scanner.nextLine();
            System.out.println("Enter data for node " + (i + 1) + ": ");
            int nodeData = scanner.nextInt();
            scanner.nextLine();

            GraphNode node = new GraphNode(nodeName, nodeData);
            graph.addNode(node);
        }

        System.out.println("How many arcs will the graph have?: ");
        int numberOfArcs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfArcs; i++) {
            System.out.println("Enter indices of two nodes to connect by an arc (space separated): ");
            int nodeIndex1 = scanner.nextInt();
            int nodeIndex2 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter width for the arc from " + nodeIndex1 + " to " + nodeIndex2 + ": ");
            int width1 = scanner.nextInt();
            scanner.nextLine();

            int width2 = width1;
            if (!orientedGraph) {
                System.out.println("Enter width for the reverse arc from " + nodeIndex2 + " to " + nodeIndex1 + ": ");
                width2 = scanner.nextInt();
                scanner.nextLine();
            }

            if (nodeIndex1 >= 0 && nodeIndex1 < graph.nodes.size() &&
                    nodeIndex2 >= 0 && nodeIndex2 < graph.nodes.size()) {

                GraphNode node1 = graph.nodes.get(nodeIndex1);
                GraphNode node2 = graph.nodes.get(nodeIndex2);

                try {
                    graph.createNewArc(node1, width1, node2, width2);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid node indices!");
            }
        }

        graph.printGraph();
        scanner.close();
    }


    public static class Graph {
        public LinkedList <GraphNode> nodes;

        public Graph() {
            this.nodes = new LinkedList<>();
        }

        public Graph(final LinkedList <GraphNode> nodes) {
            this.nodes = nodes;
        }

        public void printGraph() {
            for (GraphNode node : nodes) {
                System.out.print("(Node: " + node.getName() + ", Data: " + node.getNodeData() + ", Neighbours: ");

                if (node.getNeighbourNodes().isEmpty()) {
                    System.out.print("None");
                } else {
                    for (Map.Entry<GraphNode, Integer> entry : node.getNeighbourNodes().entrySet()) {
                        System.out.print(entry.getKey().getName() + " (width: " + entry.getValue() + ") ");
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

        public void createNewArc(GraphNode node1, Integer width1,  GraphNode node2, Integer width2) throws Exception {
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

            node1.addNeighbour(node2, width2);
            node2.addNeighbour(node1, width1);
        }
    }

    public static class OrientedGraph extends Graph {
        public void createNewArc (GraphNode node1, Integer width1,  GraphNode node2, Integer width2) throws Exception {
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

            node1.addNeighbour(node2, width2 );
        }
    }

    public static class GraphNode {
        public String name;
        public int data;

        Map<GraphNode, Integer> neighbourNodes;

        public GraphNode(String name, int node) {
            this.name = name;
            this.data = node;
            this.neighbourNodes = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNodeData() {
            return this.data;
        }

        public void setNodeData(int node) {
            this.data = node;
        }

        public Map<GraphNode, Integer> getNeighbourNodes() {
            return neighbourNodes;
        }

        public void addNeighbour(GraphNode Node, Integer width) throws Exception {
            if (neighbourNodes.containsKey(Node)) {
                throw new Exception("This arc already exists in the graph!");
            }
            neighbourNodes.put(Node, width);
        }
    }
}
