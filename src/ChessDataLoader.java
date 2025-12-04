import java.io.*;
import java.util.*;

public class ChessDataLoader {

    public List<ChessPlayer> loadPlayers(String filename) throws IOException {
        List<ChessPlayer> playersList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = readNonEmptyLine(reader)) != null) {
            // Чтение 8 строк для каждого игрока
            String ratingDate = line.trim();

            line = readNonEmptyLine(reader);
            if (line == null) break;
            int rating = parseSafeInt(line);

            line = readNonEmptyLine(reader);
            if (line == null) break;
            String[] nameParts = line.trim().split(", ");
            String lastName = nameParts[0];
            String firstName = nameParts.length > 1 ? nameParts[1] : "";

            line = readNonEmptyLine(reader);
            if (line == null) break;
            String title = line.trim();

            line = readNonEmptyLine(reader);
            if (line == null) break;
            String country = line.trim();

            line = readNonEmptyLine(reader);
            if (line == null) break;
            int standardRating = parseSafeInt(line);

            line = readNonEmptyLine(reader);
            if (line == null) break;
            int gamesPlayed = parseSafeInt(line);

            line = readNonEmptyLine(reader);
            if (line == null) break;
            int birthYear = parseSafeInt(line);

            playersList.add(new ChessPlayer(ratingDate, rating, lastName, firstName,
                    title, country, standardRating, gamesPlayed, birthYear));
        }

        reader.close();
        return playersList;
    }

    // Чтение непустой строки
    private String readNonEmptyLine(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                return line;
            }
        }
        return null;
    }

    // Безопасное преобразование строки в число
    private int parseSafeInt(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования числа: '" + str + "', используется 0");
            return 0;
        }
    }
}