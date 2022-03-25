package entities;

/**
 * Inverted index data structure
 * contains Term, TF-IDF, and Document it is present in
 */
public class InvertedIndex {

    private double tf;
    private double idf;
    private double tfIdf;

    private Document document;
    private final String term;

    public InvertedIndex(double tf, double idf, double tfIdf, Document document, String term) {
        this.tf = tf;
        this.idf = idf;
        this.tfIdf = tfIdf;
        this.document = document;
        this.term = term;
    }

    public double getTf() {
        return tf;
    }

    public double getIdf() {
        return idf;
    }

    public double getTfIdf() {
        return tfIdf;
    }

    public Document getDocument() {
        return document;
    }

    public String getTerm() {
        return term;
    }
}
