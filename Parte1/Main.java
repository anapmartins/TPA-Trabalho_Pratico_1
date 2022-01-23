import java.util.ArrayList;

public class Main{

    public static void main(String [] args) throws Exception { 

        String nome_arquivo = "./15mil.txt";
        
        MergeSortExterno merge = new MergeSortExterno();

        ArrayList<Integer> lista = merge.separaArquivo(nome_arquivo); // separa o arquivo e salva ordenado

        while (lista.size() > 1){
            ArrayList<Integer> lista_prox = new ArrayList<>(); // cria lista com os nomes dos arquivos que foram sendo salvos

            for (int i = 0; i < lista.size() - 1; i += 2)
                lista_prox.add(merge.merge(lista.get(i), lista.get(i + 1))); // faz o merge entre dois arquivo e salva o nome do arquivo resultante
            
            if (lista.size() % 2 != 0) // se a quantidade de arquivos for ímpar, adiciona o que sobra para a proxima iteração
                lista_prox.add(lista.get(lista.size()-1));

            lista = lista_prox;
        }
    }
}