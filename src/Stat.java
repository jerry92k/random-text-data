import java.io.*;
import java.util.ArrayList;
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

        lines.stream().collect(Collectors.summarizingDouble())


    }

    public void

    public List<String> readDatas(String filename){
        List<String> lines=new ArrayList<>();
        try(Reader reader = new FileReader(filename)) {
            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                String line=null;
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
