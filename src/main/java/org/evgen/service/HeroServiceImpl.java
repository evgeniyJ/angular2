package org.evgen.service;

import lombok.extern.slf4j.Slf4j;
import org.evgen.model.Hero;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HeroServiceImpl implements HeroService {

    private List<Hero> heroes = new ArrayList<>();

    {
        heroes.add(new Hero(1, "Mr. Nice"));
        heroes.add(new Hero(2, "Narco"));
        heroes.add(new Hero(3, "Bombasto"));
        heroes.add(new Hero(4, "Celeritas"));
        heroes.add(new Hero(5, "Magneta"));
        heroes.add(new Hero(6, "RubberMan"));
        heroes.add(new Hero(7, "Dynama"));
        heroes.add(new Hero(8, "Dr IQ"));
        heroes.add(new Hero(9, "Magma"));
        heroes.add(new Hero(10, "Tornado"));
    }

    @Override
    public List<Hero> getAll() {
        log.debug("Fetching all heroes");
        return heroes;
    }

    @Override
    public Hero getById(int id) {
        log.debug("Loading hero by id : {}", id);
        return heroes
                .stream()
                .filter(hero -> hero.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void update(Hero hero) {
        log.debug("Updating hero : {}", hero);
        heroes
                .stream()
                .filter(heroFromList -> heroFromList.getId() == hero.getId())
                .forEach((heroFromList) -> heroFromList.setName(hero.getName()));
    }

    @Override
    public Hero create(String name) {
        log.debug("Creating new hero with name : {}", name);
        synchronized (this) {
            Hero hero = new Hero(heroes.size() + 1, name);
            heroes.add(hero);
            return hero;
        }
    }

    @Override
    public void delete(int id) {
        log.debug("Deleting hero with id : {}", id);
        heroes.removeIf(hero -> hero.getId() == id);
    }

    @Override
    public List<Hero> searchByName(String name) {
        log.debug("Fetching heroes, which names started with : {}", name);
        return heroes
                .stream()
                .filter(hero -> hero.getName().startsWith(name))
                .collect(Collectors.toList());
    }
}
