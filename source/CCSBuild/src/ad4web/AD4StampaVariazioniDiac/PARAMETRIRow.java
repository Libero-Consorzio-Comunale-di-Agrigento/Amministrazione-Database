//PARAMETRIRow: import @6-A7841B9F
package ad4web.AD4StampaVariazioniDiac;

import java.util.*;
import com.codecharge.db.*;
//End PARAMETRIRow: import

//PARAMETRIRow: class head @6-B8FADBC0
public class PARAMETRIRow {
//End PARAMETRIRow: class head

//PARAMETRIRow: declare fiels @6-4BE59D81
    private TextField COD_UTENTE_ACCESSO = new TextField("COD_UTENTE_ACCESSO", "");
    private TextField MODULO = new TextField("MODULO", "");
    private DateField DAL = new DateField("DAL", "S_DAL");
    private DateField AL = new DateField("AL", "S_AL");
    private TextField FORMATO = new TextField("FORMATO", "FORMATO");
    private TextField CONTEXT = new TextField("CONTEXT", "CONTEXT");
    private TextField DATA_SOURCE = new TextField("DATA_SOURCE", "DS");
//End PARAMETRIRow: declare fiels

//PARAMETRIRow: constructor @6-CFDBF32C
    public PARAMETRIRow() {
    }
//End PARAMETRIRow: constructor

//PARAMETRIRow: method(s) of COD_UTENTE_ACCESSO @52-84CA0C3F
    public TextField getCOD_UTENTE_ACCESSOField() {
        return COD_UTENTE_ACCESSO;
    }

    public String getCOD_UTENTE_ACCESSO() {
        return COD_UTENTE_ACCESSO.getValue();
    }

    public void setCOD_UTENTE_ACCESSO(String value) {
        this.COD_UTENTE_ACCESSO.setValue(value);
    }
//End PARAMETRIRow: method(s) of COD_UTENTE_ACCESSO

//PARAMETRIRow: method(s) of MODULO @53-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End PARAMETRIRow: method(s) of MODULO

//PARAMETRIRow: method(s) of DAL @34-E2AA63B6
    public DateField getDALField() {
        return DAL;
    }

    public Date getDAL() {
        return DAL.getValue();
    }

    public void setDAL(Date value) {
        this.DAL.setValue(value);
    }
//End PARAMETRIRow: method(s) of DAL

//PARAMETRIRow: method(s) of AL @36-5C50CF0C
    public DateField getALField() {
        return AL;
    }

    public Date getAL() {
        return AL.getValue();
    }

    public void setAL(Date value) {
        this.AL.setValue(value);
    }
//End PARAMETRIRow: method(s) of AL

//PARAMETRIRow: method(s) of FORMATO @56-AC1DCF57
    public TextField getFORMATOField() {
        return FORMATO;
    }

    public String getFORMATO() {
        return FORMATO.getValue();
    }

    public void setFORMATO(String value) {
        this.FORMATO.setValue(value);
    }
//End PARAMETRIRow: method(s) of FORMATO

//PARAMETRIRow: method(s) of CONTEXT @54-81AE58E8
    public TextField getCONTEXTField() {
        return CONTEXT;
    }

    public String getCONTEXT() {
        return CONTEXT.getValue();
    }

    public void setCONTEXT(String value) {
        this.CONTEXT.setValue(value);
    }
//End PARAMETRIRow: method(s) of CONTEXT

//PARAMETRIRow: method(s) of DATA_SOURCE @48-7D27D60C
    public TextField getDATA_SOURCEField() {
        return DATA_SOURCE;
    }

    public String getDATA_SOURCE() {
        return DATA_SOURCE.getValue();
    }

    public void setDATA_SOURCE(String value) {
        this.DATA_SOURCE.setValue(value);
    }
//End PARAMETRIRow: method(s) of DATA_SOURCE

//PARAMETRIRow: class tail @6-FCB6E20C
}
//End PARAMETRIRow: class tail

