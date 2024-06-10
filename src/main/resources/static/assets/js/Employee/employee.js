function switchPage(value) {
	if (value == 'Add') {
		window.location.href = "/employee/addEmployee";
	} else if (value == 'View' || value == 'Back') {
		window.location.href = "/employee/displayAll";
	}

}