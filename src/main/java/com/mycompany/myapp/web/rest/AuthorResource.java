package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.service.AuthorService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Author.
 */
@RestController
@RequestMapping("/api")
public class AuthorResource {

    private final Logger log = LoggerFactory.getLogger(AuthorResource.class);

    private static final String ENTITY_NAME = "author";
        
    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * POST  /authors : Create a new author.
     *
     * @param author the author to create
     * @return the ResponseEntity with status 201 (Created) and with body the new author, or with status 400 (Bad Request) if the author has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authors")
    @Timed
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) throws URISyntaxException {
        log.debug("REST request to save Author : {}", author);
        if (author.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new author cannot already have an ID")).body(null);
        }
        Author result = authorService.save(author);
        return ResponseEntity.created(new URI("/api/authors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authors : Updates an existing author.
     *
     * @param author the author to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated author,
     * or with status 400 (Bad Request) if the author is not valid,
     * or with status 500 (Internal Server Error) if the author couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authors")
    @Timed
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) throws URISyntaxException {
        log.debug("REST request to update Author : {}", author);
        if (author.getId() == null) {
            return createAuthor(author);
        }
        Author result = authorService.save(author);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, author.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authors : get all the authors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of authors in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/authors")
    @Timed
    public ResponseEntity<List<Author>> getAllAuthors(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Authors");
        Page<Author> page = authorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /authors/:id : get the "id" author.
     *
     * @param id the id of the author to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the author, or with status 404 (Not Found)
     */
    @GetMapping("/authors/{id}")
    @Timed
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        log.debug("REST request to get Author : {}", id);
        Author author = authorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(author));
    }

    /**
     * DELETE  /authors/:id : delete the "id" author.
     *
     * @param id the id of the author to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.debug("REST request to delete Author : {}", id);
        authorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/authors?query=:query : search for the author corresponding
     * to the query.
     *
     * @param query the query of the author search 
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/authors")
    @Timed
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Authors for query {}", query);
        Page<Author> page = authorService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/authors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
