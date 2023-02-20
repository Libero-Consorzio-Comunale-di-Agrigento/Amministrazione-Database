//TitoloRow: import @43-4B286E4A
package ad4web.AD4RegistroTree;

import java.util.*;
import com.codecharge.db.*;
//End TitoloRow: import

//TitoloRow: class head @43-8DE2374C
public class TitoloRow {
//End TitoloRow: class head

//TitoloRow: declare fiels @43-0984CCE6
    private TextField USRORCL = new TextField("USRORCL", "USRORCL");
    private TextField ModChiave = new TextField("ModChiave", "MODIFICA");
    private TextField NuovaChiave = new TextField("NuovaChiave", "NUOVA_CHIAVE");
    private TextField NuovaStringa = new TextField("NuovaStringa", "NUOVA_STRINGA");
    private TextField INDIETRO = new TextField("INDIETRO", "INDIETRO");
    private TextField AD4BP = new TextField("AD4BP", "AD4BP");
//End TitoloRow: declare fiels

//TitoloRow: constructor @43-65226797
    public TitoloRow() {
    }
//End TitoloRow: constructor

//TitoloRow: method(s) of USRORCL @65-66EC325D
    public TextField getUSRORCLField() {
        return USRORCL;
    }

    public String getUSRORCL() {
        return USRORCL.getValue();
    }

    public void setUSRORCL(String value) {
        this.USRORCL.setValue(value);
    }
//End TitoloRow: method(s) of USRORCL

//TitoloRow: method(s) of ModChiave @44-69B3A478
    public TextField getModChiaveField() {
        return ModChiave;
    }

    public String getModChiave() {
        return ModChiave.getValue();
    }

    public void setModChiave(String value) {
        this.ModChiave.setValue(value);
    }
//End TitoloRow: method(s) of ModChiave

//TitoloRow: method(s) of NuovaChiave @48-7725DE0D
    public TextField getNuovaChiaveField() {
        return NuovaChiave;
    }

    public String getNuovaChiave() {
        return NuovaChiave.getValue();
    }

    public void setNuovaChiave(String value) {
        this.NuovaChiave.setValue(value);
    }
//End TitoloRow: method(s) of NuovaChiave

//TitoloRow: method(s) of NuovaStringa @52-9B86FE05
    public TextField getNuovaStringaField() {
        return NuovaStringa;
    }

    public String getNuovaStringa() {
        return NuovaStringa.getValue();
    }

    public void setNuovaStringa(String value) {
        this.NuovaStringa.setValue(value);
    }
//End TitoloRow: method(s) of NuovaStringa

//TitoloRow: method(s) of INDIETRO @57-27D6FC7A
    public TextField getINDIETROField() {
        return INDIETRO;
    }

    public String getINDIETRO() {
        return INDIETRO.getValue();
    }

    public void setINDIETRO(String value) {
        this.INDIETRO.setValue(value);
    }
//End TitoloRow: method(s) of INDIETRO

//TitoloRow: method(s) of AD4BP @AD4BP-AA4FE36E
    public TextField getAD4BPField() {
        return AD4BP;
    }

    public String getAD4BP() {
        return AD4BP.getValue();
    }

    public void setAD4BP(String value) {
        this.AD4BP.setValue(value);
    }
//End TitoloRow: method(s) of AD4BP

//TitoloRow: class tail @43-FCB6E20C
}
//End TitoloRow: class tail

