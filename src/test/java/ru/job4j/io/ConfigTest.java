package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndBlank() {
        String path = "./data/pair_with_comment_and_blank.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("lang")).isEqualTo("ja=va");
    }

    @Test
    void whenPairWithBlank() {
        String path = "./data/pair_with_blank.properties";
        Config config = new Config(path);
        config.load();
        assertThatException().isThrownBy(() -> config.value(""))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void whenPairWithTemplateNoKeyAndValue() {
        String path = "./data/pair_with_template_no_key_and_value.properties";
        Config config = new Config(path);
        assertThatIllegalArgumentException().isThrownBy(config::load);
    }

    @Test
    void whenPairWithTemplateNoKeye() {
        String path = "./data/pair_with_template_no_key.properties";
        Config config = new Config(path);
        assertThatIllegalArgumentException().isThrownBy(config::load);
    }

    @Test
    void whenPairWithTemplateNoValue() {
        String path = "./data/pair_with_template_no_value.properties";
        Config config = new Config(path);
        assertThatIllegalArgumentException().isThrownBy(config::load);
    }


}