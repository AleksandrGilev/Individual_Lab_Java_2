import java.util.*;
import java.util.stream.Collectors;

public class ChessAnalyzer {
    private List<ChessPlayer> players;

    public ChessAnalyzer(List<ChessPlayer> players) {
        this.players = players;
    }

    // Количество и доля женщин
    public Map<String, Object> getFemaleStatistics() {
        long femaleCount = players.stream()
                .filter(ChessPlayer::isFemale)
                .count();

        double femalePercentage = (double) femaleCount / players.size() * 100;

        Map<String, Object> stats = new HashMap<>();
        stats.put("count", femaleCount);
        stats.put("percentage", femalePercentage);

        return stats;
    }

    // Лучший игрок среди женщин (по рейтингу - чем меньше число, тем лучше)
    public ChessPlayer getBestFemalePlayer() {
        return players.stream()
                .filter(ChessPlayer::isFemale)
                .min(Comparator.comparingInt(ChessPlayer::getRating))
                .orElse(null);
    }

    // Игроки, отличающиеся по возрасту не более чем на N лет
    public List<ChessPlayer> getPlayersSimilarAge(ChessPlayer referencePlayer, int maxAgeDifference) {
        int referenceBirthYear = referencePlayer.getBirthYear();

        return players.stream()
                .filter(p -> Math.abs(p.getBirthYear() - referenceBirthYear) <= maxAgeDifference)
                .sorted(Comparator.comparing(ChessPlayer::getCountry)
                        .thenComparing(ChessPlayer::getLastName))
                .collect(Collectors.toList());
    }

    // Таблица по странам и годам рождения
    public Map<String, Map<Integer, Integer>> getCountryBirthYearTable() {
        Map<String, Map<Integer, Integer>> table = new TreeMap<>();

        for (ChessPlayer player : players) {
            String country = player.getCountry();
            int birthYear = player.getBirthYear();

            table.putIfAbsent(country, new TreeMap<>());
            Map<Integer, Integer> yearMap = table.get(country);
            yearMap.put(birthYear, yearMap.getOrDefault(birthYear, 0) + 1);
        }

        return table;
    }

    // 10 самых молодых игроков, отсортированных по рейтингу (по убыванию рейтинга)
    public List<ChessPlayer> getTenYoungestByRating() {
        // Сначала сортируем по году рождения (по убыванию - самые молодые),
        // затем по рейтингу (по убыванию - самые высокие рейтинги первыми)
        return players.stream()
                .sorted(Comparator.comparingInt(ChessPlayer::getBirthYear).reversed()
                        .thenComparingInt(ChessPlayer::getStandardRating).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<ChessPlayer> getAllPlayers() {
        return new ArrayList<>(players);
    }
}