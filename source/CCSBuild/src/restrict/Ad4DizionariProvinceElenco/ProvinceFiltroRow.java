//ProvinceFiltroRow: import @3-563B88D2
package restrict.Ad4DizionariProvinceElenco;

import java.util.*;
import com.codecharge.db.*;
//End ProvinceFiltroRow: import

//ProvinceFiltroRow: class head @3-356D8D02
public class ProvinceFiltroRow {
//End ProvinceFiltroRow: class head

//ProvinceFiltroRow: declare fiels @3-6ED46DC5
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_REGIONE = new TextField("s_REGIONE", "S_REGIONE");
    private TextField s_PROVINCIA = new TextField("s_PROVINCIA", "S_PROVINCIA");
//End ProvinceFiltroRow: declare fiels

//ProvinceFiltroRow: constructor @3-0005337F
    public ProvinceFiltroRow() {
    }
//End ProvinceFiltroRow: constructor

//ProvinceFiltroRow: method(s) of FILTER_SEARCH @24-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End ProvinceFiltroRow: method(s) of FILTER_SEARCH

//ProvinceFiltroRow: method(s) of s_REGIONE @26-F9B272A5
    public TextField getS_REGIONEField() {
        return s_REGIONE;
    }

    public String getS_REGIONE() {
        return s_REGIONE.getValue();
    }

    public void setS_REGIONE(String value) {
        this.s_REGIONE.setValue(value);
    }
//End ProvinceFiltroRow: method(s) of s_REGIONE

//ProvinceFiltroRow: method(s) of s_PROVINCIA @5-E3FD84CB
    public TextField getS_PROVINCIAField() {
        return s_PROVINCIA;
    }

    public String getS_PROVINCIA() {
        return s_PROVINCIA.getValue();
    }

    public void setS_PROVINCIA(String value) {
        this.s_PROVINCIA.setValue(value);
    }
//End ProvinceFiltroRow: method(s) of s_PROVINCIA

//ProvinceFiltroRow: class tail @3-FCB6E20C
}
//End ProvinceFiltroRow: class tail

