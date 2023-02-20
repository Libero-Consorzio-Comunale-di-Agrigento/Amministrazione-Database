//OMONIMIANOTEHandler Head @22-E991DD54
package ad4web.AD4Omonimia;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class OMONIMIANOTEHandler implements ValidationListener {
//End OMONIMIANOTEHandler Head

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

//OMONIMIANOTEHandler Tail @22-FCB6E20C
}
//End OMONIMIANOTEHandler Tail

