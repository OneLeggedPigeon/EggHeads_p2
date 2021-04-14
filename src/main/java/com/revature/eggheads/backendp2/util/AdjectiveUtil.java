package com.revature.eggheads.backendp2.util;

import java.util.Arrays;
import java.util.List;

public class AdjectiveUtil {
    public static String generateAdjective(String adjectiveType) {

        // TODO: Get this from the API using adjectiveType
        // https://www.datamuse.com/api/
        final List<String> adjectives = Arrays.asList(
                "rapturous",
                "turgid",
                "ecumenical",
                "sorrowful",
                "pained",
                "rapacious",
                "sesquipedalian",
                "adroit",
                "wretched",
                "parental",
                "superfluous",
                "agog",
                "tenebrous",
                "recalcitrant",
                "parsimonious",
                "dilapidated",
                "diplomatic",
                "exothermic",
                "hissing",
                "enraged",
                "valiant",
                "perturbed",
                "listless",
                "vibrating",
                "endothermic",
                "unkempt",
                "constantly pacing",
                "twitchy",
                "ungainly",
                "gainly",
                "handsome",
                "ghoulish",
                "gruntled",
                "disgruntled",
                "japish",
                "overly concerned with how it is perceived by others",
                "vain",
                "vainglorious",
                "introverted",
                "pained",
                "terrified",
                "cowardly",
                "brave",
                "constantly squawking",
                "blood-drenched",
                "rusty",
                "gender non-conforming",
                "swollen",
                "visibly armed",
                "hulking",
                "rail-thin",
                "passionate",
                "constantly back flipping",
                "meticulous",
                "serious",
                "honey-glazed",
                "disguised",
                "tiny",
                "reckless",
                "repentant",
                "jovial",
                "aggressive",
                "code smelling",
                "fashionably dressed",
                "invisible",
                "murderous",
                "amicable",
                "pithy"
        ); // I may have had too much fun with this
        return RandomUtil.getRandomString(adjectives);
    }
}
