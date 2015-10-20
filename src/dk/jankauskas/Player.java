package dk.jankauskas;

public class Player {

    private Mark playerSymbol;

    public Player(Mark playerSymbol) {
        this.playerSymbol = playerSymbol;
        System.out.println("Player: " + playerSymbol);
    }

    public Mark getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(Mark playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

}
