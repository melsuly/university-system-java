package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import model.researcher.IResearcher;

public class ResearchPaper implements Serializable {
    private int citations;
    private String name;
    private Vector<IResearcher> authors;
    private String journal;
    private int pages;
    private Date publicationDate;
    private String doi;

    public ResearchPaper(int citations, String name, Vector<IResearcher> authors, String journal, int pages,
            Date publicationDate, String doi) {
        this.citations = citations;
        this.name = name;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    public int getCitations() {
        return citations;
    }

    public String getName() {
        return name;
    }

    public Vector<IResearcher> getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public int getPages() {
        return pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getDoi() {
        return doi;
    }

    public String getCitation(Format format) {
        switch (format) {
            case PLAIN_TEXT:
                return "";
            case BIBTEX:
                return "";
            default:
                return "";
        }
    }
}
