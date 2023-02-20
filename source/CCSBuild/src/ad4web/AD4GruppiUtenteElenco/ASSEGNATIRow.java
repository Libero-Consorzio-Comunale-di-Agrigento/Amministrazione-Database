//ASSEGNATIRow: import @63-D4761356
package ad4web.AD4GruppiUtenteElenco;

import java.util.*;
import com.codecharge.db.*;
//End ASSEGNATIRow: import

//ASSEGNATIRow: class head @63-D2D66EFA
public class ASSEGNATIRow {
//End ASSEGNATIRow: class head

//ASSEGNATIRow: declare fiels @63-B0EC959D
    private TextField UTENTE_A = new TextField("UTENTE_A", "");
//End ASSEGNATIRow: declare fiels

//ASSEGNATIRow: constructor @63-4D4DA51E
    public ASSEGNATIRow() {
    }
//End ASSEGNATIRow: constructor

//ASSEGNATIRow: method(s) of UTENTE_A @64-FE7EAA58
    public TextField getUTENTE_AField() {
        return UTENTE_A;
    }

    public String getUTENTE_A() {
        return UTENTE_A.getValue();
    }

    public void setUTENTE_A(String value) {
        this.UTENTE_A.setValue(value);
    }
//End ASSEGNATIRow: method(s) of UTENTE_A

//ASSEGNATIRow: class tail @63-FCB6E20C
}
//End ASSEGNATIRow: class tail

