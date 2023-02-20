//GuidaRow: import @2-8D0C2EA5
package ad4web.Ad4DizionariZoneAslGuida;

import java.util.*;
import com.codecharge.db.*;
//End GuidaRow: import

//GuidaRow: class head @2-6A08D823
public class GuidaRow {
//End GuidaRow: class head

//GuidaRow: declare fiels @2-6B00DB80
    private TextField FOLDER1 = new TextField("FOLDER1", "FOLDER1");
    private TextField FOLDER2 = new TextField("FOLDER2", "FOLDER2");
//End GuidaRow: declare fiels

//GuidaRow: constructor @2-5B3C2AD5
    public GuidaRow() {
    }
//End GuidaRow: constructor

//GuidaRow: method(s) of FOLDER1 @3-05BCC550
    public TextField getFOLDER1Field() {
        return FOLDER1;
    }

    public String getFOLDER1() {
        return FOLDER1.getValue();
    }

    public void setFOLDER1(String value) {
        this.FOLDER1.setValue(value);
    }
//End GuidaRow: method(s) of FOLDER1

//GuidaRow: method(s) of FOLDER2 @4-B0403820
    public TextField getFOLDER2Field() {
        return FOLDER2;
    }

    public String getFOLDER2() {
        return FOLDER2.getValue();
    }

    public void setFOLDER2(String value) {
        this.FOLDER2.setValue(value);
    }
//End GuidaRow: method(s) of FOLDER2

//GuidaRow: class tail @2-FCB6E20C
}
//End GuidaRow: class tail

