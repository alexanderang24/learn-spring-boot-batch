package com.doku.remittance.spikespringbatch.step;

import com.doku.remittance.spikespringbatch.dto.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MenuItemProcessor implements ItemProcessor<Menu, Menu> {

    @Override
    public Menu process(Menu menu) {
        log.info("Processing " + menu);

        return Menu.builder()
                .nama1(menu.getNama1())
                .nama2(menu.getNama2())
                .build();
    }
}
