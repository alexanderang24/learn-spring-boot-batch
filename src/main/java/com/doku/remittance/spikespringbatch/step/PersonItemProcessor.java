package com.doku.remittance.spikespringbatch.step;

import com.doku.remittance.spikespringbatch.dto.Menu;
import com.doku.remittance.spikespringbatch.dto.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Menu> {

    @Override
    public Menu process(Person person) {
        String firstName = person.getFirstName().toUpperCase();
        String lastName = person.getLastName().toUpperCase();

        Menu transformedPerson = Menu.builder().nama1(firstName).nama2(lastName).build();

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
