
import java.util.HashMap;
import java.util.function.Consumer;

public class TextSummaryStatistics<String> implements Consumer<String> {



    // 전체 라인 수
    private long totalLine;
    // 전체 단어 수
    private long totalWords;
    // 전체 글자 수
    private long totalLetters;
    // 가장 긴 단어 길이
    private long maxWordLength;
    // 가장 짧은 단어 길이
    private long minWordLength=Long.MAX_VALUE;
    // 가장 긴 단어
    private String maxWord;
    // 가장 짧은 단어
    private String minWord;
    // 글자 도수분표
    private HashMap letterFreq;
    // 단어 길이 도수분표
    private HashMap wordLengthFreq;

    /**
     * Constructs an empty instance with zero count, zero sum,
     * {@code Double.POSITIVE_INFINITY} min, {@code Double.NEGATIVE_INFINITY}
     * max and zero average.
     */
    public TextSummaryStatistics() {
        minWordLength=Long.MAX_VALUE;
        letterFreq=new HashMap<>();
        wordLengthFreq=new HashMap<>();
    }

    @Override
    public void accept(String sentence) {
        totalLine++;
        sentence.toString().
//        String[] words=sentence.(" ");
    }


    public void combine(TextSummaryStatistics other) {
//        count += other.count;
//        simpleSum += other.simpleSum;
//        sumWithCompensation(other.sum);
//        sumWithCompensation(other.sumCompensation);
//        min = Math.min(min, other.min);
//        max = Math.max(max, other.max);
    }
}
