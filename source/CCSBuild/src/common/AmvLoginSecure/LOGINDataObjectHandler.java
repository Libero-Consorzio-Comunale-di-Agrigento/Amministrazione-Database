//LOGINDataObjectHandler head @2-8A647AE6
package common.AmvLoginSecure;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LOGINDataObjectHandler implements RecordDataObjectListener {
//End LOGINDataObjectHandler head

// //BeforeBuildSelect @2-F81417CB

//beforeBuildSelect head @2-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @2-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @2-F81417CB

//beforeExecuteSelect head @2-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @2-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @2-F81417CB

//afterExecuteSelect Head @2-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//Event AfterExecuteSelect Action Custom Code @30-44795B7A
  String sa4ck=StringUtils.encryptB64(e.getPage().getRequest().getRemoteHost()+"#"+System.currentTimeMillis());

  e.getRecord().getControl("sa4ck").setValue(sa4ck);
//End Event AfterExecuteSelect Action Custom Code

//afterExecuteSelect Tail @2-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @2-F81417CB

//beforeBuildInsert head @2-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @2-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @2-F81417CB

//beforeExecuteInsert head @2-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @2-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @2-F81417CB

//afterExecuteInsert Head @2-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @2-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @2-F81417CB

//beforeBuildUpdate head @2-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @2-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @2-F81417CB

//beforeExecuteUpdate head @2-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @2-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @2-F81417CB

//afterExecuteUpdate Head @2-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//afterExecuteUpdate Tail @2-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @2-F81417CB

//beforeBuildDelete head @2-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @2-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @2-F81417CB

//beforeExecuteDelete head @2-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @2-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @2-F81417CB

//afterExecuteDelete Head @2-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//afterExecuteDelete Tail @2-FCB6E20C
    }
//End afterExecuteDelete Tail

//LOGINDataObjectHandler Tail @2-FCB6E20C
}
//End LOGINDataObjectHandler Tail

