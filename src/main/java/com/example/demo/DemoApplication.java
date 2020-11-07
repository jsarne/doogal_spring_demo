package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.jpa.ProductionRepository;
import com.example.demo.models.Character;
import com.example.demo.models.Look;
import com.example.demo.models.Production;
import com.example.demo.models.Scene;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    ProductionRepository pr;

    public static void main(String[] args) {
	    SpringApplication.run(DemoApplication.class, args);
	}
    
    
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        Production p1 = new Production();
        p1.setName("Production One");

        Scene s1 = new Scene();
        s1.setName("Scene One");
        Scene s2 = new Scene();
        s2.setName("Scene Two");
        p1.addScene(s1);
        p1.addScene(s2);

        Character c1 = new Character();
        c1.setName("Doug");
        Character c2 = new Character();
        c2.setName("Jim");
        p1.addCharacter(c1);
        p1.addCharacter(c2);
        
        pr.save(p1);
        printProduction(p1);

        Production p2 = new Production();
        p2.setName("Production Two");

        Character c3 = new Character();
        c3.setName("Copper");
        p2.addCharacter(c3);
        
        Look l1 = new Look();
        l1.setName("Jaunty Dog");
        c3.addLook(l1);
        
        Scene s3 = new Scene();
        s3.setName("Scene 3");
        p2.addScene(s3);
        s3.addLook(l1);

        Scene s4 = new Scene();
        s4.setName("Scene 4");
        p2.addScene(s4);
        s4.addLook(l1);

        pr.save(p2);
        printProduction(p2);
        
        System.out.println("done!");
        //System.exit(0);
    }
    
    private void printProduction(Production p) {
        System.out.println(p.getName() + " scenes: ");
        for (Scene s : p.getScenes()) {
            System.out.println("  " + s.getName());
            System.out.println("    " + s.getName() + " production = " + s.getProduction().getName());
            System.out.println("    " + s.getName() + " looks: ");
            for (Look l : s.getLooks()) {
                System.out.println("      " + l.getName());
            }
        }

        System.out.println(p.getName() + " characters: ");
        for (Character c : p.getCharacters()) {
            System.out.println("  " + c.getName());
            System.out.println("    " + c.getName() + " production = " + c.getProduction().getName());
            System.out.println("    " + c.getName() + " looks: ");
            for (Look l : c.getLooks()) {
                System.out.println("      " + l.getName());
                System.out.println("      " + l.getName() + " character = " + l.getCharacter().getName());
                System.out.println("      " + l.getName() + " scenes: ");
                for (Scene s : l.getScenes()) {
                    System.out.println("        " + s.getName());
                }
            }
        }
        
    }
    
}
