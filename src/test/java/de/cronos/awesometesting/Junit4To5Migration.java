package de.cronos.awesometesting;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(SpringJUnit4ClassRunner.class)
//@Category("migration")
@ExtendWith(SpringExtension.class)
@Tag("migration")
public class Junit4To5Migration {


//    @Test(expected = Exception.class)
//    public void shouldRaiseAnException()  {
        // ...
//     }

    @Test
    public void shouldRaiseAnException() {
        assertThrows(Exception.class, () -> {
            throw new Exception();
        });
    }
}
