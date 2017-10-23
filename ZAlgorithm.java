import java.util.ArrayList;
import java.util.List;

public class ZAlgorithm {

	public static void main(String[] args) {
		String text = "abxabcabyabyaby";
		String pattern = "abyaby";
		
		Z z = new Z(text, pattern);
		List<Integer> answer = z.solve();
		
		z.print(answer);
	}
}

class Z {
	
	String text;
	String pattern;
	
	public Z(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
	}
	
	public List<Integer> solve() {
		
		String str = pattern + "$" + text;
		char[] c = str.toCharArray();
		
		int[] z = new int[c.length];
		solve(z, c);
		List<Integer> answer = new ArrayList<Integer>();
		for(int i=0; i < c.length; i++) {
			if(z[i] == pattern.length()) {
				answer.add(i-pattern.length()-1);
			}
		}
		return answer;
	}
	
	private void solve(int[] z, char[] c) {
		int l = z.length;
		z[0] = 0;
		
		int left = 0;
		int right = 0;
		
		for(int i=1; i < l; i++) {
			if(i > right) {
				left = i;
				right = i;
				
				while(right < l && c[right-left] == c[right]) {
					right++;
				}
				z[i] = right-left;
				right--;
			}else {
				int k = i-left;
				if(z[k] < right-i+1) {
					z[i] = z[k];
				}else {
					
					left = i;
					while(right < l && c[right-left] == c[right]) {
						right++;
					}
					z[i] = right-left;
					right--;
				}
			}
		}
		
	}
	
	public void print(List<Integer> answer) {
		
		for(int i=0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
		System.out.println();
	}
}
