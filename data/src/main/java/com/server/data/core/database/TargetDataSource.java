package com.server.data.core.database;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
	DatabaseType dataBaseType() default DatabaseType.xlt;
}
