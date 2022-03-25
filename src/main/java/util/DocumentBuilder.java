package util;

import entities.Document;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts
 * list of file inputs from the path
 * to wrapped Document POJOs
 */
public class DocumentBuilder {

    /**
     * Small list of stopwords
     * if the word occurred we skip it to the db,
     * however include in 'total_terms_document' value
     */
    private final List<String> stopWords = List.of("the", "a", "am", "can", "could", "will", "what");


    /**
     * Maps raw file inputs to Document POJOs
     * tokenizes the input for each document
     *
     * @param files raw file input
     * @return a list of wrapped file inputs
     */
    public List<Document> filesToDocuments(List<Path> files) {
        var documents = new ArrayList<Document>();
        files.forEach(c -> {
            try {
                var file = Files.readAllLines(c, Charset.defaultCharset())
                        .stream()
                        .map(term -> term.toLowerCase(Locale.ROOT).split("\\s+"))
                        .flatMap(Arrays::stream)
                        .map(String::valueOf)
                        .map(term -> term.toLowerCase(Locale.ROOT).replaceAll("[^A-Za-z]", ""))
                        .collect(Collectors.toList());

                var document = new Document();
                var termFrequencyMap = buildTermFrequencyMap(file);
                document.setName(String.valueOf(c.getFileName()));
                document.setAmountTerms((long) file.size());
                document.setTermsFrequency(termFrequencyMap);
                documents.add(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return documents;
    }

    /**
     * Builds a dictionary based on
     * a term's frequency in a given document
     *
     * @param tokens tokenized document data
     * @return a dictionary of [term, amount_per_document]
     */
    private Map<String, Long> buildTermFrequencyMap(List<String> tokens) {
        return tokens.stream().filter(c -> !stopWords.contains(c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
