// We make this class 'abstract' because a generic player shouldn't exist.
// They must be a specific role (Attacker or Defender).
public abstract class FootballPlayer {
    protected String name;
    protected String club;
    protected int matchesPlayed;
    protected String imageFileName; // Added to store the photo file name

    public FootballPlayer(String name, String club, int matchesPlayed, String imageFileName) {
        this.name = name;
        this.club = club;
        this.matchesPlayed = matchesPlayed;
        this.imageFileName = imageFileName;
    }

    // Abstract method: Every subclass MUST implement their own version of this
    public abstract String getStats();

    // Common getters
    public String getName() { return name; }
    public String getClub() { return club; }
    public String getImageFileName() { return imageFileName; }
}