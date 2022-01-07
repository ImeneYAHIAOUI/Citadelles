package fr.unice.polytech.startingpoint.player.IA;

import fr.unice.polytech.startingpoint.player.IA.BOTs.BuilderBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderBotTest {

    BuilderBot builderBot;

    @BeforeEach
    void setUp(){
        this.builderBot = new BuilderBot("Builder bot");
    }

    @Test
    void testStrategyBot(){
        assertEquals(this.builderBot.strategyBot,StrategyBot.BUILDER_BOT);
        assertNotEquals(this.builderBot.strategyBot, StrategyBot.NEUTRAL_BOT);
        assertNotEquals(this.builderBot.strategyBot, StrategyBot.NASTY_BOT);
        assertNotEquals(this.builderBot.strategyBot, StrategyBot.NICE_BOT);
    }
}