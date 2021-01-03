function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.KeyCode;
	
	if (charCode >= 48 && charCode <= 57 || charCode <= 31) {
		return true;
	}
	return false;
	
}

function searchRest(categoryId) {
	
	var t = document.getElementById("searchType");
	
	if (categoryId == null) {
		t.value = "Text";
	}
	
	 else {
		t.value = "Category";
		document.getElementById("categoryId").value = categoryId;
	}
	document.getElementById("form").submit();
}

function setCmd(cmd) {
    document.getElementById("cmd").value = cmd;
    document.getElementById("form").submit();
}

function filterMenu(category) {
    document.getElementById("category").value = category;
    document.getElementById("filterForm").submit();
}


