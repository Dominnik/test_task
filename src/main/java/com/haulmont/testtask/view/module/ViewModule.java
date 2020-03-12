package com.haulmont.testtask.view.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.haulmont.testtask.controller.module.ControllerModule;
import com.haulmont.testtask.view.provider.ComponentProvider;
import com.haulmont.testtask.view.provider.ComponentProviderImpl;

public class ViewModule extends AbstractModule {

    @Override
    public void configure() {
        install(new ControllerModule());

        bind(ComponentProvider.class).to(ComponentProviderImpl.class).in(Singleton.class);
    }
}
