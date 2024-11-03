package exclusionmutuaimpl;

public class Message {
    private int senderId;
    private int timestamp;
    private String type;

    public Message(int senderId, int timestamp, String type) {
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.type = type;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}
