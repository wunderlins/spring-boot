package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {
	
	private final NodeRepository nodeRepository;
	private final PersonRepository personRepository;

	@Autowired
	HelloController(NodeRepository nodeRepository,
			PersonRepository personRepository) {
		this.nodeRepository = nodeRepository;
		this.personRepository = personRepository;
	}
	
    @GetMapping("/hello")
    //@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String index() {
    	
    	Optional<Node> rootn = this.nodeRepository.findById(1L);
    	Node root = rootn.get();
        String html = root.getName() + " " + root.isRoot();
        		
    	return html;
    }

}