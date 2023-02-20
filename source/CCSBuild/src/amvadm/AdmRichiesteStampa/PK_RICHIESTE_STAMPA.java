//PK_RICHIESTE_STAMPA: import @2-7C61AE3B
package amvadm.AdmRichiesteStampa;

import java.util.*;
import com.codecharge.db.*;
//End PK_RICHIESTE_STAMPA: import

//PK_RICHIESTE_STAMPA: class head @2-3257675F
public class PK_RICHIESTE_STAMPA {
//End PK_RICHIESTE_STAMPA: class head

//RICHIESTE_STAMPARow: declare fields @2-E358EB88
    private DoubleField ID_CREDENZIALE = new DoubleField( "ID_CREDENZIALE", "ID_CREDENZIALE" );
//End RICHIESTE_STAMPARow: declare fields

//PK_RICHIESTE_STAMPA: method(s) of ID_CREDENZIALE @22-1017B4A0
    public DoubleField getID_CREDENZIALEField() {
        return ID_CREDENZIALE;
    }

    public Double getID_CREDENZIALE() {
        return ID_CREDENZIALE.getValue();
    }

    public void setID_CREDENZIALE(Double value) {
        this.ID_CREDENZIALE.setValue(value);
    }
//End PK_RICHIESTE_STAMPA: method(s) of ID_CREDENZIALE

//PK_RICHIESTE_STAMPA: class tail @2-FCB6E20C
}
//End PK_RICHIESTE_STAMPA: class tail

