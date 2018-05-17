package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public String name;
	public String description;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Node parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Node> children;
	
	@ManyToMany
	private Set<Person> responsible;
	
	public long getId() {
		return id;
	}
	
	public boolean isRoot() {
		if (id == 0)
			return true;
		return false;
	}
	
	public Set<Person> getResponsible() {
		return responsible;
	}

	public void setResponsible(Set<Person> responsible) {
		this.responsible = responsible;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", description=" + description + ", parent=" + parent
				+ ", children=" + children + "]";
	}


}
