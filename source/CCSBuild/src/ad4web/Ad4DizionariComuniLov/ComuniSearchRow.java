//ComuniSearchRow: import @2-9CDBB3C7
package ad4web.Ad4DizionariComuniLov;

import java.util.*;
import com.codecharge.db.*;
//End ComuniSearchRow: import

//ComuniSearchRow: class head @2-C3709A41
public class ComuniSearchRow {
//End ComuniSearchRow: class head

//ComuniSearchRow: declare fiels @2-FF50F3E3
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_COMUNE = new TextField("s_COMUNE", "");
    private TextField s_PROVINCIA_SIGLA = new TextField("s_PROVINCIA_SIGLA", "");
//End ComuniSearchRow: declare fiels

//ComuniSearchRow: constructor @2-E03F2411
    public ComuniSearchRow() {
    }
//End ComuniSearchRow: constructor

//ComuniSearchRow: method(s) of FILTER_SEARCH @20-0D574A29
    public TextField getFILTER_SEARCHField() {
        return FILTER_SEARCH;
    }

    public String getFILTER_SEARCH() {
        return FILTER_SEARCH.getValue();
    }

    public void setFILTER_SEARCH(String value) {
        this.FILTER_SEARCH.setValue(value);
    }
//End ComuniSearchRow: method(s) of FILTER_SEARCH

//ComuniSearchRow: method(s) of s_COMUNE @3-0A1BD254
    public TextField getS_COMUNEField() {
        return s_COMUNE;
    }

    public String getS_COMUNE() {
        return s_COMUNE.getValue();
    }

    public void setS_COMUNE(String value) {
        this.s_COMUNE.setValue(value);
    }
//End ComuniSearchRow: method(s) of s_COMUNE

//ComuniSearchRow: method(s) of s_PROVINCIA_SIGLA @14-E4EFF661
    public TextField getS_PROVINCIA_SIGLAField() {
        return s_PROVINCIA_SIGLA;
    }

    public String getS_PROVINCIA_SIGLA() {
        return s_PROVINCIA_SIGLA.getValue();
    }

    public void setS_PROVINCIA_SIGLA(String value) {
        this.s_PROVINCIA_SIGLA.setValue(value);
    }
//End ComuniSearchRow: method(s) of s_PROVINCIA_SIGLA

//ComuniSearchRow: class tail @2-FCB6E20C
}
//End ComuniSearchRow: class tail

