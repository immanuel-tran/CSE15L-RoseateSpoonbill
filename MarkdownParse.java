import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            //if(markdown.indexOf("!") == nextOpenBracket - 1){
            //    break;
            //}
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            System.out.println("nextCloseBracket: " + nextCloseBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            System.out.println("openParen: " + openParen);
            int closeParen = markdown.indexOf(")", openParen);
            System.out.println("closeParen: " + closeParen);
            //if(nextCloseBracket + 1 != openParen){
            //    break;
            //}
            if( nextCloseBracket == -1 ||
                       openParen == -1 ||
                      closeParen == -1 ||
                 nextOpenBracket == -1  ) {
                     break;
                 }
           if(markdown.indexOf("!") != nextOpenBracket - 1){
               toReturn.add(markdown.substring(openParen + 1, closeParen));
           }

            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}