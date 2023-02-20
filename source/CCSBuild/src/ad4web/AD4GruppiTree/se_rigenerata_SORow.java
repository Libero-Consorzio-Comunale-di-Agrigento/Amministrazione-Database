//se_rigenerata_SORow: import @28-0496AAF8
package ad4web.AD4GruppiTree;

import java.util.*;
import com.codecharge.db.*;
//End se_rigenerata_SORow: import

//se_rigenerata_SORow: class head @28-097D8AB9
public class se_rigenerata_SORow {
//End se_rigenerata_SORow: class head

//se_rigenerata_SORow: declare fiels @28-4B5A23D0
    private TextField JOB_RIG_SO = new TextField("JOB_RIG_SO", "JOB_RIG_SO");
    private TextField JOB_LDAP_FROM_SO = new TextField("JOB_LDAP_FROM_SO", "JOB_LDAP_FROM_SO");
//End se_rigenerata_SORow: declare fiels

//se_rigenerata_SORow: constructor @28-67D0BC62
    public se_rigenerata_SORow() {
    }
//End se_rigenerata_SORow: constructor

//se_rigenerata_SORow: method(s) of JOB_RIG_SO @29-D4DFCDBC
    public TextField getJOB_RIG_SOField() {
        return JOB_RIG_SO;
    }

    public String getJOB_RIG_SO() {
        return JOB_RIG_SO.getValue();
    }

    public void setJOB_RIG_SO(String value) {
        this.JOB_RIG_SO.setValue(value);
    }
//End se_rigenerata_SORow: method(s) of JOB_RIG_SO

//se_rigenerata_SORow: method(s) of JOB_LDAP_FROM_SO @37-83A70AA9
    public TextField getJOB_LDAP_FROM_SOField() {
        return JOB_LDAP_FROM_SO;
    }

    public String getJOB_LDAP_FROM_SO() {
        return JOB_LDAP_FROM_SO.getValue();
    }

    public void setJOB_LDAP_FROM_SO(String value) {
        this.JOB_LDAP_FROM_SO.setValue(value);
    }
//End se_rigenerata_SORow: method(s) of JOB_LDAP_FROM_SO

//se_rigenerata_SORow: class tail @28-FCB6E20C
}
//End se_rigenerata_SORow: class tail

