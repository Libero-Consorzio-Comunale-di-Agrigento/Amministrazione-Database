//ldap_configGridHandler @110-1781320F
package ad4web.AD4Omonimie;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ldap_configGridHandler implements GridListener {
//End ldap_configGridHandler

// //beforeShow @110-F81417CB

//BeforeShow Head @110-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @110-A2BDC118
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
//End Set values for static controls

//BeforeShow Tail @110-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @110-F81417CB

//beforeShowRow Head @110-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @110-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @110-F81417CB

//BeforeSelect Head @110-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @110-FCB6E20C
    }
//End BeforeSelect Tail

//ldap_configHandler Tail @110-FCB6E20C
}
//End ldap_configHandler Tail

