package org.elevenfifty.java301;

import java.math.BigDecimal;

import org.elevenfifty.java301.Sandwich.BreadType;
import org.elevenfifty.java301.beans.Ingredients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

//import Map;

@SpringBootApplication
public class ApplicationSpringBasic {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationSpringBasic.class, args);
	}

	public static void oldMain(String[] args) {
	

		ConfigurableApplicationContext context = new SpringApplicationBuilder().sources(ApplicationSpringBasic.class)
				.run(args);
		
		System.out.println("---------------------------------------------");
		Sandwich sando = new Sandwich(BreadType.WHEAT);
		sando.addIngredients(context.getBean("cheddar", Ingredients.class));
		sando.addIngredients(context.getBean("provolone", Ingredients.class));
		sando.addIngredients(context.getBean("mozzarella", Ingredients.class));
		// System.out.println("Sandwich" =sando);

		System.out.println("Sandwich");
		System.out.println("\t" + sando.getBreadType());
		System.out.println("\tIngredients:");
		sando.getIngredients().forEach(i -> System.out.println("\t\t" + i.getName()));

		BigDecimal total = sando.getIngredients().stream().map(Ingredients::getCost).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		System.out.println("\tTotal: $" + total);

		System.out.println("---------------------------------------------");
		System.exit(1);
		
		
	}

	@Bean
	public Ingredients provolone() {
		return new Ingredients("provolone ", new BigDecimal(0.45d));
	}

	@Bean
	public Ingredients cheddar() {
		return new Ingredients("cheddar", new BigDecimal(0.25d));
	}

	@Bean
	public Ingredients mozzarella() {
		return new Ingredients("mozzarella", new BigDecimal(0.35d));
	}
}
