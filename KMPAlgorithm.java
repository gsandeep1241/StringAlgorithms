import java.util.ArrayList;
import java.util.List;

public class KMPAlgorithm {

	public static void main(String[] args) {
		String text = "abxabcabyabyaby";
		String pattern = "abyaby";
		
		KMP kmp = new KMP(text, pattern);
		//matcher preprocessor
		kmp.matcher();
		//matcher array printer
		kmp.print();
		//matcher
		List<Integer> answer = kmp.solve();
		//index printer
		kmp.print(answer);
	}
}

class KMP {
	
	String text;
	String pattern;
	int[] matcher;
	
	public KMP(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		
		int n = pattern.length();
		matcher = new int[n];
	}
	
	public void matcher() {
		matcher[0] = 0;
		int j=0; 
		int l = pattern.length();
		
		for(int i=1; i < l; i++) {
			while(j != 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = matcher[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				matcher[i] = j+1;
				j++; continue;
			}
			matcher[i] = 0;
		}
	}
	
	public List<Integer> solve() {
		List<Integer> answer = new ArrayList<>();
		int m = text.length();
		int j = 0;
		
		for(int i=0; i < m; i++) {
			while(j != 0 && text.charAt(i) != pattern.charAt(j)) {
				j = matcher[j-1];
			}
			
			if(text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			if(j == pattern.length()) {
				answer.add(i-pattern.length()+1);
				j = matcher[j-1];
			}
		}
		
		return answer;
	}
	
	public void print() {
		for(int i=0; i < matcher.length; i++) {
			System.out.print(matcher[i] + " ");
		}
		System.out.println();
	}
	
	public void print(List<Integer> answer) {
		for(int i=0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
		System.out.println();
	}
}
