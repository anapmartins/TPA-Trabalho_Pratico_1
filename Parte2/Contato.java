
public class Contato {

    String Nome;
    String Telefone;
    String Cidade;
    String Pais;

    public Contato(String Nome, String Telefone, String Cidade, String Pais) {
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Cidade = Cidade;
        this.Pais = Pais;
    }

    public String toString(){
        String s;
        s = "(" + this.Nome + ", " + this.Telefone + ", " + this.Cidade + ", " + this.Pais + ")";
        
        return s;
    }
}