function numero(campo,l,d) {
   var testo;
   var decimali;
   var intero;
   var posPunto;
   testo = campo.value;
   testo = testo.replace(",",".");
   if (isNaN(testo)) {
      campo.value = "";
      alert("Attenzione. Il valore inserito non è numerico!");
   } else {
      posPunto = testo.lastIndexOf(".");
      if (posPunto != -1) {
         intero = testo.substr(0,posPunto);
         decimali = testo.substr(posPunto+1,testo.length);
      } else {
         intero = testo;
         decimali = "";
      }
      if (intero.length > (l - d) || decimali.length > d) {
         campo.value = "";
         alert("Attenzione. Il campo prevede "+(l-d)+" interi e "+d+" decimali!");
      }
   }
}
 
function lunghezza(campo,n) {
   var testo;
   testo = campo.value;
   if (testo.length > n) {
      campo.value = testo.substr(0,n);
      alert("Attenzione. Superata la lunghezza massima del campo!");
   } 
}

function data (campo,formato) {
   var testo;
   var giorno;
   var nmese;
   var anno;
   var ore;
   var minuti;
   var secondi;
   var npos;
   var newData;
   testo = campo.value;

   if (testo == "") {
      return null;
   }
      
   if (formato != "null") {
      npos = formato.indexOf("yyyy");
      if (npos > -1 && npos < testo.length) {
      	  anno = testo.substr(npos,4);
         if (isNaN(anno)) {
            campo.value = "";
	    alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
	    return null;
         }
      } else {
        anno = 1900;
      }
      
      npos = formato.indexOf("mm");
      if (npos > -1  && npos < testo.length) {
         nmese = testo.substr(npos, 2);
         if (isNaN(nmese) ) {
            campo.value = "";
			alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
			return null;
         }
      } else {
        nmese = 1;
      }
	  if (nmese < 1 || nmese > 12) {
        campo.value = "";
		alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
		return null;
	  }
      
      npos = formato.indexOf("dd");
      if (npos > -1  && npos < testo.length) {
         giorno = testo.substr(npos,2);
         if (isNaN(giorno )) {
            campo.value = "";
	    alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
	    return null;
         } 
      } else {
        giorno = 1;
      }
      
      npos = formato.indexOf("hh");
      if (npos > -1  && npos < testo.length) {
         ore = testo.substr(npos,2);
         if (isNaN(ore )) {
            campo.value = "";
	    alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
	    return null;
         }
      } else {
         ore = "00";
      }
      
      npos = formato.indexOf("mi");
      if (npos > -1 && npos < testo.length) {
         minuti= testo.substr(npos,2);
         if (isNaN(minuti)) {
            campo.value = "";
	    alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
	    return null;
         }
      } else {
         minuti = "00";
      }
      
      npos = formato.indexOf("ss");
      if (npos > -1 && npos < testo.length) {
         secondi = testo.substr(npos,2);
         if (isNaN(secondi)) {
            campo.value = "";
	    alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
	    return null;
         }
      } else {
         secondi = "00";
      }
      
      var myData = new Date();
      myData.setFullYear(anno);
//      myData.setMonth((nmese-1));
      myData.setMonth(0);
      myData.setDate(giorno);
      myData.setHours(ore);
      myData.setMinutes(minuti);
      myData.setSeconds(secondi);
      
      if ((anno != myData.getFullYear()) ||
//          ((nmese-1) != myData.getMonth()) ||
          (giorno != myData.getDate()) ||
          (ore != myData.getHours()) ||
          (minuti != myData.getMinutes()) ||
          (secondi != myData.getSeconds())) {
         campo.value = "";
	 alert("Attenzione. La data inserita deve essere nel formato: '"+formato+"'!");
         return null;
      } else {
         newData = formato;
         newData = newData.replace("dd",giorno);
         newData = newData.replace("mm",nmese);
         newData = newData.replace("yyyy",anno);
         newData = newData.replace("hh",ore);
         newData = newData.replace("mi",minuti);
         newData = newData.replace("ss",secondi);
         campo.value = newData
      }
   }
}

function campiObb(theForm) {
	theForm.Registra.disabled = true;
	if (theForm.RegistraInoltro != null) {
		theForm.RegistraInoltro.disabled = true;
	}
	var campo;
	var campo_checked = "N";
	var obb = theForm.campiobbligatori.value;
	var coll = theForm.elements;
    if (coll!=null) {
    	for (i=0; i<coll.length; i++) {
			campo = coll.item(i).name;
			if (obb.indexOf("@"+campo+"@") > -1 ) {
				if (coll.item(i).type == "radio" || coll.item(i).type == "checkbox") {
					while (coll.item(i).name == campo && i<coll.length) {
						if (coll.item(i).checked) {
							campo_checked = "Y";
						}
						i++;
					}
					i--;
					if (campo_checked == "N") {
						alert("Attenzione. Campo "+campo+" obbligatorio!");
						theForm.Registra.disabled = false;
						if (theForm.RegistraInoltro != null) {
							theForm.RegistraInoltro.disabled = false;
						}
						return null;
					}
				} else {
					if (coll.item(i).value == "") {
						alert("Attenzione. Campo "+campo+" obbligatorio!");
						theForm.Registra.disabled = false;
						if (theForm.RegistraInoltro != null) {
							theForm.RegistraInoltro.disabled = false;
						}
						return null;
					}
				}
			}
		}
	}
	newAction();
}
