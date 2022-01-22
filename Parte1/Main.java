import java.util.ArrayList;

public class Main{

    public static void main(String [] args) throws Exception { 
        int qtd_registros = 100;
        
        MergeSortExterno merge = new MergeSortExterno();

        ArrayList<Integer> lista = merge.separaArquivo(qtd_registros); // separa o arquivo e salva ordenado
        //System.out.println(lista);

        ArrayList<Integer> sobra = new ArrayList<>();

        while (lista.size() > 1){
            ArrayList<Integer> lista_prox = new ArrayList<>();

            for (int i=0; i<lista.size()-1; i+=2)
                lista_prox.add(merge.merge(lista.get(i), lista.get(i+1)) - 1); 
            
            lista = lista_prox;

            if (lista.size()%2 != 0)
                sobra.add(lista.get(lista.size() -1));
        }

        merge.merge(lista.get(0), sobra.get(0));

        
    }
}