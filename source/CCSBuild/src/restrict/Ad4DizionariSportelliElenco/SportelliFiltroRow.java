//SportelliFiltroRow: import @3-128ECA27
package restrict.Ad4DizionariSportelliElenco;

import java.util.*;
import com.codecharge.db.*;
//End SportelliFiltroRow: import

//SportelliFiltroRow: class head @3-B3C35A52
public class SportelliFiltroRow {
//End SportelliFiltroRow: class head

//SportelliFiltroRow: declare fiels @3-DFB8B2AD
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_BANCA = new TextField("s_BANCA", "S_BANCA");
    private TextField s_SPORTELLO = new TextField("s_SPORTELLO", "S_SPORTELLO");
//End SportelliFiltroRow: declare fiels

//SportelliFiltroRow: constructor @3-485F680F
    public SportelliFiltroRow() {
    }
//End SportelliFiltroRow: constructor

//SportelliFiltroRow: method(s) of FILTER_SEARCH @24-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End SportelliFiltroRow: method(s) of FILTER_SEARCH

//SportelliFiltroRow: method(s) of s_BANCA @26-B2415D8F
    public TextField getS_BANCAField() {
        return s_BANCA;
    }

    public String getS_BANCA() {
        return s_BANCA.getValue();
    }

    public void setS_BANCA(String value) {
        this.s_BANCA.setValue(value);
    }
//End SportelliFiltroRow: method(s) of s_BANCA

//SportelliFiltroRow: method(s) of s_SPORTELLO @5-E38AF5E4
    public TextField getS_SPORTELLOField() {
        return s_SPORTELLO;
    }

    public String getS_SPORTELLO() {
        return s_SPORTELLO.getValue();
    }

    public void setS_SPORTELLO(String value) {
        this.s_SPORTELLO.setValue(value);
    }
//End SportelliFiltroRow: method(s) of s_SPORTELLO

//SportelliFiltroRow: class tail @3-FCB6E20C
}
//End SportelliFiltroRow: class tail

