package dk.jankauskas;

public class Player {

    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
        System.out.println("Player: " + playerSymbol);
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }
}
