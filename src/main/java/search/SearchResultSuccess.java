package search;

import java.util.List;

/**
 * Wrapper class user gets as the return result to their query
 * if the searched term was not found returns error message in toString()
 */
public class SearchResultSuccess implements Searchable {
    private final String term;
    private final List<String> presentDocumentNames;

    public SearchResultSuccess(String term, List<String> presentDocumentNames) {
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
        for (String documentName : presentDocumentNames) {
            sb.append(documentName);
            sb.append(" ");
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
