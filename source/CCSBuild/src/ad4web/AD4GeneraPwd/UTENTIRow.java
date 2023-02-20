//UTENTIRow: import @6-E5BCCF1E
package ad4web.AD4GeneraPwd;

import java.util.*;
import com.codecharge.db.*;
//End UTENTIRow: import

//UTENTIRow: class head @6-3B5B4558
public class UTENTIRow {
//End UTENTIRow: class head

//UTENTIRow: declare fiels @6-DB7901B9
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField DESCR = new TextField("DESCR", "DESCR");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private LongField MIN_PWD_LENGTH = new LongField("MIN_PWD_LENGTH", "MIN_PWD_LENGTH");
    private TextField PWD = new TextField("PWD", "PWD");
//End UTENTIRow: declare fiels

//UTENTIRow: constructor @6-D1B97CD1
    public UTENTIRow() {
    }
//End UTENTIRow: constructor

//UTENTIRow: method(s) of NOMINATIVO @19-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End UTENTIRow: method(s) of NOMINATIVO

//UTENTIRow: method(s) of DESCR @20-BE807364
    public TextField getDESCRField() {
        return DESCR;
    }

    public String getDESCR() {
        return DESCR.getValue();
    }

    public void setDESCR(String value) {
        this.DESCR.setValue(value);
    }
//End UTENTIRow: method(s) of DESCR

//UTENTIRow: method(s) of UTENTE @7-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End UTENTIRow: method(s) of UTENTE

//UTENTIRow: method(s) of MIN_PWD_LENGTH @21-11776BFB
    public LongField getMIN_PWD_LENGTHField() {
        return MIN_PWD_LENGTH;
    }

    public Long getMIN_PWD_LENGTH() {
        return MIN_PWD_LENGTH.getValue();
    }

    public void setMIN_PWD_LENGTH(Long value) {
        this.MIN_PWD_LENGTH.setValue(value);
    }
//End UTENTIRow: method(s) of MIN_PWD_LENGTH

//UTENTIRow: method(s) of PWD @22-BF21A2BD
    public TextField getPWDField() {
        return PWD;
    }

    public String getPWD() {
        return PWD.getValue();
    }

    public void setPWD(String value) {
        this.PWD.setValue(value);
    }
//End UTENTIRow: method(s) of PWD

//UTENTIRow: class tail @6-FCB6E20C
}
//End UTENTIRow: class tail

