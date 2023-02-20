//PRIVACYRow: import @14-6FE36575
package common.AmvRegistrazioneInizio;

import java.util.*;
import com.codecharge.db.*;
//End PRIVACYRow: import

//PRIVACYRow: class head @14-1A11FC93
public class PRIVACYRow {
//End PRIVACYRow: class head

//PRIVACYRow: declare fiels @14-6F951316
    private TextField PRIVACY = new TextField("PRIVACY", "PRIVACY");
//End PRIVACYRow: declare fiels

//PRIVACYRow: constructor @14-2CA59AAA
    public PRIVACYRow() {
    }
//End PRIVACYRow: constructor

//PRIVACYRow: method(s) of PRIVACY @15-BB0AEF36
    public TextField getPRIVACYField() {
        return PRIVACY;
    }

    public String getPRIVACY() {
        return PRIVACY.getValue();
    }

    public void setPRIVACY(String value) {
        this.PRIVACY.setValue(value);
    }
//End PRIVACYRow: method(s) of PRIVACY

//PRIVACYRow: class tail @14-FCB6E20C
}
//End PRIVACYRow: class tail

