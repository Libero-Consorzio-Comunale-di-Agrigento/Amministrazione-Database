//TAB_FOLDERRow: import @186-1F03BD27
package amvadm.AdmRichieste;

import java.util.*;
import com.codecharge.db.*;
//End TAB_FOLDERRow: import

//TAB_FOLDERRow: class head @186-6AA05DB8
public class TAB_FOLDERRow {
//End TAB_FOLDERRow: class head

//TAB_FOLDERRow: declare fiels @186-E92981FF
    private TextField TAB = new TextField("TAB", "TAB");
//End TAB_FOLDERRow: declare fiels

//TAB_FOLDERRow: constructor @186-9C01D2C4
    public TAB_FOLDERRow() {
    }
//End TAB_FOLDERRow: constructor

//TAB_FOLDERRow: method(s) of TAB @187-0A7FB9DD
    public TextField getTABField() {
        return TAB;
    }

    public String getTAB() {
        return TAB.getValue();
    }

    public void setTAB(String value) {
        this.TAB.setValue(value);
    }
//End TAB_FOLDERRow: method(s) of TAB

//TAB_FOLDERRow: class tail @186-FCB6E20C
}
//End TAB_FOLDERRow: class tail

