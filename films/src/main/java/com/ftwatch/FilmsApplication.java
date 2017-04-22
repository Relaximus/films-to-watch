package com.ftwatch;

import com.ftwatch.model.Film;
import com.ftwatch.model.Note;
import com.ftwatch.repositores.FilmRepository;
import com.ftwatch.repositores.NoteRepository;
import org.hibernate.collection.internal.PersistentBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableOAuth2Sso
public class FilmsApplication extends WebSecurityConfigurerAdapter implements CommandLineRunner{
	@Autowired
	FilmRepository rep;
	@Autowired
    NoteRepository nRep;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(FilmsApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource, @Qualifier("oauth2ClientContext") OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}
	
	@Bean
	public AuthoritiesExtractor authoritiesExtractor(OAuth2RestOperations template) {
		return map -> {
			log.info(map.toString());
			return AuthorityUtils.commaSeparatedStringToAuthorityList("GOOGLE_USER");
		};
	}

	@Override
	public void run(String... strings) throws Exception {
        List<Film> films = new ArrayList<>();
        Film f = new Film();
		f.setTitle("Payback");
		f.setYear(1984);
        films.add(f);

		/*Film f2 = new Film();
		f2.setTitle("Silent Hill");
        f2.setYear(2016);
        films.add(f);*/

		Note n = new Note();
		n.setFilmTitle("Payback");
		//n.setDt(LocalDateTime.now());
        n.setNoteFilms(films);
        n = nRep.save(n);

        log.info("At the begining we have this note : {}", n);

        /*films = new ArrayList(n.getNoteFilms());
        films.removeIf(film -> film.getId()==1);
        n.setNoteFilms(films);
        n = nRep.save(n);

        log.info("And now we have this note : {}", n);*/
    }
}
