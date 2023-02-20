//AttributoHeaderRow: import @44-762B7467
package restrict.Ad4DizionariAslComuneImposta;

import java.util.*;
import com.codecharge.db.*;
//End AttributoHeaderRow: import

//AttributoHeaderRow: class head @44-F351AC90
public class AttributoHeaderRow {
//End AttributoHeaderRow: class head

//AttributoHeaderRow: declare fiels @44-824E6861
    private TextField Indietro = new TextField("Indietro", "Indietro");
//End AttributoHeaderRow: declare fiels

//AttributoHeaderRow: constructor @44-86939E32
    public AttributoHeaderRow() {
    }
//End AttributoHeaderRow: constructor

//AttributoHeaderRow: method(s) of Indietro @45-1D926309
    public TextField getIndietroField() {
        return Indietro;
    }

    public String getIndietro() {
        return Indietro.getValue();
    }

    public void setIndietro(String value) {
        this.Indietro.setValue(value);
    }
//End AttributoHeaderRow: method(s) of Indietro

//AttributoHeaderRow: class tail @44-FCB6E20C
}
//End AttributoHeaderRow: class tail

