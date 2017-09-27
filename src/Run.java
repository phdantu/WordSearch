import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Run {
	static int tamanho = 50;
	static Scanner ler = new Scanner(System.in);
	static int menu = -1;
	
	public static void main(String[] args) {
		System.out.println("|0| - Start -- |1| - Solução -- |2| - Cancela");
		while(menu != 2){
			menu = ler.nextInt();
			verifyOption(menu);
		}
	}
	private static void verifyOption(int nextInt) {
		WordSearch test = new WordSearch(openData(), tamanho);
		if(nextInt == 0){
			test.printWordSearch();
		}else{
			if(nextInt == 1){
				test.printWordSearchSolution();
			}else{
				if(nextInt == 2){
					System.out.println("END");
				}
			}
		}
	}
	private static String[] openData(){
		Scanner stdin = null;
		try {
			stdin = new Scanner(new File("src/data/words.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("Nao foi possivel abrir o arquivo!");
		}
		//Funcao eficaz para concatenar palavras.
		StringBuilder wordList = new StringBuilder();
		while(stdin.hasNext()) {
			wordList.append(stdin.next() + " ");
		}
		//deleta o ultimo espaço adicionado pelo append
		wordList.deleteCharAt(wordList.length()-1);
		//Separa a wordList Stringbuilder para um array de Strings
		String[] list = wordList.toString().split(" ");
		return list;
	}
}
