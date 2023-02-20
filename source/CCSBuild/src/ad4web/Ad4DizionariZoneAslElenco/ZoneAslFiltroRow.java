//ZoneAslFiltroRow: import @3-BB3DFB5B
package ad4web.Ad4DizionariZoneAslElenco;

import java.util.*;
import com.codecharge.db.*;
//End ZoneAslFiltroRow: import

//ZoneAslFiltroRow: class head @3-4F917277
public class ZoneAslFiltroRow {
//End ZoneAslFiltroRow: class head

//ZoneAslFiltroRow: declare fiels @3-0B010546
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_REGIONE = new TextField("s_REGIONE", "S_REGIONE");
    private TextField s_ZONA_ASL = new TextField("s_ZONA_ASL", "S_ZONA_ASL");
//End ZoneAslFiltroRow: declare fiels

//ZoneAslFiltroRow: constructor @3-3F717DD1
    public ZoneAslFiltroRow() {
    }
//End ZoneAslFiltroRow: constructor

//ZoneAslFiltroRow: method(s) of FILTER_SEARCH @4-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End ZoneAslFiltroRow: method(s) of FILTER_SEARCH

//ZoneAslFiltroRow: method(s) of s_REGIONE @26-F9B272A5
    public TextField getS_REGIONEField() {
        return s_REGIONE;
    }

    public String getS_REGIONE() {
        return s_REGIONE.getValue();
    }

    public void setS_REGIONE(String value) {
        this.s_REGIONE.setValue(value);
    }
//End ZoneAslFiltroRow: method(s) of s_REGIONE

//ZoneAslFiltroRow: method(s) of s_ZONA_ASL @27-D37C4D1A
    public TextField getS_ZONA_ASLField() {
        return s_ZONA_ASL;
    }

    public String getS_ZONA_ASL() {
        return s_ZONA_ASL.getValue();
    }

    public void setS_ZONA_ASL(String value) {
        this.s_ZONA_ASL.setValue(value);
    }
//End ZoneAslFiltroRow: method(s) of s_ZONA_ASL

//ZoneAslFiltroRow: class tail @3-FCB6E20C
}
//End ZoneAslFiltroRow: class tail

