package services.member;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HomeServiceTest {

    @Test
    public void testInput() {
        String str = "good";
        assertThat(str, allOf(equalTo("good"), startsWith("goo")));
    }
}
