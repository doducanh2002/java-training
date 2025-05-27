package org.aibles.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {"org.aibles"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Configuration {
}
