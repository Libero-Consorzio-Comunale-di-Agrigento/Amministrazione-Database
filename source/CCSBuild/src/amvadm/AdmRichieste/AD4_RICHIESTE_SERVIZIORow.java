//AD4_RICHIESTE_SERVIZIORow: import @123-1F03BD27
package amvadm.AdmRichieste;

import java.util.*;
import com.codecharge.db.*;
//End AD4_RICHIESTE_SERVIZIORow: import

//AD4_RICHIESTE_SERVIZIORow: class head @123-530C5388
public class AD4_RICHIESTE_SERVIZIORow {
//End AD4_RICHIESTE_SERVIZIORow: class head

//AD4_RICHIESTE_SERVIZIORow: declare fiels @123-D70FB430
    private TextField DATA = new TextField("DATA", "DATA");
    private TextField AUTORE = new TextField("AUTORE", "AUTORE");
    private TextField INDIRIZZO_WEB = new TextField("INDIRIZZO_WEB", "INDIRIZZO_WEB");
    private TextField DATI = new TextField("DATI", "DATI");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField ID = new TextField("ID", "ID");
//End AD4_RICHIESTE_SERVIZIORow: declare fiels

//AD4_RICHIESTE_SERVIZIORow: constructor @123-A4680146
    public AD4_RICHIESTE_SERVIZIORow() {
    }
//End AD4_RICHIESTE_SERVIZIORow: constructor

//AD4_RICHIESTE_SERVIZIORow: method(s) of DATA @124-28E61A6F
    public TextField getDATAField() {
        return DATA;
    }

    public String getDATA() {
        return DATA.getValue();
    }

    public void setDATA(String value) {
        this.DATA.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of DATA

//AD4_RICHIESTE_SERVIZIORow: method(s) of AUTORE @125-583F757A
    public TextField getAUTOREField() {
        return AUTORE;
    }

    public String getAUTORE() {
        return AUTORE.getValue();
    }

    public void setAUTORE(String value) {
        this.AUTORE.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of AUTORE

//AD4_RICHIESTE_SERVIZIORow: method(s) of INDIRIZZO_WEB @126-F718DE73
    public TextField getINDIRIZZO_WEBField() {
        return INDIRIZZO_WEB;
    }

    public String getINDIRIZZO_WEB() {
        return INDIRIZZO_WEB.getValue();
    }

    public void setINDIRIZZO_WEB(String value) {
        this.INDIRIZZO_WEB.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of INDIRIZZO_WEB

//AD4_RICHIESTE_SERVIZIORow: method(s) of DATI @178-214A91E2
    public TextField getDATIField() {
        return DATI;
    }

    public String getDATI() {
        return DATI.getValue();
    }

    public void setDATI(String value) {
        this.DATI.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of DATI

//AD4_RICHIESTE_SERVIZIORow: method(s) of AFCNavigator @166-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of AFCNavigator

//AD4_RICHIESTE_SERVIZIORow: method(s) of ID @173-2B895796
    public TextField getIDField() {
        return ID;
    }

    public String getID() {
        return ID.getValue();
    }

    public void setID(String value) {
        this.ID.setValue(value);
    }
//End AD4_RICHIESTE_SERVIZIORow: method(s) of ID

//AD4_RICHIESTE_SERVIZIORow: class tail @123-FCB6E20C
}
//End AD4_RICHIESTE_SERVIZIORow: class tail

