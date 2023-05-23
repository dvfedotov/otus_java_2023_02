package ru.otus.model;

import lombok.ToString;

import java.util.List;

@ToString
public class ObjectForMessage implements Cloneable {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ObjectForMessage cloneObjectForMessage = (ObjectForMessage) super.clone();
        cloneObjectForMessage.setData(List.copyOf(this.getData()));
        return cloneObjectForMessage;
    }
}
