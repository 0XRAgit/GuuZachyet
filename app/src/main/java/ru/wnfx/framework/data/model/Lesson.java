package ru.wnfx.framework.data.model;

public class Lesson {
    private String name;
    private String time;
    private String desct;
    private String teacher;
    private String aud;
    private String id;

    public Lesson(String name, String time, String desct, String teacher, String aud, String id) {
        this.name = name;
        this.time = time;
        this.desct = desct;
        this.teacher = teacher;
        this.aud = aud;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesct() {
        return desct;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getAud() {
        return aud;
    }

    public String getId() {
        return id;
    }
}
