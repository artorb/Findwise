package entities;

import java.util.Map;
import java.util.UUID;

/**
 * User input wrapper
 */
public class Document {
    /**
     * Could use bit indices in a single long
     * 'variable id' type to save space
     * as in [id=2 -> ..00 0010 / id=4 -> ..00 1000]
     */
    private final UUID id;

    /**
     * document name
     */
    private String name;

    /**
     * [term : frequency_per_document] dictionary
     */
    private Map<String, Long> termsFrequency;

    /**
     * amount of all terms in the given document
     */
    private Long amountTerms;

    public Document() {
        id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public Map<String, Long> getTermsFrequency() {
        return termsFrequency;
    }

    public void setTermsFrequency(Map<String, Long> termsFrequency) {
        this.termsFrequency = termsFrequency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmountTerms() {
        return amountTerms;
    }

    public void setAmountTerms(Long amountTerms) {
        this.amountTerms = amountTerms;
    }
}
