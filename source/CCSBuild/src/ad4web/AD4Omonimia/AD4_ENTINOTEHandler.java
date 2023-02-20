//AD4_ENTINOTEHandler Head @22-407973C5
package ad4web.AD4Omonimia;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_ENTINOTEHandler implements ValidationListener {
//End AD4_ENTINOTEHandler Head

//OnValidate Head @22-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Maximum Length @55-C09E1867
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

//OnValidate Tail @22-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @22-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @22-FCB6E20C
    }
//End BeforeShow Tail

//AD4_ENTINOTEHandler Tail @22-FCB6E20C
}
//End AD4_ENTINOTEHandler Tail

