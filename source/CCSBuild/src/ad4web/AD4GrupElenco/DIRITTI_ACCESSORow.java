//DIRITTI_ACCESSORow: import @146-DCBD683B
package ad4web.AD4GrupElenco;

import java.util.*;
import com.codecharge.db.*;
//End DIRITTI_ACCESSORow: import

//DIRITTI_ACCESSORow: class head @146-BE97DD40
public class DIRITTI_ACCESSORow {
//End DIRITTI_ACCESSORow: class head

//DIRITTI_ACCESSORow: declare fiels @146-D33AA980
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
//End DIRITTI_ACCESSORow: declare fiels

//DIRITTI_ACCESSORow: constructor @146-4F39847D
    public DIRITTI_ACCESSORow() {
    }
//End DIRITTI_ACCESSORow: constructor

//DIRITTI_ACCESSORow: method(s) of NOMINATIVO @148-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End DIRITTI_ACCESSORow: method(s) of NOMINATIVO

//DIRITTI_ACCESSORow: class tail @146-FCB6E20C
}
//End DIRITTI_ACCESSORow: class tail

