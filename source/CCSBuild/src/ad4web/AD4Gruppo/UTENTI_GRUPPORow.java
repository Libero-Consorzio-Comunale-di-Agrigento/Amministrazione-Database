//UTENTI_GRUPPORow: import @78-273C5FE5
package ad4web.AD4Gruppo;

import java.util.*;
import com.codecharge.db.*;
//End UTENTI_GRUPPORow: import

//UTENTI_GRUPPORow: class head @78-7970F9C9
public class UTENTI_GRUPPORow {
//End UTENTI_GRUPPORow: class head

//UTENTI_GRUPPORow: declare fiels @78-347EB98D
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField CHECK_DIAC_CAAC = new TextField("CHECK_DIAC_CAAC", "CHECK_DIAC_CAAC");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End UTENTI_GRUPPORow: declare fiels

//UTENTI_GRUPPORow: constructor @78-FFCB3D93
    public UTENTI_GRUPPORow() {
    }
//End UTENTI_GRUPPORow: constructor

//UTENTI_GRUPPORow: method(s) of NOMINATIVO @79-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End UTENTI_GRUPPORow: method(s) of NOMINATIVO

//UTENTI_GRUPPORow: method(s) of CHECK_DIAC_CAAC @87-430D307F
    public TextField getCHECK_DIAC_CAACField() {
        return CHECK_DIAC_CAAC;
    }

    public String getCHECK_DIAC_CAAC() {
        return CHECK_DIAC_CAAC.getValue();
    }

    public void setCHECK_DIAC_CAAC(String value) {
        this.CHECK_DIAC_CAAC.setValue(value);
    }
//End UTENTI_GRUPPORow: method(s) of CHECK_DIAC_CAAC

//UTENTI_GRUPPORow: method(s) of AFCNavigator @88-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End UTENTI_GRUPPORow: method(s) of AFCNavigator

//UTENTI_GRUPPORow: class tail @78-FCB6E20C
}
//End UTENTI_GRUPPORow: class tail

