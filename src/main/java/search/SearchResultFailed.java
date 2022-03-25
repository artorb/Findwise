package search;

public class SearchResultFailed implements Searchable {
    private String term;

    public SearchResultFailed(String term) {
        this.term = term;
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return term + " not found in any of the documents";
    }
}
