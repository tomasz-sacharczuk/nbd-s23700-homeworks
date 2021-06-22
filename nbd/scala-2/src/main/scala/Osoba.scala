case class Osoba(imie : String, nazwisko : String) {

  def przywitanie(osoba: Osoba) : String = osoba match {
    case Osoba("Adam","Adamowicz")=> "Hello Adam!"
    case Osoba("Bartosz","Bartoszewski")=> "Hello Bartosz!"
    case Osoba("Cezary","Cezarski")=> "Hello Cezary!"
    case _ => "Hello world!"
  }
}
