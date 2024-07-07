package com.app.AdvancedWebSorting.daos;

import com.app.AdvancedWebSorting.daos.interfaces.MarkDao;
import com.app.AdvancedWebSorting.models.Mark;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MarkImplemantation implements MarkDao {
    private static List<Mark> DB = new ArrayList<>();

    @Override
    public Mark insertMark(UUID id, Mark mark) {
        Mark newMark = new Mark(id, mark.getMark());
        DB.add(newMark);
        return newMark;
    }


    @Override
    public List<Mark> selectAllMarks() {
        return DB;
    }

    @Override
    public Mark updateMark(UUID id, Mark updatedMark) {
        List<Mark> filteredMarks = DB.stream()
                .filter(mark -> !mark.getId().equals(id))
                .collect(Collectors.toList());

        Mark newMark = new Mark(id, updatedMark.getMark());

        filteredMarks.add(newMark);

        DB = filteredMarks;

        return newMark;
    }

    @Override
    public void deleteMark(UUID id) {
        DB = DB.stream()
                .filter(mark -> !mark.getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Mark getMarkById(UUID id) {
        return DB.stream().filter(mark -> mark.getId().equals(id)).findFirst().orElse(null);
    }
}
