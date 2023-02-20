//BancheFiltroRow: import @3-441E4CA3
package restrict.Ad4DizionariBancheElenco;

import java.util.*;
import com.codecharge.db.*;
//End BancheFiltroRow: import

//BancheFiltroRow: class head @3-A803EC23
public class BancheFiltroRow {
//End BancheFiltroRow: class head

//BancheFiltroRow: declare fiels @3-F17D6C7C
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_BANCA = new TextField("s_BANCA", "S_BANCA");
//End BancheFiltroRow: declare fiels

//BancheFiltroRow: constructor @3-1B05C5C2
    public BancheFiltroRow() {
    }
//End BancheFiltroRow: constructor

//BancheFiltroRow: method(s) of FILTER_SEARCH @4-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End BancheFiltroRow: method(s) of FILTER_SEARCH

//BancheFiltroRow: method(s) of s_BANCA @5-B2415D8F
    public TextField getS_BANCAField() {
        return s_BANCA;
    }

    public String getS_BANCA() {
        return s_BANCA.getValue();
    }

    public void setS_BANCA(String value) {
        this.s_BANCA.setValue(value);
    }
//End BancheFiltroRow: method(s) of s_BANCA

//BancheFiltroRow: class tail @3-FCB6E20C
}
//End BancheFiltroRow: class tail

