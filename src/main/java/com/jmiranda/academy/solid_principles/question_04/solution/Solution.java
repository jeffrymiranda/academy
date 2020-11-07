package com.jmiranda.academy.solid_principles.question_04.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * - What do you think is bad here?
 * <p>
 * Both {@link DataSourceReader} and {@link DataSourceWriter} had been forced to depend on methods that it doesn't use.
 * So it is possible in their code for a programmer to make a mistake and call one of the other methods. So for instance,
 * the {@link DataSourceReader} class could accidentally write a record or, the {@link DataSourceWriter} class could accidentally read a record.
 * <p>
 * - Based on SOLID principle, what principle was broken?
 * <p>
 * Interface Segregation Principle.
 * Characteristics:
 * - Reduces side effects of calling unimplemented code
 * - It results in a less coupled system
 * - It makes code easier to refactor
 * - It results in more interfaces, each with fewer methods
 * <p>
 * - What can be a better solution?
 * <p>
 * The idea here is to segregate the operations on interfaces to only what calling code requires.
 * <p>
 * Advantages:
 * - It improves code readability
 * - Avoids coupling of classes by making them dependent on the same bloated interfaces.
 */
public class Solution {

    public static void main(String[] args) {
        List<Record> records = new ArrayList<>();
        records.add(new Record(3, "Margot"));

        DataSourceWriter.writeRecords(new DataSource(), records);

        DataSourceReader.readRecords(new DataSource(), Arrays.asList(1, 2, 3))
                .forEach(System.out::println);
    }
}

class Record {
    private final int Id;
    private final String name;

    public Record(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Record{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}

@FunctionalInterface
interface IDataSourceReader {
    Record readRecord(final int id);
}

@FunctionalInterface
interface IDataSourceWriter {
    void writeRecord(Record record);
}

class DataSource implements IDataSourceReader, IDataSourceWriter {

    private static final List<Record> records = new ArrayList<>();

    static {
        records.add(new Record(1, "Jeff"));
        records.add(new Record(2, "Luca"));
    }

    @Override
    public Record readRecord(final int id) {
        return records
                .stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void writeRecord(Record record) {
        records.add(record);
    }
}

class DataSourceReader {

    static List<Record> readRecords(IDataSourceReader iDataSource, List<Integer> ids) {
        List<Record> records = new ArrayList<>();
        ids.forEach(id -> records.add(iDataSource.readRecord(id)));
        return records;
    }
}

class DataSourceWriter {
    static void writeRecords(IDataSourceWriter iDataSource, List<Record> records) {
        records.forEach(iDataSource::writeRecord);
    }
}
