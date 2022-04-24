
import java.io.*;
import java.net.*;
import java.util.*;
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class


public class TimeClient {

    public String getTime() {
        String timeValue = "";
        try {
            Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13);
            try {
                InputStream inStream = s.getInputStream();
                Scanner in = new Scanner(inStream);

                while (in.hasNextLine()) {
                    timeValue = in.nextLine();
                }
            } finally {
                s.close();
            }
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
        timeValue = timeValue.substring(6, 23);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeValue, myFormatObj);
        dateTime = dateTime.minusHours(4);
        timeValue = dateTime.format(myFormatObj);
        return timeValue;
    } //end public

    public static void main(String[] args) {
        TimeClient t = new TimeClient();
        System.out.println(t.getTime());
    }
} //end class

