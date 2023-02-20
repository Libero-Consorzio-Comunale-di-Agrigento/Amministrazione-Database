//AD4_MODULINOTEHandler Head @33-8D9FA31E
package ad4web.AD4Modulo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_MODULINOTEHandler implements ValidationListener {
//End AD4_MODULINOTEHandler Head

//OnValidate Head @33-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Maximum Length @48-C09E1867
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                String value = cntrl.toString();
                if ( ! com.codecharge.util.StringUtils.isEmpty(value) ) {
                    if ( value.length() > 2000) cntrl.addError("Le note devono essere al più di 2000 caratteri");
                }
            }
        }
//End Event OnValidate Action Validate Maximum Length

//OnValidate Tail @33-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @33-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @33-FCB6E20C
    }
//End BeforeShow Tail

//AD4_MODULINOTEHandler Tail @33-FCB6E20C
}
//End AD4_MODULINOTEHandler Tail

