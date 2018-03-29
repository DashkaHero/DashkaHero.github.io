// check if element value is empty
function isEmpty(obj) {
	if(obj.val() == "") {
		obj.addClass("ui-state-error");
		obj.focus();
		return true;
	} else {
		obj.removeClass("ui-state-error");
		return false;
	}
}

function checkValue(obj, value) {
	if(obj.val() == value) {
		return true;
	} else {
		return false;
	}
}


// check min/max length of an element
function checkLength(obj, min, max) {
	if(obj.val().length > max || obj.val().length < min) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for special chars in the element value; first char must be alphabet
function checkSpecialChars(obj) {
	var regexp = /^[a-z]([0-9a-z_])+$/i;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check if element value contains only digits
function isDigits(obj) {
	var regexp = /^(\d)+$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for valid email
function checkEmail(obj) {
	var regexp = /^(\w)+@([a-z0-9])+\.([a-z0-9]){1,3}$/i;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for valid zipcode xxxxx
function checkZipCode(obj) {
	var regexp = /^(\d){5}$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

function showError(obj, message) {
	obj.find("span:last-child").html(message);	
	obj.show();
}

// check for valid phone xxx-xxx-xxxx
function checkPhone(obj) {
	var regexp = /^(\d){3}-(\d){3}-(\d){4}$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

function checkCardNumber(obj) {
	var regexp = /^(\d){4}-(\d){4}-(\d){4}-(\d){4}$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}	
}


