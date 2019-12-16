package by.dziamidka.service;

import java.util.List;

public interface MigrationService<T> {
    int Migrate(List<T> data);
}
