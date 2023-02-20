//ToolboxRow: import @418-76ED8CB0
package ad4web.AD4AccessiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End ToolboxRow: import

//ToolboxRow: class head @418-A0B9D2FD
public class ToolboxRow {
//End ToolboxRow: class head

//ToolboxRow: declare fiels @418-723835A3
    private TextField RICERCA = new TextField("RICERCA", "RICERCA");
    private TextField ELIMINA_ARCHIVIATI = new TextField("ELIMINA_ARCHIVIATI", "ELIMINA_ARCHIVIATI");
//End ToolboxRow: declare fiels

//ToolboxRow: constructor @418-BDE65ECC
    public ToolboxRow() {
    }
//End ToolboxRow: constructor

//ToolboxRow: method(s) of RICERCA @419-548D848A
    public TextField getRICERCAField() {
        return RICERCA;
    }

    public String getRICERCA() {
        return RICERCA.getValue();
    }

    public void setRICERCA(String value) {
        this.RICERCA.setValue(value);
    }
//End ToolboxRow: method(s) of RICERCA

//ToolboxRow: method(s) of ELIMINA_ARCHIVIATI @431-61844FED
    public TextField getELIMINA_ARCHIVIATIField() {
        return ELIMINA_ARCHIVIATI;
    }

    public String getELIMINA_ARCHIVIATI() {
        return ELIMINA_ARCHIVIATI.getValue();
    }

    public void setELIMINA_ARCHIVIATI(String value) {
        this.ELIMINA_ARCHIVIATI.setValue(value);
    }
//End ToolboxRow: method(s) of ELIMINA_ARCHIVIATI

//ToolboxRow: class tail @418-FCB6E20C
}
//End ToolboxRow: class tail

