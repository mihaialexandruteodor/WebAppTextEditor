package com.mihaialexandruteodor.FeatherWriter.model;

import javax.persistence.*;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "novel")
public class Novel {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int novelID;

    @Column(name = "title")
    private String title;

    @Column(name = "authorname")
    private String authorName;

    @Column(name = "draftnumber")
    private int draftNumber;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "authorcontactinfo")
    private String authorContactInfo;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FWCharacter> characters;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToOne
    @JoinColumn(name = "corkboardID")
    @RestResource(path = "novelCorkboard", rel="corkboard")
    private Corkboard corkboard;

    public int getNovelID() {
        return novelID;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public void setNovelID(int novelID) {
        this.novelID = novelID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(int draftNumber) {
        this.draftNumber = draftNumber;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAuthorContactInfo() {
        return authorContactInfo;
    }

    public void setAuthorContactInfo(String authorContactInfo) {
        this.authorContactInfo = authorContactInfo;
    }

    public List<Location> getLocations() {return locations;}

    public void setLocations(List<Location> locations) {this.locations = locations;}

    public List<Chapter> getChapters() {return chapters;}

    public void setChapters(List<Chapter> chapters) {this.chapters = chapters;}

    public List<FWCharacter> getCharacters() {return characters;}

    public void setCharacters(List<FWCharacter> characters) {this.characters = characters;}

    public List<Note> getNotes() {return notes;}

    public void setNotes(List<Note> notes) {this.notes = notes;}

    public Corkboard getCorkboard() {return corkboard;}

    public void setCorkboard(Corkboard corkboard) {this.corkboard = corkboard;}
}
