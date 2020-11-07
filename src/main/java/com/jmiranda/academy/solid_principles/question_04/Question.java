package com.jmiranda.academy.solid_principles.question_04;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Question {
    // What do you think is bad here?
    // Based on SOLID principle, what principle was broken?
    // What can be a better solution?
}

class Record {
    private int Id;
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface IDataSource {
    Record readRecord(int id);

    void writeRecord(Record record);
}

class DataSource implements IDataSource {

    private final List<Record> records = new ArrayList<>();

    @Override
    public Record readRecord(int id) {
        return this.records
                .stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void writeRecord(Record record) {
        this.records.add(record);
    }
}

class DataSourceReader {

    static List<Record> readRecords(IDataSource iDataSource, List<Integer> ids) {
        List<Record> records = new ArrayList<>();
        ids.forEach(id -> records.add(iDataSource.readRecord(id)));
        return records;
    }
}

class DataSourceWriter {
    static void writeRecords(IDataSource iDataSource, List<Record> records) {
        records.forEach(iDataSource::writeRecord);
    }
}
