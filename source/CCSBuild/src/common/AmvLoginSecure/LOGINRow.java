//LOGINRow: import @2-1B738CF9
package common.AmvLoginSecure;

import java.util.*;
import com.codecharge.db.*;
//End LOGINRow: import

//LOGINRow: class head @2-9B71E5B7
public class LOGINRow {
//End LOGINRow: class head

//LOGINRow: declare fiels @2-22D47A1C
    private TextField ACTION = new TextField("ACTION", "ACTION");
    private TextField ERRORE = new TextField("ERRORE", "ERRORE");
    private TextField u = new TextField("u", "USERNAME");
    private TextField p = new TextField("p", "PASSWORD");
    private TextField sa4ck = new TextField("sa4ck", "sa4ck");
    private TextField JSLOGIN = new TextField("JSLOGIN", "JSLOGIN");
//End LOGINRow: declare fiels

//LOGINRow: constructor @2-E217BB2E
    public LOGINRow() {
    }
//End LOGINRow: constructor

//LOGINRow: method(s) of ACTION @31-1026CEB6
    public TextField getACTIONField() {
        return ACTION;
    }

    public String getACTION() {
        return ACTION.getValue();
    }

    public void setACTION(String value) {
        this.ACTION.setValue(value);
    }
//End LOGINRow: method(s) of ACTION

//LOGINRow: method(s) of ERRORE @3-F2CEF8C4
    public TextField getERROREField() {
        return ERRORE;
    }

    public String getERRORE() {
        return ERRORE.getValue();
    }

    public void setERRORE(String value) {
        this.ERRORE.setValue(value);
    }
//End LOGINRow: method(s) of ERRORE

//LOGINRow: method(s) of u @4-3C255EA8
    public TextField getUField() {
        return u;
    }

    public String getU() {
        return u.getValue();
    }

    public void setU(String value) {
        this.u.setValue(value);
    }
//End LOGINRow: method(s) of u

//LOGINRow: method(s) of p @5-8B5BD38E
    public TextField getPField() {
        return p;
    }

    public String getP() {
        return p.getValue();
    }

    public void setP(String value) {
        this.p.setValue(value);
    }
//End LOGINRow: method(s) of p

//LOGINRow: method(s) of sa4ck @25-38BA3800
    public TextField getSa4ckField() {
        return sa4ck;
    }

    public String getSa4ck() {
        return sa4ck.getValue();
    }

    public void setSa4ck(String value) {
        this.sa4ck.setValue(value);
    }
//End LOGINRow: method(s) of sa4ck

//LOGINRow: method(s) of JSLOGIN @17-75B511C3
    public TextField getJSLOGINField() {
        return JSLOGIN;
    }

    public String getJSLOGIN() {
        return JSLOGIN.getValue();
    }

    public void setJSLOGIN(String value) {
        this.JSLOGIN.setValue(value);
    }
//End LOGINRow: method(s) of JSLOGIN

//LOGINRow: class tail @2-FCB6E20C
}
//End LOGINRow: class tail

