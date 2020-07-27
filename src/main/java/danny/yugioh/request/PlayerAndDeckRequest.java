package danny.yugioh.request;

public class PlayerAndDeckRequest {

    // 輸入要改的對象跟卡包
    private int playerIdtarget;
    private String olddeckname;
    // 輸入修改後的對象跟卡包
    private int playerId;
    private String newdeckname;

    public int getPlayerIdtarget() {
        return playerIdtarget;
    }

    public void setPlayerIdtarget(int playerIdtarget) {
        this.playerIdtarget = playerIdtarget;
    }

    public String getOlddeckname() {
        return olddeckname;
    }

    public void setOlddeckname(String olddeckname) {
        this.olddeckname = olddeckname;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getNewdeckname() {
        return newdeckname;
    }

    public void setNewdeckname(String newdeckname) {
        this.newdeckname = newdeckname;
    }
}
