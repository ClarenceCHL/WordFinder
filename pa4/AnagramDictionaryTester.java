import java.io.FileNotFoundException;

public class AnagramDictionaryTester {
    public static void main(String[] arg){
        try{
            AnagramDictionary dictionary = new AnagramDictionary("testFiles/sowpods.in");
            System.out.println(dictionary.getAnagramsOf("lobb"));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IllegalDictionaryException e){
            System.out.println(e.getMessage());
        }
    }
}
