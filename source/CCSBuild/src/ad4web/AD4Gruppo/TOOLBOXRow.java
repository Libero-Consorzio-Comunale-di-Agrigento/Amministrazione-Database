//TOOLBOXRow: import @97-273C5FE5
package ad4web.AD4Gruppo;

import java.util.*;
import com.codecharge.db.*;
//End TOOLBOXRow: import

//TOOLBOXRow: class head @97-9DB32276
public class TOOLBOXRow {
//End TOOLBOXRow: class head

//TOOLBOXRow: declare fiels @97-34916ACA
    private TextField UTGR_INS_MOD = new TextField("UTGR_INS_MOD", "UTGR_INS_MOD");
    private TextField GRUPPO = new TextField("GRUPPO", "GRUPPO");
//End TOOLBOXRow: declare fiels

//TOOLBOXRow: constructor @97-A4EFA233
    public TOOLBOXRow() {
    }
//End TOOLBOXRow: constructor

//TOOLBOXRow: method(s) of UTGR_INS_MOD @101-05183658
    public TextField getUTGR_INS_MODField() {
        return UTGR_INS_MOD;
    }

    public String getUTGR_INS_MOD() {
        return UTGR_INS_MOD.getValue();
    }

    public void setUTGR_INS_MOD(String value) {
        this.UTGR_INS_MOD.setValue(value);
    }
//End TOOLBOXRow: method(s) of UTGR_INS_MOD

//TOOLBOXRow: method(s) of GRUPPO @106-C886BC8A
    public TextField getGRUPPOField() {
        return GRUPPO;
    }

    public String getGRUPPO() {
        return GRUPPO.getValue();
    }

    public void setGRUPPO(String value) {
        this.GRUPPO.setValue(value);
    }
//End TOOLBOXRow: method(s) of GRUPPO

//TOOLBOXRow: class tail @97-FCB6E20C
}
//End TOOLBOXRow: class tail

