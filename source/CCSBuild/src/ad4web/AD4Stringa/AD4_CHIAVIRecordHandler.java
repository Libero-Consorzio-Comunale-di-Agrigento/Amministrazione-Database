//AD4_CHIAVIHandler Head @99-9B1D41FE
package ad4web.AD4Stringa;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_CHIAVIRecordHandler implements RecordListener {
//End AD4_CHIAVIHandler Head

//BeforeShow Head @99-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @158-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 String urlH="";
	if(e.getPage().getRequest().getQueryString().indexOf("_h")>=0){
       try{
       urlH=com.codecharge.util.StringUtils.decryptB64URL(e.getPage().getRequest().getQueryString().split("=")[1]);
       }catch(Exception exc){exc.printStackTrace();}
   }

   if (e.getPage().getRequest().getParameter("TIPOR")  != null) {
		SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("TIPOR", e.getPage().getRequest().getParameter("TIPOR"));
		if (e.getPage().getRequest().getParameter("TIPOR").equals("S")) {
 	           e.getRecord().setVisible(false);
           }
	} else if((!urlH.equals(""))&&(!com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR").equals(""))){
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("TIPOR",   com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR") );
		if (com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR").equals("S")) {
 	      e.getRecord().setVisible(false);
           }
    }

// if (e.getPage().getRequest().getParameter("TIPOR").equals("S")) {
// 	e.getRecord().setVisible(false);
// }
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @99-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @99-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @99-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @99-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @99-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @99-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @99-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @99-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @99-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @99-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @99-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @99-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @99-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @99-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @99-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @99-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @99-FCB6E20C
    }
//End AfterDelete Tail

//AD4_CHIAVIHandler Tail @99-FCB6E20C
}
//End AD4_CHIAVIHandler Tail

