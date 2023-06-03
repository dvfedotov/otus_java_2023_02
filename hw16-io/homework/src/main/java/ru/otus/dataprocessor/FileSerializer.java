package ru.otus.dataprocessor;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class FileSerializer implements Serializer {

    private final String fileName;
    private final Gson mapper = new Gson();

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            mapper.toJson(data, fileWriter);
        } catch (IOException e) {
            log.error("Cannot save json", e);
            throw new FileProcessException(e);
        }
    }
}
