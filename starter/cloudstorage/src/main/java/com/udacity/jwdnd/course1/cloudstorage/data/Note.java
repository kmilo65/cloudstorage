package com.udacity.jwdnd.course1.cloudstorage.data;

public class Note {

    private Long noteId;
    private String noteTitle;
    private String noteDescription;
    private Long userId;


    public Note() {}

    public Note(Long noteid, String notetitle, String notedescription, Long userid) {
        this.noteId = noteid;
        this.noteTitle = notetitle;
        this.noteDescription = notedescription;
        this.userId = userid;
    }

    public Long getNoteid() {
        return noteId;
    }

    public String getNotetitle() {
        return noteTitle;
    }

    public String getNotedescription() { return noteDescription; }

    public Long getUserid(Long userId) {
        return userId;
    }

    public void setNoteid(Long noteId) {
        this.noteId = noteId;
    }

    public void setNotetitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNotedescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setUserid(Long userId) {
        this.userId = userId;
    }
}
