import java.io.*;
import java.util.*;

public class ChessDataLoader {

    public List<ChessPlayer> loadPlayers(String filename) throws IOException {
        List<ChessPlayer> playersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> validLines = new ArrayList<>();
            String line;

            // Читаем все непустые строки
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    validLines.add(trimmed);
                }
            }

            // Обрабатываем данные блоками по 8 строк
            for (int i = 0; i < validLines.size(); i += 8) {
                if (i + 7 >= validLines.size()) {
                    System.err.println("Предупреждение: Неполные данные для игрока, пропускаем...");
                    break;
                }

                try {
                    String ratingDate = validLines.get(i);
                    int rating = parseInt(validLines.get(i + 1));

                    // Разделение фамилии и имени
                    String[] nameParts = validLines.get(i + 2).split(", ");
                    String lastName = nameParts[0];
                    String firstName = nameParts.length > 1 ? nameParts[1] : "";

                    String title = validLines.get(i + 3);
                    String country = validLines.get(i + 4);
                    int standardRating = parseInt(validLines.get(i + 5));
                    int gamesPlayed = parseInt(validLines.get(i + 6));
                    int birthYear = parseInt(validLines.get(i + 7));

                    playersList.add(new ChessPlayer(ratingDate, rating, lastName, firstName,
                            title, country, standardRating, gamesPlayed, birthYear));

                } catch (Exception e) {
                    System.err.println("Ошибка при обработке игрока: " + e.getMessage());
                }
            }
        }

        if (playersList.isEmpty()) {
            System.out.println("Внимание: не загружено ни одного игрока!");
        }

        return playersList;
    }

    private int parseInt(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования числа: '" + str + "', используется 0");
            return 0;
        }
    }
}