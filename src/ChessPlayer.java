import java.util.Objects;

public class ChessPlayer {
    private String ratingDate;
    private int rating;
    private String lastName;
    private String firstName;
    private String title;
    private String country;
    private int standardRating;
    private int gamesPlayed;
    private int birthYear;

    public ChessPlayer(String ratingDate, int rating, String lastName, String firstName,
                       String title, String country, int standardRating, int gamesPlayed, int birthYear) {
        this.ratingDate = ratingDate;
        this.rating = rating;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.country = country;
        this.standardRating = standardRating;
        this.gamesPlayed = gamesPlayed;
        this.birthYear = birthYear;
    }

    // Геттеры
    public String getRatingDate() { return ratingDate; }
    public int getRating() { return rating; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getTitle() { return title; }
    public String getCountry() { return country; }
    public int getStandardRating() { return standardRating; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getBirthYear() { return birthYear; }
    public String getFullName() { return firstName + " " + lastName; }

    public boolean isFemale() {
        return "wg".equals(title);
    }

    public int getAge(int currentYear) {
        return currentYear - birthYear;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + country + ", " + birthYear + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChessPlayer that = (ChessPlayer) obj;
        return rating == that.rating &&
                birthYear == that.birthYear &&
                ratingDate.equals(that.ratingDate) &&
                lastName.equals(that.lastName) &&
                firstName.equals(that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingDate, rating, lastName, firstName, birthYear);
    }
}