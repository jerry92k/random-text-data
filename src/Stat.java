import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Stat {

    public static void main(String[] args){

        Stat stat=new Stat();
        String filename="test.txt";
        stat.produceStat(filename);
    }

    public void produceStat(String filename){
        List<String> lines=readDatas(filename);

        TextSummaryStatistics result= lines.stream().parallel().collect(new TextCollectorImpl<String, TextSummaryStatistics, TextSummaryStatistics>(
                TextSummaryStatistics::new,
                (r, t) -> r.accept(t),
                (l, r) -> { l.combine(r); return l; }));

        System.out.println(
                "라인수:"+result.getTotalLine()+"\n" +
                "전체 단어 수:"+result.getTotalWords()+"\n" +
                "전체 글자 수:"+result.getTotalLetters()+"\n" +
                "가장 긴 단어 길이:"+result.getMaxWordLen()+"\n" +
                "가장 긴 단어:"+result.getLonggestWordsToString()+"\n" +
                "가장 잛은 단어 길이:"+result.getMinWordLen()+"\n" +
                "가장 짧은 단어:"+result.getShortestWordToString()+"\n" +
                "글자 도수분포:"+result.getLetterFreqToString()+"\n"+
                "단어 길이 도수분표:"+result.getWordLengthFreqToString()+"\n"
        );

    }

    public List<String> readDatas(String filename){
        List<String> lines=new ArrayList<>();
        try(Reader reader = new FileReader(filename)) {
            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                // 첫번째줄. 라인수 생략
                String line=bufferedReader.readLine();

                while((line=bufferedReader.readLine())!=null){
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
