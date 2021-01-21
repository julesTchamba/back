package backend.model.data;

public class CarteCredit {
private int    idCarteCredit ;
private String    numeroCarte;
  private String  typeCarte ;
   private Double totalCredits;

    public int getIdCarteCredit() {
        return idCarteCredit;
    }

    public void setIdCarteCredit(int idCarteCredit) {
        this.idCarteCredit = idCarteCredit;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public Double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Double totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getMoisExpiration() {
        return moisExpiration;
    }

    public void setMoisExpiration(int moisExpiration) {
        this.moisExpiration = moisExpiration;
    }

    public int getAnneeExpiration() {
        return anneeExpiration;
    }

    public void setAnneeExpiration(int anneeExpiration) {
        this.anneeExpiration = anneeExpiration;
    }

    private int moisExpiration;
  private int  anneeExpiration;
}
