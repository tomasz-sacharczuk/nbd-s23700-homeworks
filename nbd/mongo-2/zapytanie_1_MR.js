printjson(db.people.mapReduce(
  function () {
    emit(this.sex,{"weight": parseFloat(this.weight), "height": parseFloat(this.height),"count":1 })},
  function (key, values) {
	  var sumWeight = 0;
	  var sumHeight = 0;
	  var sumCount = 0;

	values.forEach(e => {	  
      sumWeight += e.weight,
      sumHeight += e.height,
	  sumCount += e.count
	})
    return {
      "avgWeight": (sumWeight / sumCount),
      "avgHeight": (sumHeight / sumCount)
    }},{ out: {inline:1 } }
))