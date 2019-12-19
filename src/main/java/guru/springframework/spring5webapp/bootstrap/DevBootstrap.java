package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositoreis.AuthorRepository;
import guru.springframework.spring5webapp.repositoreis.BookRepository;
import guru.springframework.spring5webapp.repositoreis.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

	private void initData() {
		Author eric = new Author("Eric", "Evans");
		Publisher pub1 = new Publisher("Harper Collins", "Street A");
		Book book1 = new Book("Domain Driven Design", "1234", pub1);
		eric.getBooks().add(book1);
		book1.getAuthors().add(eric);
		// Save in database
		authorRepository.save(eric);
		publisherRepository.save(pub1);
		bookRepository.save(book1);

		Author rod = new Author("Rod", "Johnson");
		Publisher pub2 = new Publisher("Wrox", "Street C");
		Book book2 = new Book("J2EE Development without EJB", "23444", pub2);
		rod.getBooks().add(book2);
		book2.getAuthors().add(rod);
		// Save in database
		authorRepository.save(rod);
		publisherRepository.save(pub2);
		bookRepository.save(book2);
	}

}
