import java.util.List;
import java.util.Map;

public class ConsolePrinter {

    public void printFemaleStatistics(Map<String, Object> stats) {
        long femaleCount = (long) stats.get("count");
        double femalePercentage = (double) stats.get("percentage");

        System.out.println("=== СТАТИСТИКА ПО ЖЕНЩИНАМ-ШАХМАТИСТАМ ===");
        System.out.printf("Количество женщин в списке: %d\n", femaleCount);
        System.out.printf("Доля женщин: %.2f%%\n\n", femalePercentage);
    }

    public void printBestFemaleInfo(ChessPlayer bestFemale) {
        if (bestFemale != null) {
            System.out.println("=== ЛУЧШАЯ ЖЕНЩИНА-ШАХМАТИСТ ===");
            System.out.printf("Имя: %s %s\n", bestFemale.getFirstName(), bestFemale.getLastName());
            System.out.printf("Место в рейтинге: %d\n", bestFemale.getRating());
            System.out.printf("Стандартный рейтинг: %d\n", bestFemale.getStandardRating());
            System.out.printf("Страна: %s\n", bestFemale.getCountry());
            System.out.printf("Год рождения: %d\n\n", bestFemale.getBirthYear());
        } else {
            System.out.println("В списке нет женщин-шахматисток.\n");
        }
    }

    public void printSimilarAgePlayers(List<ChessPlayer> players, ChessPlayer referencePlayer) {
        System.out.println("=== ИГРОКИ ПОХОЖЕГО ВОЗРАСТА (разница до 5 лет) ===");

        int count = 0;
        for (ChessPlayer player : players) {
            if (!player.equals(referencePlayer)) {
                System.out.printf("%d. %s %s (%s, %d г.р., рейтинг: %d)\n",
                        ++count, player.getFirstName(), player.getLastName(),
                        player.getCountry(), player.getBirthYear(), player.getStandardRating());
            }
        }

        if (count == 0) {
            System.out.println("Нет игроков с разницей в возрасте до 5 лет (кроме самой игроки).");
        }
        System.out.println();
    }

    public void printCountryBirthYearTable(Map<String, Map<Integer, Integer>> table) {
        System.out.println("=== ТАБЛИЦА ПО СТРАНАМ И ГОДАМ РОЖДЕНИЯ ===");

        int totalPlayers = 0;
        for (Map.Entry<String, Map<Integer, Integer>> countryEntry : table.entrySet()) {
            System.out.println("\n" + countryEntry.getKey() + ":");

            int countryTotal = 0;
            for (Map.Entry<Integer, Integer> yearEntry : countryEntry.getValue().entrySet()) {
                System.out.printf("  %d год: %d игрок(ов)\n",
                        yearEntry.getKey(), yearEntry.getValue());
                countryTotal += yearEntry.getValue();
            }
            System.out.printf("  Всего по стране: %d игрок(ов)\n", countryTotal);
            totalPlayers += countryTotal;
        }

        System.out.printf("\nИТОГО: %d игроков\n\n", totalPlayers);
    }

    public void printTenYoungestPlayers(List<ChessPlayer> youngestPlayers) {
        System.out.println("=== 10 САМЫХ МОЛОДЫХ ИГРОКОВ (сортировка по рейтингу) ===");
        System.out.println("Порядок вывода: имя, фамилия, стандартный рейтинг, год рождения, страна");
        System.out.println("-".repeat(80));

        int rank = 1;
        for (ChessPlayer player : youngestPlayers) {
            System.out.printf("%2d. %s %s - рейтинг: %d, год рождения: %d, страна: %s\n",
                    rank++, player.getFirstName(), player.getLastName(),
                    player.getStandardRating(), player.getBirthYear(), player.getCountry());
        }
        System.out.println();
    }
}