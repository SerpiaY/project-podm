package com.casablanca.SpringConnect;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
public class CamelCaseNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment);
    }
    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment);
    }
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment);
    }
    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment);
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return apply(name, jdbcEnvironment);
    }
    private Identifier apply(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        if (identifier == null) {
            return null;
        }
        String text = identifier.getText();
        if (text == null) {
            return identifier;
        }
        // Convert to camelCase
        String camelCase = text.replaceAll("_[a-z]", String.valueOf(Character.toUpperCase(text.charAt(text.indexOf('_') + 1))));
        return Identifier.toIdentifier(camelCase);
    }
} 
