package csc435.app;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class IndexStore {
    private final Map<String, Map<String, Integer>> globalIndex;

    public IndexStore() {
        this.globalIndex = new ConcurrentHashMap<>();
    }

    public synchronized void insertIndex(Map<String, Integer> localIndex, String fileName) {
    if (localIndex.isEmpty()) {
        System.out.println("Local index for " + fileName + " is empty, skipping.");
        return;
    }

        for (Map.Entry<String, Integer> entry : localIndex.entrySet()) {
            String term = entry.getKey();
            Integer occurrence = entry.getValue();
            
            globalIndex.computeIfAbsent(term, k -> new ConcurrentHashMap<>())
                       .merge(fileName, occurrence, Integer::sum);
        }

    }

    public Map<String, Integer> lookupIndex(String term) {
        Map<String, Integer> results = new HashMap<>(globalIndex.getOrDefault(term, new ConcurrentHashMap<>()));
    	System.out.println("Lookup for term '" + term + "': " + results); 
    	return results;
    }
    
    public void printIndex() {
    System.out.println("Global Index Summary:");
    int termCount = 0;
    for (Map.Entry<String, Map<String, Integer>> entry : globalIndex.entrySet()) {
        System.out.println("Term: " + entry.getKey() + ", Occurrences: " + entry.getValue().size());
        termCount++;
        if (termCount >= 50) { // Limit to first 50 terms
            System.out.println("... and more ...");
            break;
        }
    }
}



}
