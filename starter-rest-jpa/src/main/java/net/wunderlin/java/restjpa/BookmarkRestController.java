package net.wunderlin.java.restjpa;

import java.util.*;

import org.springframework.web.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/{userId}/bookmarks")
class BookmarkRestController {

	private final BookmarkRepository bookmarkRepository;
	private final AccountRepository accountRepository;

	@Autowired
	BookmarkRestController(BookmarkRepository bookmarkRepository,
						   AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}

	@GetMapping
	Collection<Bookmark> readBookmarks(@PathVariable String userId) {
		this.validateUser(userId);

		return this.bookmarkRepository.findByAccountUsername(userId);
	}

	@PostMapping
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
		this.validateUser(userId);

		return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account,
							input.getUri(), input.getDescription()));

					URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}")
						.buildAndExpand(result.getId()).toUri();

					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());

	}

	@GetMapping("/{bookmarkId}")
	Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
		this.validateUser(userId);

		return this.bookmarkRepository.findById(bookmarkId)
			.orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));
	}

	private void validateUser(String userId) {
		this.accountRepository.findByUsername(userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}
}
