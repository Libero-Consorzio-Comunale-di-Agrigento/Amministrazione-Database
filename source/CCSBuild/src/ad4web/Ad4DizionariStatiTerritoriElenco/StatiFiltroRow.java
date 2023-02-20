//StatiFiltroRow: import @3-B59BA419
package ad4web.Ad4DizionariStatiTerritoriElenco;

import java.util.*;
import com.codecharge.db.*;
//End StatiFiltroRow: import

//StatiFiltroRow: class head @3-E42A055F
public class StatiFiltroRow {
//End StatiFiltroRow: class head

//StatiFiltroRow: declare fiels @3-864134DA
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_STATO = new TextField("s_STATO", "S_STATO");
//End StatiFiltroRow: declare fiels

//StatiFiltroRow: constructor @3-829BF001
    public StatiFiltroRow() {
    }
//End StatiFiltroRow: constructor

//StatiFiltroRow: method(s) of FILTER_SEARCH @4-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End StatiFiltroRow: method(s) of FILTER_SEARCH

//StatiFiltroRow: method(s) of s_STATO @5-C2E8354F
    public TextField getS_STATOField() {
        return s_STATO;
    }

    public String getS_STATO() {
        return s_STATO.getValue();
    }

    public void setS_STATO(String value) {
        this.s_STATO.setValue(value);
    }
//End StatiFiltroRow: method(s) of s_STATO

//StatiFiltroRow: class tail @3-FCB6E20C
}
//End StatiFiltroRow: class tail

