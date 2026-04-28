public class Defender extends FootballPlayer {
    private int tackles;
    private int cleanSheets;

    public Defender(String name, String club, int matchesPlayed, int tackles, int cleanSheets, String imageFileName) {
        super(name, club, matchesPlayed, imageFileName);
        this.tackles = tackles;
        this.cleanSheets = cleanSheets;
    }

    @Override
    public String getStats() {
        return "Role          : DEFENDER\n" +
               "Player Name   : " + name + "\n" +
               "Club          : " + club + "\n" +
               "Matches       : " + matchesPlayed + "\n" +
               "Total Tackles : " + tackles + "\n" +
               "Clean Sheets  : " + cleanSheets + "\n";
    }
}