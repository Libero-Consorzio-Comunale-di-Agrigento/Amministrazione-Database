//AD4ProgettiVRow: import @142-472E1C9D
package ad4web.AD4ProgettiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4ProgettiVRow: import

//AD4ProgettiVRow: class head @142-5D538CF1
public class AD4ProgettiVRow {
//End AD4ProgettiVRow: class head

//AD4ProgettiVRow: declare fiels @142-E4506C5D
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private LongField PRIORITA = new LongField("PRIORITA", "PRIORITA");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField CaratteristicheAccesso = new TextField("CaratteristicheAccesso", "");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End AD4ProgettiVRow: declare fiels

//AD4ProgettiVRow: constructor @142-9B9E1E3A
    public AD4ProgettiVRow() {
    }
//End AD4ProgettiVRow: constructor

//AD4ProgettiVRow: method(s) of PROGETTO @278-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of PROGETTO

//AD4ProgettiVRow: method(s) of DESCRIZIONE @263-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of DESCRIZIONE

//AD4ProgettiVRow: method(s) of PRIORITA @333-D666E662
    public LongField getPRIORITAField() {
        return PRIORITA;
    }

    public Long getPRIORITA() {
        return PRIORITA.getValue();
    }

    public void setPRIORITA(Long value) {
        this.PRIORITA.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of PRIORITA

//AD4ProgettiVRow: method(s) of NOTE @335-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of NOTE

//AD4ProgettiVRow: method(s) of CaratteristicheAccesso @343-472E96DE
    public TextField getCaratteristicheAccessoField() {
        return CaratteristicheAccesso;
    }

    public String getCaratteristicheAccesso() {
        return CaratteristicheAccesso.getValue();
    }

    public void setCaratteristicheAccesso(String value) {
        this.CaratteristicheAccesso.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of CaratteristicheAccesso

//AD4ProgettiVRow: method(s) of AFCNavigator @339-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4ProgettiVRow: method(s) of AFCNavigator

//AD4ProgettiVRow: class tail @142-FCB6E20C
}
//End AD4ProgettiVRow: class tail

