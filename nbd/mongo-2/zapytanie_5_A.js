printjson(db.people.aggregate([
 {
    $match:{sex: "Female", nationality: "Poland"}
 },{
    $unwind: {path: "$credit"}
 },{
   $group:{
      _id: "$credit.currency",
      balanceSUM: {$sum: {$toDouble:"$credit.balance"}},
      balanceAVG: {$avg: {$toDouble:"$credit.balance"}}
    }
  }
]).toArray());