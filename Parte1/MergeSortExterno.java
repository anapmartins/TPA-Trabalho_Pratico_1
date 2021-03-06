import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.*;

public class MergeSortExterno{

    private int contador_nome_arquivo = 0;

    public ArrayList<Integer> separaArquivo(String nome) throws Exception{
        
        // lista que salva o nome dos arquivos
        ArrayList<Integer> lista_arq = new ArrayList<>(); 
        BufferedReader br1 = new BufferedReader(new FileReader(nome));

        String linha;
        linha = br1.readLine();

        ArrayList<Contato> contatos = new ArrayList<>();
        while (linha != null){
                
            String[] dados = linha.split(",");
            Contato contato = new Contato(dados[0], dados[1], dados[2], dados[3]);
            contatos.add(contato);

            linha = br1.readLine();
            // verifica se a lista de contatos tem mais que 10 milhões de elementos ou se chegou ao fim do arquivo
            if (contatos.size() >= 10000000 || linha == null){
                // ordena a lista
                contatos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));

                OutputStream os = new FileOutputStream(Integer.toString(contador_nome_arquivo) + ".txt"); 
                Writer wr = new OutputStreamWriter(os); 
                BufferedWriter br2 = new BufferedWriter(wr); 

                // salva a lista ordenada em um arquivo
                 for (int k=0; k<contatos.size(); k++){
                    br2.write(contatos.get(k).Nome + ", ");
                    br2.write(contatos.get(k).Telefone + ", ");
                    br2.write(contatos.get(k).Cidade + ", ");
                    br2.write(contatos.get(k).Pais + ", ");
                    br2.newLine();
                }

                lista_arq.add(contador_nome_arquivo);     
                contador_nome_arquivo++;
                br2.close();

                contatos.clear();
            }

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
                // avanca para a proxima entrada
                linha1 = br1.readLine(); 
                if (linha1 != null)
                // atualiza o contato
                    contato1 = linha1.split(","); 
                
            }
            // nome da entrada 2 vem antes do nome da entrada 1
            // ou os nomes são iguais
            else{ 
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
        }

        // se o arquivo 1 terminar primeiro, preenche o arquivo de saida com o restante do arquivo 2
        while (linha2 != null){
            br3.write(linha2);
            br3.newLine();
            linha2 = br2.readLine();
        }

        br1.close();
        br2.close();
        br3.close();

        return contador_nome_arquivo-1;
    }
    
}
