package search;

import java.util.List;

/**
 * Wrapper class user gets as the return result to their query
 */
public class SearchResult {
    private final String term;
    private final List<String> presentDocumentNames;

    public SearchResult(String term, List<String> presentDocumentNames) {
        this.term = term;
        this.presentDocumentNames = presentDocumentNames;
    }

    public String getTerm() {
        return term;
    }

    public List<String> getPresentDocumentNames() {
        return presentDocumentNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Term: " + term + " found in [ ");
        for (String t : presentDocumentNames) {
            sb.append(t);
            sb.append(" ");
        }
        sb.append(" ]");
        return sb.toString();
    }
}
