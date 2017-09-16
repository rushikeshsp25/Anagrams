/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    static int  wordsize = DEFAULT_WORD_LENGTH, counter = 0;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<String>();
    HashMap<String, ArrayList> lettersToWord = new HashMap<String, ArrayList>();
    HashMap<Integer, ArrayList> sizeToWord = new HashMap<Integer, ArrayList>();
    HashSet<String> wordSet = new HashSet<String>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        ArrayList<String> temp = new ArrayList<String>();
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            putIntoHashSet(word);
            String sortedWord = sorted(word);
            if (lettersToWord.containsKey(sortedWord)) {
                temp = lettersToWord.get(sortedWord);
                temp.add(word);
                lettersToWord.put(sortedWord, temp);
            } else {
                temp.add(word);
                lettersToWord.put(sortedWord, temp);
            }
            temp.clear();
            if (sizeToWord.containsKey(word.length())) {
                temp = sizeToWord.get(word.length());
                temp.add(word);
                sizeToWord.put(word.length(), temp);
            } else {
                temp.add(word);
                sizeToWord.put(word.length(), temp);
            }

        }
    }

    public void putIntoHashSet(String word) {
        wordSet.add(word);
    }

    public boolean isGoodWord(String word, String base) {
        ArrayList<String> temp = new ArrayList<String>();
        if (word.contains(base))
            return false;
        else if (wordSet.contains(word))
            return true;
        else
            return false;

    }


    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String s_targetWord = sorted(targetWord);
        result = lettersToWord.get(s_targetWord);

        return result;
    }

    public boolean isAnagram(String targetWord, String fromList) {
        String s_targetWord = sorted(targetWord);
        String s_fromList = sorted(fromList);
        if (s_targetWord.length() == s_fromList.length()) {
            if (s_targetWord.equalsIgnoreCase(s_fromList)) {
                return true;
            }
        }
        return false;
    }

    public String sorted(String str) {
        char str_arr[] = str.toCharArray();
        Arrays.sort(str_arr);
        return (new String(str_arr));
    }


    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        String w, sw;

        for (char ch = 'a'; ch < 'z'; ch++) {
            temp.clear();
            w = word + ch;
            sw = sorted(w);
            if (lettersToWord.containsKey(sw)) {
                temp = lettersToWord.get(sw);
                for (int i = 0; i < temp.size(); i++) {
                    if (isGoodWord(temp.get(i), word))
                        result.add(temp.get(i));
                }
            }
        }

        return result;
    }



    public String pickGoodStarterWord() {
        /*

        Random rng = new Random();
        ArrayList<String> temp = new ArrayList<String>();
        temp = sizeToWord.get(wordsize);

        while(true)
        {
            int wordptr = rng.nextInt(temp.size());
            String word = temp.get(wordptr);
            String s_word = sorted(word);
            temp.clear();
            temp = lettersToWord.get(s_word);
            if (temp.size() > 5) {
                counter++;
                if (counter == 10) {
                    if (wordsize < MAX_WORD_LENGTH)
                        wordsize++;
                    else
                        wordsize = DEFAULT_WORD_LENGTH;

            }


            return word;
        }

        }
        */
        return "stop";




    }


}
