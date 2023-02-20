//USER_JOBSRow: import @56-8C178940
package ad4web.AD4UserJob;

import java.util.*;
import com.codecharge.db.*;
//End USER_JOBSRow: import

//USER_JOBSRow: class head @56-19EBCDC4
public class USER_JOBSRow {
//End USER_JOBSRow: class head

//USER_JOBSRow: declare fiels @56-644874E7
    private TextField JOB = new TextField("JOB", "JOB");
    private TextField WHAT = new TextField("WHAT", "WHAT");
    private TextField WHAT_HIDDEN = new TextField("WHAT_HIDDEN", "WHAT");
    private LongField JOB_NUM = new LongField("JOB_NUM", "JOB");
    private TextField NEXT_DATE = new TextField("NEXT_DATE", "NEXT_DATE");
    private TextField BROKEN_JOB = new TextField("BROKEN_JOB", "BROKEN_JOB");
    private TextField BROKEN = new TextField("BROKEN", "BROKEN");
    private TextField INTERVAL = new TextField("INTERVAL", "INTERVAL");
//End USER_JOBSRow: declare fiels

//USER_JOBSRow: constructor @56-BEFACE74
    public USER_JOBSRow() {
    }
//End USER_JOBSRow: constructor

//USER_JOBSRow: method(s) of JOB @195-07DC32BE
    public TextField getJOBField() {
        return JOB;
    }

    public String getJOB() {
        return JOB.getValue();
    }

    public void setJOB(String value) {
        this.JOB.setValue(value);
    }
//End USER_JOBSRow: method(s) of JOB

//USER_JOBSRow: method(s) of WHAT @176-C9825D52
    public TextField getWHATField() {
        return WHAT;
    }

    public String getWHAT() {
        return WHAT.getValue();
    }

    public void setWHAT(String value) {
        this.WHAT.setValue(value);
    }
//End USER_JOBSRow: method(s) of WHAT

//USER_JOBSRow: method(s) of WHAT_HIDDEN @193-DFA2CA84
    public TextField getWHAT_HIDDENField() {
        return WHAT_HIDDEN;
    }

    public String getWHAT_HIDDEN() {
        return WHAT_HIDDEN.getValue();
    }

    public void setWHAT_HIDDEN(String value) {
        this.WHAT_HIDDEN.setValue(value);
    }
//End USER_JOBSRow: method(s) of WHAT_HIDDEN

//USER_JOBSRow: method(s) of JOB_NUM @180-908E3257
    public LongField getJOB_NUMField() {
        return JOB_NUM;
    }

    public Long getJOB_NUM() {
        return JOB_NUM.getValue();
    }

    public void setJOB_NUM(Long value) {
        this.JOB_NUM.setValue(value);
    }
//End USER_JOBSRow: method(s) of JOB_NUM

//USER_JOBSRow: method(s) of NEXT_DATE @177-F1E740D7
    public TextField getNEXT_DATEField() {
        return NEXT_DATE;
    }

    public String getNEXT_DATE() {
        return NEXT_DATE.getValue();
    }

    public void setNEXT_DATE(String value) {
        this.NEXT_DATE.setValue(value);
    }
//End USER_JOBSRow: method(s) of NEXT_DATE

//USER_JOBSRow: method(s) of BROKEN_JOB @185-55CB23CD
    public TextField getBROKEN_JOBField() {
        return BROKEN_JOB;
    }

    public String getBROKEN_JOB() {
        return BROKEN_JOB.getValue();
    }

    public void setBROKEN_JOB(String value) {
        this.BROKEN_JOB.setValue(value);
    }
//End USER_JOBSRow: method(s) of BROKEN_JOB

//USER_JOBSRow: method(s) of BROKEN @179-C6EFD963
    public TextField getBROKENField() {
        return BROKEN;
    }

    public String getBROKEN() {
        return BROKEN.getValue();
    }

    public void setBROKEN(String value) {
        this.BROKEN.setValue(value);
    }
//End USER_JOBSRow: method(s) of BROKEN

//USER_JOBSRow: method(s) of INTERVAL @178-EC5A3D56
    public TextField getINTERVALField() {
        return INTERVAL;
    }

    public String getINTERVAL() {
        return INTERVAL.getValue();
    }

    public void setINTERVAL(String value) {
        this.INTERVAL.setValue(value);
    }
//End USER_JOBSRow: method(s) of INTERVAL

//USER_JOBSRow: class tail @56-FCB6E20C
}
//End USER_JOBSRow: class tail

