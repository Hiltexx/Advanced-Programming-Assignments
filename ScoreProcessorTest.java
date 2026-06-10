package com.onboarding;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreProcessorTest {

    @Test
    void testValidScoreFile()
            throws FileNotFoundException {

        ScoreProcessor processor =
                new ScoreProcessor();

        int result =
                processor.processScoreFile(
                        "valid.txt"
                );

        assertEquals(500, result);
    }

    @Test
    void testMissingFileThrows() {

        ScoreProcessor processor =
                new ScoreProcessor();

        assertThrows(
                FileNotFoundException.class,
                () -> processor.processScoreFile(
                        "missing.txt"
                )
        );
    }
}
