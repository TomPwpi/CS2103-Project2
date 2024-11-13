import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.*;

//testActors.tsv file was modified to conduct tests. They are a part of this folder
/**
 * Code to test an <tt>GraphSearchEngine</tt> implementation.
 */
public class GraphSearchEngineTester {
	@Test
	@Timeout(5)
	void testShortestPath1 () {
		final GraphSearchEngine searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Kris");
		final Node actor2 = graph.getActor("Sandy");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(5, shortestPath.size());
		final String[] correctNames = { "Kris", "Blah2", "Sara", "Blah3", "Sandy" };
		int idx = 0;
		for (Node node : shortestPath) {
			assertEquals(correctNames[idx++], node.getName());
		}
	}

    @Test
	@Timeout(5)
	void testShortestPath2 () {
		final GraphSearchEngine searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Sandy");
		final Node actor2 = graph.getActor("Kris");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(5, shortestPath.size());
		final String[] correctNames = { "Sandy", "Blah3", "Sara", "Blah2", "Kris" };
		int idx = 0;
		for (Node node : shortestPath) {
			assertEquals(correctNames[idx++], node.getName());
		}
	}

    @Test
    void testCheckNode(){
        final GraphSearchEngineImpl searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
        try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Sandy");
		final Node actor2 = graph.getActor("Sandy");

        assertTrue(searchEngine.checkNode(actor1, actor2));
    }

    @Test
	@Timeout(5)
	void testOneNode () {
		final GraphSearchEngine searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Sandy");
		final Node actor2 = graph.getActor("Sandy");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(1, shortestPath.size());
		final String[] correctNames = {"Sandy"};
		int idx = 0;
		for (Node node : shortestPath) {
			assertEquals(correctNames[idx++], node.getName());
		}
	}

    @Test
	@Timeout(5)
	void testDisconnected () {
		final GraphSearchEngine searchEngine = new GraphSearchEngineImpl();
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("Kris");
		final Node actor2 = graph.getActor("John");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actor2);
		assertEquals(null, shortestPath);
	}

    @Test
	@Timeout(5)
	void testNullActor () {
		final IMDBGraph graph;
		try {
			graph = new IMDBGraphImpl(IMDBGraphImpl.IMDB_DIRECTORY + "/testActors.tsv",
                                      IMDBGraphImpl.IMDB_DIRECTORY + "/testMovies.tsv");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			assertTrue(false);
			return;
		}
		final Node actor1 = graph.getActor("");
		final Node actor2 = graph.getActor("Steve");
		assertTrue((actor1 == null) && (actor2 == null));
	}
}
