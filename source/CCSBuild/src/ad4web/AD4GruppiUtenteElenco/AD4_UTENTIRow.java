//AD4_UTENTIRow: import @78-D4761356
package ad4web.AD4GruppiUtenteElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTIRow: import

//AD4_UTENTIRow: class head @78-D8604D31
public class AD4_UTENTIRow {
//End AD4_UTENTIRow: class head

//AD4_UTENTIRow: declare fiels @78-D33AA980
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
//End AD4_UTENTIRow: declare fiels

//AD4_UTENTIRow: constructor @78-A3432298
    public AD4_UTENTIRow() {
    }
//End AD4_UTENTIRow: constructor

//AD4_UTENTIRow: method(s) of NOMINATIVO @80-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NOMINATIVO

//AD4_UTENTIRow: class tail @78-FCB6E20C
}
//End AD4_UTENTIRow: class tail

