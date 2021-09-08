package StringManipulation;


class WordFormat{

    private static void justify(String[] str, int start, int end, int len){
        int letterPerLine = 0;
        int wordCount = 0;
        for(int i=start; i<end; i++){
            letterPerLine += str[i].length();
            wordCount++;
        }

        int spaceCount = (len - letterPerLine);
        int spacePerWord;
        if( wordCount-1 > 0)
            spacePerWord = spaceCount / (wordCount-1);
        else spacePerWord = wordCount;

        System.out.print("\"");
        for(int i=start;i<end;i++){
            System.out.print( str[i] );
            if(i!=end-1){
                int j = 0;
                while(j < spacePerWord){
                    System.out.print(" ");
                    j++;
                }
            }
        }
        System.out.print("\"");
        System.out.println();
    }

    static void wordFormat(String[] res, int len){
        if( res.length == 0 ){
            System.out.println("Empty array");
            return;
        }
        int start = 0 ;
        int wordCount=0;
        for(int i=0; i<res.length;){
            int letterPerLine = 0;
            while( i < res.length && ( (letterPerLine + res[i].length() < len) || (letterPerLine  + res[i].length() == len) ) ){
                letterPerLine = letterPerLine + res[i].length() + 1;
                wordCount++;
                i++;
            }
            letterPerLine -= 1;
            justify(res,start, wordCount, len);
            start = i;
        }
    }
}

public class Program {

    private static void reverseString(String s){
        if(s.length() == 0){
            System.out.println("Empty array");
            return;
        }
        char[] c = s.toCharArray();
        int i = 0 ,j = s.length()-1;
        while( i < j ){
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++;
            j--;
        }
        System.out.println( c );
    }

    private static void repeatingSubString(String string){
        if(string.length() == 0){
            System.out.println("Empty array");
            return;
        }
        char[] str = string.toLowerCase().toCharArray();
        StringBuilder result = new StringBuilder();
        for( int i=0; i<str.length; i++ ){
            for(int j=i+1;j<str.length;j++){
                int k = i;
                StringBuilder sb = new StringBuilder();
                while( j < str.length && str[k] == str[j] ){
                    sb.append(str[k]);
                    k++;
                    j++;
                }
                if(sb.length() > 2) {
                    result.append(sb).append(" ");
                    i = k;
                }
            }
        }
        if( result.length() > 0 ) System.out.println( result );
        else System.out.println( "none" );
    }


    public static void main(String[] args){
        reverseString("HelloWorld");

        System.out.println();
        repeatingSubString("However, Everyday is a day");
        repeatingSubString("Happy Holi!");

        System.out.println();
        String[] str = {"This", "is", "an", "example", "of", "text", "justification."};
        WordFormat.wordFormat(str,16);

    }
}
