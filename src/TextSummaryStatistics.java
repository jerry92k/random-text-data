
import java.util.*;
import java.util.stream.Collectors;

public class TextSummaryStatistics implements StringConsumer {

    // 전체 라인 수
    private long totalLine;
    // 전체 단어 수
    private long totalWords;
    // 전체 글자 수
    private long totalLetters;

    private long maxWordLen;
    private long minWordLen;
    // 가장 긴 단어
    private List<String> longgestWords;
    // 가장 짧은 단어
    private List<String> shortestWords;
    // 글자 도수분표
    private HashMap<Character,Integer> letterFreq;
    // 단어 길이 도수분표
    private HashMap<Integer,Integer> wordLengthFreq;


    public TextSummaryStatistics() {
//        shortestWordsLength=Long.MAX_VALUE;
        longgestWords=new ArrayList<>();
        shortestWords=new ArrayList<>();
        minWordLen=Long.MAX_VALUE;
        letterFreq=new HashMap<>();
        wordLengthFreq=new HashMap<>();
    }

    @Override
    public void accept(String sentence) {
        updateStats(sentence);
    }

    public void updateStats(String sentence){
        // 전체 줄수 업데이트
        totalLine++;

        String[] words= sentence.split(" ");
        // 전체 단어 수 업데이트
        totalWords+= words.length;

        for(String word : words){
            int lengthOfWord=word.length();
            // 전체 글자 수 업데이트
            totalLetters+= lengthOfWord;

            //가장 긴 단어 계산 (길이가 같은경우에는 먼저 나온 단어를 가장 긴 단어로 함)
            if(lengthOfWord>maxWordLen){
                longgestWords.clear();
                longgestWords.add(word);
                maxWordLen=lengthOfWord;
            }
            else if(lengthOfWord==maxWordLen) {
                longgestWords.add(word);
            }
            // 가장 짧은 단어 계산
            if(shortestWords.equals("") ||  lengthOfWord<minWordLen){
                shortestWords.clear();
                shortestWords.add(word);
                minWordLen=lengthOfWord;
            }
            else if(lengthOfWord==minWordLen){
                shortestWords.add(word);
            }

            // 글자 도수분포
            for(char ch : word.toCharArray()){
                letterFreq.put(ch,letterFreq.getOrDefault(ch,0)+1);
            }

            //단어 길이 도수분표
            wordLengthFreq.put(lengthOfWord,wordLengthFreq.getOrDefault(lengthOfWord,0)+1);
        }
    }

    public void combine(TextSummaryStatistics other) {
        totalLine+=other.totalLine;
        totalWords+=other.totalWords;
        totalLetters+=other.totalLetters;

        compareAndCombineLonggestWords(other);
        compareAndCombineShortestWords(other);
        combineLetterFreq(other);
        combineWordLengthFreq(other);

    }

    private void compareAndCombineLonggestWords(TextSummaryStatistics other) {
        if(minWordLen>other.minWordLen){
            minWordLen=other.minWordLen;
            shortestWords=other.shortestWords;
        }else if(minWordLen==other.minWordLen){
            shortestWords.addAll(other.shortestWords);
        }
    }

    private void compareAndCombineShortestWords(TextSummaryStatistics other) {
        if(maxWordLen<other.maxWordLen){
            maxWordLen=other.maxWordLen;
            longgestWords=other.longgestWords;
        }else if(maxWordLen==other.maxWordLen){
            longgestWords.addAll(other.longgestWords);
        }
    }

    private void combineLetterFreq(TextSummaryStatistics other) {
        for(Map.Entry<Character,Integer> letterStat : other.letterFreq.entrySet()){
            letterFreq.put(letterStat.getKey(),
                    letterFreq.getOrDefault(letterStat.getKey(),0)+letterStat.getValue());
        }
    }

    private void combineWordLengthFreq(TextSummaryStatistics other) {
        for(Map.Entry<Integer,Integer> wordSet : other.wordLengthFreq.entrySet()){

            wordLengthFreq.put(wordSet.getKey(),
                    wordLengthFreq.getOrDefault(wordSet.getKey(),0)+wordSet.getValue());
        }
    }

    @Override
    public String toString() {
        return "TextSummaryStatistics{" +
                "\ntotalLine=" + totalLine +
                "\n, totalWords=" + totalWords +
                "\n, totalLetters=" + totalLetters +
                "\n, longgestWords='" + longgestWords + '\'' +
                "\n, shortestWords='" + shortestWords + '\'' +
                "\n, letterFreq=" + letterFreq +
                "\n, wordLengthFreq=" + wordLengthFreq +
                '}';
    }

    public long getTotalLine() {
        return totalLine;
    }

    public long getTotalWords() {
        return totalWords;
    }

    public long getTotalLetters() {
        return totalLetters;
    }

    public List<String> getLonggestWords() {
        return longgestWords;
    }

    public List<String> getShortestWord() {
        return shortestWords;
    }

    public String getLonggestWordsToString() {
        return longgestWords.stream().collect(Collectors.joining(" "));
    }

    public String getShortestWordToString() {
        return shortestWords.stream().collect(Collectors.joining(" "));
    }

    public HashMap<Character, Integer> getLetterFreq() {
        return letterFreq;
    }

    public HashMap<Integer, Integer> getWordLengthFreq() {
        return wordLengthFreq;
    }

    public String getLetterFreqToString(){
        return letterFreq.entrySet().stream().reduce(new StringBuilder()
                                                        ,(sb,entry)->{
                                                            sb.append(" ");
                                                            sb.append(entry.getKey());
                                                            sb.append(":");
                                                            sb.append(entry.getValue());
                                                            return sb;
                                                        },
                                                        (sb1,sb2)->{
                                                                sb1.append(" ");
                                                                sb1.append(sb2);
                                                                return sb1;
                                                            }
                                                        ).toString();
    }

    public String getWordLengthFreqToString(){
        return wordLengthFreq.entrySet().stream().reduce(new StringBuilder()
                ,(sb,entry)->{
                    sb.append(" ");
                    sb.append(entry.getKey());
                    sb.append(":");
                    sb.append(entry.getValue());
                    return sb;
                },
                (sb1,sb2)->{
                    sb1.append(" ");
                    sb1.append(sb2);
                    return sb1;
                }
        ).toString();
    }

    public long getMaxWordLen() {
        return maxWordLen;
    }

    public long getMinWordLen() {
        return minWordLen;
    }
}
