//TitoloRow: import @40-2110BD57
package restrict.Ad4DizionariAslComuniElenco;

import java.util.*;
import com.codecharge.db.*;
//End TitoloRow: import

//TitoloRow: class head @40-8DE2374C
public class TitoloRow {
//End TitoloRow: class head

//TitoloRow: declare fiels @40-063B2430
    private TextField TITOLO = new TextField("TITOLO", "TITOLO");
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
//End TitoloRow: declare fiels

//TitoloRow: constructor @40-65226797
    public TitoloRow() {
    }
//End TitoloRow: constructor

//TitoloRow: method(s) of TITOLO @41-FB48796E
    public TextField getTITOLOField() {
        return TITOLO;
    }

    public String getTITOLO() {
        return TITOLO.getValue();
    }

    public void setTITOLO(String value) {
        this.TITOLO.setValue(value);
    }
//End TitoloRow: method(s) of TITOLO

//TitoloRow: method(s) of Aggiungi @45-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End TitoloRow: method(s) of Aggiungi

//TitoloRow: class tail @40-FCB6E20C
}
//End TitoloRow: class tail

