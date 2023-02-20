function getAjaxHtmlIntoElement(openMethod, urlPage, urlParams, elementId) {
	//--------------------------------------------
	// Create an XMLHttpRequest Object
	//--------------------------------------------
    	var xHttpReq = createXMLHttpRequest(); 

	//--------------------------------------------
	// Make the request
	//--------------------------------------------
	makeXMLHttpRequest(xHttpReq, openMethod, urlPage, urlParams);
		
	//--------------------------------------------
	// Retrieve data
	//--------------------------------------------
	retrieveHtmlIntoElement(xHttpReq, elementId);
}

function createXMLHttpRequest() {
	var xmlHttpReq = false;
	var self = this;
	//////////////////////////////////////////////////////////////
	// Create an XMLHttpRequest Object
	// Note:
	// in Internet Explorer, XMLHttpRequest is implemented as an 
	// ActiveX, this means that if users have ActiveX objects 
	// disabled in Internet Explorer, they will be unable to use 
	// XMLHttpRequest even if JavaScript is enabled.
	//////////////////////////////////////////////////////////////
    	// Mozilla/Safari
    	if (window.XMLHttpRequest) {
        	self.xmlHttpReq = new XMLHttpRequest();
    	}
    	// IE
    	else if (window.ActiveXObject) {
        	self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    	}
		return self.xmlHttpReq;
}

function makeXMLHttpRequest(xHttpReq, openMethod, urlPage, urlParams) {
	//////////////////////////////////////////////////////////////
	// Make the request:
	// - open: initialises the connection we wish to make  with 
	//   the type of request we want to send and the location from
	//   which we wish to request data.
	// - send: once the connection has been initialised, activates
	//   the connection and makes the request.
	//////////////////////////////////////////////////////////////
	var url = urlPage;
	if (openMethod == 'GET') {
		url += '?' + urlParams;
		urlParams = null;
	}
	xHttpReq.open(openMethod, url, true);
	xHttpReq.send(urlParams);
}

function retrieveHtmlIntoElement(xHttpReq, elementId) {
	//////////////////////////////////////////////////////////////
	// Retrieve data
	// XMLHttpRequest has to contact the server and retrieve the 
	// data we requested; this process takes an indeterminate 
	// amount of time. In order to find out when the object has 
	// finished retrieving data, we need to listen for changes in 
	// XMLHttpRequest readyState variable that specifies the 
	// status of the object's connection:
	//     * 0 – Uninitialised
	//     * 1 – Loading
	//     * 2 – Loaded
	//     * 3 – Interactive
	//     * 4 – Completed
	// Upon the connection's completion ( readyState = 4) , we 
	// also have to check whether the XMLHttpRequest object 
	// successfully retrieved the data. This can be determined 
	// from the object's status property: 200 denotes a successful
	// completion.
	//////////////////////////////////////////////////////////////
	xHttpReq.onreadystatechange = function() {
       		if (xHttpReq.readyState == 4) {
       			if (xHttpReq.status == 200) {
           			updatepage(elementId, xHttpReq.responseText);
			}
       		}
	}
}

function updatepage(elementId, HTMLValue){
    	document.getElementById(elementId).innerHTML = HTMLValue;
}