package ba.unsa.etf.rpr.domain;

/**
 * Interface forces all POJO beans to have ID field.
 */
public interface Idable {

    int getId();

    void setId(int value);

    boolean isSetId();

}
