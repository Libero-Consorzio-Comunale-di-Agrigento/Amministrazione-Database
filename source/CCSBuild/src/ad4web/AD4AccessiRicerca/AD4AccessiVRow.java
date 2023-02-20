//AD4AccessiVRow: import @142-76ED8CB0
package ad4web.AD4AccessiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4AccessiVRow: import

//AD4AccessiVRow: class head @142-33C4FCE1
public class AD4AccessiVRow {
//End AD4AccessiVRow: class head

//AD4AccessiVRow: declare fiels @142-53E5F939
    private TextField TR_STYLE = new TextField("TR_STYLE", "TR_STYLE");
    private LongField ACCE_ID = new LongField("ACCE_ID", "ACCE_ID");
    private TextField DATA = new TextField("DATA", "DATA");
    private TextField SESSION_ID = new TextField("SESSION_ID", "SESSION_ID");
    private TextField DESC_LOG = new TextField("DESC_LOG", "DESC_LOG");
    private TextField PROVENIENZA = new TextField("PROVENIENZA", "PROVENIENZA");
    private TextField DB_USER = new TextField("DB_USER", "DB_USER");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField ACCESSO = new TextField("ACCESSO", "ACCESSO");
    private TextField TIPO_AUTENTICAZIONE = new TextField("TIPO_AUTENTICAZIONE", "TIPO_AUTENTICAZIONE");
    private TextField DESC_STATO = new TextField("DESC_STATO", "DESC_STATO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField ID_EVENTO = new TextField("ID_EVENTO", "ID_EVENTO");
//End AD4AccessiVRow: declare fiels

//AD4AccessiVRow: constructor @142-CF97B647
    public AD4AccessiVRow() {
    }
//End AD4AccessiVRow: constructor

//AD4AccessiVRow: method(s) of TR_STYLE @415-F486E7B3
    public TextField getTR_STYLEField() {
        return TR_STYLE;
    }

    public String getTR_STYLE() {
        return TR_STYLE.getValue();
    }

    public void setTR_STYLE(String value) {
        this.TR_STYLE.setValue(value);
    }
//End AD4AccessiVRow: method(s) of TR_STYLE

//AD4AccessiVRow: method(s) of ACCE_ID @278-0F0BBC80
    public LongField getACCE_IDField() {
        return ACCE_ID;
    }

    public Long getACCE_ID() {
        return ACCE_ID.getValue();
    }

    public void setACCE_ID(Long value) {
        this.ACCE_ID.setValue(value);
    }
//End AD4AccessiVRow: method(s) of ACCE_ID

//AD4AccessiVRow: method(s) of DATA @263-28E61A6F
    public TextField getDATAField() {
        return DATA;
    }

    public String getDATA() {
        return DATA.getValue();
    }

    public void setDATA(String value) {
        this.DATA.setValue(value);
    }
//End AD4AccessiVRow: method(s) of DATA

//AD4AccessiVRow: method(s) of SESSION_ID @333-3F0896A1
    public TextField getSESSION_IDField() {
        return SESSION_ID;
    }

    public String getSESSION_ID() {
        return SESSION_ID.getValue();
    }

    public void setSESSION_ID(String value) {
        this.SESSION_ID.setValue(value);
    }
//End AD4AccessiVRow: method(s) of SESSION_ID

//AD4AccessiVRow: method(s) of DESC_LOG @335-DD0D493D
    public TextField getDESC_LOGField() {
        return DESC_LOG;
    }

    public String getDESC_LOG() {
        return DESC_LOG.getValue();
    }

    public void setDESC_LOG(String value) {
        this.DESC_LOG.setValue(value);
    }
//End AD4AccessiVRow: method(s) of DESC_LOG

//AD4AccessiVRow: method(s) of PROVENIENZA @348-91184732
    public TextField getPROVENIENZAField() {
        return PROVENIENZA;
    }

    public String getPROVENIENZA() {
        return PROVENIENZA.getValue();
    }

    public void setPROVENIENZA(String value) {
        this.PROVENIENZA.setValue(value);
    }
//End AD4AccessiVRow: method(s) of PROVENIENZA

//AD4AccessiVRow: method(s) of DB_USER @416-33945EFE
    public TextField getDB_USERField() {
        return DB_USER;
    }

    public String getDB_USER() {
        return DB_USER.getValue();
    }

    public void setDB_USER(String value) {
        this.DB_USER.setValue(value);
    }
//End AD4AccessiVRow: method(s) of DB_USER

//AD4AccessiVRow: method(s) of NOTE @442-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4AccessiVRow: method(s) of NOTE

//AD4AccessiVRow: method(s) of ACCESSO @352-EBD40B8B
    public TextField getACCESSOField() {
        return ACCESSO;
    }

    public String getACCESSO() {
        return ACCESSO.getValue();
    }

    public void setACCESSO(String value) {
        this.ACCESSO.setValue(value);
    }
//End AD4AccessiVRow: method(s) of ACCESSO

//AD4AccessiVRow: method(s) of TIPO_AUTENTICAZIONE @354-1079E223
    public TextField getTIPO_AUTENTICAZIONEField() {
        return TIPO_AUTENTICAZIONE;
    }

    public String getTIPO_AUTENTICAZIONE() {
        return TIPO_AUTENTICAZIONE.getValue();
    }

    public void setTIPO_AUTENTICAZIONE(String value) {
        this.TIPO_AUTENTICAZIONE.setValue(value);
    }
//End AD4AccessiVRow: method(s) of TIPO_AUTENTICAZIONE

//AD4AccessiVRow: method(s) of DESC_STATO @353-EC70C3E9
    public TextField getDESC_STATOField() {
        return DESC_STATO;
    }

    public String getDESC_STATO() {
        return DESC_STATO.getValue();
    }

    public void setDESC_STATO(String value) {
        this.DESC_STATO.setValue(value);
    }
//End AD4AccessiVRow: method(s) of DESC_STATO

//AD4AccessiVRow: method(s) of AFCNavigator @339-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4AccessiVRow: method(s) of AFCNavigator

//AD4AccessiVRow: method(s) of ID_EVENTO @357-3FB9685E
    public TextField getID_EVENTOField() {
        return ID_EVENTO;
    }

    public String getID_EVENTO() {
        return ID_EVENTO.getValue();
    }

    public void setID_EVENTO(String value) {
        this.ID_EVENTO.setValue(value);
    }
//End AD4AccessiVRow: method(s) of ID_EVENTO

//AD4AccessiVRow: class tail @142-FCB6E20C
}
//End AD4AccessiVRow: class tail

