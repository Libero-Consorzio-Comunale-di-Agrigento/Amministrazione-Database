//UTENTIRow: import @80-444D74E7
package ad4web.AD4GruppoMod;

import java.util.*;
import com.codecharge.db.*;
//End UTENTIRow: import

//UTENTIRow: class head @80-3B5B4558
public class UTENTIRow {
//End UTENTIRow: class head

//UTENTIRow: declare fiels @80-8445A4B9
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField GRUPPO = new TextField("GRUPPO", "GRUPPO");
    private TextField Indietro = new TextField("Indietro", "");
//End UTENTIRow: declare fiels

//UTENTIRow: constructor @80-D1B97CD1
    public UTENTIRow() {
    }
//End UTENTIRow: constructor

//UTENTIRow: method(s) of NOMINATIVO @81-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End UTENTIRow: method(s) of NOMINATIVO

//UTENTIRow: method(s) of GRUPPO @105-C886BC8A
    public TextField getGRUPPOField() {
        return GRUPPO;
    }

    public String getGRUPPO() {
        return GRUPPO.getValue();
    }

    public void setGRUPPO(String value) {
        this.GRUPPO.setValue(value);
    }
//End UTENTIRow: method(s) of GRUPPO

//UTENTIRow: method(s) of Indietro @101-1D926309
    public TextField getIndietroField() {
        return Indietro;
    }

    public String getIndietro() {
        return Indietro.getValue();
    }

    public void setIndietro(String value) {
        this.Indietro.setValue(value);
    }
//End UTENTIRow: method(s) of Indietro

//UTENTIRow: class tail @80-FCB6E20C
}
//End UTENTIRow: class tail

