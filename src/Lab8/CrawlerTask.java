package Lab8;

import java.util.*;

public class CrawlerTask implements Runnable {

    /** A field for the given depth pair */
    public URLDepthPair depthPair;

    /** Field for the URL pool */
    public URLPool myPool;

    public CrawlerTask(URLPool pool) {
        myPool = pool;
    }

    /** A method to run the tasks of CrawlerTask */
    public void run() {

        // Get the next depth pair from the pool.
        depthPair = myPool.get();

        // The depth of the depth pair.
        int myDepth = depthPair.getDepth();

        // Get all links from the site and store them in a new linked list.
        LinkedList<String> linksList = new LinkedList<String>();
        linksList = Crawler.getAllLinks(depthPair);

        // Iterate through links from site.
        for (int i=0;i<linksList.size();i++) {
            String newURL = linksList.get(i);

            // Create a new depth pair for each link found and add to pool.
            URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
            myPool.put(newDepthPair);
        }
//        myPool.decrimentWaitingThreads();
    }

}
