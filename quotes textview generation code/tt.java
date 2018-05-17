import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class tt {
    public void t() throws Exception{
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("a.txt").toURI());

        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        String[] q = {""}, w = {""};
        final int[] i = {0};
        final int[] start = {7};
        lines.forEach(line -> {
            ++i[0];
            if (i[0] ==1)
                q[0] = line;
            else if (i[0] ==2)
                w[0] = line;
            else {
                String str = get(q[0], w[0], ++start[0]);
                System.out.println(str);
                i[0]=0;
            }
        });
        lines.close();
    }
    public String get(String q, String w, int start){
        String str ="\n" +
                "\n" +
                "            <View\n" +
                "                android:layout_width=\"match_parent\"\n" +
                "                android:layout_height=\"4dp\"\n" +
                "                android:background=\"@android:color/darker_gray\"\n" +
                "                tools:layout_editor_absoluteX=\"0dp\"\n" +
                "                tools:layout_editor_absoluteY=\"68dp\"\n" +
                "                tools:ignore=\"MissingConstraints\" />\n" +
                "\n" +
                "            <TextView\n" +
                "                android:id=\"@+id/inspirationQuote_"+start+"\"\n" +
                "                android:layout_width=\"fill_parent\"\n" +
                "                android:layout_height=\"wrap_content\"\n" +
                "                android:layout_weight=\"1.0\"\n" +
                "                android:paddingLeft=\"20dp\"\n" +
                "                android:paddingRight=\"20dp\"\n" +
                "                android:paddingTop=\"25dp\"\n" +
                "                android:text=\""+q+"\"\n" +
                "                android:textStyle=\"bold\"\n" +
                "                android:textSize=\"22dp\"\n" +
                "                tools:ignore=\"MissingConstraints\"\n" +
                "                tools:layout_editor_absoluteX=\"0dp\"\n" +
                "                tools:layout_editor_absoluteY=\"72dp\" />\n" +
                "\n" +
                "            <TextView\n" +
                "                android:id=\"@+id/inspirationWriter_"+start+"\"\n" +
                "                android:layout_width=\"fill_parent\"\n" +
                "                android:layout_height=\"wrap_content\"\n" +
                "                android:layout_weight=\"1.0\"\n" +
                "                android:gravity=\"end\"\n" +
                "                android:paddingLeft=\"20dp\"\n" +
                "                android:paddingRight=\"20dp\"\n" +
                "                android:paddingBottom=\"25dp\"\n" +
                "                android:text=\""+w+"\"\n" +
                "                android:textAlignment=\"textEnd\"\n" +
                "                tools:layout_editor_absoluteX=\"274dp\"\n" +
                "                tools:layout_editor_absoluteY=\"96dp\"\n" +
                "                tools:ignore=\"MissingConstraints\" />";


        return str;
    }
}
