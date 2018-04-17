package asquero.com.myapplication;

/**
 * Created by Anmol on 05-Apr-18.
 */

class EventList {
    private String event;
    private String desc;
    private int image;

    public EventList(String event, String desc, int image) {
        this.event = event;
        this.desc = desc;
        this.image = image;
    }

    public String getEvent() {
        return event;
    }

    public int getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }
}
