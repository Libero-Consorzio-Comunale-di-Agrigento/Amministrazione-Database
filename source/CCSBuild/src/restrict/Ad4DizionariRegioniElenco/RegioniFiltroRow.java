//RegioniFiltroRow: import @3-B2BE3682
package restrict.Ad4DizionariRegioniElenco;

import java.util.*;
import com.codecharge.db.*;
//End RegioniFiltroRow: import

//RegioniFiltroRow: class head @3-ABCD0692
public class RegioniFiltroRow {
//End RegioniFiltroRow: class head

//RegioniFiltroRow: declare fiels @3-E5D2338A
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_REGIONE = new TextField("s_REGIONE", "S_REGIONE");
//End RegioniFiltroRow: declare fiels

//RegioniFiltroRow: constructor @3-B9D6CE53
    public RegioniFiltroRow() {
    }
//End RegioniFiltroRow: constructor

//RegioniFiltroRow: method(s) of FILTER_SEARCH @4-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End RegioniFiltroRow: method(s) of FILTER_SEARCH

//RegioniFiltroRow: method(s) of s_REGIONE @5-F9B272A5
    public TextField getS_REGIONEField() {
        return s_REGIONE;
    }

    public String getS_REGIONE() {
        return s_REGIONE.getValue();
    }

    public void setS_REGIONE(String value) {
        this.s_REGIONE.setValue(value);
    }
//End RegioniFiltroRow: method(s) of s_REGIONE

//RegioniFiltroRow: class tail @3-FCB6E20C
}
//End RegioniFiltroRow: class tail

