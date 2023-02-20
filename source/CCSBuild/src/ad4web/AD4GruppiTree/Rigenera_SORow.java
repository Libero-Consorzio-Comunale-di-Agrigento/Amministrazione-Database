//Rigenera_SORow: import @22-0496AAF8
package ad4web.AD4GruppiTree;

import java.util.*;
import com.codecharge.db.*;
//End Rigenera_SORow: import

//Rigenera_SORow: class head @22-438B1C12
public class Rigenera_SORow {
//End Rigenera_SORow: class head

//Rigenera_SORow: declare fiels @22-A3D94DCE
    private TextField RIGENERA = new TextField("RIGENERA", "RIGENERA");
    private TextField ALLINEA_LDAP = new TextField("ALLINEA_LDAP", "ALLINEA_LDAP");
//End Rigenera_SORow: declare fiels

//Rigenera_SORow: constructor @22-C008D453
    public Rigenera_SORow() {
    }
//End Rigenera_SORow: constructor

//Rigenera_SORow: method(s) of RIGENERA @23-6D5BA762
    public TextField getRIGENERAField() {
        return RIGENERA;
    }

    public String getRIGENERA() {
        return RIGENERA.getValue();
    }

    public void setRIGENERA(String value) {
        this.RIGENERA.setValue(value);
    }
//End Rigenera_SORow: method(s) of RIGENERA

//Rigenera_SORow: method(s) of ALLINEA_LDAP @35-C639D998
    public TextField getALLINEA_LDAPField() {
        return ALLINEA_LDAP;
    }

    public String getALLINEA_LDAP() {
        return ALLINEA_LDAP.getValue();
    }

    public void setALLINEA_LDAP(String value) {
        this.ALLINEA_LDAP.setValue(value);
    }
//End Rigenera_SORow: method(s) of ALLINEA_LDAP

//Rigenera_SORow: class tail @22-FCB6E20C
}
//End Rigenera_SORow: class tail

