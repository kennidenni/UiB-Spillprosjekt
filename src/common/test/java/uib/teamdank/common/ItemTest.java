import org.junit.Before;
import org.junit.Test;
import uib.teamdank.common.Item;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Testing class for Item
 *
 * @author oli007
 */
public class ItemTest {
    private String name;
    private String desc;
    private Item item;

    @Before
    public void setup(){
        name = "Ost";
        desc = "En ost";
        item = new Item(name, desc);
    }

    @Test
    public void testGetDescription() throws Exception {
        assertThat(desc, is(equalTo(item.getDescription())));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(name, is(equalTo(item.getName())));
    }

}