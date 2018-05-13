package com.wiivv.assessment.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Annotation @PerSection defines life scope of related Component. Once annotated, the Component will have same life scope as the place it gets initialized.
 * in this case we will initial component at the Activity level, so the component will be alive within an Activity.
 * Name doesn't matter. We can name it with any name.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerSection {

}
