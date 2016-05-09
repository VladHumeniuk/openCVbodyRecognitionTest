package humeniuk.opencv.model;

import io.realm.RealmObject;

public class Training extends RealmObject {

    private String id;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
