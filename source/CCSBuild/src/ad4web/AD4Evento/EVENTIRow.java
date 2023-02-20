//EVENTIRow: import @56-25EBEF06
package ad4web.AD4Evento;

import java.util.*;
import com.codecharge.db.*;
//End EVENTIRow: import

//EVENTIRow: class head @56-3203EC36
public class EVENTIRow {
//End EVENTIRow: class head

//EVENTIRow: declare fiels @56-8A392C31
    private TextField ID_EVENTO = new TextField("ID_EVENTO", "ID_EVENTO");
    private TextField DATA = new TextField("DATA", "DATA");
    private TextField ID_EVENTO_HIDDEN = new TextField("ID_EVENTO_HIDDEN", "ID_EVENTO");
    private TextField DESC_TIPO = new TextField("DESC_TIPO", "DESC_TIPO");
    private TextField TIPO = new TextField("TIPO", "TIPO");
    private TextField TESTO_FILE = new TextField("TESTO_FILE", "TESTO_FILE");
    private TextField CHECK_FILE_LOCATOR = new TextField("CHECK_FILE_LOCATOR", "CHECK_FILE_LOCATOR");
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField RIPRISTINATI = new TextField("RIPRISTINATI", "RIPRISTINATI");
    private TextField DB_USER = new TextField("DB_USER", "DB_USER");
    private TextField ISTANZA_DESC = new TextField("ISTANZA_DESC", "ISTANZA_DESC");
    private TextField MODULO_DESC = new TextField("MODULO_DESC", "MODULO_DESC");
    private TextField DESC_GRAVITA = new TextField("DESC_GRAVITA", "DESC_GRAVITA");
    private TextField DESC_STATO = new TextField("DESC_STATO", "DESC_STATO");
    private TextField ANNOTAZIONI = new TextField("ANNOTAZIONI", "ANNOTAZIONI");
//End EVENTIRow: declare fiels

//EVENTIRow: constructor @56-89854494
    public EVENTIRow() {
    }
//End EVENTIRow: constructor

//EVENTIRow: method(s) of ID_EVENTO @135-3FB9685E
    public TextField getID_EVENTOField() {
        return ID_EVENTO;
    }

    public String getID_EVENTO() {
        return ID_EVENTO.getValue();
    }

    public void setID_EVENTO(String value) {
        this.ID_EVENTO.setValue(value);
    }
//End EVENTIRow: method(s) of ID_EVENTO

//EVENTIRow: method(s) of DATA @175-28E61A6F
    public TextField getDATAField() {
        return DATA;
    }

    public String getDATA() {
        return DATA.getValue();
    }

    public void setDATA(String value) {
        this.DATA.setValue(value);
    }
//End EVENTIRow: method(s) of DATA

//EVENTIRow: method(s) of ID_EVENTO_HIDDEN @181-09524EF7
    public TextField getID_EVENTO_HIDDENField() {
        return ID_EVENTO_HIDDEN;
    }

    public String getID_EVENTO_HIDDEN() {
        return ID_EVENTO_HIDDEN.getValue();
    }

    public void setID_EVENTO_HIDDEN(String value) {
        this.ID_EVENTO_HIDDEN.setValue(value);
    }
//End EVENTIRow: method(s) of ID_EVENTO_HIDDEN

//EVENTIRow: method(s) of DESC_TIPO @176-5CBC01CE
    public TextField getDESC_TIPOField() {
        return DESC_TIPO;
    }

    public String getDESC_TIPO() {
        return DESC_TIPO.getValue();
    }

    public void setDESC_TIPO(String value) {
        this.DESC_TIPO.setValue(value);
    }
//End EVENTIRow: method(s) of DESC_TIPO

//EVENTIRow: method(s) of TIPO @182-07D1E38D
    public TextField getTIPOField() {
        return TIPO;
    }

    public String getTIPO() {
        return TIPO.getValue();
    }

    public void setTIPO(String value) {
        this.TIPO.setValue(value);
    }
//End EVENTIRow: method(s) of TIPO

//EVENTIRow: method(s) of TESTO_FILE @177-07B48523
    public TextField getTESTO_FILEField() {
        return TESTO_FILE;
    }

    public String getTESTO_FILE() {
        return TESTO_FILE.getValue();
    }

    public void setTESTO_FILE(String value) {
        this.TESTO_FILE.setValue(value);
    }
//End EVENTIRow: method(s) of TESTO_FILE

//EVENTIRow: method(s) of CHECK_FILE_LOCATOR @183-0EF3BC71
    public TextField getCHECK_FILE_LOCATORField() {
        return CHECK_FILE_LOCATOR;
    }

    public String getCHECK_FILE_LOCATOR() {
        return CHECK_FILE_LOCATOR.getValue();
    }

    public void setCHECK_FILE_LOCATOR(String value) {
        this.CHECK_FILE_LOCATOR.setValue(value);
    }
//End EVENTIRow: method(s) of CHECK_FILE_LOCATOR

//EVENTIRow: method(s) of NOMINATIVO @104-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End EVENTIRow: method(s) of NOMINATIVO

//EVENTIRow: method(s) of RIPRISTINATI @186-F5479717
    public TextField getRIPRISTINATIField() {
        return RIPRISTINATI;
    }

    public String getRIPRISTINATI() {
        return RIPRISTINATI.getValue();
    }

    public void setRIPRISTINATI(String value) {
        this.RIPRISTINATI.setValue(value);
    }
//End EVENTIRow: method(s) of RIPRISTINATI

//EVENTIRow: method(s) of DB_USER @193-33945EFE
    public TextField getDB_USERField() {
        return DB_USER;
    }

    public String getDB_USER() {
        return DB_USER.getValue();
    }

    public void setDB_USER(String value) {
        this.DB_USER.setValue(value);
    }
//End EVENTIRow: method(s) of DB_USER

//EVENTIRow: method(s) of ISTANZA_DESC @105-E0B70965
    public TextField getISTANZA_DESCField() {
        return ISTANZA_DESC;
    }

    public String getISTANZA_DESC() {
        return ISTANZA_DESC.getValue();
    }

    public void setISTANZA_DESC(String value) {
        this.ISTANZA_DESC.setValue(value);
    }
//End EVENTIRow: method(s) of ISTANZA_DESC

//EVENTIRow: method(s) of MODULO_DESC @106-1717CE7E
    public TextField getMODULO_DESCField() {
        return MODULO_DESC;
    }

    public String getMODULO_DESC() {
        return MODULO_DESC.getValue();
    }

    public void setMODULO_DESC(String value) {
        this.MODULO_DESC.setValue(value);
    }
//End EVENTIRow: method(s) of MODULO_DESC

//EVENTIRow: method(s) of DESC_GRAVITA @108-C3B62047
    public TextField getDESC_GRAVITAField() {
        return DESC_GRAVITA;
    }

    public String getDESC_GRAVITA() {
        return DESC_GRAVITA.getValue();
    }

    public void setDESC_GRAVITA(String value) {
        this.DESC_GRAVITA.setValue(value);
    }
//End EVENTIRow: method(s) of DESC_GRAVITA

//EVENTIRow: method(s) of DESC_STATO @70-EC70C3E9
    public TextField getDESC_STATOField() {
        return DESC_STATO;
    }

    public String getDESC_STATO() {
        return DESC_STATO.getValue();
    }

    public void setDESC_STATO(String value) {
        this.DESC_STATO.setValue(value);
    }
//End EVENTIRow: method(s) of DESC_STATO

//EVENTIRow: method(s) of ANNOTAZIONI @178-B6D3FB86
    public TextField getANNOTAZIONIField() {
        return ANNOTAZIONI;
    }

    public String getANNOTAZIONI() {
        return ANNOTAZIONI.getValue();
    }

    public void setANNOTAZIONI(String value) {
        this.ANNOTAZIONI.setValue(value);
    }
//End EVENTIRow: method(s) of ANNOTAZIONI

//EVENTIRow: class tail @56-FCB6E20C
}
//End EVENTIRow: class tail

