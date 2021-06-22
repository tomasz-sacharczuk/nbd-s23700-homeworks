trait Pracownik extends OsobaUczelnia{
  private var _pensja = 0

  def pensja = _pensja

  def pensja_(newPensja: Int) = _pensja = newPensja

  override def podatek = 20

}
