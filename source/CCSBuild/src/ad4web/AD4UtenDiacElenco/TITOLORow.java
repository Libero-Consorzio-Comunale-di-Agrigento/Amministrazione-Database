//TITOLORow: import @146-2C2FD85A
package ad4web.AD4UtenDiacElenco;

import java.util.*;
import com.codecharge.db.*;
//End TITOLORow: import

//TITOLORow: class head @146-FFB5C417
public class TITOLORow {
//End TITOLORow: class head

//TITOLORow: declare fiels @146-5B989561
    private TextField TITOLO = new TextField("TITOLO", "TITOLO");
    private TextField INDIETRO = new TextField("INDIETRO", "INDIETRO");
    private TextField AD4BP = new TextField("AD4BP", "AD4BP");
//End TITOLORow: declare fiels

//TITOLORow: constructor @146-CF55D09D
    public TITOLORow() {
    }
//End TITOLORow: constructor

//TITOLORow: method(s) of TITOLO @147-FB48796E
    public TextField getTITOLOField() {
        return TITOLO;
    }

    public String getTITOLO() {
        return TITOLO.getValue();
    }

    public void setTITOLO(String value) {
        this.TITOLO.setValue(value);
    }
//End TITOLORow: method(s) of TITOLO

//TITOLORow: method(s) of INDIETRO @184-27D6FC7A
    public TextField getINDIETROField() {
        return INDIETRO;
    }

    public String getINDIETRO() {
        return INDIETRO.getValue();
    }

    public void setINDIETRO(String value) {
        this.INDIETRO.setValue(value);
    }
//End TITOLORow: method(s) of INDIETRO

//TITOLORow: method(s) of AD4BP @AD4BP-AA4FE36E
    public TextField getAD4BPField() {
        return AD4BP;
    }

    public String getAD4BP() {
        return AD4BP.getValue();
    }

    public void setAD4BP(String value) {
        this.AD4BP.setValue(value);
    }
//End TITOLORow: method(s) of AD4BP

//TITOLORow: class tail @146-FCB6E20C
}
//End TITOLORow: class tail

