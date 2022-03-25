package search;

import util.TfIdfCalculator;
import entities.Document;
import entities.InvertedIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple search engine
 */
public class SearchEngine {

    private SearchEngine() {
    }

    public static SearchResult search(List<Document> documents, String query) {
        List<Document> presentInDocuments = filterPresentDocuments(documents, query);

        List<InvertedIndex> searchResults = new ArrayList<>();

        for (Document document : presentInDocuments) {
            double tf = TfIdfCalculator.calculateTf(document, query);
            double idf = TfIdfCalculator.calculateIdf(documents.size(), presentInDocuments.size());
            double tfIdf = TfIdfCalculator.calculateTfIdf(tf, idf);

            InvertedIndex result = new InvertedIndex(tf, idf, tfIdf, document, query);
            searchResults.add(result);
            System.out.println("tf " + tf + " idf " + idf + " tfIdf " + tfIdf);
        }

        var presentDocumentNames = getTermPresentFilesNames(searchResults);

        return new SearchResult(query, presentDocumentNames);
    }

    private static List<Document> filterPresentDocuments(List<Document> documents, String query) {
        return documents.stream()
                .filter(document -> document.getTermsFrequency().containsKey(query))
                .collect(Collectors.toList());
    }

    /**
     * @param searchResults inverted index data structutre
     * @return a list of document the term is present in
     * sorted by TF-IDF
     */
    private static List<String> getTermPresentFilesNames(List<InvertedIndex> searchResults) {
        return searchResults.stream()
                .sorted((result1, result2) -> {
                    //TF is never 0, sort by TF if both TF-IDF are 0
                    if (result1.getTfIdf() == 0 && result2.getTfIdf() == 0) {
                        return (result1.getTf() > result2.getTf()) ? -1 : 1;
                    }

                    if (result1.getTfIdf() == result2.getTfIdf()) {
                        return 0;
                    }

                    return (result1.getTfIdf() > result2.getTfIdf()) ? -1 : 1;
                })
                .map(idx -> idx.getDocument().getName())
                .collect(Collectors.toList());
    }
}
