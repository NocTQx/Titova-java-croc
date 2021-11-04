import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String removeJavaComments(String input){
        // регулярное выражение вида /* ... */ или // ... /n
        final String regular = "(?=([^\"]*\"[^\"]*\")*[^\"]*$)(([\\/][\\*]([\\s\\S]*?)[\\*][\\/])|([\\/]{2}(.*)[^\\n]))";

        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(""); // замена найденных соответствий
    }

    public static void main(String[] args) {

        String source = "/*\n" +
                " * My first ever program in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here \n" +
                "  \n" +
                "  /* main method */\n" +
                "  public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "    // this line prints my first greeting to the screen\n" +
                "    System.out.println(\" \\* hi there *\\ \"); // :)\n" +
                "  }\n" +
                "} // the end\n" +
                "// to be continued...\n";
        String noComments = removeJavaComments(source);
        System.out.println(noComments);
    }

}
