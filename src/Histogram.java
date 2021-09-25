import exception.NotInitializedException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Histogram {

    public static void main(String[] args) throws NotInitializedException {
        Histogram histogram = new Histogram();
        histogram.makeHistogram();
    }

    public void makeHistogram() throws NotInitializedException {
        int divider=10;

        Scanner scanner=new Scanner(System.in);
        System.out.println("히스토그램을 위한 데이터를 입력해주세요.\n입력형식: 레이블1:도수1 레이블2:도수2 ...");
        String inputdata=scanner.nextLine();

        HashMap<Character,Integer> amounts=new LinkedHashMap<>();
        // 레이블-도수 셋트별로 split
        String[] pairs=inputdata.split(" ");

        int maxVal=0;
        // 레이블과 도수를 map으로 관리
        for(String pair : pairs){
            int value=pair.charAt(2)-48;
            amounts.put(pair.charAt(0),value);
            maxVal=Math.max(maxVal,value);
        }
        // O(10*n) => O(n)
        HashMap<Integer,StringBuilder> histogram=new LinkedHashMap<>();
        makeLeftNumber(histogram,maxVal);
        makeLeftLayout(histogram);
        makeDataArea(histogram,maxVal, amounts);

        for(StringBuilder sb : histogram.values()){
            System.out.println(sb.toString());
        }

    }

    private void makeDataArea(HashMap<Integer, StringBuilder> histogram, int maxVal, HashMap<Character,Integer> amounts) throws NotInitializedException {
        for(Map.Entry<Character,Integer> entry: amounts.entrySet()){
            char label=entry.getKey();
            int value=entry.getValue();
            int ratio=(int)(Math.round(((double)value/maxVal)*10));

            StringBuilder sb=new StringBuilder();

            for(int pointVal=-1; pointVal<=10; pointVal++){

                checkPreLayout(histogram,pointVal);
                sb=histogram.get(pointVal);

                if(pointVal==-1){
                    sb.append(' ');
                    sb.append(label);
                }
                else if(pointVal==0){
                    sb.append("--");
                }
                else if(pointVal<=ratio){
                    sb.append(" #");
                }else{
                    sb.append("  ");
                }
            }
        }

    }

    public void checkPreLayout(HashMap<Integer, StringBuilder> histogram, int pointVal) throws NotInitializedException {
        if(!histogram.containsKey(pointVal)){
            throw new NotInitializedException("초기 도수 레이아웃이 셋팅되지 않았습니다.");
        }
    }

    private void makeLeftLayout(HashMap<Integer, StringBuilder> histogram) throws NotInitializedException {
        for(int pointVal=10; pointVal>=-1; pointVal--){
            checkPreLayout(histogram,pointVal);
            StringBuilder sb=histogram.get(pointVal);
            switch (pointVal){
                case -1 -> sb.append(' ');
                case 0 -> sb.append('+');
                default -> sb.append('|');
            }
        }
    }

    public void makeLeftNumber(HashMap<Integer,StringBuilder> histogram, int maxVal){
        for(int pointVal=10; pointVal>=-1; pointVal--){
            StringBuilder sb=new StringBuilder();
            switch (pointVal){
                case 0 -> sb.append(0);
                case 5 -> sb.append((int)(Math.round((double) maxVal/2)));
                case 10 -> sb.append(maxVal);
                default -> sb.append(' ');
            }

            histogram.put(pointVal,sb);

        }
    }
}
