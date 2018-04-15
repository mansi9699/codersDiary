package asquero.com.myapplication;

/**
 * Created by Anmol on 05-Apr-18.
 */

class EventList {
    private String event;
    private int image;
    private String[] items;

    public EventList(String event, int image, String[] items) {
        this.event = event;
        this.image = image;
        this.items = items;
    }

    public EventList(String event, int image) {
        this.event = event;
        this.image = image;

    }

    public String getEvent() {
        return event;
    }

    public int getImage() {
        return image;
    }

    public String[] getItems() {
        return items;
    }
}
