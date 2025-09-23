 package com.demo.utility;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.demo.entity.Department;
import com.demo.entity.Employee;

public class DBConnectionUtility {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory != null) {
			System.out.println("Reusing SessionFactory: " + sessionFactory.toString());
			return sessionFactory;
		}
		
		try {
			StandardServiceRegistryBuilder registryBuilder =
					new StandardServiceRegistryBuilder();
			
            Map<String, Object> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "3306");
            
            // --- Update these lines for AWS RDS instance ---
            /*
            settings.put(Environment.URL, "jdbc:mysql://[YOUR_RDS_ENDPOINT]:3306/[YOUR_DATABASE_NAME]");
            settings.put(Environment.USER, "[YOUR_MASTER_USERNAME]");
            settings.put(Environment.PASS, "[YOUR_MASTER_PASSWORD]");
            */
            
            settings.put("hibernate.connection.pool_size", "30");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.format_sql", "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            
            // Enable second level cache
            settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);
            
            // Specify cache region factory class
            settings.put(Environment.CACHE_REGION_FACTORY, 
                  "org.hibernate.cache.jcache.JCacheRegionFactory");
            
            settings.put(Environment.USE_QUERY_CACHE, true);
            
            // Specify cache provider
            settings.put("hibernate.javax.cache.provider", 
                  "org.ehcache.jsr107.EhcacheCachingProvider");            
                               
            registry = registryBuilder.applySettings(settings).build();
            System.out.println("Hibernate Java Config Registry Created");
            
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Department.class);

              Metadata metadata = sources.getMetadataBuilder().build();
              sessionFactory = metadata.getSessionFactoryBuilder().build();
              System.out.println("New SessionFactory Created: " + sessionFactory.toString());
		} catch (Exception e) {
			System.out.println("SessionFactory Creation Failed.");
			e.printStackTrace();
		}
		   
		return sessionFactory;
	}
	
	public static void shutdown() {
		if (sessionFactory != null) sessionFactory.close();
		if (registry != null) StandardServiceRegistryBuilder.destroy(registry);
	}
}
