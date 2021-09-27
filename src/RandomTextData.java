import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomTextData {

    public static void main(String[] args){
        String filename="test.txt";
        RandomTextData randomTextData=new RandomTextData();
        randomTextData.makeRandomTextFile(args, filename);
    }

    public void makeRandomTextFile(String[] args, String filename){
        int numOfSentences= makeRandomNumOfSentences(args);
        List<String> sentences = produceRandomSentences(numOfSentences);
        outputToFiles(filename,numOfSentences,sentences);
    }

    private int makeRandomNumOfSentences(String[] args){
        checkHasArgs(args);
        int lowerBound=Integer.parseInt(args[0]);
        int upperBound=Integer.parseInt(args[1]);
        checkIsPositiveArgs(lowerBound,upperBound);

        // 입력받은 lowerBound 숫자, upperBound 숫자 사이의 난수 생성
       return getRandomNumber(lowerBound,upperBound);
    }

    private void checkHasArgs(String[] args){
        if(args==null || args.length<2){
            throw new InvalidParameterException();
        }
    }

    private void checkIsPositiveArgs(int... args){
        for(int num: args){
            if(num<=0){
                throw new InvalidParameterException();
            }
        }
    }

    public List<String> produceRandomSentences(int randomNum){

        final int wordsLenlow=2;
        final int wordsLenhigh=10;

        List<String> sentences=new ArrayList<>();

        // 2이상 10 이하의 임의의 난수 n을 발생시켜(n개의 단어를 규칙을 따라서 입력)
        for(int i=0; i<randomNum; i++){
            // 2이상 10 이하 임의의 난수 n 발생
            sentences.add(produceSentence(getRandomNumber(wordsLenlow,wordsLenhigh)));
        }
        return sentences;
    }

    public int getRandomNumber(int lowerBound, int upperBound){
        return (int)((Math.random()*(upperBound-lowerBound))+lowerBound);
    }

    public String produceSentence(int numOfWords){
        final int wordLengthLow=1;
        final int wordLengthHigh=30;

        List<String> randomWords=new ArrayList<>();

        // 각 문자열 내의 단어를 numOfWords 개 생성
        for(int j=0; j<numOfWords; j++){

            // 1이상 30 이하의 단어 길이를 난수로 정함
            String word=produceWord(getRandomNumber(wordLengthLow,wordLengthHigh));
            randomWords.add(word);
        }
        return randomWords.stream().collect(Collectors.joining(" "));
    }

    public String produceWord(int wordLength){

        StringBuilder sb= new StringBuilder();

        //각 단어 내의 문자(영문 알파벳(`[a-zA-Z]`) 중 하나)를 wordLength 개 랜덤 생성
        for(int k=0; k<wordLength; k++){
            sb.append((char)getRandomAlphabet());
        }
        return sb.toString();
    }

    public int getRandomAlphabet() {
        final int aciAlphabetLower=65; // A
        final int aciAlphabetUpper=121; // z

        int num=getRandomNumber(aciAlphabetLower,aciAlphabetUpper);
        // 91 ~ 96은 알파벳이 아닌 문자이므로 다른 대문자값으로 변경시켜줌
        if(num>=91 && num<=96){
            num=num+6;
        }
        return num;
    }

    public void outputToFiles(String filename, int numOfSentences, List<String> sentences){
        try(Writer fileWriter= new FileWriter(filename,false)){
            try(BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(String.valueOf(numOfSentences));
                for(String word : sentences){
                    bufferedWriter.newLine();
                    bufferedWriter.write(word);
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
