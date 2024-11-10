// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// import java.util.function.*;

// /**
//  * Implements the GraphSearchEngine interface.
//  */
// public class GraphSearchEngineImpl implements GraphSearchEngine {
// 	public GraphSearchEngineImpl () {
// 	}

// 	public List<Node> findShortestPath (Node s, Node t) {
// 		//return null;  // TODO implement me.
//         Node resultNode = breadthFirstSearch(s, t);
//         ArrayList<Node> result = new ArrayList<>();
//         if(resultNode != null){
//             result.add(resultNode);
//             return result;
//         }
//         return null;
// 	}

//     public Node breadthFirstSearch (Node start, Node goal){
//         ArrayList<Node> visited = new ArrayList<Node>();
//         ArrayList<Node> queue = new ArrayList<Node>();
//         Node current;
//         queue.add(start);
//         for (int i = 0; i < queue.size(); i++) {
//             current = queue.get(i);
//             if(checkNode(current, goal)){
//                 return current;
//             }
//             else{
//                 visited.add(current);
//                 for (Node neighbor: current.getNeighbors()) {
//                     if(!visited.contains(neighbor))
//                     queue.add(neighbor);
//                 }
//             }
            
//         }
//         return null;
//     }

//     public boolean checkNode(Node current, Node goal){
//         return current.getName().equals(goal.getName());
//     }
// }
import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	public GraphSearchEngineImpl() {}

	private boolean contains(Collection<? extends Node> collection, Node t) {
		for (Node node : collection) {
			if (node == t) {
				return true;
			}
		}
		return false;
	}

	private List<Node> nextDepth(List<Node> queue) {
		List<Node> newQueue = new ArrayList<>();

		for (Node node : queue) {
			for (Node neighbor : node.getNeighbors()) {
				if (!contains(newQueue, neighbor)) {
					newQueue.add(neighbor);
				}
			}
		}
		return newQueue;
	}

	private Node findOneCloser(List<Node> queue, Node t, Set<Node> visited) {
		visited.addAll(queue);

		for (Node node : queue) {

			if (contains(node.getNeighbors(), t)) {
				return node;
			}
		}

		List<Node> newQueue = nextDepth(queue);

		if (newQueue.isEmpty()) {
			return null;
		}

		return findOneCloser(newQueue, t, visited);
	}

	public List<Node> findPath(Node s, Node t, List<Node> path) {
		if (s.equals(t)) {
			path.add(t);
			return path;
		}

		// Initialize a queue with the starting node
		List<Node> queue = new ArrayList<>();
		queue.add(s);

		// Use a set for tracking visited nodes to avoid revisits and infinite loops
		Set<Node> visited = new HashSet<>();

		// Attempt to find a node closer to the target
		Node oneCloser = findOneCloser(queue, t, visited);

		// If there's no path to `t`, return null
		if (oneCloser == null) {
			return null;
		}

		// Update the path and recursively search for the remaining path
		path.add(oneCloser);
		return findPath(oneCloser, t, path);
	}

	public List<Node> findShortestPath(Node s, Node t) {
		List<Node> path = new ArrayList<>();
		return findPath(s, t, path);
	}
}
