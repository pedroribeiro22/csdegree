package utils;

import java.nio.charset.StandardCharsets;

public class SimpleMessage {

    public String topic;
    public String message;

    public SimpleMessage(final String topic, final String message) {
        this.topic = topic;
        this.message = message;
    }

    public SimpleMessage(final String topic, final int message) {
        this.topic = topic;
        this.message = String.valueOf(message);
    }

    public SimpleMessage(final byte[] bytes) {
        String[] data = new String(bytes, StandardCharsets.UTF_8).split(" ");
        this.topic = data[0];
        this.message = data[1];
    }

    public boolean isElection() {
        return this.topic.equals("election");
    }

    public boolean isState() {
        return this.topic.equals("state");
    }

    public int value() {
        return Integer.parseInt(this.message);
    }

    @Override
    public String toString() {
        return this.topic + " " + this.message;
    }

    public byte[] toBytes() {
        return this.toString().getBytes(StandardCharsets.UTF_8);
    }
}
