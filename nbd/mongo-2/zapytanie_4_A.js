printjson(db.people.aggregate([
{
	$addFields: {BMI:{$divide:
	[{$toDouble:"$weight"},{$pow:[{$divide:[{$toDouble:"$height"},100]},2]}]
	}}
},{	
	$group:{
	_id:"$nationality",
    avgBMI:{$avg:'$BMI'},
	maxBMI:{$max:'$BMI'},
	minBMI:{$min:'$BMI'}   
   }
}  
]).toArray())