//AttributoHeaderRow: import @2-02BD1203
package ad4web.Ad4DizionariProvinciaImposta;

import java.util.*;
import com.codecharge.db.*;
//End AttributoHeaderRow: import

//AttributoHeaderRow: class head @2-F351AC90
public class AttributoHeaderRow {
//End AttributoHeaderRow: class head

//AttributoHeaderRow: declare fiels @2-824E6861
    private TextField Indietro = new TextField("Indietro", "Indietro");
//End AttributoHeaderRow: declare fiels

//AttributoHeaderRow: constructor @2-86939E32
    public AttributoHeaderRow() {
    }
//End AttributoHeaderRow: constructor

//AttributoHeaderRow: method(s) of Indietro @3-1D926309
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

//AttributoHeaderRow: class tail @2-FCB6E20C
}
//End AttributoHeaderRow: class tail

