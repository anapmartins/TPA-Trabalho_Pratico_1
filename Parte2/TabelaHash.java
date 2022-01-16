
import java.util.ArrayList;

public class TabelaHash{
    
    public ArrayList<ArrayList<Contato>> lista; 
    int tamanho;
    
    public TabelaHash(){

        tamanho = 101; 

        lista = new ArrayList<ArrayList<Contato>>();
        // cria lista para cada indice da tabela
        for (int i=0; i<tamanho; i++){
            lista.add(new ArrayList<Contato>());
        }
    }

    // método que calcula a chave 
    // transforma o nome completo em um valor inteiro
    public int hashVal(String chave) {
        int hash = 0;
        for(char c : chave.toCharArray())
            hash = Math.abs((hash + 37*(int)c) % this.tamanho);
        return hash;
    }

    // método que insere um contato novo
    public void inserir(String chave, Contato valor){
       lista.get(hashVal(chave)).add(valor);
    }

    // método que consulta um contato
    public Contato consultar(String chave){
        ArrayList<Contato> listaContato = lista.get(hashVal(chave));
        
        for (int i=0; i<listaContato.size(); i++)
            if (listaContato.get(i).Nome.equals(chave)){
                return listaContato.get(i); 
        }
        return null;
    }

    // método que atualiza dados de um contato
    public void atualizar(String chave, Contato valor){   
        ArrayList<Contato> listaContato = lista.get(hashVal(chave));
        
        for (int i=0; i<listaContato.size(); i++)
            if (listaContato.get(i).Nome.equals(chave))
                listaContato.set(i, valor);  
    }

    // método que remove um contato
    public void remover(String chave){   
        ArrayList<Contato> listaContato = lista.get(hashVal(chave));
        
        for (int i=0; i<listaContato.size(); i++)
            if (listaContato.get(i).Nome.equals(chave))
                listaContato.remove(i);
    }
}