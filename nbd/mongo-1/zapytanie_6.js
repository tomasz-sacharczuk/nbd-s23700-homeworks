printjson(db.people.insert({
		"sex": "Male",
		"first_name": "Tomasz",
		"last_name": "Sacharczuk",
		"job": "Software Engineer",
		"email": "s23700@pjwstk.edu.pl",
		"location": {
			"city": "Warsaw",
			"address": {
				"streetname": "Wiejska",
				"streetnumber": "04"
			}
		},
		"description": "Zadanie 6 MongoDB",
		"height": "120.00",
		"weight": "200.00",
		"birth_date": "1997-05-05T00:00:00Z",
		"nationality": "Poland",
		"credit": [
			{
				"type": "credit-card",
				"number": "3537725125746360",
				"currency": "PLN",
				"balance": "0.0"
			}
		]
	}))