//PARAMETRI_STAMPARow: import @6-33A9B9D2
package ad4web.AD4StampaDirittiStorico;

import java.util.*;
import com.codecharge.db.*;
//End PARAMETRI_STAMPARow: import

//PARAMETRI_STAMPARow: class head @6-2C073DF4
public class PARAMETRI_STAMPARow {
//End PARAMETRI_STAMPARow: class head

//PARAMETRI_STAMPARow: declare fiels @6-4BCCFBA9
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private DateField DAL = new DateField("DAL", "DAL");
    private DateField AL = new DateField("AL", "AL");
    private TextField FORMATO = new TextField("FORMATO", "FORMATO");
    private TextField CONTEXT = new TextField("CONTEXT", "CONTEXT");
    private TextField DATA_SOURCE = new TextField("DATA_SOURCE", "DS");
//End PARAMETRI_STAMPARow: declare fiels

//PARAMETRI_STAMPARow: constructor @6-04C60289
    public PARAMETRI_STAMPARow() {
    }
//End PARAMETRI_STAMPARow: constructor

//PARAMETRI_STAMPARow: method(s) of UTENTE @61-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of UTENTE

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

//PARAMETRI_STAMPARow: method(s) of DAL @66-E2AA63B6
    public DateField getDALField() {
        return DAL;
    }

    public Date getDAL() {
        return DAL.getValue();
    }

    public void setDAL(Date value) {
        this.DAL.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of DAL

//PARAMETRI_STAMPARow: method(s) of AL @65-5C50CF0C
    public DateField getALField() {
        return AL;
    }

    public Date getAL() {
        return AL.getValue();
    }

    public void setAL(Date value) {
        this.AL.setValue(value);
    }
//End PARAMETRI_STAMPARow: method(s) of AL

//PARAMETRI_STAMPARow: method(s) of FORMATO @68-AC1DCF57
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

