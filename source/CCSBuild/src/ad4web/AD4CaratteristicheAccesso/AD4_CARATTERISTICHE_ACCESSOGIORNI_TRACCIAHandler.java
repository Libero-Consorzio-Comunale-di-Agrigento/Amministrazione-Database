//AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler Head @160-98B880AF
package ad4web.AD4CaratteristicheAccesso;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler implements ValidationListener {
//End AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler Head

//OnValidate Head @160-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Minimum Value @161-95C6365B
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
                    cntrl.addError("Il valore nel campo " + cntrl.getCaption() + " non ? corretto.");
                }
            }
        }
//End Event OnValidate Action Validate Minimum Value

//OnValidate Tail @160-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @160-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @160-FCB6E20C
    }
//End BeforeShow Tail

//AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler Tail @160-FCB6E20C
}
//End AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler Tail
