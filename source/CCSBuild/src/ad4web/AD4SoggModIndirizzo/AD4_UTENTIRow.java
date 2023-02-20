//AD4_UTENTIRow: import @2-EB2B4A77
package ad4web.AD4SoggModIndirizzo;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTIRow: import

//AD4_UTENTIRow: class head @2-D8604D31
public class AD4_UTENTIRow {
//End AD4_UTENTIRow: class head

//AD4_UTENTIRow: declare fiels @2-62C3BD0A
    private TextField VIA = new TextField("VIA", "");
    private TextField INDIRIZZO = new TextField("INDIRIZZO", "");
    private LongField NUM = new LongField("NUM", "");
//End AD4_UTENTIRow: declare fiels

//AD4_UTENTIRow: constructor @2-A3432298
    public AD4_UTENTIRow() {
    }
//End AD4_UTENTIRow: constructor

//AD4_UTENTIRow: method(s) of VIA @18-09F721F0
    public TextField getVIAField() {
        return VIA;
    }

    public String getVIA() {
        return VIA.getValue();
    }

    public void setVIA(String value) {
        this.VIA.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of VIA

//AD4_UTENTIRow: method(s) of INDIRIZZO @4-79B8981C
    public TextField getINDIRIZZOField() {
        return INDIRIZZO;
    }

    public String getINDIRIZZO() {
        return INDIRIZZO.getValue();
    }

    public void setINDIRIZZO(String value) {
        this.INDIRIZZO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of INDIRIZZO

//AD4_UTENTIRow: method(s) of NUM @19-9F247C59
    public LongField getNUMField() {
        return NUM;
    }

    public Long getNUM() {
        return NUM.getValue();
    }

    public void setNUM(Long value) {
        this.NUM.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NUM

//AD4_UTENTIRow: class tail @2-FCB6E20C
}
//End AD4_UTENTIRow: class tail

