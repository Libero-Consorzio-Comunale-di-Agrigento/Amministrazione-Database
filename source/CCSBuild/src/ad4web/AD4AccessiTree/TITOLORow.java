//TITOLORow: import @31-5FC6A384
package ad4web.AD4AccessiTree;

import java.util.*;
import com.codecharge.db.*;
//End TITOLORow: import

//TITOLORow: class head @31-FFB5C417
public class TITOLORow {
//End TITOLORow: class head

//TITOLORow: declare fiels @31-12B88C27
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
//End TITOLORow: declare fiels

//TITOLORow: constructor @31-CF55D09D
    public TITOLORow() {
    }
//End TITOLORow: constructor

//TITOLORow: method(s) of UTENTE @32-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End TITOLORow: method(s) of UTENTE

//TITOLORow: class tail @31-FCB6E20C
}
//End TITOLORow: class tail

