package com.wiivv.assessment.di.component;

import com.wiivv.assessment.MainApplication;
import com.wiivv.assessment.di.module.BaseWiivvModule;
import com.wiivv.assessment.di.module.MainApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * BaseWiivvComponent is actually the injector which provides our application level (singleton) dependencies which can be instanced when MainApplication get started.
 *
 * @Component: Components basically are injectors, let’s say a bridge between @Inject and @Module, which its main responsibility is to
 * put both together. They just give you instances of all the types you defined, for example, we must annotate an interface with @Component
 * and list all the @Modules that will compose that component, and if any of them is missing, we get errors at compile time.
 * All the components are aware of the scope of dependencies it provides through its modules.
 *
 * @Singleton is annotation for scope control. The scope defines the alive range for the dependency. In here is Application scope.
 */
@Singleton
@Component(modules = {MainApplicationModule.class, BaseWiivvModule.class})
public interface BaseWiivvComponent {

    /**
     * Dependent components require the parent component to explicitly list out what dependencies can be injected downstream, while subcomponents
     * do not. For parent components, you would need to expose to the downstream component by specifying the type and a method.
     * If you forget to add this line, you will likely to see an error about an injection target missing during compiling.
     */
    MainApplication application();
}
