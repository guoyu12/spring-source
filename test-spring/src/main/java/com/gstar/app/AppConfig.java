package com.gstar.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.gstar")
//AOP
@EnableAspectJAutoProxy
public class AppConfig {
}
