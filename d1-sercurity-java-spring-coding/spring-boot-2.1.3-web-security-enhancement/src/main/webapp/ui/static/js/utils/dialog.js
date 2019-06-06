 
 
function popUpBase(URL, w, h) {
	popoutWindow(URL, URL,  w, h);
}

function popUpFixedName(URL, w, h) {
	popoutWindow(URL, "fixedName",  w, h);
}

 

function popoutWindow(URL, givenName, w, h) {
	w = w || 750;
	h = h || 500;
	var sc = 1;
	var regex = /[^a-zA-Z0-9]/g;
	var name = givenName.replace(regex, "");

	
	var containerWidth = 1024;
	var containerHeight = 768;

	w = w || 750;
	h = h || 550;

	if (document.all || document.layers) {
	   containerWidth = screen.availWidth;
	   containerHeight = screen.availHeight;
	}
	else {
	   containerWidth = screen.width;
	   containerHeight = screen.height;
	}

	var offsetLeft = (containerWidth - w)/2;
	var offsetTop = (containerHeight - h)/2;
	var popupChild = window.open(URL, name, "top=" + offsetTop + ",left=" + offsetLeft + "toolbar=" + 0 + ",scrollbars=" + sc + ",location=" + 0 + ",status=" + 0 + ",menubar=" + 0 + ",resizable=" + 0 + ",width=" + w + ",height=" + h);

	if (popupChild != null){
		popupChild.focus();

	}
	 
}

 

