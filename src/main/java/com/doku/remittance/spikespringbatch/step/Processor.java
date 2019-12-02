package com.doku.remittance.spikespringbatch.step;

import org.springframework.stereotype.Component;

@Component
public class Processor {

    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    public MenuItemProcessor processorMenu() {
        return new MenuItemProcessor();
    }
}
