function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.KeyCode;
	
	if (charCode >= 48 && charCode <= 57 || charCode <= 31) {
		return true;
	}
	return false;
}