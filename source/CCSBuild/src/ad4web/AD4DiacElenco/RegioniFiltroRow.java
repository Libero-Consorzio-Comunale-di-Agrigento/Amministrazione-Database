//RegioniFiltroRow: import @169-9F5390D2
package ad4web.AD4DiacElenco;

import java.util.*;
import com.codecharge.db.*;
//End RegioniFiltroRow: import

//RegioniFiltroRow: class head @169-ABCD0692
public class RegioniFiltroRow {
//End RegioniFiltroRow: class head

//RegioniFiltroRow: declare fiels @169-C269D2A6
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_MODULO = new TextField("s_MODULO", "S_MODULO");
    private TextField s_ISTANZA = new TextField("s_ISTANZA", "S_ISTANZA");
//End RegioniFiltroRow: declare fiels

//RegioniFiltroRow: constructor @169-B9D6CE53
    public RegioniFiltroRow() {
    }
//End RegioniFiltroRow: constructor

//RegioniFiltroRow: method(s) of FILTER_SEARCH @170-0D574A29
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

//RegioniFiltroRow: method(s) of s_MODULO @171-1EDA3FC7
    public TextField getS_MODULOField() {
        return s_MODULO;
    }

    public String getS_MODULO() {
        return s_MODULO.getValue();
    }

    public void setS_MODULO(String value) {
        this.s_MODULO.setValue(value);
    }
//End RegioniFiltroRow: method(s) of s_MODULO

//RegioniFiltroRow: method(s) of s_ISTANZA @177-846A8DEB
    public TextField getS_ISTANZAField() {
        return s_ISTANZA;
    }

    public String getS_ISTANZA() {
        return s_ISTANZA.getValue();
    }

    public void setS_ISTANZA(String value) {
        this.s_ISTANZA.setValue(value);
    }
//End RegioniFiltroRow: method(s) of s_ISTANZA

//RegioniFiltroRow: class tail @169-FCB6E20C
}
//End RegioniFiltroRow: class tail

