//AD4_CARATTERISTICHE_ACCESSOSLEEPHandler Head @204-D5A84FE8
package ad4web.AD4CopiaUtenti;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_CARATTERISTICHE_ACCESSOSLEEPHandler implements ValidationListener {
//End AD4_CARATTERISTICHE_ACCESSOSLEEPHandler Head

//OnValidate Head @204-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Minimum Value @205-B9740CA9
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                Object value = cntrl.getValue();
                boolean invalidValue = false;
                if (value != null) {
                    if (value instanceof Long) {
                        if (((Long)value).doubleValue() < 0) cntrl.addError("Il valore deve essere un numero positivo ( >= 0 ) !");
                    } else if (value instanceof Double) {
                        if (((Double)value).doubleValue() < 0) cntrl.addError("Il valore deve essere un numero positivo ( >= 0 ) !");
                    } else if (value instanceof String) {
                        try {
                            if ((new Double((String) value)).doubleValue() < 0) cntrl.addError("Il valore deve essere un numero positivo ( >= 0 ) !");
                        } catch ( NumberFormatException nfe ) {
                            invalidValue = true;
                        }
                    } else {
                        invalidValue = true;
                    }
                }
                if ( invalidValue ) {
                    if ( cntrl.hasErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR )) ) {
                        cntrl.removeErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR ) );
                    }
                    cntrl.addError("The value in field " + cntrl.getCaption() + " is not valid.");
                }
            }
        }
//End Event OnValidate Action Validate Minimum Value

//OnValidate Tail @204-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @204-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @204-FCB6E20C
    }
//End BeforeShow Tail

//AD4_CARATTERISTICHE_ACCESSOSLEEPHandler Tail @204-FCB6E20C
}
//End AD4_CARATTERISTICHE_ACCESSOSLEEPHandler Tail
