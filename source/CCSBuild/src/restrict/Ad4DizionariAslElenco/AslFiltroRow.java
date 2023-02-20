//AslFiltroRow: import @3-B3B19EDA
package restrict.Ad4DizionariAslElenco;

import java.util.*;
import com.codecharge.db.*;
//End AslFiltroRow: import

//AslFiltroRow: class head @3-0E0D3BBB
public class AslFiltroRow {
//End AslFiltroRow: class head

//AslFiltroRow: declare fiels @3-ED533D62
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_REGIONE = new TextField("s_REGIONE", "S_REGIONE");
    private TextField s_ASL = new TextField("s_ASL", "S_ASL");
//End AslFiltroRow: declare fiels

//AslFiltroRow: constructor @3-EAD8E078
    public AslFiltroRow() {
    }
//End AslFiltroRow: constructor

//AslFiltroRow: method(s) of FILTER_SEARCH @4-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End AslFiltroRow: method(s) of FILTER_SEARCH

//AslFiltroRow: method(s) of s_REGIONE @27-F9B272A5
    public TextField getS_REGIONEField() {
        return s_REGIONE;
    }

    public String getS_REGIONE() {
        return s_REGIONE.getValue();
    }

    public void setS_REGIONE(String value) {
        this.s_REGIONE.setValue(value);
    }
//End AslFiltroRow: method(s) of s_REGIONE

//AslFiltroRow: method(s) of s_ASL @28-FB63713E
    public TextField getS_ASLField() {
        return s_ASL;
    }

    public String getS_ASL() {
        return s_ASL.getValue();
    }

    public void setS_ASL(String value) {
        this.s_ASL.setValue(value);
    }
//End AslFiltroRow: method(s) of s_ASL

//AslFiltroRow: class tail @3-FCB6E20C
}
//End AslFiltroRow: class tail

