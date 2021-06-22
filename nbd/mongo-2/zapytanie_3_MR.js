printjson(db.people.mapReduce(
  function () {
    emit(this.job, 1);
  },
  function (key, values) {
    return 1;
  },
  {
    out: {inline:1 }
  }
));