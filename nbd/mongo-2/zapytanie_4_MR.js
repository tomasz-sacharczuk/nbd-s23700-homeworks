printjson(db.people.mapReduce(
  function () {
    emit(this.nationality, {"BMI": parseFloat(this.weight)/((parseFloat(this.height)/100)**2)})
  },
  function (key, values) {
    var sumBMI = 0;
	var arrayBMI = new Array()
   values.forEach(e => {
		sumBMI += e.BMI;
		arrayBMI.push(e.BMI)
    })
	var avgBMI = sumBMI / values.length;
	var minBMI = Math.min(...arrayBMI);
	var maxBMI = Math.max(...arrayBMI);
  return { "averageBmi": avgBMI, "maxBMI":maxBMI, "minBMI":minBMI };
  },{ out: {inline:1} }
))