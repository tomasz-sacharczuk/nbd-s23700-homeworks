printjson(db.people.mapReduce(
  function() {
	if(this.nationality=="Poland" && this.sex=="Female")
	{
		this.credit.forEach(e => {emit(e.currency,{"balance":parseFloat(e.balance),"count":1})})
	}
},
	function(key, values) {
	var sumCount = 0;
	var sumBalance = 0;
	
	values.forEach(e => {	
	sumCount += e.count;
	sumBalance += e.balance;
	})
	
    var balanceSum = sumBalance;
    var avgBalance = balanceSum / sumCount;

    return {"sumBalance":balanceSum, "averageBalance":avgBalance}
  },{out: {inline:1}}
))