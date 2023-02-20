//AD4_SOGGETTORow: import @6-20D6A5A4
package ad4web.AD4SoggettoInclusa;

import java.util.*;
import com.codecharge.db.*;
//End AD4_SOGGETTORow: import

//AD4_SOGGETTORow: class head @6-CFBF638E
public class AD4_SOGGETTORow {
//End AD4_SOGGETTORow: class head

//AD4_SOGGETTORow: declare fiels @6-50ADD55E
    private TextField ID_SOGGETTO = new TextField("ID_SOGGETTO", "SOGGETTO");
    private TextField COGNOME = new TextField("COGNOME", "COGNOME");
    private TextField NOME = new TextField("NOME", "NOME");
    private TextField SOGGETTO = new TextField("SOGGETTO", "SOGGETTO");
    private TextField SESSO = new TextField("SESSO", "SESSO");
    private LongField STATO = new LongField("STATO", "STATO_TERRITORIO");
    private TextField ISLISTBOX = new TextField("ISLISTBOX", "");
    private LongField PROVINCIA_NAS = new LongField("PROVINCIA_NAS", "PROVINCIA_NAS");
    private LongField COMUNE_NASCITA = new LongField("COMUNE_NASCITA", "COMUNE_NAS");
    private DateField DATA_NASCITA = new DateField("DATA_NASCITA", "DATA_NASCITA");
    private TextField CODICE_FISCALE = new TextField("CODICE_FISCALE", "CODICE_FISCALE");
    private TextField INDIRIZZO_COMPLETO = new TextField("INDIRIZZO_COMPLETO", "INDIRIZZO");
    private LongField PROVINCIA = new LongField("PROVINCIA", "PROVINCIA");
    private LongField COMUNE = new LongField("COMUNE", "COMUNE");
    private TextField CAP = new TextField("CAP", "CAP");
    private TextField INDIRIZZO_WEB = new TextField("INDIRIZZO_WEB", "INDIRIZZO_WEB");
    private TextField TELEFONO = new TextField("TELEFONO", "TELEFONO");
    private TextField FAX = new TextField("FAX", "FAX");
    private TextField NOTE = new TextField("NOTE", "NOTE");
//End AD4_SOGGETTORow: declare fiels

//AD4_SOGGETTORow: constructor @6-B46970B5
    public AD4_SOGGETTORow() {
    }
//End AD4_SOGGETTORow: constructor

//AD4_SOGGETTORow: method(s) of ID_SOGGETTO @158-8C634BF1
    public TextField getID_SOGGETTOField() {
        return ID_SOGGETTO;
    }

    public String getID_SOGGETTO() {
        return ID_SOGGETTO.getValue();
    }

    public void setID_SOGGETTO(String value) {
        this.ID_SOGGETTO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of ID_SOGGETTO

//AD4_SOGGETTORow: method(s) of COGNOME @160-393F9325
    public TextField getCOGNOMEField() {
        return COGNOME;
    }

    public String getCOGNOME() {
        return COGNOME.getValue();
    }

    public void setCOGNOME(String value) {
        this.COGNOME.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of COGNOME

//AD4_SOGGETTORow: method(s) of NOME @162-DBA8086C
    public TextField getNOMEField() {
        return NOME;
    }

    public String getNOME() {
        return NOME.getValue();
    }

    public void setNOME(String value) {
        this.NOME.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of NOME

//AD4_SOGGETTORow: method(s) of SOGGETTO @113-585B3330
    public TextField getSOGGETTOField() {
        return SOGGETTO;
    }

    public String getSOGGETTO() {
        return SOGGETTO.getValue();
    }

    public void setSOGGETTO(String value) {
        this.SOGGETTO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of SOGGETTO

//AD4_SOGGETTORow: method(s) of SESSO @161-A1140636
    public TextField getSESSOField() {
        return SESSO;
    }

    public String getSESSO() {
        return SESSO.getValue();
    }

    public void setSESSO(String value) {
        this.SESSO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of SESSO

//AD4_SOGGETTORow: method(s) of STATO @116-D67EACF1
    public LongField getSTATOField() {
        return STATO;
    }

    public Long getSTATO() {
        return STATO.getValue();
    }

    public void setSTATO(Long value) {
        this.STATO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of STATO

//AD4_SOGGETTORow: method(s) of ISLISTBOX @157-8158BD72
    public TextField getISLISTBOXField() {
        return ISLISTBOX;
    }

    public String getISLISTBOX() {
        return ISLISTBOX.getValue();
    }

    public void setISLISTBOX(String value) {
        this.ISLISTBOX.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of ISLISTBOX

//AD4_SOGGETTORow: method(s) of PROVINCIA_NAS @123-A0BB7A37
    public LongField getPROVINCIA_NASField() {
        return PROVINCIA_NAS;
    }

    public Long getPROVINCIA_NAS() {
        return PROVINCIA_NAS.getValue();
    }

    public void setPROVINCIA_NAS(Long value) {
        this.PROVINCIA_NAS.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of PROVINCIA_NAS

//AD4_SOGGETTORow: method(s) of COMUNE_NASCITA @129-A19DA35E
    public LongField getCOMUNE_NASCITAField() {
        return COMUNE_NASCITA;
    }

    public Long getCOMUNE_NASCITA() {
        return COMUNE_NASCITA.getValue();
    }

    public void setCOMUNE_NASCITA(Long value) {
        this.COMUNE_NASCITA.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of COMUNE_NASCITA

//AD4_SOGGETTORow: method(s) of DATA_NASCITA @115-71A7627E
    public DateField getDATA_NASCITAField() {
        return DATA_NASCITA;
    }

    public Date getDATA_NASCITA() {
        return DATA_NASCITA.getValue();
    }

    public void setDATA_NASCITA(Date value) {
        this.DATA_NASCITA.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of DATA_NASCITA

//AD4_SOGGETTORow: method(s) of CODICE_FISCALE @114-3FE2CCFC
    public TextField getCODICE_FISCALEField() {
        return CODICE_FISCALE;
    }

    public String getCODICE_FISCALE() {
        return CODICE_FISCALE.getValue();
    }

    public void setCODICE_FISCALE(String value) {
        this.CODICE_FISCALE.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of CODICE_FISCALE

//AD4_SOGGETTORow: method(s) of INDIRIZZO_COMPLETO @133-92DA0D53
    public TextField getINDIRIZZO_COMPLETOField() {
        return INDIRIZZO_COMPLETO;
    }

    public String getINDIRIZZO_COMPLETO() {
        return INDIRIZZO_COMPLETO.getValue();
    }

    public void setINDIRIZZO_COMPLETO(String value) {
        this.INDIRIZZO_COMPLETO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of INDIRIZZO_COMPLETO

//AD4_SOGGETTORow: method(s) of PROVINCIA @134-A3592314
    public LongField getPROVINCIAField() {
        return PROVINCIA;
    }

    public Long getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(Long value) {
        this.PROVINCIA.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of PROVINCIA

//AD4_SOGGETTORow: method(s) of COMUNE @147-532A21D6
    public LongField getCOMUNEField() {
        return COMUNE;
    }

    public Long getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(Long value) {
        this.COMUNE.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of COMUNE

//AD4_SOGGETTORow: method(s) of CAP @146-CDFF7BC8
    public TextField getCAPField() {
        return CAP;
    }

    public String getCAP() {
        return CAP.getValue();
    }

    public void setCAP(String value) {
        this.CAP.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of CAP

//AD4_SOGGETTORow: method(s) of INDIRIZZO_WEB @109-F718DE73
    public TextField getINDIRIZZO_WEBField() {
        return INDIRIZZO_WEB;
    }

    public String getINDIRIZZO_WEB() {
        return INDIRIZZO_WEB.getValue();
    }

    public void setINDIRIZZO_WEB(String value) {
        this.INDIRIZZO_WEB.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of INDIRIZZO_WEB

//AD4_SOGGETTORow: method(s) of TELEFONO @107-E71ADE24
    public TextField getTELEFONOField() {
        return TELEFONO;
    }

    public String getTELEFONO() {
        return TELEFONO.getValue();
    }

    public void setTELEFONO(String value) {
        this.TELEFONO.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of TELEFONO

//AD4_SOGGETTORow: method(s) of FAX @108-AF87E71F
    public TextField getFAXField() {
        return FAX;
    }

    public String getFAX() {
        return FAX.getValue();
    }

    public void setFAX(String value) {
        this.FAX.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of FAX

//AD4_SOGGETTORow: method(s) of NOTE @106-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_SOGGETTORow: method(s) of NOTE

//AD4_SOGGETTORow: class tail @6-FCB6E20C
}
//End AD4_SOGGETTORow: class tail

