//NewGrid1Row: import @2-F5D39DD9
package ad4web.AD4GuidaIstanza;

import java.util.*;
import com.codecharge.db.*;
//End NewGrid1Row: import

//NewGrid1Row: class head @2-EF08A6E5
public class NewGrid1Row {
//End NewGrid1Row: class head

//NewGrid1Row: declare fiels @2-C40789F5
    private TextField FOLDER = new TextField("FOLDER", "FOLDER");
//End NewGrid1Row: declare fiels

//NewGrid1Row: constructor @2-B2F03CB8
    public NewGrid1Row() {
    }
//End NewGrid1Row: constructor

//NewGrid1Row: method(s) of FOLDER @3-938ED6C3
    public TextField getFOLDERField() {
        return FOLDER;
    }

    public String getFOLDER() {
        return FOLDER.getValue();
    }

    public void setFOLDER(String value) {
        this.FOLDER.setValue(value);
    }
//End NewGrid1Row: method(s) of FOLDER

//NewGrid1Row: class tail @2-FCB6E20C
}
//End NewGrid1Row: class tail

