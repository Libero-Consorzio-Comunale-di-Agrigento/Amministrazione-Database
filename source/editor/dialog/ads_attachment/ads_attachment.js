/*
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2004 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: fck_link.js
 * 	Scripts related to the Link dialog window (see fck_link.html).
 * 
 * Version:  2.0 RC3
 * Modified: 2005-02-09 13:53:13
 * 
 * File Authors:
 * 		Frederico Caldeira Knabben (fredck@fckeditor.net)
 */

var oEditor = window.parent.InnerDialogLoaded() ;
var FCK		= oEditor.FCK ;
var FCKLang	= oEditor.FCKLang ;

//#### Dialog Tabs
/*
// Set the dialog tabs.
window.parent.AddTab( 'Info', FCKLang.DlgLnkInfoTab ) ;
window.parent.AddTab( 'Target', FCKLang.DlgLnkTargetTab, true ) ;
// TODO : Enable File Upload (1/3).
//window.parent.AddTab( 'Upload', 'Upload', true ) ;
window.parent.AddTab( 'Advanced', FCKLang.DlgAdvancedTag ) ;

// Function called when a dialog tag is selected.
function OnDialogTabChange( tabCode )
{
	ShowE('divInfo'		, ( tabCode == 'Info' ) ) ;
	ShowE('divTarget'	, ( tabCode == 'Target' ) ) ;
// TODO : Enable File Upload (2/3).
//	ShowE('divUpload'	, ( tabCode == 'Upload' ) ) ;
	ShowE('divAttribs'	, ( tabCode == 'Advanced' ) ) ;
}
*/
//#### Regular Expressions library.
var oRegex = new Object() ;

oRegex.UriProtocol = new RegExp('') ;
oRegex.UriProtocol.compile( '^(((http|https|ftp|news):\/\/)|mailto:)', 'gi' ) ;

oRegex.UrlOnChangeProtocol = new RegExp('') ;
oRegex.UrlOnChangeProtocol.compile( '^(http|https|ftp|news)://(?=.)', 'gi' ) ;

oRegex.UrlOnChangeTestOther = new RegExp('') ;
oRegex.UrlOnChangeTestOther.compile( '^(javascript:|#|/)', 'gi' ) ;

oRegex.ReserveTarget = new RegExp('') ;
oRegex.ReserveTarget.compile( '^_(blank|self|top|parent)$', 'i' ) ;

oRegex.PopupUri = new RegExp('') ;
oRegex.PopupUri.compile( "^javascript:void\\(\\s*window.open\\(\\s*'([^']+)'\\s*,\\s*(?:'([^']*)'|null)\\s*,\\s*'([^']*)'\\s*\\)\\s*\\)\\s*$" ) ;

oRegex.PopupFeatures = new RegExp('') ;
oRegex.PopupFeatures.compile( '(?:^|,)([^=]+)=(\\d+|yes|no)', 'gi' ) ;

//#### Parser Functions

var oParser = new Object() ;

oParser.ParseEMailUrl = function( emailUrl )
{
	// Initializes the EMailInfo object.
	var oEMailInfo = new Object() ;
	oEMailInfo.Address	= '' ;
	oEMailInfo.Subject	= '' ;
	oEMailInfo.Body		= '' ;

	var oParts = emailUrl.match( /^([^\?]+)\??(.+)?/ ) ;
	if ( oParts )
	{
		// Set the e-mail address.
		oEMailInfo.Address = oParts[1] ;

		// Look for the optional e-mail parameters.
		if ( oParts[2] )
		{
			var oMatch = oParts[2].match( /(^|&)subject=([^&]+)/i ) ;
			if ( oMatch ) oEMailInfo.Subject = unescape( oMatch[2] ) ;

			oMatch = oParts[2].match( /(^|&)body=([^&]+)/i ) ;
			if ( oMatch ) oEMailInfo.Body = unescape( oMatch[2] ) ;
		}
	}

	return oEMailInfo ;
}

oParser.CreateEMailUri = function( address, subject, body )
{
	var sBaseUri = 'mailto:' + address ;

	var sParams = '' ;

	if ( subject.length > 0 )
		sParams = '?subject=' + escape( subject ) ;

	if ( body.length > 0 )
	{
		sParams += ( sParams.length == 0 ? '?' : '&' ) ;
		sParams += 'body=' + escape( body ) ;
	}

	return sBaseUri + sParams ;
}

//#### Initialization Code

// oLink: The actual selected link in the editor.
var oLink = FCK.Selection.MoveToAncestorNode( 'A' ) ;
if ( oLink )
	FCK.Selection.MoveToNode( oLink ) ;

window.onload = function()
{
	// Translate the dialog box texts.
	//oEditor.FCKLanguageManager.TranslatePage(document);

	// Fill the Anchor Names and Ids combos.
	//LoadAnchorNamesAndIds() ;

	// Load the selected link information (if any).
	//LoadSelection() ;

	// Update the dialog box.
	//SetLinkType( GetE('cmbLinkType').value ) ;

	// Show/Hide the "Browse Server" button.
	//GetE('divBrowseServer').style.display = oEditor.FCKConfig.LinkBrowser ? '' : 'none' ;

	// Show the initial dialog content.
	//GetE('divInfo').style.display = '' ;

	// Activate the "OK" button.
	window.parent.SetOkButton( true ) ;
}

var bHasAnchors ;

function LoadAnchorNamesAndIds()
{
	var aAnchors	= oEditor.FCK.EditorDocument.anchors ;
	var aIds		= oEditor.FCKTools.GetAllChildrenIds( oEditor.FCK.EditorDocument.body ) ;

	bHasAnchors = ( aAnchors.length > 0 || aIds.length > 0 ) ;

	for ( var i = 0 ; i < aAnchors.length ; i++ )
	{
		var sName = aAnchors[i].name ;
		if ( sName && sName.length > 0 )
			oEditor.FCKTools.AddSelectOption( document, GetE('cmbAnchorName'), sName, sName ) ;
	}

	for ( var i = 0 ; i < aIds.length ; i++ )
	{
		oEditor.FCKTools.AddSelectOption( document, GetE('cmbAnchorId'), aIds[i], aIds[i] ) ;
	}

	ShowE( 'divSelAnchor'	, bHasAnchors ) ;
	ShowE( 'divNoAnchor'	, !bHasAnchors ) ;
}

function LoadSelection()
{
	if ( !oLink ) return ;

	var sType = 'url' ;

	// Get the actual Link href.
	var sHRef = oLink.getAttribute('href',2) + '' ;

	// TODO: Wait stable version and remove the following commented lines.
//	if ( sHRef.startsWith( FCK.BaseUrl ) )
//		sHRef = sHRef.remove( 0, FCK.BaseUrl.length ) ;

	// Look for a popup javascript link.
	var oPopupMatch = oRegex.PopupUri.exec( sHRef ) ;
	if( oPopupMatch )
	{
		GetE('cmbTarget').value = 'popup' ;
		sHRef = oPopupMatch[1] ;
		FillPopupFields( oPopupMatch[2], oPopupMatch[3] ) ;
		SetTarget( 'popup' ) ;
	}

	// Search for the protocol.
	var sProtocol = oRegex.UriProtocol.exec( sHRef ) ;

	if ( sProtocol )
	{
		sProtocol = sProtocol[0].toLowerCase() ;
		GetE('cmbLinkProtocol').value = sProtocol ;

		// Remove the protocol and get the remainig URL.
		var sUrl = sHRef.replace( oRegex.UriProtocol, '' ) ;

		if ( sProtocol == 'mailto:' )	// It is an e-mail link.
		{
			sType = 'email' ;

			var oEMailInfo = oParser.ParseEMailUrl( sUrl ) ;
			GetE('txtEMailAddress').value	= oEMailInfo.Address ;
			GetE('txtEMailSubject').value	= oEMailInfo.Subject ;
			GetE('txtEMailBody').value		= oEMailInfo.Body ;
		}
		else				// It is a normal link.
		{
			sType = 'url' ;
			GetE('txtUrl').value = sUrl ;
		}
	}
	else if ( sHRef.substr(0,1) == '#' && sHRef.length > 2 )	// It is an anchor link.
	{
		sType = 'anchor' ;
		//GetE('cmbAnchorName').value = GetE('cmbAnchorId').value = sHRef.substr(1) ;
	}
	else					// It is another type of link.
	{
		sType = 'url' ;

		GetE('cmbLinkProtocol').value = '' ;
		GetE('txtUrl').value = sHRef ;
	}

	if ( !oPopupMatch )
	{
		// Get the target.
		var sTarget = oLink.target ;

		if ( sTarget && sTarget.length > 0 )
		{
			if ( oRegex.ReserveTarget.test( sTarget ) )
			{
				sTarget = sTarget.toLowerCase() ;
				GetE('cmbTarget').value = sTarget ;
			}
			else
				GetE('cmbTarget').value = 'frame' ;
			GetE('txtTargetFrame').value = sTarget ;
		}
	}

	// Get Advances Attributes
	GetE('txtAttId').value			= oLink.id ;
	GetE('txtAttName').value		= oLink.name ;
	GetE('cmbAttLangDir').value		= oLink.dir ;
	GetE('txtAttLangCode').value	= oLink.lang ;
	GetE('txtAttAccessKey').value	= oLink.accessKey ;
	GetE('txtAttTabIndex').value	= oLink.tabIndex <= 0 ? '' : oLink.tabIndex ;
	GetE('txtAttTitle').value		= oLink.title ;
	GetE('txtAttContentType').value	= oLink.type ;
	GetE('txtAttCharSet').value		= oLink.charset ;

	if ( oEditor.FCKBrowserInfo.IsIE )
	{
		GetE('txtAttClasses').value	= oLink.getAttribute('className',2) || '' ;
		GetE('txtAttStyle').value	= oLink.style.cssText ;
	}
	else
	{
		GetE('txtAttClasses').value	= oLink.getAttribute('class',2) || '' ;
		GetE('txtAttStyle').value	= oLink.getAttribute('style',2) ;
	}

	// Update the Link type combo.
	GetE('cmbLinkType').value = sType ;
}

//#### Link type selection.
function SetLinkType( linkType )
{
	ShowE('divLinkTypeUrl'		, (linkType == 'url') ) ;
	ShowE('divLinkTypeAnchor'	, (linkType == 'anchor') ) ;
	ShowE('divLinkTypeEMail'	, (linkType == 'email') ) ;
/*
	window.parent.SetTabVisibility( 'Target'	, (linkType == 'url') ) ;
// TODO : Enable File Upload (3/3).
//	window.parent.SetTabVisibility( 'Upload'	, (linkType == 'url') ) ;
	window.parent.SetTabVisibility( 'Advanced'	, (linkType != 'anchor' || bHasAnchors) ) ;
*/
	if ( linkType == 'email' )
		window.parent.SetAutoSize( true ) ;
}

//#### Target type selection.
function SetTarget( targetType )
{
	GetE('tdTargetFrame').style.display	= ( targetType == 'popup' ? 'none' : '' ) ;
	GetE('tdPopupName').style.display	=
		GetE('tablePopupFeatures').style.display = ( targetType == 'popup' ? '' : 'none' ) ;

	switch ( targetType )
	{
		case "_blank" :
		case "_self" :
		case "_parent" :
		case "_top" :
			GetE('txtTargetFrame').value = targetType ;
			break ;
		case "" :
			GetE('txtTargetFrame').value = '' ;
			break ;
	}

	if ( targetType == 'popup' )
		window.parent.SetAutoSize( true ) ;
}

//#### Called while the user types the URL.
function OnUrlChange()
{
	var sUrl = GetE('txtUrl').value ;
	var sProtocol = oRegex.UrlOnChangeProtocol.exec( sUrl ) ;

	if ( sProtocol )
	{
		sUrl = sUrl.substr( sProtocol[0].length ) ;
		GetE('txtUrl').value = sUrl ;
		GetE('cmbLinkProtocol').value = sProtocol[0].toLowerCase() ;
	}
	else if ( oRegex.UrlOnChangeTestOther.test( sUrl ) )
	{
		GetE('cmbLinkProtocol').value = '' ;
	}
}

//#### Called while the user types the target name.
function OnTargetNameChange()
{
	var sFrame = GetE('txtTargetFrame').value ;

	if ( sFrame.length == 0 )
		GetE('cmbTarget').value = '' ;
	else if ( oRegex.ReserveTarget.test( sFrame ) )
		GetE('cmbTarget').value = sFrame.toLowerCase() ;
	else
		GetE('cmbTarget').value = 'frame' ;
}

//#### Builds the javascript URI to open a popup to the specified URI.
function BuildPopupUri( uri )
{
	var oReg = new RegExp( "'", "g" ) ;
	var sWindowName = "'" + GetE('txtPopupName').value.replace(oReg, "\\'") + "'" ;

	var sFeatures = '' ;
	var aChkFeatures = document.getElementsByName('chkFeature') ;
	for ( var i = 0 ; i < aChkFeatures.length ; i++ )
	{
		if ( i > 0 ) sFeatures += ',' ;
		sFeatures += aChkFeatures[i].value + '=' + ( aChkFeatures[i].checked ? 'yes' : 'no' ) ;
	}

	if ( GetE('txtPopupWidth').value.length > 0 )	sFeatures += ',width=' + GetE('txtPopupWidth').value ;
	if ( GetE('txtPopupHeight').value.length > 0 )	sFeatures += ',height=' + GetE('txtPopupHeight').value ;
	if ( GetE('txtPopupLeft').value.length > 0 )	sFeatures += ',left=' + GetE('txtPopupLeft').value ;
	if ( GetE('txtPopupTop').value.length > 0 )		sFeatures += ',top=' + GetE('txtPopupTop').value ;

	return ( "javascript:void(window.open('" + uri + "'," + sWindowName + ",'" + sFeatures + "'))" ) ;
}

//#### Fills all Popup related fields.
function FillPopupFields( windowName, features )
{
	if ( windowName )
		GetE('txtPopupName').value = windowName ;

	var oFeatures = new Object() ;
	var oFeaturesMatch ;
	while( ( oFeaturesMatch = oRegex.PopupFeatures.exec( features ) ) != null )
	{
		var sValue = oFeaturesMatch[2] ;
		if ( sValue == ( 'yes' || '1' ) )
			oFeatures[ oFeaturesMatch[1] ] = true ;
		else if ( ! isNaN( sValue ) && sValue != 0 )
			oFeatures[ oFeaturesMatch[1] ] = sValue ;
	}

	// Update all features check boxes.
	var aChkFeatures = document.getElementsByName('chkFeature') ;
	for ( var i = 0 ; i < aChkFeatures.length ; i++ )
	{
		if ( oFeatures[ aChkFeatures[i].value ] )
			aChkFeatures[i].checked = true ;
	}

	// Update position and size text boxes.
	if ( oFeatures['width'] )	GetE('txtPopupWidth').value		= oFeatures['width'] ;
	if ( oFeatures['height'] )	GetE('txtPopupHeight').value	= oFeatures['height'] ;
	if ( oFeatures['left'] )	GetE('txtPopupLeft').value		= oFeatures['left'] ;
	if ( oFeatures['top'] )		GetE('txtPopupTop').value		= oFeatures['top'] ;
}

function getMimeType(ext){
	var mimetype;
	switch(ext){
		case "doc":
			mimetype = "application/msword";
			break;
        case "pdf":
			mimetype = "application/pdf";
			break;
		case "txt":
			mimetype = "text/plain";
			break;
		case "xls":
			//mimetype = "application/x-excel";
			mimetype = "application/vnd.ms-excel";
			break;
		case "pps":
			mimetype = "application/vnd.ms-powerpoint";
			break;
		case "ppt":
			mimetype = "application/vnd.ms-powerpoint";
			break;
	    case "wks":
			mimetype = "application/vnd.ms-works";
			break;
		case "rtf":
			mimetype = "application/rtf";
			break;
		case "zip":
			mimetype = "application/zip";
			break;
		case "bmp":
			mimetype = "image/bmp";
			break;
		case "jpg":
			mimetype = "image/jpg";
			break;
		case "jpeg":
			mimetype = "image/jpg";
			break;
		case "jpe":
			mimetype = "image/jpg";
			break;
		case "gif":
			mimetype = "image/gif";
			break;
		case "tif":
			mimetype = "image/tiff";
			break;
		case "tiff":
			mimetype = "image/tiff";
			break;
		case "png":
			mimetype = "image/png";
			break;
		case "xml":
			mimetype = "text/xml";
			break;
		case "mpg":
			mimetype = "video/mpeg";
			break;
		case "mpeg":
			mimetype = "video/mpeg";
			break;
		case "mpe":
			mimetype = "video/mpeg";
			break;
		case "mov":
			mimetype = "video/quicktime";
			break;	
		case "html":
			mimetype = "text/html";
			break;	
		case "htm":
			mimetype = "text/html";
			break;	
		
		default:
			mimetype = null;
			
	}
	return mimetype;
}

function Ok(){
	var doc = GetE('doc');
	//Determina il titolo del documento
	var docTitle = doc.options[doc.selectedIndex].text;
	//Torva l'indice dell'ultimo carattere .
	var index = docTitle.lastIndexOf('.');
	
	var url = '/UploadDownload/downloadservlet?table=AMV_BLOB&blobfield=BLOB_FILE&dataSource='+GetE('DATASOURCE').value+'&wherecondition=where%20'+GetE('doc').value+'&mimetype='+getMimeType(docTitle.substring(index+1));
	//var url = 'AmvDownload.jsp?'+GetE('doc').value+'&mimetype='+getMimeType(docTitle.substring(index+1));
	//Ritorna il titolo del documento che va nell'attributo alt dell'immagine
	var title =  docTitle.substring(0,index); 
	//Ritorna l'estensione del file da cui dipende l'icona che serve nell'editor
	var ext = docTitle.substring(index+1);
	
	if ( url.length == 0 )
	{
		alert( FCKLang.DlnLnkMsgNoUrl ) ;
		return false ;
	}

	if ( oLink )	// Modifying an existent link.
		oLink.href = url ;
	else			// Creating a new link.
	{
		//oLink = oEditor.FCK.CreateLink( sUri ) ;
		oLink = oEditor.FCK.CreateElement('A');
		SetAttribute(oLink, 'href', url );
		if ( ! oLink ){
			return true ;
		
		}
	}
	
	SetAttribute( oLink, 'target', null ) ;

	// Advances Attributes
	oImage = oEditor.FCK.CreateElement( 'IMG' );
	
	if(ext==null)
		ext = "html";	
	if(ext=='')
		ext = "html";
		
	//SetAttribute( oImage, 'src', '../editor/filemanager/browser/default/images/icons/'+extension+'.gif');
	SetAttribute( oImage, 'src', '../common/images/AMV/'+ext+'.gif');
	SetAttribute( oImage, 'alt', docTitle);
	
	var oTesto = null;
	oTesto = GetE('txtTesto').value;
	
	if(oTesto!=null && oTesto!='')
		oLink.innerHTML = oTesto;
	else
		//oLink.innerHTML = docTitle+'.'+ext;
		oLink.innerHTML = docTitle;
	
		
		
	oLink.appendChild(oImage);
	
    //var oTesto = oEditor.FCK.CreateElement('b');
	//oTesto.innerHTML = ' Ma che bel castello';
	//oLink.appendChild(oTesto);
	return true ;
}

function BrowseServer()
{
	// Set the browser window feature.
	var iWidth	= oEditor.FCKConfig.AttachmentBrowserWindowWidth ;
	var iHeight	= oEditor.FCKConfig.AttachmentBrowserWindowHeight ;

	var iLeft = (screen.width  - iWidth) / 2 ;
	var iTop  = (screen.height - iHeight) / 2 ;

	var sOptions = "toolbar=no,status=no,resizable=yes,dependent=yes" ;
	sOptions += ",width=" + iWidth ;
	sOptions += ",height=" + iHeight ;
	sOptions += ",left=" + iLeft ;
	sOptions += ",top=" + iTop ;

	// Open the browser window.
	var oWindow = window.open( oEditor.FCKConfig.AttachmentBrowserURL, "FCKBrowseWindow", sOptions ) ;
}

function SetUrl( url )
{
	document.getElementById('txtUrl').value = url ;
	OnUrlChange() ;
}