import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class MergeSortExterno{

    private int contador_nome_arquivo = 0;

    public ArrayList<Integer> separaArquivo(int tamanho) throws Exception{
        
        ArrayList<Integer> lista_arq = new ArrayList<>(); // lista que contem os arquivos ordenados
        BufferedReader br1 = new BufferedReader(new FileReader("./teste.txt"));

        String linha;
        linha = br1.readLine();
        
        while (linha != null){
            // cria lista para ordenar o arquivo
            ArrayList<Contato> contatos = new ArrayList<>();

            for (int i=0; i<tamanho/10; i++){
            
                String[] dados = linha.split(",");
                Contato contato = new Contato(dados[0], dados[1], dados[2], dados[3]);
                contatos.add(contato);
                linha = br1.readLine();
            }
            // ordena a lista
            contatos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));

            OutputStream os = new FileOutputStream(Integer.toString(contador_nome_arquivo) + ".txt"); 
            Writer wr = new OutputStreamWriter(os); 
            BufferedWriter br2 = new BufferedWriter(wr); 
            // salva a lista ordenada em um arquivo
            for (int i=0; i<contatos.size(); i++){
                br2.write(contatos.get(i).Nome + ", ");
                br2.write(contatos.get(i).Telefone + ", ");
                br2.write(contatos.get(i).Cidade + ", ");
                br2.write(contatos.get(i).Pais + ", ");
                br2.newLine();
            }
            lista_arq.add(contador_nome_arquivo);     
            contador_nome_arquivo++;
            br2.close();
        }
        br1.close();  

        return lista_arq;
    }

    public int merge(Integer arq1, Integer arq2) throws Exception{
        // le os dois arquivos
        BufferedReader br1 = new BufferedReader(new FileReader(Integer.toString(arq1) + ".txt"));
        BufferedReader br2 = new BufferedReader(new FileReader(Integer.toString(arq2) + ".txt"));
        
        OutputStream os = new FileOutputStream(Integer.toString(contador_nome_arquivo) + ".txt"); 
        Writer wr = new OutputStreamWriter(os); 
        BufferedWriter br3 = new BufferedWriter(wr); 

        contador_nome_arquivo++;

        String linha1, linha2;
        linha1 = br1.readLine();
        linha2 = br2.readLine();

        String[] contato1 = linha1.split(",");
        String[] contato2 = linha2.split(",");

      
        while (linha1 != null && linha2 != null) {
            // verifica se o nome da entrada 1 vem antes do nome da entrada 2
            if (contato1[0].compareTo(contato2[0]) < 0){ 
                br3.write(linha1); 
                br3.newLine();
                linha1 = br1.readLine(); // avanca para a proxima entrada
                if (linha1 != null)
                    contato1 = linha1.split(","); // atualiza o contato
                
            }
            else{ // o nome da entrada 2 vem antes do nome da entrada 1
                br3.write(linha2); 
                br3.newLine();
                linha2 = br2.readLine(); 
                if (linha2 != null)
                    contato2 = linha2.split(",");
            }
        }

         // se o arquivo 2 terminar primeiro, preenche o arquivo de saida com o restante do arquivo 1
        while (linha1 != null){
            br3.write(linha1);
            br3.newLine();
            linha1 = br1.readLine();
            if (linha2 != null)
                contato1 = linha1.split(",");
        }

        // se o arquivo 1 terminar primeiro, preenche o arquivo de saida com o restante do arquivo 2
        while (linha2 != null){
            br3.write(linha2);
            br3.newLine();
            linha2 = br2.readLine();
            if (linha2 != null)
                contato2 = linha2.split(",");
        }

        br1.close();
        br2.close();
        br3.close();

        return contador_nome_arquivo;
    }
    
}
