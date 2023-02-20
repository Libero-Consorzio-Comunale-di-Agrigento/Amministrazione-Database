//PARAMETRI_STAMPARow: import @6-FDDF4FB2
package ad4web.AD4StampaUtenti;

import java.util.*;
import com.codecharge.db.*;
//End PARAMETRI_STAMPARow: import

//PARAMETRI_STAMPARow: class head @6-2C073DF4
public class PARAMETRI_STAMPARow {
//End PARAMETRI_STAMPARow: class head

//PARAMETRI_STAMPARow: declare fiels @6-56EE5134
    private TextField TIPO_UTENTE = new TextField("TIPO_UTENTE", "TIPO_UTENTE");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField FORMATO = new TextField("FORMATO", "FORMATO");
    private TextField CONTEXT = new TextField("CONTEXT", "CONTEXT");
    private TextField DATA_SOURCE = new TextField("DATA_SOURCE", "DS");
//End PARAMETRI_STAMPARow: declare fiels

//PARAMETRI_STAMPARow: constructor @6-04C60289
    public PARAMETRI_STAMPARow() {
    }
//End PARAMETRI_STAMPARow: constructor

//PARAMETRI_STAMPARow: method(s) of TIPO_UTENTE @61-3F9E6945
    public TextField getTIPO_UTENTEField() {
        return TIPO_UTENTE;
    }

    public String getTIPO_UTENTE() {
        return TIPO_UTENTE.getValue();
    }

    public void setTIPO_UTENTE(String value) {
        this.TIPO_UTENTE.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of TIPO_UTENTE

//PARAMETRI_STAMPARow: method(s) of MODULO @34-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of MODULO

//PARAMETRI_STAMPARow: method(s) of ISTANZA @62-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of ISTANZA

//PARAMETRI_STAMPARow: method(s) of FORMATO @63-AC1DCF57
    public TextField getFORMATOField() {
        return FORMATO;
    }

    public String getFORMATO() {
        return FORMATO.getValue();
    }

    public void setFORMATO(String value) {
        this.FORMATO.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of FORMATO

//PARAMETRI_STAMPARow: method(s) of CONTEXT @51-81AE58E8
    public TextField getCONTEXTField() {
        return CONTEXT;
    }

    public String getCONTEXT() {
        return CONTEXT.getValue();
    }

    public void setCONTEXT(String value) {
        this.CONTEXT.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of CONTEXT

//PARAMETRI_STAMPARow: method(s) of DATA_SOURCE @48-7D27D60C
    public TextField getDATA_SOURCEField() {
        return DATA_SOURCE;
    }

    public String getDATA_SOURCE() {
        return DATA_SOURCE.getValue();
    }

    public void setDATA_SOURCE(String value) {
        this.DATA_SOURCE.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of DATA_SOURCE

//PARAMETRI_STAMPARow: class tail @6-FCB6E20C
}
//End PARAMETRI_STAMPARow: class tail

