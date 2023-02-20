//AD4_MODULIRow: import @6-A88BEE49
package ad4web.AD4ModuElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_MODULIRow: import

//AD4_MODULIRow: class head @6-847A18DD
public class AD4_MODULIRow {
//End AD4_MODULIRow: class head

//AD4_MODULIRow: declare fiels @6-22C2A96D
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField AMMINISTRATORE = new TextField("AMMINISTRATORE", "AMMINISTRATORE");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField CaratteristicheAccesso = new TextField("CaratteristicheAccesso", "");
    private TextField Abilitazioni = new TextField("Abilitazioni", "");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End AD4_MODULIRow: declare fiels

//AD4_MODULIRow: constructor @6-39155B6B
    public AD4_MODULIRow() {
    }
//End AD4_MODULIRow: constructor

//AD4_MODULIRow: method(s) of MODULO @12-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_MODULIRow: method(s) of MODULO

//AD4_MODULIRow: method(s) of PROGETTO @83-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_MODULIRow: method(s) of PROGETTO

//AD4_MODULIRow: method(s) of DESCRIZIONE @14-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_MODULIRow: method(s) of DESCRIZIONE

//AD4_MODULIRow: method(s) of AMMINISTRATORE @84-1A240544
    public TextField getAMMINISTRATOREField() {
        return AMMINISTRATORE;
    }

    public String getAMMINISTRATORE() {
        return AMMINISTRATORE.getValue();
    }

    public void setAMMINISTRATORE(String value) {
        this.AMMINISTRATORE.setValue(value);
    }
//End AD4_MODULIRow: method(s) of AMMINISTRATORE

//AD4_MODULIRow: method(s) of NOTE @16-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_MODULIRow: method(s) of NOTE

//AD4_MODULIRow: method(s) of CaratteristicheAccesso @78-472E96DE
    public TextField getCaratteristicheAccessoField() {
        return CaratteristicheAccesso;
    }

    public String getCaratteristicheAccesso() {
        return CaratteristicheAccesso.getValue();
    }

    public void setCaratteristicheAccesso(String value) {
        this.CaratteristicheAccesso.setValue(value);
    }
//End AD4_MODULIRow: method(s) of CaratteristicheAccesso

//AD4_MODULIRow: method(s) of Abilitazioni @76-EF7A06DD
    public TextField getAbilitazioniField() {
        return Abilitazioni;
    }

    public String getAbilitazioni() {
        return Abilitazioni.getValue();
    }

    public void setAbilitazioni(String value) {
        this.Abilitazioni.setValue(value);
    }
//End AD4_MODULIRow: method(s) of Abilitazioni

//AD4_MODULIRow: method(s) of AFCNavigator @68-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4_MODULIRow: method(s) of AFCNavigator

//AD4_MODULIRow: class tail @6-FCB6E20C
}
//End AD4_MODULIRow: class tail

