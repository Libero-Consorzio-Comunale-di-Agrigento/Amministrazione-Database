//TitoloRow: import @135-1292E5BC
package ad4web.AD4RichiestaParametri;

import java.util.*;
import com.codecharge.db.*;
//End TitoloRow: import

//TitoloRow: class head @135-8DE2374C
public class TitoloRow {
//End TitoloRow: class head

//TitoloRow: declare fiels @135-B68FBAC4
    private TextField Nuovo = new TextField("Nuovo", "1");
//End TitoloRow: declare fiels

//TitoloRow: constructor @135-65226797
    public TitoloRow() {
    }
//End TitoloRow: constructor

//TitoloRow: method(s) of Nuovo @143-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End TitoloRow: method(s) of Nuovo

//TitoloRow: class tail @135-FCB6E20C
}
//End TitoloRow: class tail

