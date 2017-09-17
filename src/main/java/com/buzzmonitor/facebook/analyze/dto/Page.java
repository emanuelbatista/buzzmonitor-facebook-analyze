
package com.buzzmonitor.facebook.analyze.dto;

/**
 * Paging returned by Graph API
 * 
 * @author emanuel
 *
 */
public class Page {

    private Cursor cursors;
    private String next;
    private String previous;

    public Cursor getCursors() {
        return cursors;
    }

    public void setCursors(Cursor cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

}
