package com.endava.model;

import javax.persistence.*;

/**
 * Created by Dumitru Cadea on 03.01.17.
 */
@Entity
@Table(name = "T_FILE")
public class TFile {
    private Long id;
    private String filename;
    private String content;

    public TFile() {
        // Empty constructor
    }

    public TFile(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TFile{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
