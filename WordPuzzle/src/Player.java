import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player {
	private Socket socket;
	private String name;
	private ArrayList<String> words;
	private ArrayList<String> dictionary;
	private String letters;
	private HashMap<Character, Integer> letterDict;
	private int score;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public Player(Socket socket, ArrayList<String> dict,String letters){
		this.socket=socket;
		this.dictionary=dict;
		this.letters=letters;
		this.letterDict=this.getWordDict(this.letters);
		this.name="";
		this.words= new ArrayList<>();
		this.score=0;
	}
	
	public BufferedReader getReader() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
		return in;
	}
	
	public BufferedWriter getWriter() throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
		return out;
	}

	public void close() throws IOException {
		this.socket.close();
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}
	
	public void evaluate(){
		HashSet<String> unique_words= new HashSet<String>(words);
		for (String word : unique_words){
			if (this.validWord(word) && this.dictionary.contains(word)){
				this.score+=word.length();
			}
		}
	}

	public int getScore() {
		return score;
	}
	
	private boolean validWord(String word){
		HashMap<Character, Integer> wordDict=this.getWordDict(word);
		for (char c : wordDict.keySet()){
			if (wordDict.get(c)>this.letterDict.getOrDefault(c, 0)){
				return false;
			}
		}
		return true;
	}
	
	private HashMap<Character, Integer> getWordDict(String word){
		HashMap<Character, Integer> wordDict=new HashMap<Character, Integer>();
		for (char c : word.toCharArray()){
			wordDict.put(c, wordDict.getOrDefault(c, 0)+1);
		}
		return wordDict;
	}

}
