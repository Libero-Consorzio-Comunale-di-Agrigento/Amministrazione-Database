//AD4_PROGETTIRow: import @23-938A2F47
package ad4web.AD4Progetto;

import java.util.*;
import com.codecharge.db.*;
//End AD4_PROGETTIRow: import

//AD4_PROGETTIRow: class head @23-41D48DE6
public class AD4_PROGETTIRow {
//End AD4_PROGETTIRow: class head

//AD4_PROGETTIRow: declare fiels @23-6B4A7FBB
    private TextField Caratteristiche = new TextField("Caratteristiche", "");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField PROGETTO_ORIG = new TextField("PROGETTO_ORIG", "PROGETTO_ORIG");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private DoubleField PRIORITA = new DoubleField("PRIORITA", "PRIORITA");
    private TextField NOTE = new TextField("NOTE", "NOTE");
//End AD4_PROGETTIRow: declare fiels

//AD4_PROGETTIRow: constructor @23-E8308C11
    public AD4_PROGETTIRow() {
    }
//End AD4_PROGETTIRow: constructor

//AD4_PROGETTIRow: method(s) of Caratteristiche @49-5BD60891
    public TextField getCaratteristicheField() {
        return Caratteristiche;
    }

    public String getCaratteristiche() {
        return Caratteristiche.getValue();
    }

    public void setCaratteristiche(String value) {
        this.Caratteristiche.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of Caratteristiche

//AD4_PROGETTIRow: method(s) of PROGETTO @30-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of PROGETTO

//AD4_PROGETTIRow: method(s) of PROGETTO_ORIG @41-FD42BE57
    public TextField getPROGETTO_ORIGField() {
        return PROGETTO_ORIG;
    }

    public String getPROGETTO_ORIG() {
        return PROGETTO_ORIG.getValue();
    }

    public void setPROGETTO_ORIG(String value) {
        this.PROGETTO_ORIG.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of PROGETTO_ORIG

//AD4_PROGETTIRow: method(s) of DESCRIZIONE @31-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of DESCRIZIONE

//AD4_PROGETTIRow: method(s) of PRIORITA @32-77B7F635
    public DoubleField getPRIORITAField() {
        return PRIORITA;
    }

    public Double getPRIORITA() {
        return PRIORITA.getValue();
    }

    public void setPRIORITA(Double value) {
        this.PRIORITA.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of PRIORITA

//AD4_PROGETTIRow: method(s) of NOTE @33-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_PROGETTIRow: method(s) of NOTE

//AD4_PROGETTIRow: class tail @23-FCB6E20C
}
//End AD4_PROGETTIRow: class tail

