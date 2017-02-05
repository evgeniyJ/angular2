package org.evgen.controller;

import org.evgen.model.Hero;
import org.evgen.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public List<Hero> getHeroes() {
        return heroService.getAll();
    }

    @GetMapping("{id}")
    public Hero getHeroes(@PathVariable int id) {
        return heroService.getById(id);
    }

    @PutMapping("{id}")
    public void updateHero(@RequestBody Hero hero) {
        heroService.update(hero);
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero hero) {
        return heroService.create(hero.getName());
    }

    @DeleteMapping("{id}")
    public void deleteHero(@PathVariable int id) {
        heroService.delete(id);
    }

    @GetMapping(value = "search", params = {"name"})
    public List<Hero> search(@RequestParam String name) {
        return heroService.searchByName(name);
    }
}
