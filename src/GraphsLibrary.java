import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;

public class GraphsLibrary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean orientedGraph = false;

        System.out.println("Is your graph oriented? yes or no: ");
        String userAnswer = scanner.nextLine();

        if (userAnswer.equals("yes")) {
            orientedGraph = true;
        } else if (!userAnswer.equals("no")) {
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
            int firstNodeIndex = scanner.nextInt();
            int secondNodeIndex = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter width for the arc from " + firstNodeIndex + " to " + secondNodeIndex + ": ");
            int lengthBetweenNodes = scanner.nextInt();
            scanner.nextLine();


            if (firstNodeIndex >= 0 && firstNodeIndex < graph.nodes.size() &&
                    secondNodeIndex >= 0 && secondNodeIndex < graph.nodes.size()) {

                GraphNode firstNode = graph.nodes.get(firstNodeIndex);
                GraphNode secondNode = graph.nodes.get(secondNodeIndex);

                try {
                    graph.createNewArc(firstNode, lengthBetweenNodes, secondNode);
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
        public LinkedList<GraphNode> nodes;

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

        public void addNode(GraphNode node){
            nodes.add(node);
        }

        public void removeNode(int index) {
            nodes.remove(index);
        }

        public void createNewArc(GraphNode firstNode, Integer lengthBetweenNodes,  GraphNode secondNode) throws Exception {
            boolean findFirstNode = false;
            boolean findSecondNode = false;

            for (GraphNode node: nodes) {
                if (node.equals(firstNode)) {
                    findFirstNode = true;
                }
                if ((node.equals(secondNode))) {
                    findSecondNode = true;
                }
            }

            if (!findFirstNode || !findSecondNode) {
                throw new Exception("Node not in graph!");
            }

            firstNode.addNeighbour(secondNode, lengthBetweenNodes);
            secondNode.addNeighbour(firstNode, lengthBetweenNodes);
        }
    }

    public static class OrientedGraph extends Graph {
        public void createNewArc(GraphNode firstNode, Integer lengthBetweenNodes, GraphNode secondNode) throws Exception {
            boolean findFirstNode = false;
            boolean findSecondNode = false;

            for (GraphNode node: nodes) {
                if (node.equals(firstNode)) {
                    findFirstNode = true;
                }
                if ((node.equals(secondNode))) {
                    findFirstNode = true;
                }
            }

            if (!findFirstNode || !findSecondNode) {
                throw new Exception("Node not in graph!");
            }

            firstNode.addNeighbour(secondNode, lengthBetweenNodes);
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
