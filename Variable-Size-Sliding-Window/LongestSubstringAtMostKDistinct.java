import java.util.HashMap;

class Solution {

    public int longestSubstring(String s, int k) {
        // Stores frequency of each character in the current window
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        // Expand the window by moving right
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // Add current character to the frequency map
            map.put(current, map.getOrDefault(current, 0) + 1);

            // Shrink window until it has at most k distinct characters
            while (map.size() > k) {
                char leftChar = s.charAt(left);

                // Decrease frequency of the leftmost character
                map.put(leftChar, map.get(leftChar) - 1);

                // Remove character when its frequency becomes zero
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                left++;
            }

            // Update maximum length of a valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
Short Notes:
1. right expands the sliding window.
2. HashMap stores character frequencies.
3. If distinct characters > k, move left to shrink the window.
4. Remove a character from the map when its frequency becomes 0.
5. For a valid window, update maxLen = right - left + 1.

Time Complexity: O(n) - each pointer moves at most n times.
Space Complexity: O(k) - at most k distinct characters remain in the valid window.

Example: s = "aaabbccd", k = 2
Answer: 5 ("aaabb")
*/