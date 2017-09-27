import java.util.Random;

public class WordSearch {
	char[][] data;
	char[][] dataSolution;
	int size = 0;
	int cruze=0;
	public WordSearch(String[] list, int size) {
		this.size = size;
		data = new char[size][size]; 
		dataSolution = new char[size][size]; 
		for(int line=0;line<size;line++){
			for(int col=0;col<size;col++){
				data[line][col] = '-';
			}
		}
		for(String word : list){
			insertWord(word);
		}
		for(int line=0;line<size;line++){
			for(int col=0;col<size;col++){
				dataSolution[line][col] = data[line][col];
			}
		}
		//COMPLETA O PUZZLE COM LETRAS ALEATORIAS
				fillWordSearch();
	}
	private void insertWord(String word){
		String localWord = word;
		int direction = sortDirection();
		int orientation = sortOrientation();
		if(orientation == 1){
			//tras pra frente
			localWord = flip(localWord);
		}
		if(direction == 0){
			//horizontal
			int col = sortLocationWord();
			int row = sortLocationWord();
			if(testIfSpaceIsFree(localWord,row,col,orientation)){
				markWordInitHorizontal(col, row);
				for(int i=0;i<localWord.length();i++){
					data[row][col] = word.charAt(i);
					row++;
				}
			}
		}
		if(direction == 1){
			//vertical
			int col = sortLocationWord();
			int row = sortLocationWord();
			if(testIfSpaceIsFree(localWord,row,col,orientation)){
				markWordInitVertical(col, row);
				for(int i=0;i<localWord.length();i++){
					data[row][col] = word.charAt(i);
					col++;
				}
			}
		}
		if(direction == 2){
			//diagonal
			int col = sortLocationWord();
			int row = sortLocationWord();
			if(testIfSpaceIsFree(localWord,row,col,orientation)){
				markWordInitDiagonal(col, row);
				for(int i=0;i<localWord.length();i++){
					data[row][col] = word.charAt(i);
					row++;
					col++;
				}
			}	
		}
		
	}
	private void markWordInitHorizontal(int col, int row) {
		if(col<size-1 && data[row][col+1] == '-'){
			data[row][col+1] = '|';
		}
		if(col>0 && data[row][col-1] == '-'){
			data[row][col-1] = '|';
		}
		if(row>0 && data[row-1][col] == '-'){
			data[row-1][col] = '|';
		}
	}
	private void markWordInitVertical(int col, int row) {
		if(col>0 && data[row][col-1] == '-'){
			data[row][col-1] = '|';
		}
		if(row<size-1 && data[row+1][col] =='-'){
			data[row+1][col] = '|';
		}
		if(row>0 && data[row-1][col] == '-'){
			data[row-1][col] = '|';
		}
	}
	private void markWordInitDiagonal(int col, int row) {
		if(col>0 && data[row][col-1] =='-'){
			data[row][col-1] = '|';
		}if(col<size-1 && data[row][col+1] =='-'){
			data[row][col+1] = '|';
		}
		if(row<size-1 && data[row+1][col] == '-'){
			data[row+1][col] = '|';
		}
		if(row>0 && data[row-1][col] == '-'){
			data[row-1][col] = '|';
		}
	}
	private boolean testIfSpaceIsFree(String word, int row, int col, int orientation){
		boolean ret = false;
		for(int i=0;i<word.length();i++){
			if(orientation ==0){
				//horizontal
				if(data[row][col]=='-'){
					row++;
					ret = true;
				}else{
					ret = false;
					break;
				}
			}
			if(orientation ==1){
				//vertical
				if(data[row][col]=='-'){
					col++;
					ret = true;
				}else{
					ret = false;
					break;
				}
			}
			if(orientation ==2){
				//diagonal
				if(data[row][col]=='-'){
					row++;
					col++;
					ret = true;
				}else{
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
	private void fillWordSearch(){
		RandomChar rnd = new RandomChar();
		for(int line=0;line<size;line++){
			for(int col=0;col<size;col++){
				if(data[line][col] == '-' || data[line][col] == '|'){
					data[line][col] = rnd.nextChar();
				}
			}
		}
	}
	private int sortLocationWord(){
		Random rnd = new Random();
		return rnd.nextInt(size-11);
	}
	public void printWordSearch(){
		for(int line=0;line<data.length;line++){
			for(int col=0;col<data.length;col++){
				System.out.print(data[line][col]);
			}
			System.out.println();
		}
	}
	public void printWordSearchSolution(){
		for(int line=0;line<dataSolution.length;line++){
			for(int col=0;col<dataSolution.length;col++){
				System.out.print(dataSolution[line][col]);
			}
			System.out.println();
		}
	}
	private int sortOrientation(){
		// true(0) = frente,   false(1) = au contraire
		Random rnd = new Random();
		return rnd.nextInt(2);
	}
	private int sortDirection(){
		// 0 = Horizontal, 1 = Vertical,  2 = Diagonal
		Random rnd = new Random();
		return rnd.nextInt(3);
	}
	
	private String flip(String word) {
		StringBuilder ret = new StringBuilder();
		for(int i=word.length()-1; i>=0; i--)
			ret.append(word.charAt(i));
		return ret.toString();
	}

}
