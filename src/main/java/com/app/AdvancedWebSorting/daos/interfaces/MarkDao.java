package com.app.AdvancedWebSorting.daos.interfaces;

import com.app.AdvancedWebSorting.models.Mark;

import java.util.List;
import java.util.UUID;

public interface MarkDao {
    Mark insertMark(UUID id, Mark mark);

    default Mark insertMark(Mark mark) {
        UUID id = UUID.randomUUID();
        System.out.println("UUID: " + id);
        return insertMark(id, mark);
    }

    List<Mark> selectAllMarks();

    Mark updateMark(UUID id, Mark updatedMark);

    void deleteMark(UUID id);

    Mark getMarkById(UUID id);
}
