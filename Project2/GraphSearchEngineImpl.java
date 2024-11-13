import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Implements the GraphSearchEngine interface.
 */
public class GraphSearchEngineImpl implements GraphSearchEngine {
	public GraphSearchEngineImpl () {
	}

	// public List<Node> findShortestPath (Node s, Node t) {
	// 	//return null;  // TODO implement me.
    //     // Node resultNode = breadthFirstSearch(s, t);
    //     // ArrayList<Node> result = new ArrayList<>();
    //     // if(resultNode != null){
    //     //     result.add(resultNode);
    //     //     return result;
    //     // }
    //     // return null;
    //     return breadthFirstSearch(s, t);
	// }

    /**
     * Finds the shortest path between two nodes in a graph
     * @param s - starting node that the search begins at
     * @param t - the end node that the search tries to reach
     * @return a list of nodes indicating the path taken from the start node to end node
     */
    public List<Node> findShortestPath (Node s, Node t){
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Node> queue = new ArrayList<Node>();
        HashMap<Node, Node> parent = new HashMap<Node, Node>();
        Node current;

        queue.add(s);

        for (int i = 0; i < queue.size(); i++) {
            current = queue.get(i);
            if(checkNode(current, t)){
                return makePath(parent, s, t);
                //return current;
            }
            else{
                visited.add(current);
                for (Node neighbor: current.getNeighbors()) {
                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                        parent.put(neighbor, current);
                    }
                    
                }
            }
        }
        return null;
    }

    /**
     * Checks whether the current node is equal to the goal node using the node's name
     * @param current - the node being compared
     * @param goal - the node being compared against
     * @return - a boolean whether the two nodes are the same
     */
    public boolean checkNode(Node current, Node goal){
        return current.getName().equals(goal.getName());
    }

    /**
     * Returns the list of nodes taken to get from start to goal
     * @param parents - A hashmap of nodes assigning the parent of the nodes used in the path from start to goal
     * @param start - The start node
     * @param goal - The end node
     * @return A list of nodes indicating the path from start to goal
     */
    public List<Node> makePath(HashMap<Node, Node> parents, Node start, Node goal){
        ArrayList<Node> path = new ArrayList<Node>();

        Node currentNode = goal;

        while (!checkNode(currentNode, start)) {
            path.add(0, currentNode);
            currentNode = parents.get(currentNode);
        }
        path.add(0, start);
    
        return path;
    }

    // public List<Node> reverseList(List<Node> list){
    //     ArrayList<Node> result = new ArrayList<Node>();

    //     for (int i = list.size() - 1; i >= 0; i--) {
    //         result.add(list.get(i));
    //     }

    //     return result;
    // }
}
// import java.util.*;

// public class GraphSearchEngineImpl implements GraphSearchEngine {

// 	public GraphSearchEngineImpl() {}

// 	private boolean contains(Collection<? extends Node> collection, Node t) {
// 		for (Node node : collection) {
// 			if (node == t) {
// 				return true;
// 			}
// 		}
// 		return false;
// 	}

// 	private List<Node> nextDepth(List<Node> queue) {
// 		List<Node> newQueue = new ArrayList<>();

// 		for (Node node : queue) {
// 			for (Node neighbor : node.getNeighbors()) {
// 				if (!contains(newQueue, neighbor)) {
// 					newQueue.add(neighbor);
// 				}
// 			}
// 		}
// 		return newQueue;
// 	}

// 	private Node findOneCloser(List<Node> queue, Node t, Set<Node> visited) {
// 		visited.addAll(queue);

// 		for (Node node : queue) {

// 			if (contains(node.getNeighbors(), t)) {
// 				return node;
// 			}
// 		}

// 		List<Node> newQueue = nextDepth(queue);

// 		if (newQueue.isEmpty()) {
// 			return null;
// 		}

// 		return findOneCloser(newQueue, t, visited);
// 	}

// 	public List<Node> findPath(Node s, Node t, List<Node> path) {
// 		if (s.equals(t)) {
// 			path.add(t);
// 			return path;
// 		}

// 		// Initialize a queue with the starting node
// 		List<Node> queue = new ArrayList<>();
// 		queue.add(s);

// 		// Use a set for tracking visited nodes to avoid revisits and infinite loops
// 		Set<Node> visited = new HashSet<>();

// 		// Attempt to find a node closer to the target
// 		Node oneCloser = findOneCloser(queue, t, visited);

// 		// If there's no path to `t`, return null
// 		if (oneCloser == null) {
// 			return null;
// 		}

// 		// Update the path and recursively search for the remaining path
// 		path.add(oneCloser);
// 		return findPath(oneCloser, t, path);
// 	}

// 	public List<Node> findShortestPath(Node s, Node t) {
// 		List<Node> path = new ArrayList<>();
// 		return findPath(s, t, path);
// 	}
// }
