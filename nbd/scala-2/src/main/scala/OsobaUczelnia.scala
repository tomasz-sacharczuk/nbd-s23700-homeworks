case class OsobaUczelnia(imieP: String, nazwiskoP: String, podatekP: Int) {
  private val _imie = imieP
  private val _nazwisko = nazwiskoP
  private val _podatek = podatekP

  def imie = _imie

  def nazwisko = _nazwisko

  def podatek = _podatek

  def wyswietlDane() =
    println("Dane osoby: " + imie + " " + nazwisko + " " + podatek)

}
