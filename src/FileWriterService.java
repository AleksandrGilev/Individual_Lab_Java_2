import java.io.*;
import java.util.List;
import java.util.Map;

public class FileWriterService {

    public void writeSimilarAgePlayers(List<ChessPlayer> players, ChessPlayer referencePlayer,
                                       String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write("Игроки, отличающиеся по возрасту не более чем на 5 лет от "
                + referencePlayer.getFullName() + " (г.р. " + referencePlayer.getBirthYear() + "):\n");
        writer.write("=".repeat(80) + "\n");

        int count = 0;
        for (ChessPlayer player : players) {
            if (!player.equals(referencePlayer)) {
                writer.write(String.format("%d. %s %s, %s, %d г.р., стандартный рейтинг: %d\n",
                        ++count, player.getFirstName(), player.getLastName(), player.getCountry(),
                        player.getBirthYear(), player.getStandardRating()));
            }
        }

        if (count == 0) {
            writer.write("Нет игроков с разницей в возрасте до 5 лет (кроме самой игроки).\n");
        }

        writer.close();
    }

    public void writeCountryBirthYearTable(Map<String, Map<Integer, Integer>> table,
                                           String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write("Таблица распределения игроков по странам и годам рождения:\n");
        writer.write("=".repeat(80) + "\n\n");

        int totalPlayers = 0;
        for (Map.Entry<String, Map<Integer, Integer>> countryEntry : table.entrySet()) {
            writer.write(countryEntry.getKey() + ":\n");

            int countryTotal = 0;
            for (Map.Entry<Integer, Integer> yearEntry : countryEntry.getValue().entrySet()) {
                writer.write(String.format("  %d год: %d игрок(ов)\n",
                        yearEntry.getKey(), yearEntry.getValue()));
                countryTotal += yearEntry.getValue();
            }
            writer.write(String.format("  Всего по стране: %d игрок(ов)\n\n", countryTotal));
            totalPlayers += countryTotal;
        }

        writer.write(String.format("ИТОГО: %d игроков\n", totalPlayers));
        writer.close();
    }
}
