import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Connect to the DLB website and parse the HTML
        Document doc = Jsoup.connect("https://www.dlb.lk/").get();

        // Select the section that contains lottery results0
        Elements elements = doc.getElementsByClass("col-md-8 no_padding");

        for (Element element : elements) {
            // Get the text of each element
            String text = element.text();

            // Define the days of the week so that unnecessary details mentioned before the day can be omitted
            String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            // Find the position of the first day of the week mentioned in the text
            int position = -1;
            for (String day : daysOfWeek) {
                if (text.contains(day)) {
                    position = text.indexOf(day) + day.length();
                    break;
                }
            }

            // If a day was found, print the rest of the text after the day and a space
            if (position != -1) {
                String result = text.substring(position).trim();
                System.out.println(result);
            }
        }
    }
}
