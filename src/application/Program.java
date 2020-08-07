package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		//C:\ws-eclipse\in.txt
		
		System.out.print("Enter full file path: ");
		String caminho = sc.next();
		System.out.print("Enter salary: ");
		double valor = sc.nextDouble();
		System.out.println("Email of people whose salary is more than " + String.format("%.2f", valor) + ":");
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			List<Employee> list = new ArrayList<>();
			String linha = br.readLine();
			
			while(linha != null) {
				String[] vet = linha.split(",");
				list.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
				linha = br.readLine();
			}
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > valor)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
					
			emails.forEach(System.out::println);
			
			System.out.print("Digite uma letra para somar os salários: ");
			char letra = sc.next().charAt(0);
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == letra)
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			System.out.println("Sum of salary of people whose name starts with '" + letra + "': " + String.format("%.2f", sum));
	
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}
}