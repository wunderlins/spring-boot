package hello;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "node", path = "node")
public interface NodeRepository extends PagingAndSortingRepository<Node, Long> {
	List<Node> findByName(@Param("name") String name);
	Optional<Node> findById(@Param("id") Long id);
}

