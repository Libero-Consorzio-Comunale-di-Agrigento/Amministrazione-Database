//PARAMETRI_RICHIESTARow: import @6-1292E5BC
package ad4web.AD4RichiestaParametri;

import java.util.*;
import com.codecharge.db.*;
//End PARAMETRI_RICHIESTARow: import

//PARAMETRI_RICHIESTARow: class head @6-13FEF83D
public class PARAMETRI_RICHIESTARow {
//End PARAMETRI_RICHIESTARow: class head

//PARAMETRI_RICHIESTARow: declare fiels @6-B4929447
    private TextField ID_PARAMETRO = new TextField("ID_PARAMETRO", "ID_PARAMETRO");
    private TextField ID_RICHIESTA = new TextField("ID_RICHIESTA", "ID_RICHIESTA");
    private TextField PARAMETRO = new TextField("PARAMETRO", "PARAMETRO");
    private TextField VALORE = new TextField("VALORE", "VALORE");
    private TextField Indietro = new TextField("Indietro", "Indietro");
    private TextField IDPAR = new TextField("IDPAR", "ID_PARAMETRO");
//End PARAMETRI_RICHIESTARow: declare fiels

//PARAMETRI_RICHIESTARow: constructor @6-E7649800
    public PARAMETRI_RICHIESTARow() {
    }
//End PARAMETRI_RICHIESTARow: constructor

//PARAMETRI_RICHIESTARow: method(s) of ID_PARAMETRO @166-71F6E318
    public TextField getID_PARAMETROField() {
        return ID_PARAMETRO;
    }

    public String getID_PARAMETRO() {
        return ID_PARAMETRO.getValue();
    }

    public void setID_PARAMETRO(String value) {
        this.ID_PARAMETRO.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of ID_PARAMETRO

//PARAMETRI_RICHIESTARow: method(s) of ID_RICHIESTA @167-1BA7EEE1
    public TextField getID_RICHIESTAField() {
        return ID_RICHIESTA;
    }

    public String getID_RICHIESTA() {
        return ID_RICHIESTA.getValue();
    }

    public void setID_RICHIESTA(String value) {
        this.ID_RICHIESTA.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of ID_RICHIESTA

//PARAMETRI_RICHIESTARow: method(s) of PARAMETRO @118-E0500EC9
    public TextField getPARAMETROField() {
        return PARAMETRO;
    }

    public String getPARAMETRO() {
        return PARAMETRO.getValue();
    }

    public void setPARAMETRO(String value) {
        this.PARAMETRO.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of PARAMETRO

//PARAMETRI_RICHIESTARow: method(s) of VALORE @120-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of VALORE

//PARAMETRI_RICHIESTARow: method(s) of Indietro @168-1D926309
    public TextField getIndietroField() {
        return Indietro;
    }

    public String getIndietro() {
        return Indietro.getValue();
    }

    public void setIndietro(String value) {
        this.Indietro.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of Indietro

//PARAMETRI_RICHIESTARow: method(s) of IDPAR @165-E9667D8D
    public TextField getIDPARField() {
        return IDPAR;
    }

    public String getIDPAR() {
        return IDPAR.getValue();
    }

    public void setIDPAR(String value) {
        this.IDPAR.setValue(value);
    }
//End PARAMETRI_RICHIESTARow: method(s) of IDPAR

//PARAMETRI_RICHIESTARow: class tail @6-FCB6E20C
}
//End PARAMETRI_RICHIESTARow: class tail

