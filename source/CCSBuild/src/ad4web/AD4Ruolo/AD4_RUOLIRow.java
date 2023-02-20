//AD4_RUOLIRow: import @23-C2EA29D2
package ad4web.AD4Ruolo;

import java.util.*;
import com.codecharge.db.*;
//End AD4_RUOLIRow: import

//AD4_RUOLIRow: class head @23-9B0929FB
public class AD4_RUOLIRow {
//End AD4_RUOLIRow: class head

//AD4_RUOLIRow: declare fiels @23-97B029B2
    private TextField RUOLO = new TextField("RUOLO", "RUOLO");
    private TextField RUOLO_ORIG = new TextField("RUOLO_ORIG", "RUOLO_ORIG");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField ISLISTBOX = new TextField("ISLISTBOX", "");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private LongField PROFILO = new LongField("PROFILO", "PROFILO");
    private TextField STATO = new TextField("STATO", "STATO");
    private TextField GRUPPO_LAVORO = new TextField("GRUPPO_LAVORO", "GRUPPO_LAVORO");
    private TextField GRUPPO_SO = new TextField("GRUPPO_SO", "GRUPPO_SO");
//End AD4_RUOLIRow: declare fiels

//AD4_RUOLIRow: constructor @23-E5DBD9E7
    public AD4_RUOLIRow() {
    }
//End AD4_RUOLIRow: constructor

//AD4_RUOLIRow: method(s) of RUOLO @30-E9232889
    public TextField getRUOLOField() {
        return RUOLO;
    }

    public String getRUOLO() {
        return RUOLO.getValue();
    }

    public void setRUOLO(String value) {
        this.RUOLO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of RUOLO

//AD4_RUOLIRow: method(s) of RUOLO_ORIG @41-DA2B0C44
    public TextField getRUOLO_ORIGField() {
        return RUOLO_ORIG;
    }

    public String getRUOLO_ORIG() {
        return RUOLO_ORIG.getValue();
    }

    public void setRUOLO_ORIG(String value) {
        this.RUOLO_ORIG.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of RUOLO_ORIG

//AD4_RUOLIRow: method(s) of DESCRIZIONE @31-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of DESCRIZIONE

//AD4_RUOLIRow: method(s) of PROGETTO @61-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of PROGETTO

//AD4_RUOLIRow: method(s) of ISLISTBOX @75-8158BD72
    public TextField getISLISTBOXField() {
        return ISLISTBOX;
    }

    public String getISLISTBOX() {
        return ISLISTBOX.getValue();
    }

    public void setISLISTBOX(String value) {
        this.ISLISTBOX.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of ISLISTBOX

//AD4_RUOLIRow: method(s) of MODULO @33-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of MODULO

//AD4_RUOLIRow: method(s) of PROFILO @51-E8D6E768
    public LongField getPROFILOField() {
        return PROFILO;
    }

    public Long getPROFILO() {
        return PROFILO.getValue();
    }

    public void setPROFILO(Long value) {
        this.PROFILO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of PROFILO

//AD4_RUOLIRow: method(s) of STATO @82-B34568E8
    public TextField getSTATOField() {
        return STATO;
    }

    public String getSTATO() {
        return STATO.getValue();
    }

    public void setSTATO(String value) {
        this.STATO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of STATO

//AD4_RUOLIRow: method(s) of GRUPPO_LAVORO @83-D83D33C7
    public TextField getGRUPPO_LAVOROField() {
        return GRUPPO_LAVORO;
    }

    public String getGRUPPO_LAVORO() {
        return GRUPPO_LAVORO.getValue();
    }

    public void setGRUPPO_LAVORO(String value) {
        this.GRUPPO_LAVORO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of GRUPPO_LAVORO

//AD4_RUOLIRow: method(s) of GRUPPO_SO @84-2163F8C5
    public TextField getGRUPPO_SOField() {
        return GRUPPO_SO;
    }

    public String getGRUPPO_SO() {
        return GRUPPO_SO.getValue();
    }

    public void setGRUPPO_SO(String value) {
        this.GRUPPO_SO.setValue(value);
    }
//End AD4_RUOLIRow: method(s) of GRUPPO_SO

//AD4_RUOLIRow: class tail @23-FCB6E20C
}
//End AD4_RUOLIRow: class tail

