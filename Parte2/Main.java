
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main{
    public static void main(String [] args) throws Exception { 

        Scanner sc = new Scanner(System.in); 
        int opcao = 6;
        String s;
        System.out.println("## Escolha uma das opções abaixo ##");
        System.out.println("Opção 1 - Carregar Arquivo");
        System.out.println("Opção 2 - Consultar Contato");
        System.out.println("Opção 3 - Inserir Contato Novo");
        System.out.println("Opção 4 - Excluir Contato");
        System.out.println("Opção 5 - Atualizar Contato");
        System.out.println("Opção 6 - Salvar Dados");
        System.out.println("Opção 7 - Sair do Programa");
        System.out.println("_______________________");

        opcao = Integer.parseInt(sc.nextLine());

        TabelaHash tabela = new TabelaHash();

        while (opcao != 7){
            if (opcao == 1){ //Carrega o arquivo
                BufferedReader br = new BufferedReader(new FileReader("./teste.txt"));
        
                String linha;
                linha = br.readLine();
        
                while (linha != null){
        
                    String chave;
                    String[] dados = linha.split(",");
                    chave = dados[0];
                    Contato contato = new Contato(dados[0], dados[1], dados[2], dados[3]);

                    tabela.inserir(chave, contato);
                
                    linha = br.readLine();
                }

                br.close();
                System.out.println("Arquivo carregado com sucesso.");
                }

            else{
                if (opcao == 2){ //Consulta um contato
                System.out.println("Informe um nome completo");
                s = sc.nextLine();
                System.out.println(tabela.consultar(s));
                }

                else{
                    if (opcao == 3){ // Insere um contato novo
                        System.out.println("Informe nome completo, telefone, cidade e pais");
                        s = sc.nextLine();
                        String chave;
                        String[] dados = s.split(",");
                        chave = dados[0];
                        Contato contato = new Contato(dados[0], dados[1], dados[2], dados[3]);
                        tabela.inserir(chave, contato);
                        System.out.println("Novo contato inserido com sucesso.");
                    }
                    else{
                        if (opcao == 4){ //Exclui um contato
                            System.out.println("Informe nome completo");
                            s = sc.nextLine();
                            tabela.remover(s);
                            System.out.println("Contato excluído com sucesso");
                        }
                        else{
                            if (opcao == 5){ //Atualiza contato
                                System.out.println("Informe nome completo, telefone, cidade e pais");
                                s = sc.nextLine();
                                String[] dados = s.split(",");
                                Contato contato = new Contato(dados[0],dados[1], dados[2], dados[3]);
                                tabela.atualizar(dados[0], contato);
                                System.out.println("Contato atualizado com sucesso");
                            }
                            else{
                                if (opcao == 6){ //Salva os dados em um arquivo
                                    OutputStream os = new FileOutputStream("Contatos.txt"); // nome do arquivo que será escrito
                                    Writer wr = new OutputStreamWriter(os); // criação de um escritor
                                    BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer
                                    
                                    for (int i=0; i<tabela.tamanho; i++){
                                        if (!tabela.lista.get(i).isEmpty()){
                                            ArrayList<Contato> listaContato = tabela.lista.get(i);
                                           for (int j=0; j<tabela.lista.get(i).size(); j++){
                                               br.write(listaContato.get(j).Nome + ", ");
                                               br.write(listaContato.get(j).Telefone + ", ");
                                               br.write(listaContato.get(j).Cidade + ", ");
                                               br.write(listaContato.get(j).Pais + ", ");
                                               br.newLine();
                                           }
                                        }
                                    }
                                    br.close();

                                    System.out.println("Dados salvos com sucesso"); 
                                }
                                else{
                                    if (opcao == 7)
                                        break;
                                    else
                                        System.out.println("Informe um valor válido");
                                }
                            }
                        }
                    }
                }
            }
            opcao = Integer.parseInt(sc.nextLine()); 
        }   
    }
}