/**
 * Upload Dialog , 
 * Author Jian Zhang  
 *  
 * @param event
 * @param actionType
 */
function okPromptDialog(event,actionType) {
	 var selectedFilter = document.getElementById("enteredLine").value;	
	 
	 if (isBlank(selectedFilter)) {
		 return;
	 } 
	 $("#promptDialog").fadeOut(300);
	 event.preventDefault();
	 document.forms[0].actionType.value=actionType;
	 document.forms[0].actionParam.value = selectedFilter; 	
	 document.forms[0].submit();
	
}



function cancelPromptDialog(event) {
	 $("#promptDialog").fadeOut(300);
	 event.preventDefault();
	 
}


function promptInputDialog(event,promptLine1,promptLine2,actionType) {
	
	 
		$("#promptDialog").fadeIn(300); 
		$("#btnPrmOk").bind("click", function (e){ okPromptDialog(e,actionType);});
       $("#btnPrmCancel").bind("click", function (e){ cancelPromptDialog(e); });
       $("#btnPrmClose").bind("click", function (e){ cancelPromptDialog(e); });
       document.getElementById("promptLine1").innerHTML = promptLine1;
       document.getElementById("promptline2").innerHTML = promptLine2;
       
	
	 event.preventDefault();
}
function popUp(URL) {
	popUpBase(URL, '750', '360');
}

function popUpWide(URL){
	popUpBase(URL, '750', '360');
}

function popUpBig(URL){
    popUpBase(URL, '750', '550');
}

function popUpLoginAs(URL, w, h) {
	popUpFull(URL, 'loginAsUser', '0', '1', '0', '1', '1', '1', w, h);
}

function popUpBaseMany(URL, w, h) {
    var day = new Date();
	var id = day.getTime();

    popUpFull(URL, id, '0', '1', '0', '0', '0', '1', w, h);
}

function popUpSingle(URL, id, w, h) {
	popUpFull(URL, id, '0', '1', '0', '0', '0', '1', w, h);
}

function popUpBase(URL, w, h) {
	popUpFull(URL, URL, '0', '1', '0', '0', '0', '1', w, h);
}

function popUpFixedName(URL, w, h) {
	popUpFull(URL, "fixedName", '0', '1', '0', '0', '0', '1', w, h);
}

function popUpFullScreen(URL, w, h) {
	popUpFull(URL, "fullScreen", '0', '1', '0', '0', '0', '1', w, h);
}


function popUpHelp(URL) {
	popUpFull(URL, 'help_' + URL, '0', '1', '0', '1', '0', '1');
}

function popUpTest(URL, id) {
	popUpFull(URL, id, '0', '1', '0', '0', '0', '1');
}

function popUpUpdate(msg) {
  	popUpPB('/update_notice.html', 'update_notice', msg, '0', '1', '0', '1', '0', '0');
}

function popUpPB(URL, name, msg, t, sc, l, st, m, r, w, h) {
	return popUpFull(URL, name, t, sc, l, st, m, r, w, h, msg);
}

function popUpFull(URL, givenName, t, sc, l, st, m, r, w, h, msg) {
	w = w || 750;
	h = h || 500;
	
	var regex = /[^a-zA-Z0-9]/g;
	var name = givenName.replace(regex, "");
	var offsetObj = getPopupCenter(h, w);
	var popupChild = window.open(URL, name, "top=" + offsetObj.top + ",left=" + offsetObj.left + "toolbar=" + t + ",scrollbars=" + sc + ",location=" + l + ",status=" + st + ",menubar=" + m + ",resizable=" + r + ",width=" + w + ",height=" + h);

	if (popupChild != null){
		popupChild.focus();

		if (typeof SWL != 'undefined' && SWL != null && SWL.trackChild){ SWL.trackChild(popupChild); }
	}
	else if (msg != null){
		SWL.alert(msg);
	}
}

function getPopupCenter (popupHeight, popupWidth){
	var containerWidth = 1024;
	var containerHeight = 768;

	popupWidth = popupWidth || 750;
	popupHeight = popupHeight || 550;

	if (document.all || document.layers) {
	   containerWidth = screen.availWidth;
	   containerHeight = screen.availHeight;
	}
	else {
	   containerWidth = screen.width;
	   containerHeight = screen.height;
	}

	var offsetLeft = (containerWidth - popupWidth)/2;
	var offsetTop = (containerHeight - popupHeight)/2;

	return {left: offsetLeft, top: offsetTop};
}

