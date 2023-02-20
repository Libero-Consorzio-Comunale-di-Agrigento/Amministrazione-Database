//AD4_UTENTEGridRow: import @2-DC6FFAD0
package common.AmvDatiRegistrazione;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTEGridRow: import

//AD4_UTENTEGridRow: class head @2-76483156
public class AD4_UTENTEGridRow {
//End AD4_UTENTEGridRow: class head

//AD4_UTENTEGridRow: declare fiels @2-7E0FC82C
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
    private TextField TIPO_NOTIFICA = new TextField("TIPO_NOTIFICA", "TIPO_NOTIFICA");
    private TextField INDIRIZZO_NOTIFICA = new TextField("INDIRIZZO_NOTIFICA", "INDIRIZZO_NOTIFICA");
//End AD4_UTENTEGridRow: declare fiels

//AD4_UTENTEGridRow: constructor @2-68C480A3
    public AD4_UTENTEGridRow() {
    }
//End AD4_UTENTEGridRow: constructor

//AD4_UTENTEGridRow: method(s) of NOME_SOGGETTO @22-2FF850FF
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

//AD4_UTENTEGridRow: method(s) of DATA_NASCITA @23-2B992BC2
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

//AD4_UTENTEGridRow: method(s) of COMUNE_NASCITA @25-6B838941
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

//AD4_UTENTEGridRow: method(s) of PROVINCIA_NASCITA @26-21BC079E
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

//AD4_UTENTEGridRow: method(s) of CODICE_FISCALE @24-3FE2CCFC
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

//AD4_UTENTEGridRow: method(s) of INDIRIZZO_COMPLETO @6-92DA0D53
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

//AD4_UTENTEGridRow: method(s) of INDIRIZZO_WEB @9-F718DE73
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

//AD4_UTENTEGridRow: method(s) of TELEFONO @12-E71ADE24
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

//AD4_UTENTEGridRow: method(s) of FAX @13-AF87E71F
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

//AD4_UTENTEGridRow: method(s) of NOMINATIVO @3-3BDE962A
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

//AD4_UTENTEGridRow: method(s) of PASSWORD @4-217C0394
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

//AD4_UTENTEGridRow: method(s) of TIPO_NOTIFICA @27-C59F1488
    public TextField getTIPO_NOTIFICAField() {
        return TIPO_NOTIFICA;
    }

    public String getTIPO_NOTIFICA() {
        return TIPO_NOTIFICA.getValue();
    }

    public void setTIPO_NOTIFICA(String value) {
        this.TIPO_NOTIFICA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of TIPO_NOTIFICA

//AD4_UTENTEGridRow: method(s) of INDIRIZZO_NOTIFICA @5-A4148738
    public TextField getINDIRIZZO_NOTIFICAField() {
        return INDIRIZZO_NOTIFICA;
    }

    public String getINDIRIZZO_NOTIFICA() {
        return INDIRIZZO_NOTIFICA.getValue();
    }

    public void setINDIRIZZO_NOTIFICA(String value) {
        this.INDIRIZZO_NOTIFICA.setValue(value);
    }
//End AD4_UTENTEGridRow: method(s) of INDIRIZZO_NOTIFICA

//AD4_UTENTEGridRow: class tail @2-FCB6E20C
}
//End AD4_UTENTEGridRow: class tail

