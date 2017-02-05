package org.evgen.service;

import org.evgen.model.Hero;

import java.util.List;

public interface HeroService {

    List<Hero> getAll();

    Hero getById(int id);

    void update(Hero hero);

    Hero create(String name);

    void delete(int id);

    List<Hero> searchByName(String name);
}
