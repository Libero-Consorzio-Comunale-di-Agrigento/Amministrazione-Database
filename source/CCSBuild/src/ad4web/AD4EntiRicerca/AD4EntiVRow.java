//AD4EntiVRow: import @142-EA0254E8
package ad4web.AD4EntiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4EntiVRow: import

//AD4EntiVRow: class head @142-3B649C4F
public class AD4EntiVRow {
//End AD4EntiVRow: class head

//AD4EntiVRow: declare fiels @142-EB31FEB9
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField DATI_ENTE = new TextField("DATI_ENTE", "DATI_ENTE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField ENTE = new TextField("ENTE", "ENTE");
//End AD4EntiVRow: declare fiels

//AD4EntiVRow: constructor @142-07B217D0
    public AD4EntiVRow() {
    }
//End AD4EntiVRow: constructor

//AD4EntiVRow: method(s) of DESCRIZIONE @278-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4EntiVRow: method(s) of DESCRIZIONE

//AD4EntiVRow: method(s) of DATI_ENTE @263-E3CDE898
    public TextField getDATI_ENTEField() {
        return DATI_ENTE;
    }

    public String getDATI_ENTE() {
        return DATI_ENTE.getValue();
    }

    public void setDATI_ENTE(String value) {
        this.DATI_ENTE.setValue(value);
    }
//End AD4EntiVRow: method(s) of DATI_ENTE

//AD4EntiVRow: method(s) of AFCNavigator @340-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4EntiVRow: method(s) of AFCNavigator

//AD4EntiVRow: method(s) of ENTE @281-D1A00E41
    public TextField getENTEField() {
        return ENTE;
    }

    public String getENTE() {
        return ENTE.getValue();
    }

    public void setENTE(String value) {
        this.ENTE.setValue(value);
    }
//End AD4EntiVRow: method(s) of ENTE

//AD4EntiVRow: class tail @142-FCB6E20C
}
//End AD4EntiVRow: class tail

