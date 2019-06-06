
/**
 * Below Section for Agent Login
 */
var loginIsEmptyfields = {
	'Email Address' : '#emailAddress',
	'Password' : '#password'
};
function uploadAttachment(attachment) {
	errorFlag = false;
	document.getElementById("cProgressSamll").style.display = "block";
	if (!errorFlag) {
		document.getElementById("actionType").value = "upload-" + attachment;
		document.forms['emailServerForm'].action = 'uploadAttachment.html';
		document.forms['emailServerForm'].submit();
	}
}

function deleteAttachment(attachment) {
	errorFlag = false;
	document.getElementById("cProgressSamll").style.display = "block";
	if (!errorFlag) {
		document.getElementById("actionType").value = "delete-" + attachment;
		document.forms['emailServerForm'].action = 'uploadAttachment.html';
		document.forms['emailServerForm'].submit();
	}
}

function showAttachment() {
	document.getElementById("actionType").value = "show-Attachments";
	document.forms['emailServerForm'].action = 'uploadAttachment.html';
	document.forms['emailServerForm'].submit();
	reloadParentCloseSelf();
}
 