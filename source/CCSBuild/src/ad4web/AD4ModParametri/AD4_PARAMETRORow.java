//AD4_PARAMETRORow: import @23-6E6892E7
package ad4web.AD4ModParametri;

import java.util.*;
import com.codecharge.db.*;
//End AD4_PARAMETRORow: import

//AD4_PARAMETRORow: class head @23-096EBD00
public class AD4_PARAMETRORow {
//End AD4_PARAMETRORow: class head

//AD4_PARAMETRORow: declare fiels @23-C3A291E8
    private TextField PARAMETRO = new TextField("PARAMETRO", "PARAMETRO");
    private TextField ID_PARAMETRO = new TextField("ID_PARAMETRO", "ID_PARAMETRO");
    private TextField ID_RICHIESTA = new TextField("ID_RICHIESTA", "ID_RICHIESTA");
    private TextField VALORE = new TextField("VALORE", "VALORE");
//End AD4_PARAMETRORow: declare fiels

//AD4_PARAMETRORow: constructor @23-0FAF274E
    public AD4_PARAMETRORow() {
    }
//End AD4_PARAMETRORow: constructor

//AD4_PARAMETRORow: method(s) of PARAMETRO @30-E0500EC9
    public TextField getPARAMETROField() {
        return PARAMETRO;
    }

    public String getPARAMETRO() {
        return PARAMETRO.getValue();
    }

    public void setPARAMETRO(String value) {
        this.PARAMETRO.setValue(value);
    }
//End AD4_PARAMETRORow: method(s) of PARAMETRO

//AD4_PARAMETRORow: method(s) of ID_PARAMETRO @41-71F6E318
    public TextField getID_PARAMETROField() {
        return ID_PARAMETRO;
    }

    public String getID_PARAMETRO() {
        return ID_PARAMETRO.getValue();
    }

    public void setID_PARAMETRO(String value) {
        this.ID_PARAMETRO.setValue(value);
    }
//End AD4_PARAMETRORow: method(s) of ID_PARAMETRO

//AD4_PARAMETRORow: method(s) of ID_RICHIESTA @73-1BA7EEE1
    public TextField getID_RICHIESTAField() {
        return ID_RICHIESTA;
    }

    public String getID_RICHIESTA() {
        return ID_RICHIESTA.getValue();
    }

    public void setID_RICHIESTA(String value) {
        this.ID_RICHIESTA.setValue(value);
    }
//End AD4_PARAMETRORow: method(s) of ID_RICHIESTA

//AD4_PARAMETRORow: method(s) of VALORE @31-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End AD4_PARAMETRORow: method(s) of VALORE

//AD4_PARAMETRORow: class tail @23-FCB6E20C
}
//End AD4_PARAMETRORow: class tail

