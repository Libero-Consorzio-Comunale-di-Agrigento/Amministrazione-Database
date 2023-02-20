//AD4_ENTIRow: import @6-3CE12C70
package ad4web.AD4Ente;

import java.util.*;
import com.codecharge.db.*;
//End AD4_ENTIRow: import

//AD4_ENTIRow: class head @6-F0D1C49C
public class AD4_ENTIRow {
//End AD4_ENTIRow: class head

//AD4_ENTIRow: declare fiels @6-1801FC21
    private TextField SVUOTA_REG_ANAGRAFICA = new TextField("SVUOTA_REG_ANAGRAFICA", "SVUOTA_REG_ANAGRAFICA");
    private TextField MOD_REGISTRAZIONE_ANAGRAFICA = new TextField("MOD_REGISTRAZIONE_ANAGRAFICA", "MOD_REGISTRAZIONE_ANAGRAFICA");
    private TextField ENTE = new TextField("ENTE", "ENTE");
    private TextField ENTE_ORIG = new TextField("ENTE_ORIG", "ENTE_ORIG");
    private LongField SOGGETTO = new LongField("SOGGETTO", "SOGGETTO");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField BITMAP = new TextField("BITMAP", "BITMAP");
    private TextField DISEGNO = new TextField("DISEGNO", "DISEGNO");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField TITOLO_SOGG = new TextField("TITOLO_SOGG", "TITOLO_SOGG");
    private TextField DATI_SOGGETTO = new TextField("DATI_SOGGETTO", "DATI_SOGGETTO");
    private TextField s_ENTE = new TextField("s_ENTE", "ENTE_ORIG");
//End AD4_ENTIRow: declare fiels

//AD4_ENTIRow: constructor @6-41140C2D
    public AD4_ENTIRow() {
    }
//End AD4_ENTIRow: constructor

//AD4_ENTIRow: method(s) of SVUOTA_REG_ANAGRAFICA @111-07696205
    public TextField getSVUOTA_REG_ANAGRAFICAField() {
        return SVUOTA_REG_ANAGRAFICA;
    }

    public String getSVUOTA_REG_ANAGRAFICA() {
        return SVUOTA_REG_ANAGRAFICA.getValue();
    }

    public void setSVUOTA_REG_ANAGRAFICA(String value) {
        this.SVUOTA_REG_ANAGRAFICA.setValue(value);
    }
//End AD4_ENTIRow: method(s) of SVUOTA_REG_ANAGRAFICA

//AD4_ENTIRow: method(s) of MOD_REGISTRAZIONE_ANAGRAFICA @113-05C82DF3
    public TextField getMOD_REGISTRAZIONE_ANAGRAFICAField() {
        return MOD_REGISTRAZIONE_ANAGRAFICA;
    }

    public String getMOD_REGISTRAZIONE_ANAGRAFICA() {
        return MOD_REGISTRAZIONE_ANAGRAFICA.getValue();
    }

    public void setMOD_REGISTRAZIONE_ANAGRAFICA(String value) {
        this.MOD_REGISTRAZIONE_ANAGRAFICA.setValue(value);
    }
//End AD4_ENTIRow: method(s) of MOD_REGISTRAZIONE_ANAGRAFICA

//AD4_ENTIRow: method(s) of ENTE @78-D1A00E41
    public TextField getENTEField() {
        return ENTE;
    }

    public String getENTE() {
        return ENTE.getValue();
    }

    public void setENTE(String value) {
        this.ENTE.setValue(value);
    }
//End AD4_ENTIRow: method(s) of ENTE

//AD4_ENTIRow: method(s) of ENTE_ORIG @98-2961C782
    public TextField getENTE_ORIGField() {
        return ENTE_ORIG;
    }

    public String getENTE_ORIG() {
        return ENTE_ORIG.getValue();
    }

    public void setENTE_ORIG(String value) {
        this.ENTE_ORIG.setValue(value);
    }
//End AD4_ENTIRow: method(s) of ENTE_ORIG

//AD4_ENTIRow: method(s) of SOGGETTO @115-3F2B29F6
    public LongField getSOGGETTOField() {
        return SOGGETTO;
    }

    public Long getSOGGETTO() {
        return SOGGETTO.getValue();
    }

    public void setSOGGETTO(Long value) {
        this.SOGGETTO.setValue(value);
    }
//End AD4_ENTIRow: method(s) of SOGGETTO

//AD4_ENTIRow: method(s) of DESCRIZIONE @119-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_ENTIRow: method(s) of DESCRIZIONE

//AD4_ENTIRow: method(s) of BITMAP @95-AFEE0ECC
    public TextField getBITMAPField() {
        return BITMAP;
    }

    public String getBITMAP() {
        return BITMAP.getValue();
    }

    public void setBITMAP(String value) {
        this.BITMAP.setValue(value);
    }
//End AD4_ENTIRow: method(s) of BITMAP

//AD4_ENTIRow: method(s) of DISEGNO @96-AE9476D7
    public TextField getDISEGNOField() {
        return DISEGNO;
    }

    public String getDISEGNO() {
        return DISEGNO.getValue();
    }

    public void setDISEGNO(String value) {
        this.DISEGNO.setValue(value);
    }
//End AD4_ENTIRow: method(s) of DISEGNO

//AD4_ENTIRow: method(s) of NOTE @22-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_ENTIRow: method(s) of NOTE

//AD4_ENTIRow: method(s) of TITOLO_SOGG @116-6E1A4BC8
    public TextField getTITOLO_SOGGField() {
        return TITOLO_SOGG;
    }

    public String getTITOLO_SOGG() {
        return TITOLO_SOGG.getValue();
    }

    public void setTITOLO_SOGG(String value) {
        this.TITOLO_SOGG.setValue(value);
    }
//End AD4_ENTIRow: method(s) of TITOLO_SOGG

//AD4_ENTIRow: method(s) of DATI_SOGGETTO @40-869C3147
    public TextField getDATI_SOGGETTOField() {
        return DATI_SOGGETTO;
    }

    public String getDATI_SOGGETTO() {
        return DATI_SOGGETTO.getValue();
    }

    public void setDATI_SOGGETTO(String value) {
        this.DATI_SOGGETTO.setValue(value);
    }
//End AD4_ENTIRow: method(s) of DATI_SOGGETTO

//AD4_ENTIRow: method(s) of s_ENTE @114-61AA297A
    public TextField getS_ENTEField() {
        return s_ENTE;
    }

    public String getS_ENTE() {
        return s_ENTE.getValue();
    }

    public void setS_ENTE(String value) {
        this.s_ENTE.setValue(value);
    }
//End AD4_ENTIRow: method(s) of s_ENTE

//AD4_ENTIRow: class tail @6-FCB6E20C
}
//End AD4_ENTIRow: class tail

