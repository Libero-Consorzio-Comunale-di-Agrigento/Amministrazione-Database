//ComuniFiltroRow: import @3-2110BD57
package restrict.Ad4DizionariAslComuniElenco;

import java.util.*;
import com.codecharge.db.*;
//End ComuniFiltroRow: import

//ComuniFiltroRow: class head @3-66BC0E3C
public class ComuniFiltroRow {
//End ComuniFiltroRow: class head

//ComuniFiltroRow: declare fiels @3-B991553A
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_COMUNE = new TextField("s_COMUNE", "S_COMUNE");
//End ComuniFiltroRow: declare fiels

//ComuniFiltroRow: constructor @3-3BE91E32
    public ComuniFiltroRow() {
    }
//End ComuniFiltroRow: constructor

//ComuniFiltroRow: method(s) of FILTER_SEARCH @24-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End ComuniFiltroRow: method(s) of FILTER_SEARCH

//ComuniFiltroRow: method(s) of s_COMUNE @5-0A1BD254
    public TextField getS_COMUNEField() {
        return s_COMUNE;
    }

    public String getS_COMUNE() {
        return s_COMUNE.getValue();
    }

    public void setS_COMUNE(String value) {
        this.s_COMUNE.setValue(value);
    }
//End ComuniFiltroRow: method(s) of s_COMUNE

//ComuniFiltroRow: class tail @3-FCB6E20C
}
//End ComuniFiltroRow: class tail

