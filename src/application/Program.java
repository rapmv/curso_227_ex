package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o caminho do arquivo: ");
		String arquivo = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){

			List<Funcionario> funcionario = new ArrayList<>();
			
			String line = br.readLine();
			while(line!=null) {
				String[] fields = line.split(",");
				funcionario.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Digite o valor minimo para busca: ");
			double busca = sc.nextDouble();
			
			
			List <String> emails = funcionario.stream()
					.filter(p->p.getSalario()>busca)
					.map(p->p.getEmail())
					.sorted((s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()))
					.collect(Collectors.toList());
			
			System.out.println("Email dos salarios que são maiores que "+String.format("%.2f",busca)+": ");
			emails.forEach(System.out::println);
			
			
			double soma = funcionario.stream()
					.filter(p->p.getNome().charAt(0)=='M')
					.map(p-> p.getSalario())
					.reduce(0.0, (x, y) -> x+y);
			
			System.out.println("Soma total dos salarios dos funcionarios que começam com a letra M no nome: "+String.format("%.2f",soma));
			
		}
		catch(IOException e) {
			System.out.println("Erro: " +e.getMessage());
		}
		sc.close();
	}

}
