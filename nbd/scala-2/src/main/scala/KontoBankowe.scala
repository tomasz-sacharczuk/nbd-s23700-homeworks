//2
class KontoBankowe(var poczatkowyStanKonta: Int, var wyzerujStanKonta: Boolean) {
  def this(poczatkowyStanKonta : Int) {
    this(poczatkowyStanKonta, false)
  }
  def this(wyzerujStanKonta : Boolean) {
    this(0, wyzerujStanKonta)
  }

  var stanKonta = poczatkowyStanKonta

  def wplata(kwotaWplaty: Int): KontoBankowe = {
    this.stanKonta += kwotaWplaty
    this
  }

  def wyplata(kwotaWyplaty: Int): KontoBankowe = {
    this.stanKonta -= kwotaWyplaty
    this
  }

  def wyswietlStanKonta() =
    println("Stan konta wynosi: " + stanKonta)
}
