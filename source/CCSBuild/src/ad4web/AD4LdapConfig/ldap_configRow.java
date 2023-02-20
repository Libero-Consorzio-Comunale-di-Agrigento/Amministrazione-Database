//ldap_configRow: import @110-F2CF7305
package ad4web.AD4LdapConfig;

import java.util.*;
import com.codecharge.db.*;
//End ldap_configRow: import

//ldap_configRow: class head @110-E8056342
public class ldap_configRow {
//End ldap_configRow: class head

//ldap_configRow: declare fiels @110-3E1B296B
    private TextField STRINGA = new TextField("STRINGA", "STRINGA");
    private TextField VALORE = new TextField("VALORE", "VALORE");
    private TextField COMMENTO = new TextField("COMMENTO", "COMMENTO");
    private TextField ID = new TextField("ID", "CHIAVE");
    private TextField CHIAVE = new TextField("CHIAVE", "CHIAVE");
//End ldap_configRow: declare fiels

//ldap_configRow: constructor @110-0E133156
    public ldap_configRow() {
    }
//End ldap_configRow: constructor

//ldap_configRow: method(s) of STRINGA @111-A3BF594E
    public TextField getSTRINGAField() {
        return STRINGA;
    }

    public String getSTRINGA() {
        return STRINGA.getValue();
    }

    public void setSTRINGA(String value) {
        this.STRINGA.setValue(value);
    }
//End ldap_configRow: method(s) of STRINGA

//ldap_configRow: method(s) of VALORE @112-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End ldap_configRow: method(s) of VALORE

//ldap_configRow: method(s) of COMMENTO @137-0F4C155C
    public TextField getCOMMENTOField() {
        return COMMENTO;
    }

    public String getCOMMENTO() {
        return COMMENTO.getValue();
    }

    public void setCOMMENTO(String value) {
        this.COMMENTO.setValue(value);
    }
//End ldap_configRow: method(s) of COMMENTO

//ldap_configRow: method(s) of ID @126-2B895796
    public TextField getIDField() {
        return ID;
    }

    public String getID() {
        return ID.getValue();
    }

    public void setID(String value) {
        this.ID.setValue(value);
    }
//End ldap_configRow: method(s) of ID

//ldap_configRow: method(s) of CHIAVE @126-2CA70E12
    public TextField getCHIAVEField() {
        return CHIAVE;
    }

    public String getCHIAVE() {
        return CHIAVE.getValue();
    }

    public void setCHIAVE(String value) {
        this.CHIAVE.setValue(value);
    }
//End ldap_configRow: method(s) of CHIAVE

//ldap_configRow: class tail @110-FCB6E20C
}
//End ldap_configRow: class tail

