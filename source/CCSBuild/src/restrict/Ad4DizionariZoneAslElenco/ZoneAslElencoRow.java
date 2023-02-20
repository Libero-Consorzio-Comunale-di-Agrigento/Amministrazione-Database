//ZoneAslElencoRow: import @8-7821C8F9
package restrict.Ad4DizionariZoneAslElenco;

import java.util.*;
import com.codecharge.db.*;
//End ZoneAslElencoRow: import

//ZoneAslElencoRow: class head @8-FA5A85AE
public class ZoneAslElencoRow {
//End ZoneAslElencoRow: class head

//ZoneAslElencoRow: declare fiels @8-9BB09416
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField CODICE_REGIONE = new TextField("CODICE_REGIONE", "CODICE_REGIONE");
    private TextField CODICE_ZONA = new TextField("CODICE_ZONA", "CODICE_ZONA");
    private TextField TITOLO = new TextField("TITOLO", "TITOLO");
    private TextField REGIONE_DENOMINAZIONE = new TextField("REGIONE_DENOMINAZIONE", "REGIONE_DENOMINAZIONE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField ID_ZONA_ASL = new TextField("ID_ZONA_ASL", "ID_ZONA_ASL");
//End ZoneAslElencoRow: declare fiels

//ZoneAslElencoRow: constructor @8-EA621F99
    public ZoneAslElencoRow() {
    }
//End ZoneAslElencoRow: constructor

//ZoneAslElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of Aggiungi

//ZoneAslElencoRow: method(s) of CODICE_REGIONE @28-AABA1029
    public TextField getCODICE_REGIONEField() {
        return CODICE_REGIONE;
    }

    public String getCODICE_REGIONE() {
        return CODICE_REGIONE.getValue();
    }

    public void setCODICE_REGIONE(String value) {
        this.CODICE_REGIONE.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of CODICE_REGIONE

//ZoneAslElencoRow: method(s) of CODICE_ZONA @29-0F3B5B3B
    public TextField getCODICE_ZONAField() {
        return CODICE_ZONA;
    }

    public String getCODICE_ZONA() {
        return CODICE_ZONA.getValue();
    }

    public void setCODICE_ZONA(String value) {
        this.CODICE_ZONA.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of CODICE_ZONA

//ZoneAslElencoRow: method(s) of TITOLO @13-FB48796E
    public TextField getTITOLOField() {
        return TITOLO;
    }

    public String getTITOLO() {
        return TITOLO.getValue();
    }

    public void setTITOLO(String value) {
        this.TITOLO.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of TITOLO

//ZoneAslElencoRow: method(s) of REGIONE_DENOMINAZIONE @16-878582D6
    public TextField getREGIONE_DENOMINAZIONEField() {
        return REGIONE_DENOMINAZIONE;
    }

    public String getREGIONE_DENOMINAZIONE() {
        return REGIONE_DENOMINAZIONE.getValue();
    }

    public void setREGIONE_DENOMINAZIONE(String value) {
        this.REGIONE_DENOMINAZIONE.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of REGIONE_DENOMINAZIONE

//ZoneAslElencoRow: method(s) of AFCNavigator @22-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of AFCNavigator

//ZoneAslElencoRow: method(s) of ID_ZONA_ASL @15-1A368EE5
    public TextField getID_ZONA_ASLField() {
        return ID_ZONA_ASL;
    }

    public String getID_ZONA_ASL() {
        return ID_ZONA_ASL.getValue();
    }

    public void setID_ZONA_ASL(String value) {
        this.ID_ZONA_ASL.setValue(value);
    }
//End ZoneAslElencoRow: method(s) of ID_ZONA_ASL

//ZoneAslElencoRow: class tail @8-FCB6E20C
}
//End ZoneAslElencoRow: class tail

