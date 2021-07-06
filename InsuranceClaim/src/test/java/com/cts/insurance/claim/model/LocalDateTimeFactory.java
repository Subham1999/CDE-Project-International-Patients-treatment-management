package com.cts.insurance.claim.model;

import java.time.LocalDate;
import org.meanbean.lang.Factory;
public class LocalDateTimeFactory implements Factory<LocalDate> {
    @Override
    public LocalDate create() {
        return LocalDate.now();
    }
}