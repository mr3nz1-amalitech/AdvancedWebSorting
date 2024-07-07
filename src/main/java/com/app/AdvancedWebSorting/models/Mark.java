package com.app.AdvancedWebSorting.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class Mark extends RepresentationModel<Mark> {
    private final UUID id;
    private final int mark;

    public Mark(@JsonProperty("id") UUID id, @JsonProperty("mark") int mark) {
        this.id = id;
        this.mark = mark;
    }

    public UUID getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }
}
