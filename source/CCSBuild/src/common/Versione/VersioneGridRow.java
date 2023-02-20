//VersioneGridRow: import @3-B68024AC
package common.Versione;

import java.util.*;
import com.codecharge.db.*;
//End VersioneGridRow: import

//VersioneGridRow: class head @3-F290DC6A
public class VersioneGridRow {
//End VersioneGridRow: class head

//VersioneGridRow: declare fiels @3-DD17717D
    private TextField VERSIONE = new TextField("VERSIONE", "VERSIONE");
    private TextField VERSIONE_DB = new TextField("VERSIONE_DB", "VERSIONE_DB");
//End VersioneGridRow: declare fiels

//VersioneGridRow: constructor @3-D20E87DA
    public VersioneGridRow() {
    }
//End VersioneGridRow: constructor

//VersioneGridRow: method(s) of VERSIONE @5-D2D0EF90
    public TextField getVERSIONEField() {
        return VERSIONE;
    }

    public String getVERSIONE() {
        return VERSIONE.getValue();
    }

    public void setVERSIONE(String value) {
        this.VERSIONE.setValue(value);
    }
//End VersioneGridRow: method(s) of VERSIONE

//VersioneGridRow: method(s) of VERSIONE_DB @4-10522938
    public TextField getVERSIONE_DBField() {
        return VERSIONE_DB;
    }

    public String getVERSIONE_DB() {
        return VERSIONE_DB.getValue();
    }

    public void setVERSIONE_DB(String value) {
        this.VERSIONE_DB.setValue(value);
    }
//End VersioneGridRow: method(s) of VERSIONE_DB

//VersioneGridRow: class tail @3-FCB6E20C
}
//End VersioneGridRow: class tail

