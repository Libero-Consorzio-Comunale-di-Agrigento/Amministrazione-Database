//AdmSezioneHandler imports @1-4FFEEDCA
package amvadm.AdmSezione;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AdmSezioneHandler imports

//AdmSezioneHandler Head @1-0D575EF8
public class AdmSezionePageHandler implements PageListener {
//End AdmSezioneHandler Head

//AfterInitialize Head @1-89E84600
    public void afterInitialize(Event e) {
//End AfterInitialize Head

//AfterInitialize Tail @1-FCB6E20C
    }
//End AfterInitialize Tail

//OnInitializeView Head @1-E3C15E0F
    public void onInitializeView(Event e) {
//End OnInitializeView Head

//Event OnInitializeView Action Custom Code @33-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
  SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVIF", "common/images");
  SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVOPT", "UPLOAD");

//End Event OnInitializeView Action Custom Code

//OnInitializeView Tail @1-FCB6E20C
    }
//End OnInitializeView Tail

//BeforeShow Head @1-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @1-FCB6E20C
    }
//End BeforeShow Tail

//BeforeUnload Head @1-1DDBA584
    public void beforeUnload(Event e) {
//End BeforeUnload Head

//BeforeUnload Tail @1-FCB6E20C
    }
//End BeforeUnload Tail

//AdmSezioneHandler Tail @1-FCB6E20C
}
//End AdmSezioneHandler Tail

