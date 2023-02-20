//AslElencoRow: import @8-B3B19EDA
package restrict.Ad4DizionariAslElenco;

import java.util.*;
import com.codecharge.db.*;
//End AslElencoRow: import

//AslElencoRow: class head @8-BBC6CC62
public class AslElencoRow {
//End AslElencoRow: class head

//AslElencoRow: declare fiels @8-6A969D47
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField REGIONE_ASL = new TextField("REGIONE_ASL", "REGIONE_ASL");
    private TextField CODICE_ASL = new TextField("CODICE_ASL", "CODICE_ASL");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField REGIONE_DENOMINAZIONE = new TextField("REGIONE_DENOMINAZIONE", "REGIONE_DENOMINAZIONE");
    private TextField ATTIVA = new TextField("ATTIVA", "ATTIVA");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End AslElencoRow: declare fiels

//AslElencoRow: constructor @8-3FCB8230
    public AslElencoRow() {
    }
//End AslElencoRow: constructor

//AslElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End AslElencoRow: method(s) of Aggiungi

//AslElencoRow: method(s) of REGIONE_ASL @29-D320B4C9
    public TextField getREGIONE_ASLField() {
        return REGIONE_ASL;
    }

    public String getREGIONE_ASL() {
        return REGIONE_ASL.getValue();
    }

    public void setREGIONE_ASL(String value) {
        this.REGIONE_ASL.setValue(value);
    }
//End AslElencoRow: method(s) of REGIONE_ASL

//AslElencoRow: method(s) of CODICE_ASL @30-7EBEACC7
    public TextField getCODICE_ASLField() {
        return CODICE_ASL;
    }

    public String getCODICE_ASL() {
        return CODICE_ASL.getValue();
    }

    public void setCODICE_ASL(String value) {
        this.CODICE_ASL.setValue(value);
    }
//End AslElencoRow: method(s) of CODICE_ASL

//AslElencoRow: method(s) of DESCRIZIONE @13-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AslElencoRow: method(s) of DESCRIZIONE

//AslElencoRow: method(s) of REGIONE_DENOMINAZIONE @16-878582D6
    public TextField getREGIONE_DENOMINAZIONEField() {
        return REGIONE_DENOMINAZIONE;
    }

    public String getREGIONE_DENOMINAZIONE() {
        return REGIONE_DENOMINAZIONE.getValue();
    }

    public void setREGIONE_DENOMINAZIONE(String value) {
        this.REGIONE_DENOMINAZIONE.setValue(value);
    }
//End AslElencoRow: method(s) of REGIONE_DENOMINAZIONE

//AslElencoRow: method(s) of ATTIVA @39-907CE3CD
    public TextField getATTIVAField() {
        return ATTIVA;
    }

    public String getATTIVA() {
        return ATTIVA.getValue();
    }

    public void setATTIVA(String value) {
        this.ATTIVA.setValue(value);
    }
//End AslElencoRow: method(s) of ATTIVA

//AslElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End AslElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//AslElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End AslElencoRow: method(s) of DATA_AGGIORNAMENTO

//AslElencoRow: method(s) of AFCNavigator @22-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AslElencoRow: method(s) of AFCNavigator

//AslElencoRow: class tail @8-FCB6E20C
}
//End AslElencoRow: class tail

