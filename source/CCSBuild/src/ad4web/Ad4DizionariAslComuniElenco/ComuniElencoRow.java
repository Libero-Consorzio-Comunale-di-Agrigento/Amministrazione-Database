//ComuniElencoRow: import @8-02F1C136
package ad4web.Ad4DizionariAslComuniElenco;

import java.util.*;
import com.codecharge.db.*;
//End ComuniElencoRow: import

//ComuniElencoRow: class head @8-D377F9E5
public class ComuniElencoRow {
//End ComuniElencoRow: class head

//ComuniElencoRow: declare fiels @8-F9577F61
    private TextField COMUNE_DESC = new TextField("COMUNE_DESC", "COMUNE_DESC");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField COMUNE = new TextField("COMUNE", "COMUNE");
    private TextField PROVINCIA = new TextField("PROVINCIA", "PROVINCIA");
    private TextField REGIONE_ASL = new TextField("REGIONE_ASL", "REGIONE_ASL");
    private TextField CODICE_ASL = new TextField("CODICE_ASL", "CODICE_ASL");
//End ComuniElencoRow: declare fiels

//ComuniElencoRow: constructor @8-EEFA7C7A
    public ComuniElencoRow() {
    }
//End ComuniElencoRow: constructor

//ComuniElencoRow: method(s) of COMUNE_DESC @13-E6E222F6
    public TextField getCOMUNE_DESCField() {
        return COMUNE_DESC;
    }

    public String getCOMUNE_DESC() {
        return COMUNE_DESC.getValue();
    }

    public void setCOMUNE_DESC(String value) {
        this.COMUNE_DESC.setValue(value);
    }
//End ComuniElencoRow: method(s) of COMUNE_DESC

//ComuniElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End ComuniElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//ComuniElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End ComuniElencoRow: method(s) of DATA_AGGIORNAMENTO

//ComuniElencoRow: method(s) of AFCNavigator @28-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ComuniElencoRow: method(s) of AFCNavigator

//ComuniElencoRow: method(s) of COMUNE @15-B41017A6
    public TextField getCOMUNEField() {
        return COMUNE;
    }

    public String getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(String value) {
        this.COMUNE.setValue(value);
    }
//End ComuniElencoRow: method(s) of COMUNE

//ComuniElencoRow: method(s) of PROVINCIA @30-0A4A498E
    public TextField getPROVINCIAField() {
        return PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA.setValue(value);
    }
//End ComuniElencoRow: method(s) of PROVINCIA

//ComuniElencoRow: method(s) of REGIONE_ASL @38-D320B4C9
    public TextField getREGIONE_ASLField() {
        return REGIONE_ASL;
    }

    public String getREGIONE_ASL() {
        return REGIONE_ASL.getValue();
    }

    public void setREGIONE_ASL(String value) {
        this.REGIONE_ASL.setValue(value);
    }
//End ComuniElencoRow: method(s) of REGIONE_ASL

//ComuniElencoRow: method(s) of CODICE_ASL @39-7EBEACC7
    public TextField getCODICE_ASLField() {
        return CODICE_ASL;
    }

    public String getCODICE_ASL() {
        return CODICE_ASL.getValue();
    }

    public void setCODICE_ASL(String value) {
        this.CODICE_ASL.setValue(value);
    }
//End ComuniElencoRow: method(s) of CODICE_ASL

//ComuniElencoRow: class tail @8-FCB6E20C
}
//End ComuniElencoRow: class tail

