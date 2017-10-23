import java.util.ArrayList;
import java.util.List;

public class RabinKarpAlgorithm {

	public static void main(String[] args) {
		String text = "abxabcabyabyaby";
		String pattern = "abyaby";
		
		RabinKarp rabinKarp = new RabinKarp(text, pattern);
		List<Integer> answer = rabinKarp.solve();
		rabinKarp.print(answer);
	}

}

class RabinKarp {
	
	String text;
	String pattern;
	
	public RabinKarp(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
	}
	
	//assuming only lowercase english alphabet
	public int computeHash(String s) {
		int l = s.length();
		int val = 0;
		for(int i=l-1; i >= 0; i--) {
			val = val + (int)Math.pow(26, i)*(s.charAt(l-1-i)-97);
		}
		return val;
	}
	
	public int recomputeHash(int hash, String text, int i, int m) {
		
		hash = (hash-(int)Math.pow(26, m-1)*(text.charAt(i)-97))*26 + text.charAt(i+m)-97;
		
		return hash;
	}
	
	public List<Integer> solve() {
		int n = text.length();
		int m = pattern.length();
		
		int hashP = computeHash(pattern);
		int hashT = computeHash(text.substring(0, m));
		
		
		List<Integer> answer = new ArrayList<Integer>();
		for(int i=0; i <= n-m-1; i++) {
			if(hashT == hashP) {
				boolean broken = false;
				for(int j=0; j < m; j++) {
					if(pattern.charAt(j) != text.charAt(i+j)) {
						broken = true;
						break;
					}
					
				}
				if(!broken) {
					answer.add(i);
				}
			}
			if(i+1+m < n) {
				hashT = recomputeHash(hashT, text, i, m);
			}
		}
		return answer;
	}
	
	public void print(List<Integer> answer) {
		for(int i=0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
		System.out.println();
	}
}
