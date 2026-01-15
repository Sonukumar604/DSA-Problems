import java.util.*;

public class WordBreak {
	public List<String> wordBreak(String s, List<String> wordDict){
		List<String> result = new ArrayList<>();
		// Use a Set for efficient O(1) average time complexity for `contains` checks.
		Set<String> wordSet = new HashSet<>(wordDict);
		backtrack(s, wordSet, 0 , new ArrayList<>(), result);
		return result;
	}
	private void backtrack(String s, Set<String> wordSet, int start, List<String> current, List<String> result){
		// Base case: if we've reached the end of the string, we have a valid sentence.
		if(start == s.length()){
			result.add(String.join(" ", current));
			return;
		}
		// The loop must go up to s.length() to include substrings that end at the last character.
		for(int end = start + 1; end <= s.length(); end++){
			String word = s.substring(start, end);
			if(wordSet.contains(word)){
				current.add(word);
				backtrack(s, wordSet, end, current, result);
				// Backtrack: remove the last added word to explore other possibilities.
				current.remove(current.size() - 1);
			}
		}
	}
	// Example usage
	public static void main(String[] args) {
		WordBreak wb = new WordBreak();
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		List<String> sentences = wb.wordBreak(s, wordDict);
		for (String sentence : sentences) {
			System.out.println(sentence);
		}
	}
}
