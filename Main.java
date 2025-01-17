/*
 * INSTRUCTION:
 *     This is a Java staring code for hw3_2.
 *     When you finish the development, download this file and and submit to Canvas
 *     according to the submission instructions.

 *     Please DO NOT change the name of the main class "Main"
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Title: CST370-HW3_2_Java
 * Abstract: Program accepts a graph data structure type input from a user, and outputs the shortest
 * path from a given node through all other nodes and back to the starting node.
 * Name: Bryan Zanoli
 * Date: 9/17/2024
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main
{
    public static class Graph {
        int nodeCount;
        int edgeCount;
        int minPath;
        List<Node> nodes = new ArrayList<Node>();
        List<Edge> edges = new ArrayList<Edge>();
        List<Path> paths = new ArrayList<Path>();

        Graph() {
            this.nodeCount = 0;
            this.edgeCount = 0;
            this.minPath = -1;
        }

        Graph(int nodeCount, int edgeCount) {
            this.nodeCount = nodeCount;
            this.edgeCount = edgeCount;
            this.minPath = -1;
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

        void addPath(int startNode, String nodes) {
            Node sNode = this.getNode(startNode);
            Path path = new Path(sNode, nodes, this);
            this.paths.add(path);
            if(path.totalWeight < this.minPath || this.minPath == -1) {
                this.minPath = path.totalWeight;
            }
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
            if (this.paths.isEmpty()) {
                paths += "Path: " + "\n" + "Cost: -1" + "\n";
            } else {
                for (Path path : this.paths) {
                    paths += "Path:" + path.pathName + "\n" + "Cost:" + path.totalWeight + "\n";
                }
            }
            paths += "\n";
            return nodes + edges + paths;
        }

        public String printPaths() {
            String paths = "Path:";
            if(!this.paths.isEmpty()) {
                for (Path path : this.paths) {
                    if (path.totalWeight == this.minPath) {
                        paths += path.pathName + "\n";
                    }
                }
            } else {
                paths += "\n";
            }
            paths += "Cost:" + this.minPath;
            return paths;
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

        public Node copy() {
            return new Node(this.value);
        }

        public String toString() {
            return Integer.toString(this.value);
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

        String pathName;
        Path() {
            this.totalWeight = 0;
            this.pathName = "";
        }

        Path(Node startNode) {
            this.startNode = startNode;
            this.totalWeight = 0;
            this.pathName = "";
        }

        Path(Node startNode, String nodes, Graph graph) {
            String [] nodeValues = nodes.split(" ");
            List<Edge> edges = new ArrayList<Edge>();
            edges.add(graph.getEdge(startNode.value, Integer.parseInt(nodeValues[0])));
            for (int i = 0; i < nodeValues.length - 1; i++) {
                Edge edge = graph.getEdge(Integer.parseInt(nodeValues[i]), Integer.parseInt(nodeValues[i+1]));
                if(edge == null) {
                    break;
                } else {
                    edges.add(edge);
                }
            }
            edges.add(graph.getEdge(Integer.parseInt(nodeValues[(nodeValues.length - 1)]), startNode.value));
            this.startNode = startNode;
            this.pathName = "";
            for (int i = 0; i < edges.size(); i++) {
                this.addNodes(edges.get(i));
                if(i == 0) {
                    this.pathName += this.startNode.value + "->";
                    this.pathName += edges.get(i).node2.value + "->";
                } else if(i == edges.size() - 1) {
                    this.pathName += edges.get(i).node2.value;
                } else {
                    this.pathName += edges.get(i).node2.value + "->";
                }
            }
        }

        void addNodes(Edge edge) {
            if(this.startNode == null) {
                this.nodes.add(edge.node1);
            }
            this.nodes.add(edge.node2);
            this.totalWeight += edge.getWeight();
        }
    }

    public static boolean pathExists(int startNode, String nodes, Graph graph) {
        String [] nodeValues = nodes.split(" ");
        boolean pathExists = true;

        //Check all edges in the path
        Edge edge1 = graph.getEdge(startNode, Integer.parseInt(nodeValues[0]));
        if(edge1 == null) {
            pathExists = false;
            return pathExists;
        } else {
            for (int i = 0; i < nodeValues.length - 1 && pathExists; i++) {
                Edge edge = graph.getEdge(Integer.parseInt(nodeValues[i]),
                    Integer.parseInt(nodeValues[i + 1]));
                if (edge == null) {
                    pathExists = false;
                    break;
                }
            }
            Edge edgeEnd = graph.getEdge(Integer.parseInt(nodeValues[(nodeValues.length - 1)]), startNode);
            if(edgeEnd == null) {
                pathExists = false;
            }
        }
        return pathExists;
    }

    public static List<String> Permute(List<Node> nodes, int startindex, List<String> paths) {
        int size = nodes.size();

        if (size == startindex + 1) {
            String path = "";
            for (int i = 0; i < size; i++) {
                path += nodes.get(i).value + " ";
            }
            paths.add(path);
            return paths;
        } else {
            for (int i = startindex; i < size; i++) {
                Node temp = nodes.get(i);
                nodes.set(i, nodes.get(startindex));
                nodes.set(startindex, temp);

                Permute(nodes, startindex + 1, paths);
                temp = nodes.get(i);
                nodes.set(i, nodes.get(startindex));
                nodes.set(startindex, temp);
            }
        }
        return paths;
    }

    public static void main(String[] args) {

        //Open scanner to read in user input
        Scanner scanner = new Scanner(System.in);

        //Scan in first two lines which correspond to node count and number of edges
        String nodeCount = scanner.nextLine();
        String edgesCount = scanner.nextLine();

        int nodeCountInt = Integer.parseInt(nodeCount);
        int edgeCountInt = Integer.parseInt(edgesCount);

        //Create a new graph with node count and edge count, then add nodes and edges
        //to the graph from the user input
        Graph graph = new Graph(nodeCountInt, edgeCountInt);
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

        //Scan in the start node with is the final line of input
        String startNode = scanner.nextLine();
        if(!scanner.hasNextLine()) {
            scanner.close(); //close scanner as all lines of input have been read
        } else {
            System.out.println("Error: More input than expected");
        }
        int startNodeInt = Integer.parseInt(startNode);

        //Create a list of nodes that exclude the starting node
        List<Node> perNodes = new ArrayList<>(graph.nodes.size());
        for (Node node : graph.nodes) {
            if(node.value != startNodeInt) {
                perNodes.add(node.copy());
            }
        }

        //Create a list of paths and check if they exist in the graph
        List <String> paths = new ArrayList<>();
        if(perNodes.size() > 1) { //Only check permutations if there is more than one intermediate node
            Permute(perNodes, 0, paths);
        } else {
            paths.add(Integer.toString(perNodes.get(0).value));
        }

        //Add the paths to the graph if the path exists
        for (String path : paths) {
            if(pathExists(startNodeInt, path, graph)) { //Check if permutation results in path that starts and ends
                //with starting node
                graph.addPath(startNodeInt, path);
            }
        }

        System.out.println(graph.printPaths());
    }
}

