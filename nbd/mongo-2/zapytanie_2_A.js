printjson(db.people.aggregate([
	{
	  $unwind:
		{path: "$credit"}
	},{
    $group: {
      _id: "$credit.currency",
      balanceSum: {$sum:{$toDouble:"$credit.balance"}}
    }
  }
]).toArray());