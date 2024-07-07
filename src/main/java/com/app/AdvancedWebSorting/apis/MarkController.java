package com.app.AdvancedWebSorting.apis;

import com.app.AdvancedWebSorting.models.Mark;
import com.app.AdvancedWebSorting.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/marks")
@RestController
public class MarkController {
    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public Mark addMark(@RequestBody Mark mark) {
        return markService.addMark(mark);
    }

    @GetMapping
    public List<Mark> getAllMarks() {
        return markService.getAllMarks();
    }

    @GetMapping("/method/{method}")
    public Mark[] getMarks(@RequestParam String method) {
        return markService.getMarks(method);
    }

    @PutMapping("/{id}")
    public Mark updateMark(@PathVariable UUID id, @RequestBody Mark mark) {
        return markService.updateMark(id, mark);
    }


    @DeleteMapping("/{id}")
    public Class<?> deleteMark(@PathVariable UUID id) {
        markService.deleteMark(id);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Mark>> getMark(@PathVariable UUID id) {
        Mark mark = markService.getMark(id);

        if (mark != null) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarkController.class).getMark(mark.getId())).withSelfRel();
            Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarkController.class).updateMark(mark.getId(), mark)).withRel("update");

            Link getAllLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarkController.class).getAllMarks()).withRel("getAll");
            Link addLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarkController.class).addMark(mark)).withRel("add");

            mark.add(selfLink, updateLink, getAllLink, addLink);

            return new ResponseEntity<>(EntityModel.of(mark), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}