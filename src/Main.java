import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("==========================================");
            System.out.println("АНАЛИЗ ДАННЫХ ШАХМАТНЫХ ИГРОКОВ");
            System.out.println("==========================================\n");

            // 1. Загрузка данных
            ChessDataLoader loader = new ChessDataLoader();
            List<ChessPlayer> players = loader.loadPlayers("data_chess_player.txt");
            System.out.printf("Загружено %d игроков\n\n", players.size());

            // 2. Создание анализатора
            ChessAnalyzer analyzer = new ChessAnalyzer(players);

            // 3. Создание сервисов для вывода
            ConsolePrinter printer = new ConsolePrinter();
            FileWriterService fileWriter = new FileWriterService();

            // 4. Статистика по женщинам
            Map<String, Object> femaleStats = analyzer.getFemaleStatistics();
            printer.printFemaleStatistics(femaleStats);

            // 5. Лучшая женщина-шахматист
            ChessPlayer bestFemale = analyzer.getBestFemalePlayer();
            printer.printBestFemaleInfo(bestFemale);

            if (bestFemale != null) {
                // 6. Игроки похожего возраста
                List<ChessPlayer> similarAgePlayers = analyzer.getPlayersSimilarAge(bestFemale, 5);
                printer.printSimilarAgePlayers(similarAgePlayers, bestFemale);

                // 7. Запись в файл игроков похожего возраста
                fileWriter.writeSimilarAgePlayers(similarAgePlayers, bestFemale,
                        "similar_age_players.txt");
                System.out.println("Список игроков похожего возраста сохранен в файл: similar_age_players.txt\n");
            }

            // 8. Таблица по странам и годам рождения
            Map<String, Map<Integer, Integer>> table = analyzer.getCountryBirthYearTable();
            printer.printCountryBirthYearTable(table);

            // 9. Запись таблицы в файл
            fileWriter.writeCountryBirthYearTable(table, "country_birthyear_table.txt");
            System.out.println("Таблица по странам и годам рождения сохранена в файл: country_birthyear_table.txt\n");

            // 10. 10 самых молодых игроков
            List<ChessPlayer> youngestPlayers = analyzer.getTenYoungestByRating();
            printer.printTenYoungestPlayers(youngestPlayers);

            System.out.println("==========================================");
            System.out.println("АНАЛИЗ ЗАВЕРШЕН УСПЕШНО!");
            System.out.println("==========================================");

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}