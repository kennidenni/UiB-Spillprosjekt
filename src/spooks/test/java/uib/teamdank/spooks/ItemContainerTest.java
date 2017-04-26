package uib.teamdank.spooks;

import org.junit.Before;
import org.junit.Test;
import uib.teamdank.common.Item;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by oml35 on 25-Apr-17.
 */
public class ItemContainerTest {
    Item item;
    ItemContainer i;

    @Before
    public void setUp() throws Exception {
        i = new ItemContainer("Shelf", "Store objects");
        item = new Item ("Sturle", "What a lad");
        i.getInventory().addItem(item);
    }

    @Test
    public void getInventory() throws Exception {
        int index = i.getInventory().getItemCount() -1;
        assertThat(item, is(equalTo(i.getInventory().getItem(index))));
    }

}