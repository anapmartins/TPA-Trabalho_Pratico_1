import java.util.ArrayList;

public class Main{

    public static void main(String [] args) throws Exception { 

        String nome_arquivo = "./15mil.txt";
        
        MergeSortExterno merge = new MergeSortExterno();
        
        // separa o arquivo de entrada e salva ordenado
        ArrayList<Integer> lista = merge.separaArquivo(nome_arquivo); 

        while (lista.size() > 1){
            // cria lista com os nomes dos arquivos que foram sendo salvos
            ArrayList<Integer> lista_prox = new ArrayList<>(); 

            for (int i = 0; i < lista.size() - 1; i += 2)
            // faz o merge entre dois arquivo e salva o nome do arquivo resultante
                lista_prox.add(merge.merge(lista.get(i), lista.get(i + 1))); 
            
            // se a quantidade de arquivos for ímpar, adiciona o que sobra para a proxima iteração
            if (lista.size() % 2 != 0) 
                lista_prox.add(lista.get(lista.size()-1));

            lista = lista_prox;
        }
    }
}