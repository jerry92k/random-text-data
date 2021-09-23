import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomTextData {

    public static void main(String[] args){
        RandomTextData randomTextData=new RandomTextData();
        randomTextData.produceRandomText(args);
    }

    public void produceRandomText(String[] args){
        if(!isValidArgs(args)){
            return;
        }

        int lowerBound;
        int upperBound;
        try{
            lowerBound=Integer.parseInt(args[0]);
            upperBound=Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex){
            System.out.println(ex.getStackTrace());
            return;
        }

        // 입력받은 lowerBound 숫자, upperBound 숫자 사이의 난수 생성
        int randomNum=getRandomNumber(lowerBound,upperBound);
        // 16진수로 출력
        System.out.println(Integer.toHexString(randomNum));

        // randomNum 개의 문자열 생성 (각 문자열의 문자들은 공백으로 이어서 한줄로 출력)
        outputSentences(randomNum);


    }

    public void outputSentences(int randomNum){

        final int wordsLenlow=2;
        final int wordsLenhigh=10;

        // 2이상 10 이하의 임의의 난수 n을 발생시켜(n개의 단어를 규칙을 따라서 입력)
        for(int i=0; i<randomNum; i++){
            // 2이상 10 이하 임의의 난수 n 발생
            produceSentence(getRandomNumber(wordsLenlow,wordsLenhigh));
        }
    }

    public void produceSentence(int numOfWords){
        final int wordLengthLow=1;
        final int wordLengthHigh=30;

        List<String> randomWords=new ArrayList<>();

        // 각 문자열 내의 단어를 numOfWords 개 생성
        for(int j=0; j<numOfWords; j++){

            // 1이상 30 이하의 단어 길이를 난수로 정함
            String word=produceWord(getRandomNumber(wordLengthLow,wordLengthHigh));
            randomWords.add(word);
        }
        System.out.println(randomWords.stream().collect(Collectors.joining(" ")));
    }

    public String produceWord(int wordLength){

        StringBuilder sb= new StringBuilder();

        //각 단어 내의 문자(영문 알파벳(`[a-zA-Z]`) 중 하나)를 wordLength 개 랜덤 생성
        for(int k=0; k<wordLength; k++){
            sb.append((char)getRandomAlphabet());
        }
        return sb.toString();
    }

    public int getRandomNumber(int lowerBound, int upperBound){
        return (int)((Math.random()*(upperBound-lowerBound))+lowerBound);
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

    public boolean isValidArgs(String[] args){
        if(args==null || args.length<2){
            return false;
        }
        return true;
    }
}
