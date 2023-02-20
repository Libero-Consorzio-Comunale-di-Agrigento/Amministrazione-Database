//AD4_UTENTEGridRow: import @71-E5CB4D60
package amvadm.AdmRichiestaPrint;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTEGridRow: import

//AD4_UTENTEGridRow: class head @71-76483156
public class AD4_UTENTEGridRow {
//End AD4_UTENTEGridRow: class head

//AD4_UTENTEGridRow: declare fiels @71-2D37A7B4
    private TextField DATA_RICHIESTA = new TextField("DATA_RICHIESTA", "DATA");
    private TextField NOME_SOGGETTO = new TextField("NOME_SOGGETTO", "NOME_SOGGETTO");
    private TextField DATA_NASCITA = new TextField("DATA_NASCITA", "DATA_NASCITA");
    private TextField COMUNE_NASCITA = new TextField("COMUNE_NASCITA", "COMUNE_NASCITA");
    private TextField PROVINCIA_NASCITA = new TextField("PROVINCIA_NASCITA", "PROVINCIA_NASCITA");
    private TextField CODICE_FISCALE = new TextField("CODICE_FISCALE", "CODICE_FISCALE");
    private TextField INDIRIZZO_COMPLETO = new TextField("INDIRIZZO_COMPLETO", "INDIRIZZO_COMPLETO");
    private TextField INDIRIZZO_WEB = new TextField("INDIRIZZO_WEB", "INDIRIZZO_WEB");
    private TextField TELEFONO = new TextField("TELEFONO", "TELEFONO");
    private TextField FAX = new TextField("FAX", "FAX");
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField PASSWORD = new TextField("PASSWORD", "PASSWORD");
//End AD4_UTENTEGridRow: declare fiels

//AD4_UTENTEGridRow: constructor @71-68C480A3
    public AD4_UTENTEGridRow() {
    }
//End AD4_UTENTEGridRow: constructor

//AD4_UTENTEGridRow: method(s) of DATA_RICHIESTA @84-C96FBAFD
    public TextField getDATA_RICHIESTAField() {
        return DATA_RICHIESTA;
    }

    public String getDATA_RICHIESTA() {
        return DATA_RICHIESTA.getValue();
    }

    public void setDATA_RICHIESTA(String value) {
        this.DATA_RICHIESTA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of DATA_RICHIESTA

//AD4_UTENTEGridRow: method(s) of NOME_SOGGETTO @72-2FF850FF
    public TextField getNOME_SOGGETTOField() {
        return NOME_SOGGETTO;
    }

    public String getNOME_SOGGETTO() {
        return NOME_SOGGETTO.getValue();
    }

    public void setNOME_SOGGETTO(String value) {
        this.NOME_SOGGETTO.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of NOME_SOGGETTO

//AD4_UTENTEGridRow: method(s) of DATA_NASCITA @73-2B992BC2
    public TextField getDATA_NASCITAField() {
        return DATA_NASCITA;
    }

    public String getDATA_NASCITA() {
        return DATA_NASCITA.getValue();
    }

    public void setDATA_NASCITA(String value) {
        this.DATA_NASCITA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of DATA_NASCITA

//AD4_UTENTEGridRow: method(s) of COMUNE_NASCITA @74-6B838941
    public TextField getCOMUNE_NASCITAField() {
        return COMUNE_NASCITA;
    }

    public String getCOMUNE_NASCITA() {
        return COMUNE_NASCITA.getValue();
    }

    public void setCOMUNE_NASCITA(String value) {
        this.COMUNE_NASCITA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of COMUNE_NASCITA

//AD4_UTENTEGridRow: method(s) of PROVINCIA_NASCITA @75-21BC079E
    public TextField getPROVINCIA_NASCITAField() {
        return PROVINCIA_NASCITA;
    }

    public String getPROVINCIA_NASCITA() {
        return PROVINCIA_NASCITA.getValue();
    }

    public void setPROVINCIA_NASCITA(String value) {
        this.PROVINCIA_NASCITA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of PROVINCIA_NASCITA

//AD4_UTENTEGridRow: method(s) of CODICE_FISCALE @76-3FE2CCFC
    public TextField getCODICE_FISCALEField() {
        return CODICE_FISCALE;
    }

    public String getCODICE_FISCALE() {
        return CODICE_FISCALE.getValue();
    }

    public void setCODICE_FISCALE(String value) {
        this.CODICE_FISCALE.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of CODICE_FISCALE

//AD4_UTENTEGridRow: method(s) of INDIRIZZO_COMPLETO @77-92DA0D53
    public TextField getINDIRIZZO_COMPLETOField() {
        return INDIRIZZO_COMPLETO;
    }

    public String getINDIRIZZO_COMPLETO() {
        return INDIRIZZO_COMPLETO.getValue();
    }

    public void setINDIRIZZO_COMPLETO(String value) {
        this.INDIRIZZO_COMPLETO.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of INDIRIZZO_COMPLETO

//AD4_UTENTEGridRow: method(s) of INDIRIZZO_WEB @78-F718DE73
    public TextField getINDIRIZZO_WEBField() {
        return INDIRIZZO_WEB;
    }

    public String getINDIRIZZO_WEB() {
        return INDIRIZZO_WEB.getValue();
    }

    public void setINDIRIZZO_WEB(String value) {
        this.INDIRIZZO_WEB.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of INDIRIZZO_WEB

//AD4_UTENTEGridRow: method(s) of TELEFONO @79-E71ADE24
    public TextField getTELEFONOField() {
        return TELEFONO;
    }

    public String getTELEFONO() {
        return TELEFONO.getValue();
    }

    public void setTELEFONO(String value) {
        this.TELEFONO.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of TELEFONO

//AD4_UTENTEGridRow: method(s) of FAX @80-AF87E71F
    public TextField getFAXField() {
        return FAX;
    }

    public String getFAX() {
        return FAX.getValue();
    }

    public void setFAX(String value) {
        this.FAX.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of FAX

//AD4_UTENTEGridRow: method(s) of NOMINATIVO @81-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of NOMINATIVO

//AD4_UTENTEGridRow: method(s) of PASSWORD @82-217C0394
    public TextField getPASSWORDField() {
        return PASSWORD;
    }

    public String getPASSWORD() {
        return PASSWORD.getValue();
    }

    public void setPASSWORD(String value) {
        this.PASSWORD.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of PASSWORD

//AD4_UTENTEGridRow: class tail @71-FCB6E20C
}
//End AD4_UTENTEGridRow: class tail

