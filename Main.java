/*
 * INSTRUCTION:
 *     This is a Java staring code for hw3_2.
 *     When you finish the development, download this file and and submit to Canvas
 *     according to the submission instructions.

 *     Please DO NOT change the name of the main class "Main"
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Abstract: Describe the main ideas of the program.
 * Name: Write your name
 * Date: MM/DD/YYYY
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main
{
    public static class Graph {
        int nodeCount;
        int edgeCount;
        List<Node> nodes = new ArrayList<Node>();
        List<Edge> edges = new ArrayList<Edge>();
        List<Path> paths = new ArrayList<Path>();

        Graph() {
            nodeCount = 0;
            edgeCount = 0;
            this.nodes = null;
            this.edges = null;
        }

        Graph(int nodeCount, int edgeCount) {
            this.nodeCount = nodeCount;
            this.edgeCount = edgeCount;
        }

        void addNode(Node node) {
            this.nodes.add(node);
        }

        Node getNode(Node node) {
            for (Node n : this.nodes) {
                if (n == node) {
                    return n;
                }
            }
            return null;
        }

        Node getNode(int value) {
            for (Node n : this.nodes) {
                if (n.value == value) {
                    return n;
                }
            }
            return null;
        }

        int getNodeValue(Node node) {
            for (Node n : this.nodes) {
                if (n == node) {
                    return n.value;
                }
            }
            return -1;
        }

        int getNodeValue(int value) {
            for (Node n : this.nodes) {
                if (n.value == value) {
                    return n.value;
                }
            }
            return -1;
        }

        void addEdge(Edge edge) {
            this.edges.add(edge);
        }

        Edge getEdge(Node node1, Node node2) {
            for (Edge edge : this.edges) {
                if (edge.node1 == node1 && edge.node2 == node2) {
                    return edge;
                }
            }
            return null;
        }

        Edge getEdge(int node1, int node2) {
            for (Edge edge : this.edges) {
                if (edge.node1.value == node1 && edge.node2.value == node2) {
                    return edge;
                }
            }
            return null;
        }

        int getWeight(Node node1, Node node2) {
            for (Edge edge : this.edges) {
                if (edge.node1 == node1 && edge.node2 == node2) {
                    return edge.getWeight();
                }
            }
            return -1;
        }

        int getWeight(int node1, int node2) {
            for (Edge edge : this.edges) {
                if (edge.node1.value == node1 && edge.node2.value == node2) {
                    return edge.getWeight();
                }
            }
            return -1;
        }

        void addPath(Node startNode, String nodes) {
            Path path = new Path(startNode, nodes, this);
            this.paths.add(path);
        }

        public String toString() {
            String nodes = "Nodes: ";
            for (Node node : this.nodes) {
                nodes += node.value + " ";
            }
            nodes += "\n";
            String edges = "Edges: ";
            for (Edge edge : this.edges) {
                edges += edge.node1.value + " " + edge.node2.value + " " + edge.weight + "\n";
            }
            edges += "\n";
            String paths = "Paths: ";
            for (Path path : this.paths) {
                paths += path.startNode.value + " " + path.totalWeight + "\n";
            }
            paths += "\n";
            return nodes + edges + paths;
        }
    }

    public static class Node {
        int value;
        Node() {
            this.value = -1;
        }
        Node(int value) {
            this.value = value;
        }
    }

    public static class Edge {
        Node node1;
        Node node2;
        int weight;
        Edge() {
            this.node1 = null;
            this.node2 = null;
            this.weight = 0;
        }
        Edge(Node node1, Node node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        int getWeight() {
            return this.weight;
        }
    }

    public static class Path {
        List<Node> nodes = new ArrayList<Node>();
        Node startNode;
        int totalWeight;
        Path() {
            this.totalWeight = 0;
        }

        Path(Node startNode) {
            this.startNode = startNode;
            this.totalWeight = 0;
        }

        Path(Node startNode, String nodes, Graph graph) {
            this.startNode = startNode;
            String [] nodeValues = nodes.split(" ");
            this.totalWeight = graph.getWeight(startNode.value, Integer.parseInt(nodeValues[0]));
            for (int i = 0; i < nodeValues.length - 1; i++) {
                Edge edge = graph.getEdge(Integer.parseInt(nodeValues[i]), Integer.parseInt(nodeValues[i+1]));
                this.addNodes(edge);
                totalWeight += edge.getWeight();
            }
            this.totalWeight = graph.getWeight(Integer.parseInt(nodeValues[(nodeValues.length - 1)]), startNode.value);
        }

        void addNodes(Edge edge) {
            if(this.startNode == null) {
                this.nodes.add(edge.node1);
            }
            this.nodes.add(edge.node2);
            this.totalWeight += edge.getWeight();
        }
    }

    public static List<String> Permute(Graph graph, List<Node> nodes, int startindex, List<String> paths) {
        int size = nodes.size();
        System.out.println("Size: " + size + " StartIndex: " + startindex);

        if (size == startindex + 1) {
            String path = "";
            for (int i = 0; i < size; i++) {
                System.out.print(nodes.get(i).value + "  ");
                path += nodes.get(i).value + " ";
            }
            paths.add(path);
            return paths;
        } else {
            for (int i = startindex; i < size; i++) {
                Node temp = nodes.get(i);
                nodes.set(i, nodes.get(startindex));
                nodes.set(startindex, temp);
                System.out.println("StartIndex: " + startindex + " i: " + i);

                Permute(graph, nodes, startindex + 1, paths);
                temp = nodes.get(i);
                nodes.set(i, nodes.get(startindex));
                nodes.set(startindex, temp);
            }
        }
        return paths;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String nodeCount = scanner.nextLine();
        String edgesCount = scanner.nextLine();

        int nodeCountInt = Integer.parseInt(nodeCount);
        int edgeCountInt = Integer.parseInt(edgesCount);
        Graph graph = new Graph(nodeCountInt, edgeCountInt);
        List <String> paths = new ArrayList<String>();

        for (int i = 0; i < edgeCountInt; i++) {
            String edge = scanner.nextLine();
            String[] edgeValues = edge.split(" ");
            int node1 = Integer.parseInt(edgeValues[0]);
            int node2 = Integer.parseInt(edgeValues[1]);
            int weight = Integer.parseInt(edgeValues[2]);
            if(graph.getNode(node1) == null) {
                graph.addNode(new Node(node1));
            }
            if(graph.getNode(node2) == null) {
                graph.addNode(new Node(node2));
            }
            graph.addEdge(new Edge(graph.getNode(node1), graph.getNode(node2), weight));
        }

        String startNode = scanner.nextLine();
        List<Node> perNodes = graph.nodes;
        perNodes.remove(Integer.parseInt(startNode));
        Permute(graph, perNodes, Integer.parseInt(startNode), paths);
        for (String path : paths) {
            graph.addPath(graph.getNode(Integer.parseInt(startNode)), path);
        }

        System.out.println("Graph: " + graph.nodeCount + " " + graph.edgeCount + " " + graph);
        System.out.println("Paths: " + paths);
    }
}

