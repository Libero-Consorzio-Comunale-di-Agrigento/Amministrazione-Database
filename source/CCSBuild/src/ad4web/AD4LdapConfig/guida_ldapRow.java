//guida_ldapRow: import @83-F2CF7305
package ad4web.AD4LdapConfig;

import java.util.*;
import com.codecharge.db.*;
//End guida_ldapRow: import

//guida_ldapRow: class head @83-08893296
public class guida_ldapRow {
//End guida_ldapRow: class head

//guida_ldapRow: declare fiels @83-3D33C3A5
    private TextField guidaLDAP = new TextField("guidaLDAP", "GUIDALDAP");
//End guida_ldapRow: declare fiels

//guida_ldapRow: constructor @83-0E055648
    public guida_ldapRow() {
    }
//End guida_ldapRow: constructor

//guida_ldapRow: method(s) of guidaLDAP @84-D1D847F3
    public TextField getGuidaLDAPField() {
        return guidaLDAP;
    }

    public String getGuidaLDAP() {
        return guidaLDAP.getValue();
    }

    public void setGuidaLDAP(String value) {
        this.guidaLDAP.setValue(value);
    }
//End guida_ldapRow: method(s) of guidaLDAP

//guida_ldapRow: class tail @83-FCB6E20C
}
//End guida_ldapRow: class tail

