package client;

public class Player {
    private int playerNr;
    private String name;
    private String password;
    private boolean singlePlayerMode;
    private ISeaBattleGUI application;
    private Grid grid;
    private boolean isReady = false;

    public Player(int playerNr, String name, String password, boolean singlePlayerMode, ISeaBattleGUI application) {
        this.playerNr = playerNr;
        this.name = name;
        this.password = password;
        this.singlePlayerMode = singlePlayerMode;
        this.application = application;
        this.grid = new Grid(new Fleet());
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSinglePlayerMode() {
        return singlePlayerMode;
    }

    public void setSinglePlayerMode(boolean singlePlayerMode) {
        this.singlePlayerMode = singlePlayerMode;
    }

    public ISeaBattleGUI getApplication() {
        return application;
    }

    public void setApplication(ISeaBattleGUI application) {
        this.application = application;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
