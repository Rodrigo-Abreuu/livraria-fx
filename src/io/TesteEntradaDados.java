package io;

import java.util.Scanner;

public class TesteEntradaDados {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite seu nome: ");
		String nomeDigitado = sc.nextLine();
		System.out.println("Digite sua idade: ");
		int idadeDigitada = sc.nextInt();
		System.out.println("Nome: " + nomeDigitado);
		System.out.println("Idade: " + idadeDigitada);
		sc.close();
	}
}
