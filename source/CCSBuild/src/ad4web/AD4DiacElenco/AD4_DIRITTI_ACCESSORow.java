//AD4_DIRITTI_ACCESSORow: import @6-9F5390D2
package ad4web.AD4DiacElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_DIRITTI_ACCESSORow: import

//AD4_DIRITTI_ACCESSORow: class head @6-A706698E
public class AD4_DIRITTI_ACCESSORow {
//End AD4_DIRITTI_ACCESSORow: class head

//AD4_DIRITTI_ACCESSORow: declare fiels @6-AC0D9A8D
    private LongField SEQUENZA = new LongField("SEQUENZA", "SEQUENZA");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField DES_MODULO = new TextField("DES_MODULO", "DES_MODULO");
    private TextField DES_ISTANZA = new TextField("DES_ISTANZA", "DES_ISTANZA");
    private TextField DATI = new TextField("DATI", "DATI");
    private TextField CaratteristicheAccesso = new TextField("CaratteristicheAccesso", "");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField TIPO_ACCESSO = new TextField("TIPO_ACCESSO", "TIPO_ACCESSO");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
//End AD4_DIRITTI_ACCESSORow: declare fiels

//AD4_DIRITTI_ACCESSORow: constructor @6-FB401988
    public AD4_DIRITTI_ACCESSORow() {
    }
//End AD4_DIRITTI_ACCESSORow: constructor

//AD4_DIRITTI_ACCESSORow: method(s) of SEQUENZA @22-E6112114
    public LongField getSEQUENZAField() {
        return SEQUENZA;
    }

    public Long getSEQUENZA() {
        return SEQUENZA.getValue();
    }

    public void setSEQUENZA(Long value) {
        this.SEQUENZA.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of SEQUENZA

//AD4_DIRITTI_ACCESSORow: method(s) of UTENTE @70-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of UTENTE

//AD4_DIRITTI_ACCESSORow: method(s) of DES_MODULO @17-BC58F4FE
    public TextField getDES_MODULOField() {
        return DES_MODULO;
    }

    public String getDES_MODULO() {
        return DES_MODULO.getValue();
    }

    public void setDES_MODULO(String value) {
        this.DES_MODULO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of DES_MODULO

//AD4_DIRITTI_ACCESSORow: method(s) of DES_ISTANZA @20-44FBF6B0
    public TextField getDES_ISTANZAField() {
        return DES_ISTANZA;
    }

    public String getDES_ISTANZA() {
        return DES_ISTANZA.getValue();
    }

    public void setDES_ISTANZA(String value) {
        this.DES_ISTANZA.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of DES_ISTANZA

//AD4_DIRITTI_ACCESSORow: method(s) of DATI @132-214A91E2
    public TextField getDATIField() {
        return DATI;
    }

    public String getDATI() {
        return DATI.getValue();
    }

    public void setDATI(String value) {
        this.DATI.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of DATI

//AD4_DIRITTI_ACCESSORow: method(s) of CaratteristicheAccesso @141-472E96DE
    public TextField getCaratteristicheAccessoField() {
        return CaratteristicheAccesso;
    }

    public String getCaratteristicheAccesso() {
        return CaratteristicheAccesso.getValue();
    }

    public void setCaratteristicheAccesso(String value) {
        this.CaratteristicheAccesso.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of CaratteristicheAccesso

//AD4_DIRITTI_ACCESSORow: method(s) of AFCNavigator @135-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of AFCNavigator

//AD4_DIRITTI_ACCESSORow: method(s) of MODULO @64-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of MODULO

//AD4_DIRITTI_ACCESSORow: method(s) of ISTANZA @65-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of ISTANZA

//AD4_DIRITTI_ACCESSORow: method(s) of TIPO_ACCESSO @142-D9722F75
    public TextField getTIPO_ACCESSOField() {
        return TIPO_ACCESSO;
    }

    public String getTIPO_ACCESSO() {
        return TIPO_ACCESSO.getValue();
    }

    public void setTIPO_ACCESSO(String value) {
        this.TIPO_ACCESSO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of TIPO_ACCESSO

//AD4_DIRITTI_ACCESSORow: method(s) of PROGETTO @143-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSORow: method(s) of PROGETTO

//AD4_DIRITTI_ACCESSORow: class tail @6-FCB6E20C
}
//End AD4_DIRITTI_ACCESSORow: class tail

