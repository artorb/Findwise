package util;

import entities.Document;

/**
 * Simple TF-IDF calculator
 */
public class TfIdfCalculator {

    private TfIdfCalculator() {
    }

    public static Double calculateTfIdf(double tf, double idf) {
        return tf * idf;
    }

    public static Double calculateIdf(long corpusAmount, long occurredDocumentsAmount) {
        if (occurredDocumentsAmount == 0) {
            // we should never end up here
            throw new ArithmeticException("Occurrence is 0");
        }
        return Math.log10((double) corpusAmount / (double) occurredDocumentsAmount);
    }

    public static Double calculateTf(Document document, String query) {
        if (document.getAmountTerms() == 0) {
            // we should never end up here
            throw new ArithmeticException();
        }
        return document.getTermsFrequency().get(query).doubleValue() / document.getAmountTerms();
    }
}
