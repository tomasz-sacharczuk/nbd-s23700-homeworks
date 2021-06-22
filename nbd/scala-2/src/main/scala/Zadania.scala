
object Zadania {
  def main(args: Array[String]): Unit = {
    //1
    def detectDay (day : String) = day match {
      case "poniedziałek" => "praca"
      case "wtorek" => "praca"
      case "środa" => "praca"
      case "czwartek" => "praca"
      case "piątek" => "praca"
      case "sobota" => "weekend"
      case "niedziela" => "weekend"
      case _ => "nie ma takiego dnia!"
    }
    //1
    println(detectDay("poniedziałek"))
    println(detectDay("sobota"))
    println(detectDay("poniedzielnik"))
    //2
    var kontoBankowe = new KontoBankowe(5000)
    kontoBankowe.wplata(10000)
    kontoBankowe.wyswietlStanKonta()
    kontoBankowe.wyplata(10000)
    kontoBankowe.wyswietlStanKonta()
    var kontoBankowe2 = new KontoBankowe(true)
    kontoBankowe2.wyswietlStanKonta()
    //3
    var osoba1 = new Osoba("Adam", "Adamowicz")
    var osoba2 = new Osoba("Bartosz", "Bartoszewski")
    var osoba3 = new Osoba("Cezary", "Cezarski")
    var osoba4 = new Osoba("Biggus", "Dickus")
    var osoba5 = new Osoba("Witacz","Witaczewski")
    println(osoba5.przywitanie(osoba1))
    println(osoba5.przywitanie(osoba2))
    println(osoba5.przywitanie(osoba3))
    println(osoba5.przywitanie(osoba4))
    //4
    def triple (value : Int, callback : (Int) => Int): Int ={
      callback(value)
    }
    def multiply(number : Int) = number * 3
    println(triple(12,multiply))
    //5
    new OsobaUczelnia("Adam","Adamowicz",50).wyswietlDane()
    (new OsobaUczelnia("Bartosz","Bartoszewski", 5) with Pracownik).wyswietlDane()
    (new OsobaUczelnia("Cezary","Cezarski", 5) with Student).wyswietlDane()
    (new OsobaUczelnia("Biggus","Dickus", 5) with Nauczyciel).wyswietlDane()
    (new OsobaUczelnia("Dickus","Biggus", 5) with Student with Pracownik).wyswietlDane()
    (new OsobaUczelnia("Diggus","Bickus", 5) with Pracownik with Student).wyswietlDane()
  }
}
