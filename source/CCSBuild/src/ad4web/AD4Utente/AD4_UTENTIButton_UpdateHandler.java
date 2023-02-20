//AD4_UTENTIButton_UpdateHandler Head @8-865FD8AD
package ad4web.AD4Utente;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTIButton_UpdateHandler implements ButtonListener {
//End AD4_UTENTIButton_UpdateHandler Head

//onClick Head @8-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Save Control Value @157-BF90664D
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4UTENTE", ((com.codecharge.components.Label) ((com.codecharge.components.Record) (e.getPage().getChild( "AD4_UTENTI" ))).getChild( "UTENTE_VIS" )).getValue() );
//End Event OnClick Action Save Control Value

//onClick Tail @8-FCB6E20C
}
//End onClick Tail

//beforeShow Head @8-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_UTENTIButton_UpdateHandler Tail @8-F5FC18C5
    }
}
//End AD4_UTENTIButton_UpdateHandler Tail

