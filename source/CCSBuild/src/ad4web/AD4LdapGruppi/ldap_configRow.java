//ldap_configRow: import @110-F4B7BA18
package ad4web.AD4LdapGruppi;

import java.util.*;
import com.codecharge.db.*;
//End ldap_configRow: import

//ldap_configRow: class head @110-E8056342
public class ldap_configRow {
//End ldap_configRow: class head

//ldap_configRow: declare fiels @110-E26EB882
    private TextField GRUPPO_AD4 = new TextField("GRUPPO_AD4", "GRUPPO_AD4");
    private TextField VALORE = new TextField("VALORE", "VALORE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField STRINGA = new TextField("STRINGA", "STRINGA");
//End ldap_configRow: declare fiels

//ldap_configRow: constructor @110-0E133156
    public ldap_configRow() {
    }
//End ldap_configRow: constructor

//ldap_configRow: method(s) of GRUPPO_AD4 @111-732457A8
    public TextField getGRUPPO_AD4Field() {
        return GRUPPO_AD4;
    }

    public String getGRUPPO_AD4() {
        return GRUPPO_AD4.getValue();
    }

    public void setGRUPPO_AD4(String value) {
        this.GRUPPO_AD4.setValue(value);
    }
//End ldap_configRow: method(s) of GRUPPO_AD4

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

//ldap_configRow: method(s) of AFCNavigator @131-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ldap_configRow: method(s) of AFCNavigator

//ldap_configRow: method(s) of STRINGA @127-A3BF594E
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

//ldap_configRow: class tail @110-FCB6E20C
}
//End ldap_configRow: class tail

