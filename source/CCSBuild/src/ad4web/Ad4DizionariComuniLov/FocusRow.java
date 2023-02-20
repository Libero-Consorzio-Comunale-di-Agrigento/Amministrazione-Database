//FocusRow: import @21-9CDBB3C7
package ad4web.Ad4DizionariComuniLov;

import java.util.*;
import com.codecharge.db.*;
//End FocusRow: import

//FocusRow: class head @21-E6D2AB11
public class FocusRow {
//End FocusRow: class head

//FocusRow: declare fiels @21-0C3B6B80
    private TextField FIELD_FOCUS = new TextField("FIELD_FOCUS", "FIELD_FOCUS");
//End FocusRow: declare fiels

//FocusRow: constructor @21-1EC70A58
    public FocusRow() {
    }
//End FocusRow: constructor

//FocusRow: method(s) of FIELD_FOCUS @22-D3F60D94
    public TextField getFIELD_FOCUSField() {
        return FIELD_FOCUS;
    }

    public String getFIELD_FOCUS() {
        return FIELD_FOCUS.getValue();
    }

    public void setFIELD_FOCUS(String value) {
        this.FIELD_FOCUS.setValue(value);
    }
//End FocusRow: method(s) of FIELD_FOCUS

//FocusRow: class tail @21-FCB6E20C
}
//End FocusRow: class tail

