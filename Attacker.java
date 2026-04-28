public class Attacker extends FootballPlayer {
    private int goals;
    private int assists;

    public Attacker(String name, String club, int matchesPlayed, int goals, int assists, String imageFileName) {
        super(name, club, matchesPlayed, imageFileName);
        this.goals = goals;
        this.assists = assists;
    }

    @Override
    public String getStats() {
        return "Role          : ATTACKER\n" +
               "Player Name   : " + name + "\n" +
               "Club          : " + club + "\n" +
               "Matches       : " + matchesPlayed + "\n" +
               "Goals Scored  : " + goals + "\n" +
               "Assists       : " + assists + "\n";
    }
}