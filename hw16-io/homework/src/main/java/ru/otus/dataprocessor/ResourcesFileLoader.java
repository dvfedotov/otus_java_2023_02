package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Slf4j
public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private final Gson mapper = new Gson();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        Type measurementListType = new TypeToken<List<Measurement>>() {
        }.getType();
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(Measurement.class.getClassLoader().getResourceAsStream(fileName)),
                StandardCharsets.UTF_8)) {
            return mapper.fromJson(reader, measurementListType);
        } catch (IOException e) {
            log.error("Cannot parse json", e);
            return Collections.emptyList();
        }
    }
}
