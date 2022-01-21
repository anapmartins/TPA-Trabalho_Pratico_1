import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class MergeSortExterno{

    public static void separaArquivo() throws Exception{
        int tam = 100;
        
        BufferedReader br1 = new BufferedReader(new FileReader("./teste.txt"));

        String linha;
        linha = br1.readLine();
        int j = 0;
        
        while (linha != null){
            // cria lista para ordenar o arquivo
            ArrayList<Contato> contatos = new ArrayList<>();

            for (int i=0; i<tam/10; i++){
            
                String[] dados = linha.split(",");
                Contato contato = new Contato(dados[0], dados[1], dados[2], dados[3]);
                contatos.add(contato);
                linha = br1.readLine();
            }
            // ordena a lista
            contatos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));

            OutputStream os = new FileOutputStream(Integer.toString(j) + ".txt"); 
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
            j++;
            br2.close();
        }
        br1.close();       
    }

    public static void merge() throws Exception{
        // le os dois arquivos
        BufferedReader br1 = new BufferedReader(new FileReader("0.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("1.txt"));

        OutputStream os = new FileOutputStream("merging.txt"); 
        Writer wr = new OutputStreamWriter(os); 
        BufferedWriter br3 = new BufferedWriter(wr); 

        String linha1, linha2;
        linha1 = br1.readLine();
        linha2 = br2.readLine();

        String[] contato1 = linha1.split(",");
        String[] contato2 = linha2.split(",");
      
        while (linha1 != null && linha2 != null) {
         
            if (contato1[0].compareTo(contato2[0]) < 0){ 
                br3.write(linha1); 
                br3.newLine();
                linha1 = br1.readLine(); 
                if (linha1 != null)
                    contato1 = linha1.split(",");
                
            }
            else{
                if (contato1[0].compareTo(contato2[0]) > 0){
                    br3.write(linha2); 
                    br3.newLine();
                    linha2 = br2.readLine(); 
                    if (linha2 != null)
                        contato2 = linha2.split(",");
                }
            }
        }

        while (linha1 != null){
            br3.write(linha1);
            br3.newLine();
            linha1 = br1.readLine();
            if (linha2 != null)
                contato1 = linha1.split(",");
        }

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
    }


    public static void main(String [] args) throws Exception { 

        separaArquivo(); // separa o arquivo e salva ordenado
        merge();


    }


}
