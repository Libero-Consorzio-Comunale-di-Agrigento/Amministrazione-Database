//SportelliFiltroRow: import @32-B3B19EDA
package restrict.Ad4DizionariAslElenco;

import java.util.*;
import com.codecharge.db.*;
//End SportelliFiltroRow: import

//SportelliFiltroRow: class head @32-B3C35A52
public class SportelliFiltroRow {
//End SportelliFiltroRow: class head

//SportelliFiltroRow: declare fiels @32-13049BA7
    private TextField FILTER_SEARCH = new TextField("FILTER_SEARCH", "FILTER_SEARCH");
    private TextField s_REGIONE = new TextField("s_REGIONE", "S_REGIONE");
    private TextField s_ASL = new TextField("s_ASL", "S_ASL");
    private TextField s_ATTIVA = new TextField("s_ATTIVA", "");
//End SportelliFiltroRow: declare fiels

//SportelliFiltroRow: constructor @32-485F680F
    public SportelliFiltroRow() {
    }
//End SportelliFiltroRow: constructor

//SportelliFiltroRow: method(s) of FILTER_SEARCH @33-0D574A29
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

//SportelliFiltroRow: method(s) of s_REGIONE @34-F9B272A5
    public TextField getS_REGIONEField() {
        return s_REGIONE;
    }

    public String getS_REGIONE() {
        return s_REGIONE.getValue();
    }

    public void setS_REGIONE(String value) {
        this.s_REGIONE.setValue(value);
    }
//End SportelliFiltroRow: method(s) of s_REGIONE

//SportelliFiltroRow: method(s) of s_ASL @36-FB63713E
    public TextField getS_ASLField() {
        return s_ASL;
    }

    public String getS_ASL() {
        return s_ASL.getValue();
    }

    public void setS_ASL(String value) {
        this.s_ASL.setValue(value);
    }
//End SportelliFiltroRow: method(s) of s_ASL

//SportelliFiltroRow: method(s) of s_ATTIVA @40-37DB52C1
    public TextField getS_ATTIVAField() {
        return s_ATTIVA;
    }

    public String getS_ATTIVA() {
        return s_ATTIVA.getValue();
    }

    public void setS_ATTIVA(String value) {
        this.s_ATTIVA.setValue(value);
    }
//End SportelliFiltroRow: method(s) of s_ATTIVA

//SportelliFiltroRow: class tail @32-FCB6E20C
}
//End SportelliFiltroRow: class tail

