//ComuniRow: import @5-A04945A1
package restrict.Ad4DizionariComuniLov;

import java.util.*;
import com.codecharge.db.*;
//End ComuniRow: import

//ComuniRow: class head @5-5958697C
public class ComuniRow {
//End ComuniRow: class head

//ComuniRow: declare fiels @5-22D429E4
    private TextField COMUNE = new TextField("COMUNE", "COMUNE");
    private TextField PROVINCIA = new TextField("PROVINCIA", "PROVINCIA");
    private TextField SOPPRESSIONE = new TextField("SOPPRESSIONE", "SOPPRESSIONE");
//End ComuniRow: declare fiels

//ComuniRow: constructor @5-4EC5A89B
    public ComuniRow() {
    }
//End ComuniRow: constructor

//ComuniRow: method(s) of COMUNE @6-B41017A6
    public TextField getCOMUNEField() {
        return COMUNE;
    }

    public String getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(String value) {
        this.COMUNE.setValue(value);
    }
//End ComuniRow: method(s) of COMUNE

//ComuniRow: method(s) of PROVINCIA @12-0A4A498E
    public TextField getPROVINCIAField() {
        return PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA.setValue(value);
    }
//End ComuniRow: method(s) of PROVINCIA

//ComuniRow: method(s) of SOPPRESSIONE @24-150544B2
    public TextField getSOPPRESSIONEField() {
        return SOPPRESSIONE;
    }

    public String getSOPPRESSIONE() {
        return SOPPRESSIONE.getValue();
    }

    public void setSOPPRESSIONE(String value) {
        this.SOPPRESSIONE.setValue(value);
    }
//End ComuniRow: method(s) of SOPPRESSIONE

//ComuniRow: class tail @5-FCB6E20C
}
//End ComuniRow: class tail

