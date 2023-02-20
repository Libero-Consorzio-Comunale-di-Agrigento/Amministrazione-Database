//AD4_SERVIZIRow: import @6-0B984133
package ad4web.AD4ServElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_SERVIZIRow: import

//AD4_SERVIZIRow: class head @6-2B4FB4ED
public class AD4_SERVIZIRow {
//End AD4_SERVIZIRow: class head

//AD4_SERVIZIRow: declare fiels @6-034B170A
    private LongField ID_SERVIZIO = new LongField("ID_SERVIZIO", "ID_SERVIZIO");
    private TextField DES_MODULO = new TextField("DES_MODULO", "DES_MODULO");
    private TextField DES_ISTANZA = new TextField("DES_ISTANZA", "DES_ISTANZA");
    private TextField DATI = new TextField("DATI", "DATI");
    private TextField Abilitazioni = new TextField("Abilitazioni", "");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField MODULO = new TextField("MODULO", "MODULO");
//End AD4_SERVIZIRow: declare fiels

//AD4_SERVIZIRow: constructor @6-8447D09E
    public AD4_SERVIZIRow() {
    }
//End AD4_SERVIZIRow: constructor

//AD4_SERVIZIRow: method(s) of ID_SERVIZIO @22-6ECD06A2
    public LongField getID_SERVIZIOField() {
        return ID_SERVIZIO;
    }

    public Long getID_SERVIZIO() {
        return ID_SERVIZIO.getValue();
    }

    public void setID_SERVIZIO(Long value) {
        this.ID_SERVIZIO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ID_SERVIZIO

//AD4_SERVIZIRow: method(s) of DES_MODULO @17-BC58F4FE
    public TextField getDES_MODULOField() {
        return DES_MODULO;
    }

    public String getDES_MODULO() {
        return DES_MODULO.getValue();
    }

    public void setDES_MODULO(String value) {
        this.DES_MODULO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of DES_MODULO

//AD4_SERVIZIRow: method(s) of DES_ISTANZA @20-44FBF6B0
    public TextField getDES_ISTANZAField() {
        return DES_ISTANZA;
    }

    public String getDES_ISTANZA() {
        return DES_ISTANZA.getValue();
    }

    public void setDES_ISTANZA(String value) {
        this.DES_ISTANZA.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of DES_ISTANZA

//AD4_SERVIZIRow: method(s) of DATI @21-214A91E2
    public TextField getDATIField() {
        return DATI;
    }

    public String getDATI() {
        return DATI.getValue();
    }

    public void setDATI(String value) {
        this.DATI.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of DATI

//AD4_SERVIZIRow: method(s) of Abilitazioni @166-EF7A06DD
    public TextField getAbilitazioniField() {
        return Abilitazioni;
    }

    public String getAbilitazioni() {
        return Abilitazioni.getValue();
    }

    public void setAbilitazioni(String value) {
        this.Abilitazioni.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of Abilitazioni

//AD4_SERVIZIRow: method(s) of AFCNavigator @159-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of AFCNavigator

//AD4_SERVIZIRow: method(s) of ISTANZA @167-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ISTANZA

//AD4_SERVIZIRow: method(s) of MODULO @171-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of MODULO

//AD4_SERVIZIRow: class tail @6-FCB6E20C
}
//End AD4_SERVIZIRow: class tail

