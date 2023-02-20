//AD4GruppiVRow: import @142-91AFF762
package ad4web.AD4GruppiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4GruppiVRow: import

//AD4GruppiVRow: class head @142-9B7B71C0
public class AD4GruppiVRow {
//End AD4GruppiVRow: class head

//AD4GruppiVRow: declare fiels @142-C2F8097C
    private TextField GRUPPO = new TextField("GRUPPO", "GRUPPO");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End AD4GruppiVRow: declare fiels

//AD4GruppiVRow: constructor @142-EED38B2F
    public AD4GruppiVRow() {
    }
//End AD4GruppiVRow: constructor

//AD4GruppiVRow: method(s) of GRUPPO @278-C886BC8A
    public TextField getGRUPPOField() {
        return GRUPPO;
    }

    public String getGRUPPO() {
        return GRUPPO.getValue();
    }

    public void setGRUPPO(String value) {
        this.GRUPPO.setValue(value);
    }
//End AD4GruppiVRow: method(s) of GRUPPO

//AD4GruppiVRow: method(s) of DESCRIZIONE @263-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4GruppiVRow: method(s) of DESCRIZIONE

//AD4GruppiVRow: method(s) of NOTE @335-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4GruppiVRow: method(s) of NOTE

//AD4GruppiVRow: method(s) of AFCNavigator @345-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4GruppiVRow: method(s) of AFCNavigator

//AD4GruppiVRow: class tail @142-FCB6E20C
}
//End AD4GruppiVRow: class tail

