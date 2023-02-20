//AD4EventiVRow: import @142-28F1335F
package ad4web.AD4EventiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4EventiVRow: import

//AD4EventiVRow: class head @142-FB422D00
public class AD4EventiVRow {
//End AD4EventiVRow: class head

//AD4EventiVRow: declare fiels @142-9ABCC0ED
    private LongField ID_EVENTO = new LongField("ID_EVENTO", "ID_EVENTO");
    private TextField DATA = new TextField("DATA", "DATA");
    private TextField DESC_TIPO = new TextField("DESC_TIPO", "DESC_TIPO");
    private TextField DESC_TIPO_RIDOTTA = new TextField("DESC_TIPO_RIDOTTA", "DESC_TIPO_RIDOTTA");
    private TextField TESTO_NOTE = new TextField("TESTO_NOTE", "TESTO_NOTE");
    private TextField PROVENIENZA = new TextField("PROVENIENZA", "PROVENIENZA");
    private TextField DB_USER = new TextField("DB_USER", "DB_USER");
    private TextField GRAVITA = new TextField("GRAVITA", "GRAVITA");
    private TextField DESC_STATO = new TextField("DESC_STATO", "DESC_STATO");
    private TextField DESC_NOTIFICATO = new TextField("DESC_NOTIFICATO", "DESC_NOTIFICATO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End AD4EventiVRow: declare fiels

//AD4EventiVRow: constructor @142-3677875F
    public AD4EventiVRow() {
    }
//End AD4EventiVRow: constructor

//AD4EventiVRow: method(s) of ID_EVENTO @278-67BD1D54
    public LongField getID_EVENTOField() {
        return ID_EVENTO;
    }

    public Long getID_EVENTO() {
        return ID_EVENTO.getValue();
    }

    public void setID_EVENTO(Long value) {
        this.ID_EVENTO.setValue(value);
    }
//End AD4EventiVRow: method(s) of ID_EVENTO

//AD4EventiVRow: method(s) of DATA @263-28E61A6F
    public TextField getDATAField() {
        return DATA;
    }

    public String getDATA() {
        return DATA.getValue();
    }

    public void setDATA(String value) {
        this.DATA.setValue(value);
    }
//End AD4EventiVRow: method(s) of DATA

//AD4EventiVRow: method(s) of DESC_TIPO @355-5CBC01CE
    public TextField getDESC_TIPOField() {
        return DESC_TIPO;
    }

    public String getDESC_TIPO() {
        return DESC_TIPO.getValue();
    }

    public void setDESC_TIPO(String value) {
        this.DESC_TIPO.setValue(value);
    }
//End AD4EventiVRow: method(s) of DESC_TIPO

//AD4EventiVRow: method(s) of DESC_TIPO_RIDOTTA @333-F4DDE5B6
    public TextField getDESC_TIPO_RIDOTTAField() {
        return DESC_TIPO_RIDOTTA;
    }

    public String getDESC_TIPO_RIDOTTA() {
        return DESC_TIPO_RIDOTTA.getValue();
    }

    public void setDESC_TIPO_RIDOTTA(String value) {
        this.DESC_TIPO_RIDOTTA.setValue(value);
    }
//End AD4EventiVRow: method(s) of DESC_TIPO_RIDOTTA

//AD4EventiVRow: method(s) of TESTO_NOTE @335-2A57EA46
    public TextField getTESTO_NOTEField() {
        return TESTO_NOTE;
    }

    public String getTESTO_NOTE() {
        return TESTO_NOTE.getValue();
    }

    public void setTESTO_NOTE(String value) {
        this.TESTO_NOTE.setValue(value);
    }
//End AD4EventiVRow: method(s) of TESTO_NOTE

//AD4EventiVRow: method(s) of PROVENIENZA @348-91184732
    public TextField getPROVENIENZAField() {
        return PROVENIENZA;
    }

    public String getPROVENIENZA() {
        return PROVENIENZA.getValue();
    }

    public void setPROVENIENZA(String value) {
        this.PROVENIENZA.setValue(value);
    }
//End AD4EventiVRow: method(s) of PROVENIENZA

//AD4EventiVRow: method(s) of DB_USER @352-33945EFE
    public TextField getDB_USERField() {
        return DB_USER;
    }

    public String getDB_USER() {
        return DB_USER.getValue();
    }

    public void setDB_USER(String value) {
        this.DB_USER.setValue(value);
    }
//End AD4EventiVRow: method(s) of DB_USER

//AD4EventiVRow: method(s) of GRAVITA @356-232F478F
    public TextField getGRAVITAField() {
        return GRAVITA;
    }

    public String getGRAVITA() {
        return GRAVITA.getValue();
    }

    public void setGRAVITA(String value) {
        this.GRAVITA.setValue(value);
    }
//End AD4EventiVRow: method(s) of GRAVITA

//AD4EventiVRow: method(s) of DESC_STATO @353-EC70C3E9
    public TextField getDESC_STATOField() {
        return DESC_STATO;
    }

    public String getDESC_STATO() {
        return DESC_STATO.getValue();
    }

    public void setDESC_STATO(String value) {
        this.DESC_STATO.setValue(value);
    }
//End AD4EventiVRow: method(s) of DESC_STATO

//AD4EventiVRow: method(s) of DESC_NOTIFICATO @354-5BF4412D
    public TextField getDESC_NOTIFICATOField() {
        return DESC_NOTIFICATO;
    }

    public String getDESC_NOTIFICATO() {
        return DESC_NOTIFICATO.getValue();
    }

    public void setDESC_NOTIFICATO(String value) {
        this.DESC_NOTIFICATO.setValue(value);
    }
//End AD4EventiVRow: method(s) of DESC_NOTIFICATO

//AD4EventiVRow: method(s) of AFCNavigator @339-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4EventiVRow: method(s) of AFCNavigator

//AD4EventiVRow: class tail @142-FCB6E20C
}
//End AD4EventiVRow: class tail

