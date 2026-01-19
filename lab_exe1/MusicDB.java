import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicDB {

    public static void main(String[] args) throws Exception {

        // Hashmap stores data in key-val pairs
        Map<String, String> filters = new HashMap<>();

        // For every string in CLI args
        for (String arg : args) {
            String[] parts = arg.split("=", 2);
            if (parts.length == 2) {
                filters.put(parts[0].toLowerCase(),
                            parts[1].replace("\"", "").toLowerCase());
            }
        }

        Map<String, String> artistOrigins = new HashMap<>();
        BufferedReader artistReader = new BufferedReader(new FileReader("artists.csv"));
        String line;

        // Artist - origin hashmap (artistOrigins)
        while ((line = artistReader.readLine()) != null) {
            String[] record = line.split(",");
            String name = record[0].trim().replace("\"", "");
            String origin = record[1].trim().replace("\"", "");
            artistOrigins.put(name.toLowerCase(), origin.toLowerCase());
        }
        artistReader.close();

        BufferedReader albumReader = new BufferedReader(new FileReader("albums.csv"));

        // Keys n values ArrayList from the filters HashMap
        // Initially added because of the two key-value pairs from the LabGuide
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }
        
        // Note: CSV file was modified
        while ((line = albumReader.readLine()) != null) {
            String[] record = line.split(",");

            String album = record[0].trim().replace("\"", "");
            String artist = record[1].trim().replace("\"", "");
            int year = Integer.parseInt(record[2].trim());


            // Filter implementation
            // This works assuming a certain CLI format
            // One key-value pair
            String origin = artistOrigins.get(artist.toLowerCase());
            String originUpp = origin.substring(0, 1).toUpperCase() + origin.substring(1);
            if(keys.contains("artist")) {
                if(artist.toLowerCase().contains(values.get(0))) {
                    System.out.println(artist + " - " + album + " (" + year + ")" + " [" + originUpp +"]");
                }
            }
            else if(keys.contains("album")) {
                if(album.toLowerCase().contains(values.get(0))) {
                    System.out.println(artist + " (" + year + ") " + "[" + originUpp + "]");
                }
                
            }
            else if(keys.contains("year")) {
                if(year == Integer.parseInt(values.get(0))) {
                    System.out.println(artist + " - " + album + " [" + originUpp + "]");
                }
            }
            else if(keys.contains("origin")) {
                if(origin.equals(values.get(0))) {
                    System.out.println(artist + " - " + album + " (" + year + ")");
                }
            } 
            else if(keys.isEmpty()) { // Else if no addtl args are given in the CLI, print albums
                System.out.println(album);
            }
        }
        albumReader.close();
    }
}

