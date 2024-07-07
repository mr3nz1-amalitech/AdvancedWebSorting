package com.app.AdvancedWebSorting.services;

import com.app.AdvancedWebSorting.daos.interfaces.MarkDao;
import com.app.AdvancedWebSorting.models.Mark;
import com.app.AdvancedWebSorting.util.Sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarkService {
    private MarkDao markDao;

    @Autowired
    public MarkService(MarkDao markDao) {
        this.markDao = markDao;
    }

    public Mark addMark(Mark mark) {
        return markDao.insertMark(mark);
    }

    public List<Mark> getAllMarks() {
        return markDao.selectAllMarks();
    }

    public Mark[] getMarks(String method) {
        Mark[] arr = markDao.selectAllMarks().toArray(new Mark[0]);

        System.out.println("Method: " + method);

        if (method.equals("mergeSort")) {
            Sorting.mergeSort(arr, 0, arr.length - 1);
            return arr;
        } else if (method.equals("quickSort")) {
            Sorting.quickSort(arr, 0, arr.length - 1);
            return arr;
        }

        return arr;
    }

    public Mark updateMark(UUID id, Mark mark) {
        return markDao.updateMark(id, mark);
    }

    public void deleteMark(UUID id) {
        markDao.deleteMark(id);
    }

    public Mark getMark(UUID id) {
        return markDao.getMarkById(id);
    }
}
