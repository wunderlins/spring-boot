package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "node", path = "node")
public interface NodeRepository extends PagingAndSortingRepository<Node, Long> {
	List<Node> findByName(@Param("name") String name);
	String findNodeById(@Param("id") Long id);
}

