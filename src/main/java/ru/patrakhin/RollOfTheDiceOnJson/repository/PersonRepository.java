package ru.patrakhin.RollOfTheDiceOnJson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.RollOfTheDiceOnJson.model.People;

@Repository
public interface PersonRepository extends JpaRepository<People, Integer> {
    // Дополнительные методы репозитория, если необходимо
}