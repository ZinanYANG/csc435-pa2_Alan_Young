package csc435.app;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Arrays;



public class ProcessingEngine {
    private IndexStore store;
    private ExecutorService indexExecutor;

    public ProcessingEngine(IndexStore store) {
        this.store = store;
        this.indexExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
    
    public String indexFiles(String directoryPath) {
    long startTime = System.nanoTime();
    File directory = new File(directoryPath).getAbsoluteFile();

    if (!directory.exists()) {
        return "The path provided does not exist: " + directoryPath;
    }

    indexEntries(directory);
    indexExecutor.shutdown();
    try {
        while (!indexExecutor.isTerminated()) {
            indexExecutor.awaitTermination(5, TimeUnit.SECONDS);
        }
    } catch (InterruptedException e) {
        System.err.println("Indexing interrupted: " + e.getMessage());
        Thread.currentThread().interrupt();
    }

    System.out.println("All indexing tasks have completed.");

    long endTime = System.nanoTime();
    double elapsedTime = (endTime - startTime) / 1e9;

    String successMessage = String.format("Indexing completed for: %s in %.2f seconds", directoryPath, elapsedTime);

    return successMessage;

}

private void indexEntries(File entry) {
    if (entry.isDirectory()) {
        File[] subEntries = entry.listFiles();
        if (subEntries != null) {
            for (File subEntry : subEntries) {
                indexEntries(subEntry);
            }
        }
    } else {
        indexExecutor.submit(() -> {
            String content = readFileContent(entry);
            if (content != null) {
                Map<String, Integer> localIndex = buildLocalIndex(content);
                store.insertIndex(localIndex, entry.getName());
            }
        });
    }
}


public String searchFiles(String query) {

    String[] terms = query.toLowerCase().split("\\s+and\\s+");

    System.out.println("Original query: '" + query + "'");
    System.out.println("Split query into terms: " + Arrays.toString(terms));
    Map<String, Integer> allFilesOccurrences = new HashMap<>();
    for (String term : terms) {
        term = term.trim();
        System.out.println("Processing term: '" + term + "'");

        Map<String, Integer> termResults = store.lookupIndex(term);
        System.out.println("Results for term '" + term + "': " + termResults);
        if (allFilesOccurrences.isEmpty()) {
            allFilesOccurrences.putAll(termResults);
        } else {
            allFilesOccurrences.keySet().retainAll(termResults.keySet());
            for (String file : allFilesOccurrences.keySet()) {
                allFilesOccurrences.put(file, allFilesOccurrences.get(file) + termResults.getOrDefault(file, 0));
            }
        }
    }

    System.out.println("Intersection of all terms: " + allFilesOccurrences);
    
    if (allFilesOccurrences.isEmpty()) {
        return "No files contain all the terms: " + query;
    }

    String resultString = allFilesOccurrences.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .limit(10)
        .map(entry -> entry.getKey() + " " + entry.getValue())
        .collect(Collectors.joining("\n"));

    return resultString;
}

    public void stopWorkers() {
        try {
            indexExecutor.shutdown();
            if (!indexExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                indexExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            indexExecutor.shutdownNow();
        }
    }

    private String readFileContent(File file) {
    try {
        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        System.out.println("Successfully read file: " + file.getName());
        return content;
    } catch (IOException e) {
        System.err.println("Error reading file " + file.getAbsolutePath() + ": " + e.getMessage());
        return null;
    }
}


	


    private Map<String, Integer> buildLocalIndex(String content) {
    if (content == null || content.isEmpty()) {
        System.out.println("Content is null or empty, skipping indexing.");
        return Collections.emptyMap();
    }

    Map<String, Integer> localIndex = new HashMap<>();
    Pattern pattern = Pattern.compile("\\b\\w+\\b", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(content);

    while (matcher.find()) {
        String word = matcher.group().toLowerCase();
        localIndex.merge(word, 1, Integer::sum);
    }

    return localIndex;
}


}
