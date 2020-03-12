package com.haulmont.testtask.view.core;

public class Column {
    private String id;
    private String caption;
    private boolean isHidden;
    private Class<?> type;

    public Column(String id, String caption, boolean isHidden) {
        this(id, caption, isHidden, null);
    }

    public Column(String id, String caption, boolean isHidden, Class<?> type) {
        this.id = id;
        this.caption = caption;
        this.isHidden = isHidden;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public Class<?> getType() {
        return type;
    }
}