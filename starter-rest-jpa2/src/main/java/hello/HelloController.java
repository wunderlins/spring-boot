package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Main page
 * 
 * @author wus
 */
@RestController
public class HelloController {
	
	private final NodeRepository nodeRepository;
	private final PersonRepository personRepository;

	@Autowired
	HelloController(NodeRepository nodeRepository, PersonRepository personRepository) {
		this.nodeRepository = nodeRepository;
		this.personRepository = personRepository;
	}
	
    //@GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String index() {
    	String html = "";
    	
    	Optional<Node> rootn = this.nodeRepository.findById(0L);
    	Node root = rootn.get();
        html = root.getName() + " " + root.isRoot();
    	
 s       /*
        Set<Node> children = root.getChildren();
        for(Node c: children) {
        	html += "<br>" + c.getName() + " " + c.getId();
        }
        */
        
        Set<Person> r = root.getResponsible();
        Person p1 = new Person("Klugscheisser", "Krausi");
        Person p2 = new Person("p2", "02");
        this.personRepository.save(p1);
        this.personRepository.save(p2);
		r.add(p1);
		r.add(p2);
		root.setResponsible(r);
		this.nodeRepository.save(root);

        
    	return html;
    }

}