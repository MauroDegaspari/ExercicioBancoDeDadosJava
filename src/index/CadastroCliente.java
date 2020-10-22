package index;


import java.util.List;
import java.util.Scanner;

import dao.RepoPesquisa;
import dao.RepositorioCadastro;
import entidade.Endereco;
import entidade.Pessoa;

public class CadastroCliente {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		RepositorioCadastro repo = new RepositorioCadastro();
		Endereco e = new Endereco();
		Pessoa p = new Pessoa();

		int opcao = 0;
		while (opcao != 6) {
			System.out.println("============== CADASTRO ==============");
			System.out.println("");
			System.out.println("           Selecione a opção: ");
			System.out.println("");
			System.out.println(" -- 1 -- CADASTRA PESSOA E ENDEREÇO");
			System.out.println(" -- 2 -- ALTERAR ");
			System.out.println(" -- 3 -- PESQUISAR ");
			System.out.println(" -- 4 -- REMOVER ");
			System.out.println(" -- 5 -- LISTAR CADASTROS");
			System.out.println(" -- 6 -- SAIR");
			System.out.println("");
			System.out.print(" Digite uma opção: ");
			opcao = sc.nextInt();
			System.out.println("");

			switch (opcao) {
			case (1):
				System.out.println("========= CADASTRO PESSOA & ENDEREÇO=========");
				System.out.println("");

				// CADASTRO PESSOA
				System.out.println("**** DADOS DE PESSOA ****");
				System.out.println("");
				System.out.print(" Nome: ");
				p.setNome(sc.next());
				System.out.print(" Idade: ");
				p.setIdade(sc.nextInt());
				System.out.print(" Sexo: ");
				p.setSexo(sc.next());
				System.out.println("CPF");
				p.setCpf(sc.next());
				p.setEndereco(e);

				// CADASTRO ENDEREÇO
				System.out.println("**** DADOS DE ENDEREÇO ****");
				System.out.println("");
				System.out.print(" RUA: ");
				e.setRua(sc.next());
				System.out.print(" n°: ");
				e.setNumero(sc.nextInt());
				System.out.print(" COMPLEMENTO: ");
				e.setComplemento(sc.next());
                
				
				repo.inserirEndereco(e);
				repo.inserirPessoa(p);
				

				break;
			case (2):

				break;
			case (3):
				RepoPesquisa repopesq = new RepoPesquisa();
			
			System.out.println("========= LISTAR PESSOA & ENDEREÇO =========");
			System.out.println("");
						
			List<Pessoa> pess = repopesq.pesquisarPessoa("");
			
			for (Pessoa pessoa: pess) {
				System.out.println(pessoa.getCpf() + " - " + pessoa.getEndereco());
			}
				

				break;

			case (4):

				break;
			case (5):

				break;
			case (6):
				System.out.println(" ----> SAIR <----");
				break;

			default:
				System.out.println("!!Opção invalida!!! TRY AGAIN");
				System.out.println("");
				break;
			}

		} // WHILE
		sc.close();
	}// MAIN

}
