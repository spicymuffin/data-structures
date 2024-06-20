public class KMP {
    public static int[] computeFail(String pattern) {
        int[] f = new int[pattern.length()];
        // for 0th character, f is defined as 0 (technically it is 1 but set it to 0)
        f[0] = 0;
        // cycle through pattern, starting from 1th char.
        for (int i = 1; i < pattern.length(); i++) {
            // initialize to previous matched until but not including index
            int prev_matched_until_but_not_including_indx = f[i - 1];

            // transfer to previous mathced until but not including index
            // until the characters at that index match or it becomes 0
            while ((pattern.charAt(i) != pattern.charAt(prev_matched_until_but_not_including_indx))
                    && (prev_matched_until_but_not_including_indx >= 1)) {
                prev_matched_until_but_not_including_indx = f[prev_matched_until_but_not_including_indx - 1];
            }
            // if we exited bc characters match, we set f[i] to that index + 1
            if (pattern.charAt(i) == pattern.charAt(prev_matched_until_but_not_including_indx)) {
                f[i] = prev_matched_until_but_not_including_indx + 1;
            }
            // else set f[i] to zero. all the previouis suffixes didnt match and the current
            // character isnt the same as the fist one
            else {
                f[i] = 0;
            }
        }
        return f;
    }

    public static int kmpMatch(String text, String pattern, int[] f) {
        int text_iterator = 0, pattern_iterator = 0;
        int n = text.length(), m = pattern.length();
        // iterate while we dont "overflow"
        while ((text_iterator < n)) {
            // if characters match, step text iterator and pattern iterator
            if (text.charAt(text_iterator) == pattern.charAt(pattern_iterator)) {
                text_iterator++;
                pattern_iterator++;

                if (pattern_iterator == m) {
                    System.out.println("hit at " + (text_iterator - m));
                    pattern_iterator = f[pattern_iterator - 1];
                }
            }
            // else, the characters were not matching
            else {
                // if the pattern iterator was at 0, just step the text iterator
                // since we know for sure that the pattern isnt going to be found
                // in the text starting from index pattern_iterator
                if (pattern_iterator == 0) {
                    text_iterator++;
                }
                // however if the pattern iterator wasnt 0, we know that the pattern
                // was somehow matching in the text. we know that we went pattern_iterator
                // characters deep inside the pattern, but found a mismatch
                //
                // the i-1th entry in f indicates the index at which the mismatch should've
                // occured if the matching were to starting from the last f[i-1] characters
                //
                // in other words,
                // if we failed at p_i = i we know that symbols up to index = i-1 match
                // also we know that if the length of the matched substring is i + 1 (index
                // adjust), then
                // the first f[i-1] of the substring are equal to the last f[i-1] characters
                // so why dont we just pretend that we already checked the first f[i-1]
                // characters?
                // we exactly do that. we set pattern iterator to f[i-1]. that essentially makes
                // it
                // like we already match indices 0, 1, ... to f[i-1] - 1 and are at the f[i-1]th
                // index,
                // the not matching one.
                //
                // once we perfomed this shift, we now have to restart the main loop and make
                // sure to
                // check the character we stopped at again.
                //
                // if we fail on it again, we will "roll back" further down f[] to find a new,
                // smaller
                // substring, that makes us able to pretend that we already checked a part of
                // the string
                else {
                    pattern_iterator = f[pattern_iterator - 1];
                }
            }
        }
        if (pattern_iterator < m)
            return -1;
        else
            return text_iterator - m;
    }

    public static void main(String[] args) {
        String text = "babcbabcabcaabcabcabcacabc";
        String pattern = "abcabcacab";
        int[] f = computeFail(pattern);

        for (int i = 0; i < pattern.length(); i++) {
            System.out.print(f[i] + " ");
        }
        System.out.println();

        int ans = kmpMatch(text, pattern, f);
        System.out.println(ans);
    }
}
