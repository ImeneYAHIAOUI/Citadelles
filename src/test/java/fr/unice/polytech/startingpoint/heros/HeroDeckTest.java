package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.heros.character.Assassin;
import fr.unice.polytech.startingpoint.heros.character.King;
import fr.unice.polytech.startingpoint.heros.character.Magician;
import fr.unice.polytech.startingpoint.heros.character.Merchant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroDeckTest {
    HeroDeck heroes = null;

    @BeforeEach
    void setUp(){
        this.heroes = new HeroDeck();
        heroes.add(new King());
        heroes.add(new Merchant());
        heroes.add(new Assassin());
        heroes.add(new Magician());
    }

    @Test
    void testSize(){
        assertEquals(4,this.heroes.size());
    }

    @Test
    void testChoose1Hero(){
        IHero hero = null;
        hero = this.heroes.chooseHero(HeroName.Magician);

        assertNotNull(hero);
        assertEquals(3,this.heroes.size());
        assertEquals(HeroName.Magician,hero.getName());
    }

    @Test
    void testChooseAHeroAlreadyChosen(){
        IHero hero = null;
        this.heroes.chooseHero(HeroName.Magician);
        hero = this.heroes.chooseHero(HeroName.Magician);

        assertEquals(3, this.heroes.size());
        assertNull(hero);
    }

    @Test
    void testRandomChoice(){
        IHero hero = null;
        hero = this.heroes.randomChoice();
        assertNotNull(hero);
        assertEquals(3,this.heroes.size());
    }

    @Test
    void testRandomChoiceWhitNoHero(){
        IHero hero = null;

        hero = this.heroes.randomChoice();
        assertNotNull(hero);
        assertEquals(3,this.heroes.size());
        hero = this.heroes.randomChoice();
        assertNotNull(hero);
        assertEquals(2,this.heroes.size());
        hero = this.heroes.randomChoice();
        assertNotNull(hero);
        assertEquals(1,this.heroes.size());
        hero = this.heroes.randomChoice();
        assertNotNull(hero);
        assertEquals(0,this.heroes.size());

        // No Hero
        hero = this.heroes.randomChoice();
        assertEquals(0,this.heroes.size());
        assertNull(hero);
    }
}