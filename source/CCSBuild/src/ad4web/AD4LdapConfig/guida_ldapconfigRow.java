//guida_ldapconfigRow: import @137-F2CF7305
package ad4web.AD4LdapConfig;

import java.util.*;
import com.codecharge.db.*;
//End guida_ldapconfigRow: import

//guida_ldapconfigRow: class head @137-5C5DD064
public class guida_ldapconfigRow {
//End guida_ldapconfigRow: class head

//guida_ldapconfigRow: declare fiels @137-E90F366A
    private TextField GUIDALDAPC = new TextField("GUIDALDAPC", "GUIDALDAPC");
//End guida_ldapconfigRow: declare fiels

//guida_ldapconfigRow: constructor @137-99BB8F42
    public guida_ldapconfigRow() {
    }
//End guida_ldapconfigRow: constructor

//guida_ldapconfigRow: method(s) of GUIDALDAPC @138-CA9708CB
    public TextField getGUIDALDAPCField() {
        return GUIDALDAPC;
    }

    public String getGUIDALDAPC() {
        return GUIDALDAPC.getValue();
    }

    public void setGUIDALDAPC(String value) {
        this.GUIDALDAPC.setValue(value);
    }
//End guida_ldapconfigRow: method(s) of GUIDALDAPC

//guida_ldapconfigRow: class tail @137-FCB6E20C
}
//End guida_ldapconfigRow: class tail

