//AD4_CHIAVIRow: import @99-1F31A8E1
package ad4web.AD4Stringa;

import java.util.*;
import com.codecharge.db.*;
//End AD4_CHIAVIRow: import

//AD4_CHIAVIRow: class head @99-540BC956
public class AD4_CHIAVIRow {
//End AD4_CHIAVIRow: class head

//AD4_CHIAVIRow: declare fiels @99-25A9B083
    private TextField TITOLO = new TextField("TITOLO", "TITOLO");
    private TextField PADRE = new TextField("PADRE", "PADRE");
    private TextField SUB_CHIAVE = new TextField("SUB_CHIAVE", "SUB_CHIAVE");
    private TextField CHIAVE = new TextField("CHIAVE", "CHIAVE");
    private TextField CHIAVE_OLD = new TextField("CHIAVE_OLD", "CHIAVE_OLD");
    private TextField TIPOR = new TextField("TIPOR", "TIPOR");
//End AD4_CHIAVIRow: declare fiels

//AD4_CHIAVIRow: constructor @99-6B541A2E
    public AD4_CHIAVIRow() {
    }
//End AD4_CHIAVIRow: constructor

//AD4_CHIAVIRow: method(s) of TITOLO @165-FB48796E
    public TextField getTITOLOField() {
        return TITOLO;
    }

    public String getTITOLO() {
        return TITOLO.getValue();
    }

    public void setTITOLO(String value) {
        this.TITOLO.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of TITOLO

//AD4_CHIAVIRow: method(s) of PADRE @161-5B3F1133
    public TextField getPADREField() {
        return PADRE;
    }

    public String getPADRE() {
        return PADRE.getValue();
    }

    public void setPADRE(String value) {
        this.PADRE.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of PADRE

//AD4_CHIAVIRow: method(s) of SUB_CHIAVE @157-782971DE
    public TextField getSUB_CHIAVEField() {
        return SUB_CHIAVE;
    }

    public String getSUB_CHIAVE() {
        return SUB_CHIAVE.getValue();
    }

    public void setSUB_CHIAVE(String value) {
        this.SUB_CHIAVE.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of SUB_CHIAVE

//AD4_CHIAVIRow: method(s) of CHIAVE @162-2CA70E12
    public TextField getCHIAVEField() {
        return CHIAVE;
    }

    public String getCHIAVE() {
        return CHIAVE.getValue();
    }

    public void setCHIAVE(String value) {
        this.CHIAVE.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of CHIAVE

//AD4_CHIAVIRow: method(s) of CHIAVE_OLD @101-5F28DEDF
    public TextField getCHIAVE_OLDField() {
        return CHIAVE_OLD;
    }

    public String getCHIAVE_OLD() {
        return CHIAVE_OLD.getValue();
    }

    public void setCHIAVE_OLD(String value) {
        this.CHIAVE_OLD.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of CHIAVE_OLD

//AD4_CHIAVIRow: method(s) of TIPOR @156-A9BE4CAE
    public TextField getTIPORField() {
        return TIPOR;
    }

    public String getTIPOR() {
        return TIPOR.getValue();
    }

    public void setTIPOR(String value) {
        this.TIPOR.setValue(value);
    }
//End AD4_CHIAVIRow: method(s) of TIPOR

//AD4_CHIAVIRow: class tail @99-FCB6E20C
}
//End AD4_CHIAVIRow: class tail

