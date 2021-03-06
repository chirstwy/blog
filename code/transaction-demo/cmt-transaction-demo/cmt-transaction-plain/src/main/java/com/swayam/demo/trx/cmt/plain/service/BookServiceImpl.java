package com.swayam.demo.trx.cmt.plain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swayam.demo.trx.cmt.plain.dao.AuthorDao;
import com.swayam.demo.trx.cmt.plain.dao.GenreDao;
import com.swayam.demo.trx.cmt.plain.entity.Author;
import com.swayam.demo.trx.cmt.plain.entity.Genre;
import com.swayam.demo.trx.cmt.plain.web.dto.AuthorRequest;

public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	private final AuthorDao authorDao;
	private final GenreDao genreDao;

	public BookServiceImpl(AuthorDao authorDao, GenreDao genreDao) {
		this.authorDao = authorDao;
		this.genreDao = genreDao;
	}

	@Override
	public List<Genre> getGenres() {
		return genreDao.getGenres();
	}

	@Override
	public List<Author> getAuthors() {
		return authorDao.getAuthors();
	}

	@Override
	public Map<String, Long> addAuthorWithGenre(AuthorRequest authorRequest) {
		Map<String, Long> map = new HashMap<>();
		// save genre
		long genreId = genreDao
				.addGenre(new Genre(null, authorRequest.getGenreShortName(), authorRequest.getGenreName()));

		LOGGER.info("genreId: {}", genreId);

		map.put("genreId", genreId);
		// save author
		long authorId = authorDao.addAuthor(new Author(authorRequest.getAuthorId(), authorRequest.getAuthorFirstName(),
				authorRequest.getAuthorLastName()));
		map.put("authorId", authorId);

		LOGGER.info("authorId: {}", authorId);

		return map;
	}

}
