//ldap_gruppoRow: import @136-2F73E533
package ad4web.AD4LdapGruppo;

import java.util.*;
import com.codecharge.db.*;
//End ldap_gruppoRow: import

//ldap_gruppoRow: class head @136-8D17FA05
public class ldap_gruppoRow {
//End ldap_gruppoRow: class head

//ldap_gruppoRow: declare fiels @136-FE589933
    private TextField STRINGA = new TextField("STRINGA", "STRINGA");
    private TextField CHIAVE = new TextField("CHIAVE", "CHIAVE");
    private TextField OLD_STRINGA = new TextField("OLD_STRINGA", "OLD_STRINGA");
    private TextField VALORE = new TextField("VALORE", "VALORE");
//End ldap_gruppoRow: declare fiels

//ldap_gruppoRow: constructor @136-28FCEA6D
    public ldap_gruppoRow() {
    }
//End ldap_gruppoRow: constructor

//ldap_gruppoRow: method(s) of STRINGA @137-A3BF594E
    public TextField getSTRINGAField() {
        return STRINGA;
    }

    public String getSTRINGA() {
        return STRINGA.getValue();
    }

    public void setSTRINGA(String value) {
        this.STRINGA.setValue(value);
    }
//End ldap_gruppoRow: method(s) of STRINGA

//ldap_gruppoRow: method(s) of CHIAVE @150-2CA70E12
    public TextField getCHIAVEField() {
        return CHIAVE;
    }

    public String getCHIAVE() {
        return CHIAVE.getValue();
    }

    public void setCHIAVE(String value) {
        this.CHIAVE.setValue(value);
    }
//End ldap_gruppoRow: method(s) of CHIAVE

//ldap_gruppoRow: method(s) of OLD_STRINGA @159-F017FE98
    public TextField getOLD_STRINGAField() {
        return OLD_STRINGA;
    }

    public String getOLD_STRINGA() {
        return OLD_STRINGA.getValue();
    }

    public void setOLD_STRINGA(String value) {
        this.OLD_STRINGA.setValue(value);
    }
//End ldap_gruppoRow: method(s) of OLD_STRINGA

//ldap_gruppoRow: method(s) of VALORE @138-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End ldap_gruppoRow: method(s) of VALORE

//ldap_gruppoRow: class tail @136-FCB6E20C
}
//End ldap_gruppoRow: class tail

