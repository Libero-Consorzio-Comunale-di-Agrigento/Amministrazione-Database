//ProvinceElencoRow: import @8-563B88D2
package restrict.Ad4DizionariProvinceElenco;

import java.util.*;
import com.codecharge.db.*;
//End ProvinceElencoRow: import

//ProvinceElencoRow: class head @8-80A67ADB
public class ProvinceElencoRow {
//End ProvinceElencoRow: class head

//ProvinceElencoRow: declare fiels @8-B2D89FAB
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField PROVINCIA = new TextField("PROVINCIA", "PROVINCIA");
    private TextField LINK_COMUNI = new TextField("LINK_COMUNI", "LINK_COMUNI");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField SIGLA = new TextField("SIGLA", "SIGLA");
    private TextField REGIONE = new TextField("REGIONE", "REGIONE");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End ProvinceElencoRow: declare fiels

//ProvinceElencoRow: constructor @8-D5165137
    public ProvinceElencoRow() {
    }
//End ProvinceElencoRow: constructor

//ProvinceElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End ProvinceElencoRow: method(s) of Aggiungi

//ProvinceElencoRow: method(s) of PROVINCIA @23-0A4A498E
    public TextField getPROVINCIAField() {
        return PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA.setValue(value);
    }
//End ProvinceElencoRow: method(s) of PROVINCIA

//ProvinceElencoRow: method(s) of LINK_COMUNI @29-CB592EF2
    public TextField getLINK_COMUNIField() {
        return LINK_COMUNI;
    }

    public String getLINK_COMUNI() {
        return LINK_COMUNI.getValue();
    }

    public void setLINK_COMUNI(String value) {
        this.LINK_COMUNI.setValue(value);
    }
//End ProvinceElencoRow: method(s) of LINK_COMUNI

//ProvinceElencoRow: method(s) of DENOMINAZIONE @13-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End ProvinceElencoRow: method(s) of DENOMINAZIONE

//ProvinceElencoRow: method(s) of SIGLA @16-3A41CFD1
    public TextField getSIGLAField() {
        return SIGLA;
    }

    public String getSIGLA() {
        return SIGLA.getValue();
    }

    public void setSIGLA(String value) {
        this.SIGLA.setValue(value);
    }
//End ProvinceElencoRow: method(s) of SIGLA

//ProvinceElencoRow: method(s) of REGIONE @12-3A5F1C79
    public TextField getREGIONEField() {
        return REGIONE;
    }

    public String getREGIONE() {
        return REGIONE.getValue();
    }

    public void setREGIONE(String value) {
        this.REGIONE.setValue(value);
    }
//End ProvinceElencoRow: method(s) of REGIONE

//ProvinceElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End ProvinceElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//ProvinceElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End ProvinceElencoRow: method(s) of DATA_AGGIORNAMENTO

//ProvinceElencoRow: method(s) of AFCNavigator @28-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ProvinceElencoRow: method(s) of AFCNavigator

//ProvinceElencoRow: class tail @8-FCB6E20C
}
//End ProvinceElencoRow: class tail

