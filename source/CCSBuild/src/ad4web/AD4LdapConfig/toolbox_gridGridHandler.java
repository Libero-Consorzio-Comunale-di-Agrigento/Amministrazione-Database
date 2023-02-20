//toolbox_gridGridHandler @120-23AD79C8
package ad4web.AD4LdapConfig;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class toolbox_gridGridHandler implements GridListener {
//End toolbox_gridGridHandler

// //beforeShow @120-F81417CB

//BeforeShow Head @120-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @120-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @120-F81417CB

//beforeShowRow Head @120-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @120-F2DAE9E2
        e.getGrid().getLink("Nuovo").getParameter("TIPOR").setValue(( "S" ));
//End Set values for static controls

//beforeShowRow Tail @120-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @120-F81417CB

//BeforeSelect Head @120-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @120-FCB6E20C
    }
//End BeforeSelect Tail

//toolbox_gridHandler Tail @120-FCB6E20C
}
//End toolbox_gridHandler Tail

