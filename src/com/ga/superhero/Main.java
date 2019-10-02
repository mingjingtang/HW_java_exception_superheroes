package com.ga.superhero;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	private static ArrayList<Superhero> superheroes = new ArrayList<Superhero>();

	public static void main(String[] args) throws IOException {


		readFile("input.txt");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your superhero name: ");
		String name = scanner.nextLine();

		String result = playGame();
		writeFile("output.txt", name, result);


	}

	public static void writeFile(String filename, String name, String result) throws IOException {
		File file = new File("out.txt");
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(name + result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public static String playGame(){
		int right = 0, wrong = 0;
		Scanner scanner = new Scanner(System.in);

		for(Superhero superhero : superheroes ){
			System.out.println("What is " + superhero.getSuperName() + "'s real name?");
			String answer = scanner.nextLine();
			if(superhero.getRealName().equals(answer)){
				right++;
			}
			else{
				wrong++;
				System.out.println("Sorry, wrong answer!");
			}
		}
		if(right > wrong){
			System.out.println("You won the game!");
			return "won";
		}
		else{
			System.out.println("Sorry, you lose!");
			return "lose";
		}
	}


	public static void readFile(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String currentLine = br.readLine();

			while(currentLine!=null){
				Superhero superhero = new Superhero();
				String[] data = currentLine.split(",");
				superhero.setSuperName(data[0]);
				superhero.setRealName(data[1]);
				superheroes.add(superhero);
				currentLine = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally {
			br.close();
		}
	}
	
}
