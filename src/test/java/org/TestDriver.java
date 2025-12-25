package org;


public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver found in classpath!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver NOT found in classpath!");
            System.out.println("Make sure you have this in pom.xml:");
            System.out.println("<dependency>");
            System.out.println("    <groupId>org.postgresql</groupId>");
            System.out.println("    <artifactId>postgresql</artifactId>");
            System.out.println("    <version>42.6.0</version>");
            System.out.println("</dependency>");
        }
    }
}